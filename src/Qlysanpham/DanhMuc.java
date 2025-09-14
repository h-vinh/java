package Qlysanpham;

public class DanhMuc {
    private int id;
    private String tenDanhMuc;

    public DanhMuc(int id, String tenDanhMuc) {
        this.id = id;
        this.tenDanhMuc = tenDanhMuc;
    }

    public int getId() { 
    	return id; 
    }
    
    public String getTenDanhMuc() { 
    	return tenDanhMuc; 
    }

    @Override
    public String toString() {
        return id + " - " + tenDanhMuc;
    }
}

