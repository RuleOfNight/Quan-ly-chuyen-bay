package service;

import entity.PhiCong;
import entity.TiepVien;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class PhiCongManager{
    Scanner sc = new Scanner(System.in);
    private List<PhiCong> PhiCongList = new ArrayList<>();



    // Thêm phi công
    public void addPhiCong(){
        System.out.print("Nhập mã nhân viên: ");
        String maNhanVien = sc.nextLine().trim();

        System.out.print("Nhập tên nhân viên(Tên - Họ): ");
        String tenNhanVien = sc.nextLine().trim();

        String ngaySinh;
        do{
            System.out.print("Nhập ngày sinh(dd/mm/yyyy): ");
            ngaySinh = sc.nextLine();
            if(!ngaySinh.matches("^([0-2][0-9]|3[0-1])/([0][1-9]|1[0-2])/([0-9]{4})$")){
                System.out.println("Định dạng ngày sinh không hợp lệ. Vui lòng nhập lại.");
            }
        } while(!ngaySinh.matches("^([0-2][0-9]|3[0-1])/([0][1-9]|1[0-2])/([0-9]{4})$"));

        System.out.print("Nhập loại nhân viên: ");
        String loaiNhanVien = sc.nextLine().trim();

        String sdt;
        do{
            System.out.print("Nhập số điện thoại: ");
            sdt = sc.nextLine();
            if(!sdt.matches("^0[0-9]{9}$")){
                System.out.println("Số điện thoại không hợp lệ. Vui lòng nhập lại.");
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

        int soGioBay;
        do{
            System.out.print("Nhập số giờ bay: ");
            while(!sc.hasNextInt()){
                System.out.print("Vui lòng nhập một số hợp lệ cho số giờ bay: ");
                sc.next();
            }
            soGioBay = sc.nextInt();
            sc.nextLine();
            if(soGioBay < 0){
                System.out.println("Số giờ bay không được âm. Vui lòng nhập lại.");
            }
        } while(soGioBay < 0);

        toFile(maNhanVien, tenNhanVien, ngaySinh, loaiNhanVien, sdt, diaChi, email, soGioBay);

        System.out.println("Thêm phi công thành công!");
    }


    public void hienThi(){
        fromFile();
        for(PhiCong pc : PhiCongList){
            System.out.println(pc);
        }


    }
    // xoas phi coong
    public void removePhiCong(){
        System.out.print("Nhập mã phi công cần xóa: ");
        String maNhanVien = sc.nextLine();
        List<PhiCong> updatedList = new ArrayList<>();
        boolean found = false;

        try(BufferedReader reader = new BufferedReader(new FileReader("phicong.txt"))){
            String line;
            while((line = reader.readLine()) != null){
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


                PhiCong pc = new PhiCong(fields[0].trim(),
                        fields[1].trim(), fields[2].trim(),
                        fields[3].trim(), fields[4].trim(),
                        fields[5].trim(),
                        fields[6].trim(), Integer.parseInt(fields[7].trim()));
                updatedList.add(pc);
            }
        } catch(IOException e){
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }


        try(BufferedWriter wr = new BufferedWriter(new FileWriter("phicong.txt"))){
            for(PhiCong pc : updatedList){
                wr.write(pc.getMaNhanVien() + "," + pc.getTenNhanVien() +
                        "," + pc.getNgaySinh() + "," + pc.getLoaiNhanVien() +
                        "," + pc.getSdt() + "," + pc.getDiaChi() +
                        "," + pc.getEmail() + "," + pc.getSoGioBay());
                wr.newLine();
            }
        } catch(IOException e){
            System.out.println("Lỗi khi ghi vào file: " + e.getMessage());
        }

        if(found){
            System.out.println("Xóa phi công thành công!");
        } else{
            System.out.println("Không tìm thấy phi công với mã: " + maNhanVien);
        }
    }

    //Sửa phi công
    public void editPhiCong(){
        System.out.print("Nhập mã nhân viên phi công cần chỉnh sửa: ");
        String maNhanVien = sc.nextLine();
        boolean found = false;
        List<PhiCong> updatedList = new ArrayList<>();

        try(BufferedReader rd = new BufferedReader(new FileReader("phicong.txt"))){
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

                    System.out.print("Nhập tên nhân viên mới: ");
                    String tenNhanVien = sc.nextLine();

                    System.out.print("Nhập ngày sinh mới(dd/mm/yyyy): ");
                    String ngaySinh = sc.nextLine();
                    if(!ngaySinh.matches("^([0-2][0-9]|3[0-1])/([0][1-9]|1[0-2])/([0-9]{4})$")){
                        System.out.println("Định dạng ngày sinh không hợp lệ.");
                        return;
                    }

                    System.out.print("Nhập loại nhân viên mới: ");
                    String loaiNhanVien = sc.nextLine();

                    System.out.print("Nhập số điện thoại mới: ");
                    String sdt = sc.nextLine();
                    if(!sdt.matches("^0[0-9]{9}$")){
                        System.out.println("Số điện thoại không hợp lệ.");
                        return;
                    }

                    System.out.print("Nhập địa chỉ mới: ");
                    String diaChi = sc.nextLine();

                    System.out.print("Nhập email mới: ");
                    String email = sc.nextLine();
                    if(!email.matches("^\\S+@\\S+\\.\\S+$")){
                        System.out.println("Email không hợp lệ.");
                        return;
                    }

                    System.out.print("Nhập số giờ bay mới: ");
                    int soGioBay = sc.nextInt();
                    sc.nextLine();

                    PhiCong pc = new PhiCong(maNhanVien, tenNhanVien, ngaySinh, loaiNhanVien, sdt, diaChi, email, soGioBay);
                    updatedList.add(pc);
                } else{

                    PhiCong pc = new PhiCong(fields[0].trim(), fields[1].trim(), fields[2].trim(),
                            fields[3].trim(), fields[4].trim(), fields[5].trim(),
                            fields[6].trim(), Integer.parseInt(fields[7].trim()));
                    updatedList.add(pc);
                }
            }
        } catch(IOException e){
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }


        try(BufferedWriter writer = new BufferedWriter(new FileWriter("phicong.txt"))){
            for(PhiCong pc : updatedList){
                writer.write(pc.getMaNhanVien() + "," + pc.getTenNhanVien() + "," + pc.getNgaySinh() + ","
                        + pc.getLoaiNhanVien() + "," + pc.getSdt() + "," + pc.getDiaChi() + ","
                        + pc.getEmail() + "," + pc.getSoGioBay());
                writer.newLine();
            }
        } catch(IOException e){
            System.out.println("Lỗi khi ghi vào file: " + e.getMessage());
        }

        if(found){
            System.out.println("Chỉnh sửa phi công thành công!");
        } else{
            System.out.println("Không tìm thấy phi công với mã nhân viên: " + maNhanVien);
        }
    }

    public void sortPhiCong() {
        fromFile();
        if (PhiCongList.isEmpty()) {
            System.out.println("Danh sách phi công rỗng.");
            return;
        }

        System.out.println("Chọn tiêu chí sắp xếp:");
        System.out.println("1. Theo tên nhân viên");
        System.out.println("2. Theo số giờ bay");
        System.out.print("Nhập lựa chọn của bạn: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                PhiCongList.sort(Comparator.comparing(tv -> tv.getTenNhanVien().toLowerCase()));
                System.out.println("Đã sắp xếp theo tên nhân viên.");
                break;
            case 2:
                PhiCongList.sort(Comparator.comparingInt(PhiCong::getSoGioBay));
                System.out.println("Đã sắp xếp theo sô giờ bay.");
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
                return;
        }
        saveSortedListToFile();

        hienThi();
    }


    private void saveSortedListToFile() {
        try (BufferedWriter wr = new BufferedWriter(new FileWriter("phicong.txt"))) {
            for (PhiCong pc : PhiCongList) {
                wr.write(pc.getMaNhanVien() + "," + pc.getTenNhanVien() +
                        "," + pc.getNgaySinh() + "," + pc.getLoaiNhanVien() +
                        "," + pc.getSdt() + "," + pc.getDiaChi() +
                        "," + pc.getEmail() + "," + pc.getSoGioBay());
                wr.newLine();
            }
            System.out.println("Đã ghi danh sách đã sắp xếp vào file.");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi vào file: " + e.getMessage());
        }
    }

    private void toFile(String maNhanVien, String tenNhanVien, String ngaysinh, String loaiNhanVien, String sdt, String diaChi, String email, int soGioBay){
        String fileName = "phicong.txt";
        try(BufferedWriter wr = new BufferedWriter(new FileWriter(fileName, true))){
            wr.write(maNhanVien + "," + tenNhanVien +
                    ","  + ngaysinh + "," + loaiNhanVien +
                    "," + sdt + "," + diaChi + "," + email +
                    "," + soGioBay);
            wr.newLine();
        } catch(IOException e){
            System.out.println("Lỗi khi ghi vào file: " + e.getMessage());
        }
    }


    public void fromFile(){
        String fileName = "phicong.txt";
        PhiCongList.clear(); // xóa danh sách trước tránh bị dup
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = reader.readLine()) != null){
                String[] fields = line.split(",");

                if(fields.length != 8){
                    System.out.println("Dữ liệu không hợp lệ: " + line);
                    continue;
                }
                String maNhanVien = fields[0].trim();
                String tenNhanVien = fields[1].trim();
                String ngaySinh = fields[2].trim();
                String loaiNhanVien = fields[3].trim();
                String sdt = fields[4].trim();
                String diaChi = fields[5].trim();
                String email = fields[6].trim();


                int soGioBay;
                try{
                    soGioBay = Integer.parseInt(fields[7].trim());
                } catch(NumberFormatException e){
                    System.out.println("Số giờ bay không hợp lệ: " + tenNhanVien);
                    continue;
                }

                PhiCong pc = new PhiCong(maNhanVien, tenNhanVien, ngaySinh, loaiNhanVien, sdt, diaChi, email, soGioBay);
                PhiCongList.add(pc);
            }
        } catch(IOException e){
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }
}