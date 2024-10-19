package entity;

import java.io.Serializable;

//Tính kế thừa

public class PhiCong extends NhanVien {
    private static final long serialVersionUID = 1L;
    private int soGioBay;

    public PhiCong(String maNhanVien, String tenNhanVien, String ngaySinh, String loaiNhanVien, String sdt, String diaChi, String email) {
        super(maNhanVien, tenNhanVien, ngaySinh, loaiNhanVien, sdt, diaChi, email);
    }

    public PhiCong(String maNhanVien, String tenNhanVien, String ngaySinh, String loaiNhanVien, String sdt, String diaChi, String email, int soGioBay) {
        super(maNhanVien, tenNhanVien, ngaySinh, loaiNhanVien, sdt, diaChi, email);
        this.soGioBay = soGioBay;
    }

    public int getSoGioBay() {
        return soGioBay;
    }

    public void setSoGioBay(int soGioBay) {
        this.soGioBay = soGioBay;
    }
// trừu tượng và đa hình
    @Override
    public void hienThi() {
        System.out.println("Phi Công: ");
        System.out.println("Mã nhân viên: " + getMaNhanVien());
        System.out.println("Tên nhân viên: " + getTenNhanVien());
        System.out.println("Ngày sinh: " + getNgaySinh());
        System.out.println("Loại nhân viên: " + getLoaiNhanVien());
        System.out.println("SĐT: " + getSdt());
        System.out.println("Địa chỉ: " + getDiaChi());
        System.out.println("Email: " + getEmail());
        if(email.matches("^\\S+@\\S+\\.\\S+$")){
            System.out.println("Định dạng email không hợp lệ.");
        }
        System.out.println("Số giờ bay: " + soGioBay);
    }

    @Override
    public String toString() {
        return super.toString() + ","  + "sogiobay="+soGioBay;
    }
}
