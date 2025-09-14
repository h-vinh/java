package Qlysanpham;

import java.sql.*;
import java.util.*;

public class Database {
    private static Connection conn;

    public static void connect() throws Exception {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ql_cuahang", "root", "");
        }
    }

    public static List<SanPham> getAllSanPham() throws Exception {
        connect();
        List<SanPham> ds = new ArrayList<>();
        String sql = "SELECT sp.*, dm.tenDanhMuc FROM SanPham sp JOIN DanhMuc dm ON sp.idDanhMuc=dm.id";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            DanhMuc dm = new DanhMuc(rs.getInt("idDanhMuc"), rs.getString("tenDanhMuc"));
            ds.add(new SanPham(
                rs.getInt("id"),
                rs.getString("ten"),
                rs.getDouble("gia"),
                rs.getInt("soLuong"),
                rs.getDouble("giamGia"),
                dm
            ));
        }
        return ds;
    }

    public static int capNhatGia(String ten, double gia) throws Exception {
        connect();
        String sql = "UPDATE SanPham SET gia=? WHERE ten=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDouble(1, gia);
        ps.setString(2, ten);
        return ps.executeUpdate();
    }

    public static int giamGia(String ten, double giam) throws Exception {
        connect();
        String sql = "UPDATE SanPham SET giamGia=? WHERE ten=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDouble(1, giam);
        ps.setString(2, ten);
        return ps.executeUpdate();
    }

    public static void tongGiaTriTheoDanhMuc() throws Exception {
        connect();
        String sql = "SELECT dm.tenDanhMuc, SUM(sp.gia*sp.soLuong) as Tong " +
                     "FROM SanPham sp JOIN DanhMuc dm ON sp.idDanhMuc=dm.id GROUP BY dm.id";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1) + ": " + rs.getDouble(2) + " VND");
        }
    }

    public static double datHang(String ten, int soLuong) throws Exception {
        connect();
        String sql = "SELECT gia, giamGia FROM SanPham WHERE ten=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, ten);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            double gia = rs.getDouble("gia") * (1 - rs.getDouble("giamGia")/100);
            return gia * soLuong;
        }
        return 0;
    }
}
