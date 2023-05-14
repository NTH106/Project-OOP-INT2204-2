import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class App extends Application {

    private ObservableList<HangHoa> hangHoaData = FXCollections.observableArrayList();

    private TextField txtTenHangHoa;
    private ComboBox<String> cboLoaiHang;
    private DatePicker dpNgayNhap;
    private Button btnThem;
    private Button btnXoa;
    private TableView<HangHoa> tblHangHoa;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Phần mềm quản lý cửa hàng rau củ quả");

        tblHangHoa = createTable();
        VBox vbox = createInputForm();

        btnThem.setOnAction(event -> {
            String tenHangHoa = txtTenHangHoa.getText();
            String loaiHang = cboLoaiHang.getValue();
            LocalDate ngayNhap = dpNgayNhap.getValue();
            if (tenHangHoa.isEmpty() || loaiHang.isEmpty() || ngayNhap == null) {
                showAlert("Lỗi", "Vui lòng nhập đầy đủ thông tin.");
            } else {
                HangHoa hangHoa = new HangHoa(tenHangHoa, loaiHang, ngayNhap);
                hangHoaData.add(hangHoa);
                clearInput();
            }
        });

        btnXoa.setOnAction(event -> {
            HangHoa selectedHangHoa = tblHangHoa.getSelectionModel().getSelectedItem();
            if (selectedHangHoa != null) {
                hangHoaData.remove(selectedHangHoa);
            }
        });

        tblHangHoa.setOnMouseClicked((MouseEvent event) -> {
            HangHoa selectedHangHoa = tblHangHoa.getSelectionModel().getSelectedItem();
            if (selectedHangHoa != null) {
                txtTenHangHoa.setText(selectedHangHoa.getTenHangHoa());
                cboLoaiHang.setValue(selectedHangHoa.getLoaiHang());
                dpNgayNhap.setValue(selectedHangHoa.getNgayNhap());
            }
        });

       
       
        Button btnTimKiem = new Button("Tìm kiếm");
        btnTimKiem.setOnAction(event -> search(txtTenHangHoa.getText()));
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(txtTenHangHoa, 12, 0);
        gridPane.add(dpNgayNhap, 12, 1);
        gridPane.add(btnThem, 17, 0);
        gridPane.add(btnXoa, 17, 1);
        gridPane.add(btnTimKiem, 20, 0);

        VBox root = new VBox();
        root.setSpacing(5);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(tblHangHoa, vbox, gridPane);

        hangHoaData.add(new HangHoa("Cà chua", "Quả", LocalDate.of(2023, 5, 6)));
        hangHoaData.add(new HangHoa("Bắp cải", "Rau củ", LocalDate.of(2023, 5, 12)));
        hangHoaData.add(new HangHoa("Táo", "Quả", LocalDate.of(2023, 5, 12)));

        primaryStage.setScene(new Scene(root, 650, 450));
        primaryStage.show();
    }

    private TableView<HangHoa> createTable() {
        TableView<HangHoa> table = new TableView<>();
        TableColumn<HangHoa, String> colTenHangHoa = new TableColumn<>("Tên hàng hóa");
        colTenHangHoa.setCellValueFactory(new PropertyValueFactory<>("tenHangHoa"));
        colTenHangHoa.setMinWidth(170);
        TableColumn<HangHoa, String> colLoaiHang = new TableColumn<>("Loại hàng");
        colLoaiHang.setCellValueFactory(new PropertyValueFactory<>("loaiHang"));
        colLoaiHang.setMinWidth(150);
        TableColumn<HangHoa, LocalDate> colNgayNhap = new TableColumn<>("Ngày nhập");
        colNgayNhap.setCellValueFactory(new PropertyValueFactory<>("ngayNhap"));
        colNgayNhap.setMinWidth(150);
        TableColumn<HangHoa, String> colTinhTrang = new TableColumn<>("Tình trạng");
        colTinhTrang.setCellValueFactory(cellData -> {
            StringProperty tinhTrang = new SimpleStringProperty();
            HangHoa hangHoa = cellData.getValue();
            long ngayNhap = ChronoUnit.DAYS.between(hangHoa.getNgayNhap(), LocalDate.now());
            if (hangHoa.getLoaiHang().equals("Rau củ")) {
                if (ngayNhap >= 5) {
                    tinhTrang.set("Đã Hết Hạn");
                } else {
                    tinhTrang.set("Chưa Hết Hạn ");
                }
            } else if (hangHoa.getLoaiHang().equals("Quả")) {
                if (ngayNhap >= 10) {
                    tinhTrang.set("Đã Hết Hạn");
                } else {
                    tinhTrang.set("Chưa Hết Hạn ");
                }
            }
            return tinhTrang;
        });
        colTinhTrang.setMinWidth(140);
        table.getColumns().addAll(colTenHangHoa, colLoaiHang, colNgayNhap, colTinhTrang);
        table.setItems(hangHoaData);

        return table;
    }

    private VBox createInputForm() {
        txtTenHangHoa = new TextField();
        txtTenHangHoa.setPromptText("Tên hàng hóa");

        cboLoaiHang = new ComboBox<>();
        cboLoaiHang.setPromptText("Loại hàng");
        cboLoaiHang.getItems().addAll("Rau củ", "Quả");

        dpNgayNhap = new DatePicker();
        dpNgayNhap.setPromptText("Ngày nhập");

        btnThem = new Button("Thêm sản phẩm");
        btnXoa = new Button("Xóa sản phẩm");

        VBox vbox = new VBox(5);
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.getChildren().addAll(txtTenHangHoa, cboLoaiHang, dpNgayNhap, btnThem, btnXoa);

        return vbox;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void search(String keyword) {
        ObservableList<HangHoa> filteredList = FXCollections.observableArrayList();
    
        String selectedLoaiHang = cboLoaiHang.getValue();
    
        for (HangHoa hangHoa : hangHoaData) {
            if (hangHoa.getTenHangHoa().toLowerCase().contains(keyword.toLowerCase()) &&
                (selectedLoaiHang == null || hangHoa.getLoaiHang().equalsIgnoreCase(selectedLoaiHang))) {
                filteredList.add(hangHoa);
            }
        }
    
        tblHangHoa.setItems(filteredList);
    }
    
    private void clearInput() {
        txtTenHangHoa.clear();
        cboLoaiHang.setValue(null);
        dpNgayNhap.setValue(null);
    }
}
