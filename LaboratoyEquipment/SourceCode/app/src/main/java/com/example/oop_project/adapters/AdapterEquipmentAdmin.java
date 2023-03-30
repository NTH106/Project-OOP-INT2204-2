package com.example.oop_project.adapters;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.example.oop_project.EquipmentDetailActivity;
import com.example.oop_project.EquipmentEditActivity;
import com.example.oop_project.MyApplication;
import com.example.oop_project.databinding.RowEquipmentsBinding;
import com.example.oop_project.filters.FilterEquipment;
import com.example.oop_project.models.ModelEquipment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class AdapterEquipmentAdmin extends RecyclerView.Adapter<AdapterEquipmentAdmin.HolderEquipmentAdmin> implements Filterable {
    // context
    private Context context;
    public ArrayList<ModelEquipment> equipmentArraylist, filterList;
    private RowEquipmentsBinding binding;
    private FilterEquipment filter;

    private ProgressDialog progressDialog;

    public AdapterEquipmentAdmin(Context context, ArrayList<ModelEquipment> equipmentArraylist) {
        this.context = context;
        this.filterList = equipmentArraylist;
        this.equipmentArraylist = equipmentArraylist;

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Please wait!");
        progressDialog.setCanceledOnTouchOutside(false);
    }

    @NonNull
    @Override
    public HolderEquipmentAdmin onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RowEquipmentsBinding.inflate(LayoutInflater.from(context), parent, false);

        return new HolderEquipmentAdmin(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull HolderEquipmentAdmin holder, int position) {
        // get date, set data..
        ModelEquipment model = equipmentArraylist.get(position);
        String title = model.getTitle();
        String description = model.getDescription();
        long timestamp = model.getTimestamp();
        String formattedDate = MyApplication.formatTimestamp(timestamp);

        holder.titleTv.setText(title);
        holder.descriptionTv.setText(description);
        holder.dateTv.setText(formattedDate);

        loadCategory(model, holder);

        // handle Click, show option 1: Edit, 2 Delete
        holder.moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreOptionsDialog(model, holder);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EquipmentDetailActivity.class);
                intent.putExtra("equipmentId", model.getId());
                context.startActivity(intent);
            }
        });
    }

    private void moreOptionsDialog(ModelEquipment model, HolderEquipmentAdmin holder) {
        // options to show in dialog
        String equipmentId = model.getId();
        String[] options = {"Edit", "Delete"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose Options")
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            // Edit clicked
                            Intent intent = new Intent(context, EquipmentEditActivity.class);
                            intent.putExtra("equipmentId",equipmentId);
                            context.startActivity(intent);
                        } else if (which == 1) {
                            // Delete clicked
                            deleteEquipment(model, holder);
                        }
                    }
                })
                .show();
    }

    private void deleteEquipment(ModelEquipment model, HolderEquipmentAdmin holder) {
        String equipmentId = model.getId();
        String equipmentTitle = model.getTitle();
        progressDialog.setMessage("Deleting...");
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Equipments");
        ref.child(equipmentId)
                .removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "Delete Successfully!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(context, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void loadCategory(ModelEquipment model, HolderEquipmentAdmin holder) {
        String categoryId = model.getCategoryId();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Categories");
        ref.child(categoryId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String category = "" + snapshot.child("title").getValue();

                        holder.categoryTv.setText(category);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    @Override
    public int getItemCount() {
        return equipmentArraylist.size(); // return number of records
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new FilterEquipment(filterList, this);
        }
        return filter;
    }

    // view Holder class
    class HolderEquipmentAdmin extends RecyclerView.ViewHolder {

        // full views
        ProgressBar progressBar;
        TextView titleTv, descriptionTv, categoryTv, dateTv;
        ImageButton moreBtn;

        public HolderEquipmentAdmin(@NonNull View itemView) {
            super(itemView);

            progressBar = binding.progressBar;
            titleTv = binding.titleTv;
            descriptionTv = binding.descriptionTv;
            categoryTv = binding.categoryTv;
            dateTv = binding.dateTv;
            moreBtn = binding.moreBtn;
        }
    }
}
