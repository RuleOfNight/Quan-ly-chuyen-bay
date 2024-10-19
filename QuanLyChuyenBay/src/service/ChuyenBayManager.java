package service;

import entity.ChuyenBay;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChuyenBayManager {
    private List<ChuyenBay> cblist = new ArrayList<>();
    private final String FILE_PATH = "chuyenbay.txt";
    private final String FILE_PHI_CONG = "phicong.txt";
    private final String FILE_TIEP_VIEN = "tiepvien.txt";
    private Scanner sc = new Scanner(System.in);



    public ChuyenBayManager() {
        fromFile();
    }

    public void addChuyenBay() {
        String maChuyenBay = inputMaChuyenBay();
        int soGhe = inputSoGhe();
        String ngayBay = inputNgayBay();
        String gioBay = inputGioBay();
        String tgBay = inputThoiGianBay();
        String diemDen = inputDiemDen();
        String hang = inputHangMayBay();
        String[] maPhiCongs = inputMaPhiCong();
        String[] maTiepViens = inputMaTiepVien();

        // Tìm tên phi công(file)
        String tenPhiCong1 = timKiemTenNhanVien(maPhiCongs[0], "phicong.txt");
        String tenPhiCong2 = timKiemTenNhanVien(maPhiCongs[1], "phicong.txt");

        // Tìm tên tiếp viên
        String[] tenTiepViens = new String[maTiepViens.length];
        for (int i = 0; i < maTiepViens.length; i++) {
            tenTiepViens[i] = timKiemTenNhanVien(maTiepViens[i], "tiepvien.txt");
        }


        ChuyenBay cb = new ChuyenBay(
                maChuyenBay,
                soGhe,
                ngayBay,
                gioBay,
                tgBay,
                diemDen,
                hang,
                tenPhiCong1 != null ? tenPhiCong1 : "Không tìm thấy",
                tenPhiCong2 != null ? tenPhiCong2 : "Không tìm thấy",
                tenTiepViens
        );

        cblist.add(cb);
        System.out.println("Thêm chuyến bay thành công.");
        toFile();
    }

    private String inputMaChuyenBay() {
        String maChuyenBay;
        do {
            System.out.print("Nhập mã chuyến bay: ");
            maChuyenBay = sc.nextLine();
        } while (maChuyenBay.trim().isEmpty());
        return maChuyenBay;
    }

    private int inputSoGhe() {
        int soGhe;
        do {
            System.out.print("Nhập số ghế: ");
            while (!sc.hasNextInt()) {
                System.out.print("Vui lòng nhập một số hợp lệ cho số ghế: ");
                sc.next();
            }
            soGhe = sc.nextInt();
            sc.nextLine();
        } while (soGhe <= 0);
        return soGhe;
    }

    private String inputNgayBay() {
        String ngayBay;
        do {
            System.out.print("Nhập ngày bay (dd/mm/yyyy): ");
            ngayBay = sc.nextLine();
        } while (!ngayBay.matches("\\d{2}/\\d{2}/\\d{4}"));
        return ngayBay;
    }

    private String inputGioBay() {
        String gioBay;
        do {
            System.out.print("Nhập giờ bay (hh:mm): ");
            gioBay = sc.nextLine();
        } while (!gioBay.matches("\\d{2}:\\d{2}"));
        return gioBay;
    }

    private String inputThoiGianBay() {
        String tgBay;
        do {
            System.out.print("Nhập thời gian bay: ");
            tgBay = sc.nextLine();
        } while (tgBay.trim().isEmpty());
        return tgBay;
    }

    private String inputDiemDen() {
        String diemDen;
        do {
            System.out.print("Nhập điểm đến: ");
            diemDen = sc.nextLine();
        } while (diemDen.trim().isEmpty());
        return diemDen;
    }

    private String inputHangMayBay() {
        String hang;
        do {
            System.out.print("Nhập hãng máy bay: ");
            hang = sc.nextLine();
        } while (hang.trim().isEmpty());
        return hang;
    }

    private String[] inputMaPhiCong() {
        String[] maPhiCongs = new String[2];
        for (int i = 0; i < 2; i++) {
            do {
                System.out.print("Nhập mã phi công số " + (i + 1) + ": ");
                maPhiCongs[i] = sc.nextLine();
            } while (maPhiCongs[i].trim().isEmpty());
        }
        return maPhiCongs;
    }

    private String[] inputMaTiepVien() {
        int soLuongTiepVien;
        do {
            System.out.print("Nhập số lượng tiếp viên: ");
            while (!sc.hasNextInt()) {
                System.out.print("Vui lòng nhập số lượng tiếp viên hợp lệ (số nguyên): ");
                sc.next();
            }
            soLuongTiepVien = sc.nextInt();
            sc.nextLine();
        } while (soLuongTiepVien <= 0);

        String[] maTiepViens = new String[soLuongTiepVien];
        for (int i = 0; i < soLuongTiepVien; i++) {
            do {
                System.out.print("Nhập mã tiếp viên số " + (i + 1) + ": ");
                maTiepViens[i] = sc.nextLine();
            } while (maTiepViens[i].trim().isEmpty());
        }
        return maTiepViens;
    }

    private String timKiemTenNhanVien(String maNhanVien, String filePath) {
        return searchFromFile(filePath, maNhanVien);
    }

    private String searchFromFile(String filePath, String maNhanVien) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(maNhanVien)) {
                    return parts[1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeChuyenBay() {
        System.out.print("Nhập mã chuyến bay cần xóa: ");
        String maChuyenBay = sc.nextLine();

        List<ChuyenBay> updatedList = new ArrayList<>();
        boolean found = false;

        for (ChuyenBay cb : cblist) {
            if (!cb.getMaChuyenBay().equals(maChuyenBay)) {
                updatedList.add(cb);
            } else {
                found = true;
            }
        }

        if (found) {
            cblist = updatedList;
            toFile();
            System.out.println("Đã xóa chuyến bay có mã: " + maChuyenBay);
        } else {
            System.out.println("Không tìm thấy chuyến bay với mã: " + maChuyenBay);
        }
    }

    public void editChuyenBay() {
        System.out.print("Nhập mã chuyến bay cần chỉnh sửa: ");
        String maChuyenBay = sc.nextLine();

        boolean found = false;
        for (ChuyenBay cb : cblist) {
            if (cb.getMaChuyenBay().equals(maChuyenBay)) {
                found = true;
                System.out.println("Đang chỉnh sửa chuyến bay mã: " + maChuyenBay);
                editChuyenBayDetails(cb);
                System.out.println("Chỉnh sửa chuyến bay thành công.");
                toFile();
                break;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy chuyến bay với mã này.");
        }
    }

    private void editChuyenBayDetails(ChuyenBay cb) {
        System.out.println("(nhấn Enter để bỏ qua)");
        System.out.print("Nhập mã chuyến bay mới: ");
        String maChuyenBayMoi = sc.nextLine();
        if (!maChuyenBayMoi.isEmpty()) {
            cb.setMaChuyenBay(maChuyenBayMoi);
        }

        // Chỉnh sửa ngày bay
        System.out.print("Nhập ngày bay mới (dd/mm/yyyy): ");
        String ngayBayMoi = sc.nextLine();
        if (!ngayBayMoi.isEmpty() && ngayBayMoi.matches("\\d{2}/\\d{2}/\\d{4}")) {
            cb.setNgayBay(ngayBayMoi);
        }

        // Chỉnh sửa giờ bay
        System.out.print("Nhập giờ bay mới (hh:mm): ");
        String gioBayMoi = sc.nextLine();
        if (!gioBayMoi.isEmpty()) {
            cb.setGioBay(gioBayMoi);
        }

        // Chỉnh sửa thời gian bay
        System.out.print("Nhập thời gian bay mới: ");
        String tgBayMoi = sc.nextLine();
        if (!tgBayMoi.isEmpty()) {
            cb.setTgBay(tgBayMoi);
        }

        // Chỉnh sửa điểm đến
        System.out.print("Nhập điểm đến mới: ");
        String diemDenMoi = sc.nextLine();
        if (!diemDenMoi.isEmpty()) {
            cb.setDiemDen(diemDenMoi);
        }

        // Chỉnh sửa số ghế
        System.out.print("Nhập số ghế mới: ");
        String soGheMoi = sc.nextLine();
        if (!soGheMoi.isEmpty()) {
            cb.setSoGhe(Integer.parseInt(soGheMoi));
        }

        // Chỉnh sửa hãng máy bay
        System.out.print("Nhập hãng máy bay mới: ");
        String hangMoi = sc.nextLine();
        if (!hangMoi.isEmpty()) {
            cb.setHang(hangMoi);
        }

        // Chỉnh sửa phi công
        for (int i = 0; i < 2; i++) {
            System.out.print("Nhập mã phi công thứ " + (i + 1) + " mới: ");
            String maPhiCongMoi = sc.nextLine();
            if (!maPhiCongMoi.isEmpty()) {
                cb.setMaPhiCongs(i, maPhiCongMoi);
            }
        }

        // Chỉnh sửa tiếp viên
        System.out.print("Nhập số lượng tiếp viên mới: ");
        int soLuongTiepVienMoi = Integer.parseInt(sc.nextLine());
        String[] maTiepViensMoi = new String[soLuongTiepVienMoi];
        for (int i = 0; i < soLuongTiepVienMoi; i++) {
            System.out.print("Nhập mã tiếp viên thứ " + (i + 1) + " mới: ");
            String maTiepVienMoi = sc.nextLine();
            if (!maTiepVienMoi.isEmpty()) {
                maTiepViensMoi[i] = maTiepVienMoi;
            }
        }
        cb.setMaTiepViens(maTiepViensMoi);
    }


    public void locChuyenBayTheoDiemDen() {
        System.out.print("Nhập điểm đến để lọc chuyến bay: ");
        String diemDen = sc.nextLine();
        List<ChuyenBay> filteredList = new ArrayList<>();

        for (ChuyenBay cb : cblist) {
            if (cb.getDiemDen().equalsIgnoreCase(diemDen)) {
                filteredList.add(cb);
            }
        }

        if (filteredList.isEmpty()) {
            System.out.println("Không tìm thấy chuyến bay nào đến " + diemDen);
        } else {
            System.out.println("Danh sách chuyến bay đến " + diemDen + ":");
            for (ChuyenBay cb : filteredList) {
                System.out.println(cb);
            }
        }
    }


    public void fromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 10) {
                    String maChuyenBay = parts[0];
                    int soGhe = Integer.parseInt(parts[1]);
                    String ngayBay = parts[2];
                    String gioBay = parts[3];
                    String tgBay = parts[4];
                    String diemDen = parts[5];
                    String hang = parts[6];
                    String maPhiCong1 = parts[7];
                    String maPhiCong2 = parts[8];
                    String[] maTiepViens = parts[9].split(";");

                    ChuyenBay cb = new ChuyenBay(maChuyenBay, soGhe, ngayBay, gioBay, tgBay, diemDen, hang, maPhiCong1, maPhiCong2, maTiepViens);
                    cblist.add(cb);
                } else {
                    System.out.println("Dòng không đủ dữ liệu: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public void toFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (ChuyenBay cb : cblist) {
                writer.write(cb.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printChuyenBay() {
        System.out.println("Danh sách chuyến bay:");
        for (ChuyenBay cb : cblist) {
            System.out.println(cb);
        }
    }
}
