public class Bai6 {
    public static void main(String[] args) {

    }
}

class KhachHang {
    private int id;
    private String gioiTinh;
    private int doTuoi;

    public KhachHang(int id, String gioiTinh, int doTuoi) {
        this.id = id;
        this.gioiTinh = gioiTinh;
        this.doTuoi = doTuoi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getDoTuoi() {
        return doTuoi;
    }

    public void setDoTuoi(int doTuoi) {
        this.doTuoi = doTuoi;
    }
}

class NhanVienBanHang {

    private int id;
    private String gioiTinh;
    private String ngayLamViec;
    private String caDangKy;

    public NhanVienBanHang(int id, String gioiTinh, String ngayLamViec, String caDangKy) {
        this.id = id;
        this.gioiTinh = gioiTinh;
        this.ngayLamViec = ngayLamViec;
        this.caDangKy = caDangKy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNgayLamViec() {
        return ngayLamViec;
    }

    public void setNgayLamViec(String ngayLamViec) {
        this.ngayLamViec = ngayLamViec;
    }

    public String getCaDangKy() {
        return caDangKy;
    }

    public void setCaDangKy(String caDangKy) {
        this.caDangKy = caDangKy;
    }
}
