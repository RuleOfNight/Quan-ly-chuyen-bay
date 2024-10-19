package entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

public class ChuyenBay implements Serializable {
    private static final long serialVersionUID = 1L;

    private String maChuyenBay;
    private int soGhe;
    private String ngayBay;
    private String gioBay;
    private String tgBay;
    private String diemDen;
    private String hang;
    private String maPhiCong1;
    private String maPhiCong2;
    private String[] maTiepViens;


    // Thêm thuộc tính tên phi công và tiếp viên
    private String tenPhiCong1;
    private String tenPhiCong2;
    private String[] tenTiepViens;


    // Constructor
    public ChuyenBay(String maChuyenBay, int soGhe, String ngayBay, String gioBay,
                     String tgBay, String diemDen, String hang, String maPhiCong1,
                     String maPhiCong2, String[] maTiepViens) {
        this.maChuyenBay = maChuyenBay;
        this.soGhe = soGhe;
        this.ngayBay = ngayBay;
        this.gioBay = gioBay;
        this.tgBay = tgBay;
        this.diemDen = diemDen;
        this.hang = hang;
        this.maPhiCong1 = maPhiCong1;
        this.maPhiCong2 = maPhiCong2;
        this.maTiepViens = maTiepViens;

        // Tìm tên phi công từ mã
        this.tenPhiCong1 = timTenNhanVien(maPhiCong1, "phicong.txt");
        this.tenPhiCong2 = timTenNhanVien(maPhiCong2, "phicong.txt");

        // Tìm tên tiếp viên từ mã
        this.tenTiepViens = new String[maTiepViens.length];
        for (int i = 0; i < maTiepViens.length; i++) {
            tenTiepViens[i] = timTenNhanVien(maTiepViens[i], "tiepvien.txt");
        }
    }

    private String timTenNhanVien(String maNhanVien, String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 1 && parts[0].equals(maNhanVien)) {
                    return parts[1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Không tìm thấy";
    }

    // Getter và Setter cho các thuộc tính
    public String getMaChuyenBay() {
        return maChuyenBay;
    }

    public void setMaChuyenBay(String maChuyenBay) {
        this.maChuyenBay = maChuyenBay;
    }

    public int getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }

    public String getNgayBay() {
        return ngayBay;
    }

    public void setNgayBay(String ngayBay) {
        this.ngayBay = ngayBay;
    }

    public String getGioBay() {
        return gioBay;
    }

    public void setGioBay(String gioBay) {
        this.gioBay = gioBay;
    }

    public String getTgBay() {
        return tgBay;
    }

    public void setTgBay(String tgBay) {
        this.tgBay = tgBay;
    }

    public String getDiemDen() {
        return diemDen;
    }

    public void setDiemDen(String diemDen) {
        this.diemDen = diemDen;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public String getMaPhiCong1() {
        return maPhiCong1;
    }

    public void setMaPhiCong1(String maPhiCong1) {
        this.maPhiCong1 = maPhiCong1;
        this.tenPhiCong1 = timTenNhanVien(maPhiCong1, "phicong.txt"); // Cập nhật tên phi công
    }

    public String getMaPhiCong2() {
        return maPhiCong2;
    }

    public void setMaPhiCong2(String maPhiCong2) {
        this.maPhiCong2 = maPhiCong2;
        this.tenPhiCong2 = timTenNhanVien(maPhiCong2, "phicong.txt"); // Cập nhật tên phi công
    }

    public String[] getMaTiepViens() {
        return maTiepViens;
    }

    public void setMaTiepViens(String[] maTiepViens) {
        this.maTiepViens = maTiepViens;
        this.tenTiepViens = new String[maTiepViens.length]; // Cập nhật tên tiếp viên
        for (int i = 0; i < maTiepViens.length; i++) {
            tenTiepViens[i] = timTenNhanVien(maTiepViens[i], "tiepvien.txt");
        }
    }

    public String getTenPhiCong1() {
        return tenPhiCong1;
    }

    public String getTenPhiCong2() {
        return tenPhiCong2;
    }

    public String[] getTenTiepViens() {
        return tenTiepViens;
    }

    public void hienThi() {
        System.out.println("Chuyến Bay: ");
        System.out.println("Mã chuyến bay: " + getMaChuyenBay());
        System.out.println("Ngày bay: " + getNgayBay());
        System.out.println("Giờ bay: " + getGioBay());
        System.out.println("Thời gian bay: " + getTgBay());
        System.out.println("Điểm đến: " + getDiemDen());
        System.out.println("Số ghế: " + getSoGhe());
        System.out.println("Hãng máy bay: " + getHang());

        System.out.println("Tên phi công 1: " + (tenPhiCong1 != null ? tenPhiCong1 : "Không tìm thấy"));
        System.out.println("Tên phi công 2: " + (tenPhiCong2 != null ? tenPhiCong2 : "Không tìm thấy"));

        System.out.println("Tên tiếp viên: ");
        for (String tenTiepVien : tenTiepViens) {
            System.out.println(tenTiepVien != null ? tenTiepVien : "Không tìm thấy");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(maChuyenBay).append(",")
                .append(soGhe).append(",")
                .append(ngayBay).append(",")
                .append(gioBay).append(",")
                .append(tgBay).append(",")
                .append(diemDen).append(",")
                .append(hang).append("\nTên phi công 1: ")
                .append(maPhiCong1).append("\nTên phi công 2: ")
                .append(maPhiCong2).append("\nTên những tiếp viên phục vụ: ")
                .append(String.join(";", maTiepViens));
        return sb.toString();
    }

    public void setMaPhiCongs(int index, String maPhiCongMoi) {
        if (index == 0) {
            setMaPhiCong1(maPhiCongMoi);
        } else if (index == 1) {
            setMaPhiCong2(maPhiCongMoi);
        }
    }

}
