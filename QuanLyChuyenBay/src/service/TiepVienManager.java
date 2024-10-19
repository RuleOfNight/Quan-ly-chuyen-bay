package service;

import entity.TiepVien;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class TiepVienManager{
    Scanner sc = new Scanner(System.in);
    private List<TiepVien> TiepVienList = new ArrayList<>();

    public void hienThi(){
        readFromFile();
        for(TiepVien tv : TiepVienList){
            System.out.println(tv);
        }
    }

    // Thêm tiếp viên
    public void addTiepVien(){
        System.out.print("Nhập mã nhân viên: ");
        String maNhanVien = sc.nextLine().trim();

        System.out.print("Nhập tên nhân viên(Tên - Họ): ");
        String tenNhanVien = sc.nextLine().trim();

        String ngaySinh;
        do{
            System.out.print("Nhập ngày sinh(dd/mm/yyyy): ");
            ngaySinh = sc.nextLine();
            if(!ngaySinh.matches("^([0-2][0-9]|3[0-1])/([0][1-9]|1[0-2])/([0-9]{4})$")){
                System.out.println("Định dạng ngày sinh không hợp lệ, vui lòng nhập lại(dd/mm/yyyy).");
            }
        } while(!ngaySinh.matches("^([0-2][0-9]|3[0-1])/([0][1-9]|1[0-2])/([0-9]{4})$"));

        System.out.print("Nhập loại nhân viên: ");
        String loaiNhanVien = sc.nextLine().trim();

        String sdt;
        do{
            System.out.print("Nhập số điện thoại: ");
            sdt = sc.nextLine();
            if(!sdt.matches("^0[0-9]{9}$")){
                System.out.println("Số điện thoại không hợp lệ. Vui lòng nhập lại(10 số).");
            }
        } while(!sdt.matches("^0[0-9]{9}$"));

        System.out.print("Nhập địa chỉ: ");
        String diaChi = sc.nextLine().trim();

        String email;
        do{
            System.out.print("Nhập email: ");
            email = sc.nextLine();
            if(!email.matches("^\\S+@\\S+\\.\\S+$")){
                System.out.println("Email không hợp lệ. Vui lòng nhập lại.");
            }
        } while(!email.matches("^\\S+@\\S+\\.\\S+$"));

        int toeic;
        do{
            System.out.print("Nhập điểm TOEIC(0-990): ");
            while(!sc.hasNextInt()){
                System.out.print("Vui lòng nhập điểm hợp lệ cho TOEIC(0-990): ");
                sc.next();
            }
            toeic = sc.nextInt();
            sc.nextLine();
            if(toeic < 0 || toeic > 990){
                System.out.println("Vui lòng nhập điểm hợp lệ cho TOEIC(0-990):");
            }
        } while(toeic < 0 || toeic > 990);


        writeToFile(maNhanVien, tenNhanVien, ngaySinh, loaiNhanVien, sdt, diaChi, email, toeic);

        System.out.println("Thêm tiếp viên thành công!");
    }

    //Xóa tiếp viên
    public void removeTiepVien(){
        System.out.print("Nhập mã nhân viên tiếp viên cần xóa: ");
        String maNhanVien = sc.nextLine();
        List<TiepVien> updatedList = new ArrayList<>();
        boolean found = false;

        try(BufferedReader rd = new BufferedReader(new FileReader("TiepVien.txt"))){
            String line;
            while((line = rd.readLine()) != null){
                String[] fields = line.split(",");

                if(fields.length != 8){
                    System.out.println("Dữ liệu không hợp lệ: " + line);
                    continue;
                }

                String currentMaNhanVien = fields[0].trim();
                if(currentMaNhanVien.equals(maNhanVien)){
                    found = true;
                    continue;
                }


                TiepVien tv = new TiepVien(fields[0].trim(), fields[1].trim(), fields[2].trim(),
                        fields[3].trim(), fields[4].trim(), fields[5].trim(),
                        fields[6].trim(), Integer.parseInt(fields[7].trim()));
                updatedList.add(tv);
            }
        } catch(IOException e){
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }


        try(BufferedWriter wr = new BufferedWriter(new FileWriter("TiepVien.txt"))){
            for(TiepVien tv : updatedList){
                wr.write(tv.getMaNhanVien() + "," + tv.getTenNhanVien() +
                        "," + tv.getNgaySinh() + "," + tv.getLoaiNhanVien() +
                        "," + tv.getSdt() + "," + tv.getDiaChi() + ","
                        + tv.getEmail() + "," + tv.getToeic());
                wr.newLine();
            }
        } catch(IOException e){
            System.out.println("Lỗi khi ghi vào file: " + e.getMessage());
        }

        if(found){
            System.out.println("Xóa tiếp viên thành công!");
        } else{
            System.out.println("Không tìm thấy tiếp viên với mã: " + maNhanVien);
        }
    }

    //Sửa tiếp viên
    public void editTiepVien(){
        System.out.print("Nhập mã nhân viên tiếp viên cần chỉnh sửa: ");
        String maNhanVien = sc.nextLine();
        boolean found = false;
        List<TiepVien> updatedList = new ArrayList<>();

        try(BufferedReader rd = new BufferedReader(new FileReader("TiepVien.txt"))){
            String line;
            while((line = rd.readLine()) != null){
                String[] fields = line.split(",");

                if(fields.length != 8){
                    System.out.println("Dữ liệu không hợp lệ: " + line);
                    continue;
                }
                String currentMaNhanVien = fields[0].trim();

                if(currentMaNhanVien.equals(maNhanVien)){
                    found = true;

                    String tenNhanVien;
                    do {
                        System.out.print("Nhập tên nhân viên mới: ");
                        tenNhanVien = sc.nextLine();
                    } while (tenNhanVien.isEmpty());

                    String ngaySinh;
                    do {
                        System.out.print("Nhập ngày sinh mới (dd/mm/yyyy): ");
                        ngaySinh = sc.nextLine();
                        if (!ngaySinh.matches("^([0-2][0-9]|3[0-1])/([0][1-9]|1[0-2])/([0-9]{4})$")) {
                            System.out.println("Định dạng ngày sinh không hợp lệ. Vui lòng nhập lại.");
                        }
                    } while (!ngaySinh.matches("^([0-2][0-9]|3[0-1])/([0][1-9]|1[0-2])/([0-9]{4})$"));

                    String loaiNhanVien;
                    do {
                        System.out.print("Nhập loại nhân viên mới: ");
                        loaiNhanVien = sc.nextLine();
                    } while (loaiNhanVien.isEmpty());

                    String sdt;
                    do {
                        System.out.print("Nhập số điện thoại mới: ");
                        sdt = sc.nextLine();
                        if (!sdt.matches("^0[0-9]{9}$")) {
                            System.out.println("Số điện thoại không hợp lệ. Vui lòng nhập lại.");
                        }
                    } while (!sdt.matches("^0[0-9]{9}$"));

                    String diaChi;
                    do {
                        System.out.print("Nhập địa chỉ mới: ");
                        diaChi = sc.nextLine();
                    } while (diaChi.isEmpty());

                    String email;
                    do {
                        System.out.print("Nhập email mới: ");
                        email = sc.nextLine();
                        if (!email.matches("^\\S+@\\S+\\.\\S+$")) {
                            System.out.println("Email không hợp lệ. Vui lòng nhập lại.");
                        }
                    } while (!email.matches("^\\S+@\\S+\\.\\S+$"));

                    int Toeic;
                    do {
                        System.out.print("Nhập điểm TOEIC mới: ");
                        while (!sc.hasNextInt()) {
                            System.out.println("Điểm TOEIC phải là một số. Vui lòng nhập lại.");
                            sc.next();
                        }
                        Toeic = sc.nextInt();
                        sc.nextLine();
                        if (Toeic < 0 || Toeic > 990) {
                            System.out.println("Điểm TOEIC phải nằm trong khoảng 0 - 990. Vui lòng nhập lại.");
                        }
                    } while (Toeic < 0 || Toeic > 990);



                    TiepVien tv = new TiepVien(maNhanVien, tenNhanVien, ngaySinh, loaiNhanVien, sdt, diaChi, email, Toeic);
                    updatedList.add(tv);
                } else{

                    TiepVien tv = new TiepVien(fields[0].trim(), fields[1].trim(), fields[2].trim(),
                            fields[3].trim(), fields[4].trim(), fields[5].trim(),
                            fields[6].trim(), Integer.parseInt(fields[7].trim()));
                    updatedList.add(tv);
                }
            }
        } catch(IOException e){
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }


        try(BufferedWriter writer = new BufferedWriter(new FileWriter("TiepVien.txt"))){
            for(TiepVien tv : updatedList){
                writer.write(tv.getMaNhanVien() + "," + tv.getTenNhanVien() +
                        "," + tv.getNgaySinh() + "," + tv.getLoaiNhanVien() +
                        "," + tv.getSdt() + "," + tv.getDiaChi() +
                        "," + tv.getEmail() + "," + tv.getToeic());
                writer.newLine();
            }
        } catch(IOException e){
            System.out.println("Lỗi khi ghi vào file: " + e.getMessage());
        }

        if(found){
            System.out.println("Chỉnh sửa tiếp viên thành công!");
        } else{
            System.out.println("Không tìm thấy tiếp viên với mã nhân viên: " + maNhanVien);
        }
    }

    public void sortTiepVien() {
        readFromFile();
        if (TiepVienList.isEmpty()) {
            System.out.println("Danh sách tiếp viên rỗng.");
            return;
        }

        System.out.println("Chọn tiêu chí sắp xếp:");
        System.out.println("1. Theo tên nhân viên");
        System.out.println("2. Theo điểm TOEIC");
        System.out.print("Nhập lựa chọn của bạn: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                TiepVienList.sort(Comparator.comparing(tv -> tv.getTenNhanVien().toLowerCase()));
                System.out.println("Đã sắp xếp theo tên nhân viên.");
                break;
            case 2:
                TiepVienList.sort(Comparator.comparingInt(TiepVien::getToeic));
                System.out.println("Đã sắp xếp theo điểm TOEIC.");
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
                return;
        }
        saveSortedListToFile();

        hienThi();
    }


    private void saveSortedListToFile() {
        try (BufferedWriter wr = new BufferedWriter(new FileWriter("TiepVien.txt"))) {
            for (TiepVien tv : TiepVienList) {
                wr.write(tv.getMaNhanVien() + "," + tv.getTenNhanVien() +
                        "," + tv.getNgaySinh() + "," + tv.getLoaiNhanVien() +
                        "," + tv.getSdt() + "," + tv.getDiaChi() +
                        "," + tv.getEmail() + "," + tv.getToeic());
                wr.newLine();
            }
            System.out.println("Đã ghi danh sách đã sắp xếp vào file.");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi vào file: " + e.getMessage());
        }
    }


    private void writeToFile(String maNhanVien, String tenNhanVien, String ngaysinh, String loaiNhanVien, String sdt, String diaChi, String email, int Toeic){
        String fileName = "TiepVien.txt";
        try(BufferedWriter wr = new BufferedWriter(new FileWriter(fileName, true))){
            wr.write(maNhanVien + "," + tenNhanVien +
                    ","  + ngaysinh + "," + loaiNhanVien +
                    "," + sdt + "," + diaChi + "," + email +
                    "," + Toeic);
            wr.newLine();
        } catch(IOException e){
            System.out.println("Lỗi khi ghi vào file: " + e.getMessage());
        }
    }


    public void readFromFile() {
        String fileName = "TiepVien.txt";
        TiepVienList.clear(); // xóa danh sách trước tránh bị dup

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] fields = line.split(",");
                if (fields.length != 8) {
                    System.out.println("Dữ liệu không hợp lệ (thiếu trường): " + line);
                    continue;
                }
                String maNhanVien = fields[0].trim();
                String tenNhanVien = fields[1].trim();
                String ngaySinh = fields[2].trim();
                String loaiNhanVien = fields[3].trim();
                String sdt = fields[4].trim();
                String diaChi = fields[5].trim();
                String email = fields[6].trim();

                int toeic;
                try {
                    toeic = Integer.parseInt(fields[7].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Điểm TOEIC không hợp lệ cho tiếp viên: " + tenNhanVien);
                    continue;
                }
                TiepVien tv = new TiepVien(maNhanVien, tenNhanVien, ngaySinh, loaiNhanVien, sdt, diaChi, email, toeic);
                TiepVienList.add(tv);
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }

}