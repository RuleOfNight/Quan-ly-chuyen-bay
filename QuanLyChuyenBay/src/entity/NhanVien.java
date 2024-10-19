package entity;


import java.io.Serializable;
import java.io.*;

//trừu tượng
public abstract class NhanVien{
    private String maNhanVien;
    private String tenNhanVien;
    private String ngaySinh;
    private String loaiNhanVien;
    private String sdt;
    private String diaChi;
    public String email;

    public NhanVien(String maNhanVien, String tenNhanVien, String ngaySinh, String loaiNhanVien, String sdt, String diaChi, String email) {
        this.diaChi = diaChi;
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.ngaySinh = ngaySinh;
        this.loaiNhanVien = loaiNhanVien;
        this.sdt = sdt;
        this.email = email;
    }

//đóng gói

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getLoaiNhanVien() {
        return loaiNhanVien;
    }

    public void setLoaiNhanVien(String loaiNhanVien) {
        this.loaiNhanVien = loaiNhanVien;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public abstract void hienThi();

    @Override
    public String toString() {
        return
                "Mã Nhân Viên: " + maNhanVien +
                ", Tên Nhân Viên: " + tenNhanVien +
                ", Ngày Sinh" + ngaySinh +
                ", Loại Nhân Viên: " + loaiNhanVien +
                ", Số điện thoại: " + sdt +
                ", Địa chỉ: " + diaChi +
                ", Email: " + email
                ;
    }
}
