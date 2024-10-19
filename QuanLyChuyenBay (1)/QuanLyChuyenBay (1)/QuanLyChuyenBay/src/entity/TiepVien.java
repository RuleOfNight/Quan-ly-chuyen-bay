package entity;

import java.io.Serializable;

//Tính kế thừa

public class TiepVien extends NhanVien {
    private static final long serialVersionUID = 1L;
    private int Toeic;

    public TiepVien(String maNhanVien, String tenNhanVien, String ngaySinh, String loaiNhanVien, String sdt, String diaChi, String email) {
        super(maNhanVien, tenNhanVien, ngaySinh, loaiNhanVien, sdt, diaChi, email);
    }

    public TiepVien(String maNhanVien, String tenNhanVien, String ngaySinh, String loaiNhanVien, String sdt, String diaChi, String email, int Toeic) {
        super(maNhanVien, tenNhanVien, ngaySinh, loaiNhanVien, sdt, diaChi, email);
        this.Toeic = Toeic;
    }

    public int getToeic() {
        return Toeic;
    }

    public void setToeic(int Toeic) {
        this.Toeic = Toeic;
    }
    // trừu tượng và đa hình
    @Override
    public void hienThi() {
        System.out.println("Tiếp Viên: ");
        System.out.println("Mã nhân viên: " + getMaNhanVien());
        System.out.println("Tên nhân viên: " + getTenNhanVien());
        System.out.println("Ngày sinh: " + getNgaySinh());
        System.out.println("Loại nhân viên: " + getLoaiNhanVien());
        System.out.println("SĐT: " + getSdt());
        System.out.println("Địa chỉ: " + getDiaChi());
        System.out.println("Email: " + getEmail());
        if(email.matches("^\\S+@\\S+\\.\\S+$")){
            System.out.println("Định dạng không hợp lệ (dd/mm/yyyy).");
        }
        System.out.println("Trình độ tiếng Anh: " + Toeic+ " Toeic");
    }

    @Override
    public String toString() {
        return super.toString() + ","  + "Toeic="+Toeic;
    }
}
