import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ThietBi {
    private StringProperty tenThietBi;
    private StringProperty tacDung;
    private IntegerProperty soLuong;
    private StringProperty viTri;
    private StringProperty nguoiMuon;

    public ThietBi(String tenThietBi, String tacDung, int soLuong, String viTri, String nguoiMuon) {
        this.tenThietBi = new SimpleStringProperty(tenThietBi);
        this.tacDung = new SimpleStringProperty(tacDung);
        this.soLuong = new SimpleIntegerProperty(soLuong);
        this.viTri = new SimpleStringProperty(viTri);
        this.nguoiMuon = new SimpleStringProperty(nguoiMuon);
    }
    
    // Getters and Setters for the properties
    
    public String getTenThietBi() {
        return tenThietBi.get();
    }
    
    public StringProperty tenThietBiProperty() {
        return tenThietBi;
    }
    
    public void setTenThietBi(String tenThietBi) {
        this.tenThietBi.set(tenThietBi);
    }
    
    public String getTacDung() {
        return tacDung.get();
    }
    
    public StringProperty tacDungProperty() {
        return tacDung;
    }
    
    public void setTacDung(String tacDung) {
        this.tacDung.set(tacDung);
    }
    
    public int getSoLuong() {
        return soLuong.get();
    }
    
    public IntegerProperty soLuongProperty() {
        return soLuong;
    }
    
    public void setSoLuong(int soLuong) {
        this.soLuong.set(soLuong);
    }
    
    public String getViTri() {
        return viTri.get();
    }
    
    public StringProperty viTriProperty() {
        return viTri;
    }
    
    public void setViTri(String viTri) {
        this.viTri.set(viTri);
    }
    
    public String getNguoiMuon() {
        return nguoiMuon.get();
    }
    
    public StringProperty nguoiMuonProperty() {
        return nguoiMuon;
    }
    
    public void setNguoiMuon(String nguoiMuon) {
        this.nguoiMuon.set(nguoiMuon);
    }
}    