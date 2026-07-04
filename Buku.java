public class Buku {
    private String judul;
    private int stok;
    public Buku(String judul, int stok) {
        this.judul = judul;
        this.stok = stok;
    }

    public String getJudul(){
        return judul;
    }
    public int getStok(){
        return stok;
    }
}
