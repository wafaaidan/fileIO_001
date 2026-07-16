public abstract class Anggota implements Peminjam{ //abstract class anggota
    //encapsulation
    protected String nama;
    protected String id;
    private String kodeJenis;
    private int jumlahPinjam = 0; 

    //constructor untuk variable 
    public Anggota(String id, String nama, String kodeJenis) {
        this.nama = nama;
        this.id = id;
        this.kodeJenis = kodeJenis;
    } 
    
    //membuat getter dan setter
    public String getNama() {
        return nama;
    }

    public String getId() {
        return id;
    }

    public String getKodeJenis() {
        return kodeJenis;
    }

    public void tambahPinjam() {
        jumlahPinjam++;
    } //method untuk peminjaman buku

    public int getJumlahPinjam() {
        return jumlahPinjam;
    } 
    public abstract void tampilkanInfo();

    public abstract int getMaksHariPinjam();

    public abstract double getTarifDendaPerHari();

    @Override
    public void pinjamBuku(Buku buku) {
        System.out.println(nama + " meminjam buku: " + buku.getJudul());
    }
    @Override
    public double hitungDenda(int hariTerlambat) {
        if (hariTerlambat <= 0) {
            return 0; // tidak telat, denda = Rp0
        }
        return hariTerlambat * getTarifDendaPerHari();
    }
    public abstract String toFileString();

    @Override
    public String toString() {
        return "[" + id + "] " + nama;
    }
}
