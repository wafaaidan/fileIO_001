public class Buku {

    private String kodeBuku;
    private String judul;
    private String author;
    private int stok;

    public Buku(String kodeBuku, String judul, String author, int stok) {
        this.kodeBuku = kodeBuku;
        this.judul = judul;
        this.author = author;
        this.stok = stok;
    }

    public Buku(String kodeBuku, String judul) {
        this(kodeBuku, judul, " - ", 0);
    }
    public Buku(String kodeBuku, String judul, int stok) {
        this(kodeBuku, judul, " - ", stok);
    }
    
    public String getkodeBuku(){
        return kodeBuku;
    }

    public String getJudul(){
        return judul;
    }

    public String getAuthor() {
        return author;
    }

    public int getStok(){
        return stok;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setPenulis(String author) {
        this.author = author;
    }

    public void setStok(int stok) {
        if (stok < 0) {
            this.stok = 0;
        } else {
            this.stok = stok;
        }
    }

    public boolean kurangiStok() {
        if (stok > 0) {
            stok--;
            return true;
        }
        return false; 
    }

    public void tambahStok() {
        stok++;
    }
    @Override
    public String toString() {
        return kodeBuku + " | " + judul + " | " + author + " | " + stok;
    }
}
