package Qlysanpham;

public class SanPham {
    private int id;
    private String ten;
    private double gia;
    private int soLuong;
    private double giamGia;
    private DanhMuc danhMuc;

    public SanPham(int id, String ten, double gia, int soLuong, double giamGia, DanhMuc danhMuc) {
        this.id = id;
        this.ten = ten;
        this.gia = gia;
        this.soLuong = soLuong;
        this.giamGia = giamGia;
        this.danhMuc = danhMuc;
    }

    public int getId() { 
    	return id; 
    }
    
    public String getTen() {
    	return ten; 
    }
    
    public double getGia() {
    	return gia * (1 - giamGia / 100); 
    }
    
    public int getSoLuong() {
    	return soLuong; 
    }
    
    public DanhMuc getDanhMuc() {
    	return danhMuc; 
    }

    @Override
    public String toString() {
        return ten + " - " + getGia() + " VND - SL:" + soLuong + " - Danh mục:" + danhMuc.getTenDanhMuc();
    }
}
