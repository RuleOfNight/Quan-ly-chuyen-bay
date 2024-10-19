import entity.ChuyenBay;
import entity.PhiCong;
import entity.TiepVien;
import service.ChuyenBayManager;
import service.PhiCongManager;
import service.TiepVienManager;
import Swing.DangKi;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.io.*;


public class Main {
    private static final Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);
    public static void main(String[] args) throws InterruptedException {
        ChuyenBayManager qlyChuyenBay = new ChuyenBayManager();
        PhiCongManager qlyPhiCong = new PhiCongManager();
        Scanner sc = new Scanner(System.in);
        login();
        //adminlogin();


        DangKi dangKi = new DangKi();
        dangKi.setVisible(true);
        while (dangKi.isVisible()) {
            Thread.sleep(1000);
        }
    }


    public static void login() {
        DangKi dangKi = new DangKi();
        dangKi.setVisible(true);
        while (dangKi.isVisible()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String username = dangKi.getTaiKhoan();
        String password = dangKi.getMatKhau();
        String adminname = "admin";
        String adminpassword = "123";
        String inputUsername;
        String inputPassword;
        System.out.println("---Tính năng dành cho khách hàng tạm thời chưa phát triển ---");
        while (true) {
            System.out.print("Nhập tên đăng nhập: ");
            inputUsername = sc.nextLine();
            System.out.print("Nhập mật khẩu: ");
            inputPassword = sc.nextLine();

            if (inputUsername.equals(adminname) && inputPassword.equals(adminpassword)) {
                System.out.println("Đăng nhập thành công!");
                showMenu();
                break;
            } else {
                System.out.println("Tên đăng nhập hoặc mật khẩu không đúng. Vui lòng thử lại.");

            }
        }
    }



    public static void showMenu(){
        int choice;
        do{
            System.out.println("\nADMIN SYSTEM");
            System.out.println("\n=== Menu chính ===");
            System.out.println("1. Quản lý chuyến bay");
            System.out.println("2. Quản lý nhân sự");
            System.out.println("0. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1 -> quanlychuyenbay();
                case 2 -> {
                    int choice2;
                    do{
                        System.out.println("\n=== Menu quản lý nhân sự ===");
                        System.out.println("1. Quản lý Phi Công");
                        System.out.println("2. Quản lý Tiếp Viên");
                        System.out.println("0. Quay lại Menu chính");
                        choice2 = sc.nextInt();
                        sc.nextLine();
                        switch (choice2){
                            case 1 -> quanlyphicong();
                            case 2 -> quanLytiepvien();
                        }
                    }while(choice2 != 0);

                }

                case 0 -> System.out.println("Thoát chương trình");
                default -> System.out.println("Không hợp lệ");
            }
        }while(choice !=0);
    }


    public static void quanlychuyenbay(){
        ChuyenBayManager cb = new ChuyenBayManager();
        List<ChuyenBay> cblist;
        int choice;
        do{
            System.out.println("\n=== Menu quản lý chuyến bay ===");
            System.out.println("1. Thêm chuyến bay");
            System.out.println("2. Sửa chuyến bay");
            System.out.println("3. Xóa chuyến bay");
            System.out.println("4. Hiển thị danh sách chuyến bay");
            System.out.println("5. Lọc chuyến bay");
            System.out.println("0. Về menu chính");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice){
                case 1 -> {
                    cb.addChuyenBay();
                    break;
                }
                case 2 -> {
                    cb.editChuyenBay();
                    break;
                }
                case 3 -> {
                    cb.removeChuyenBay();
                    break;
                }
                case 4 -> {
                    cb.printChuyenBay();
                    break;
                }
                case 5 -> {
                    cb.locChuyenBayTheoDiemDen();
                    break;
                }
                case 0 -> System.out.println("Thoát chương trình");
                default -> System.out.println("Không hợp lệ");

            }
        }while(choice !=0);

    }


    public static void quanlyphicong(){
        PhiCongManager pc = new PhiCongManager();
        List<PhiCong> pclist;
        int choice;
        do{
            System.out.println("\n=== Menu quản lý phi công ===");
            System.out.println("1. Thêm phi công");
            System.out.println("2. Sửa phi công");
            System.out.println("3. Xóa phi công");
            System.out.println("4. Hiển thị danh sách phi công");
            System.out.println("5. Lọc phi công");
            System.out.println("0. Về menu chính");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1 -> {
                    pc.addPhiCong();
                    break;
                }
                case 2 -> {
                    pc.editPhiCong();
                    break;
                }
                case 3 -> {
                    pc.removePhiCong();
                    break;
                }
                case 4 -> {
                    int choice2;
                    do{
                        System.out.println("\n=== Danh sách phi công ===");
                        System.out.println("1. Hiển thị danh sách");
                        System.out.println("2. Sắp xếp");
                        System.out.println("0. Về Menu chính");
                        choice2 = sc.nextInt();
                        switch (choice2){
                            case 1 -> pc.hienThi();
                            case 2 -> pc.sortPhiCong();

                        }
                    }while(choice2 != 0);
                    break;
                }

            }
        }while(choice != 0);
    }


    public static void quanLytiepvien(){
        TiepVienManager tv = new TiepVienManager();
        List<TiepVien>  tvlist;
        int choice;
        do{
            System.out.println("\n=== Menu quản lý tiếp viên ===");
            System.out.println("1. Thêm tiếp viên");
            System.out.println("2. Sửa tiếp viên");
            System.out.println("3. Xóa tiếp viên");
            System.out.println("4. Hiển thị danh sách tiếp viên");
            System.out.println("0. Về menu chính");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1 -> {
                    tv.addTiepVien();
                    break;
                }
                case 2 -> {
                    tv.editTiepVien();
                    break;
                }
                case 3 -> {
                    tv.removeTiepVien();
                    break;
                }
                case 4 -> {
                    int choice2;
                    do{
                        System.out.println("\n=== Danh sách tiếp viên ===");
                        System.out.println("1. Hiển thị danh sách");
                        System.out.println("2. Sắp xếp");
                        System.out.println("0. Về Menu chính");
                        choice2 = sc.nextInt();
                        switch (choice2){
                            case 1 -> tv.hienThi();
                            case 2 -> tv.sortTiepVien();

                        }
                    }while(choice2 != 0);
                    break;
                }
            }
        }while(choice !=0);
    }
}