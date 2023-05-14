import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HangHoa {
    private StringProperty tenHangHoa;
    private StringProperty loaiHang;
    private LocalDate ngayNhap;
    
    public HangHoa(String tenHangHoa, String loaiHang, LocalDate ngayNhap) {
        this.tenHangHoa = new SimpleStringProperty(tenHangHoa);
        this.loaiHang = new SimpleStringProperty(loaiHang);
        this.ngayNhap = ngayNhap;
    }

    public String getTenHangHoa() {
        return tenHangHoa.get();
    }

    public String getLoaiHang() {
        return loaiHang.get();
    }

    public LocalDate getNgayNhap() {
        return ngayNhap;
    }
}


    
