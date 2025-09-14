package Qlysanpham;

import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            System.out.println("Kết nối DB thành công!");

            while (true) {
                System.out.println("\n--- MENU ---");
                System.out.println("1. Hiển thị sản phẩm");
                System.out.println("2. Cập nhật giá sản phẩm");
                System.out.println("3. Tổng giá trị tồn kho theo danh mục");
                System.out.println("4. Giảm giá sản phẩm");
                System.out.println("5. Đặt hàng");
                System.out.println("0. Thoát");
                int chon = sc.nextInt(); sc.nextLine();

                switch (chon) {
                    case 1 -> {
                        for (SanPham sp : Database.getAllSanPham())
                            System.out.println(sp);
                    }
                    case 2 -> {
                        System.out.print("Tên sản phẩm: ");
                        String ten = sc.nextLine();
                        System.out.print("Giá mới: ");
                        double gia = sc.nextDouble(); sc.nextLine();
                        int row = Database.capNhatGia(ten, gia);
                        System.out.println(row > 0 ? "Đã cập nhật!" : "Không tìm thấy!");
                    }
                    case 3 -> Database.tongGiaTriTheoDanhMuc();
                    case 4 -> {
                        System.out.print("Tên sản phẩm: ");
                        String ten = sc.nextLine();
                        System.out.print("% giảm giá: ");
                        double giam = sc.nextDouble(); sc.nextLine();
                        int row = Database.giamGia(ten, giam);
                        System.out.println(row > 0 ? "Đã áp dụng giảm giá!" : "Không tìm thấy!");
                    }
                    case 5 -> {
                        double tongTien = 0;
                        while (true) {
                            System.out.print("Tên sản phẩm (x để thoát): ");
                            String ten = sc.nextLine();
                            if (ten.equalsIgnoreCase("x")) break;
                            System.out.print("Số lượng: ");
                            int sl = sc.nextInt(); sc.nextLine();
                            tongTien += Database.datHang(ten, sl);
                        }
                        System.out.println("Tổng tiền đơn hàng: " + tongTien + " VND");
                    }
                    case 0 -> System.exit(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

