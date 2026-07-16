public class Transaksi {
    private static int counter = 0;

    private String idTransaksi;
    private Buku buku;
    private Anggota peminjam; 
    private int tanggalPinjam;
    private int tanggalKembali; 
    private boolean sudahKembali;

    public Transaksi(Buku buku, Anggota peminjam, int tanggalPinjam) {
        counter++;
        this.idTransaksi = "TRX" + counter;
        this.buku = buku;
        this.peminjam = peminjam;
        this.tanggalPinjam = tanggalPinjam; 
        this.sudahKembali = false;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public Buku getBuku() {
        return buku;
    }

    public Anggota getPeminjam() {
        return peminjam;
    }

    public int getTanggalPinjam() {
        return tanggalPinjam;
    }

    public boolean isSudahKembali() {
        return sudahKembali;
    }


    public double kembalikanBuku(int tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
        this.sudahKembali = true;
        buku.tambahStok(); 

        int maksHari = peminjam.getMaksHariPinjam();
        
        int lamaPinjam = this.tanggalKembali - this.tanggalPinjam;
        
        int hariTerlambat = 0;
        
        if (lamaPinjam > maksHari) {
            hariTerlambat = lamaPinjam - maksHari;
        }

        return peminjam.hitungDenda(hariTerlambat);
    }

    @Override
    public String toString() {
        String status = sudahKembali ? "Sudah kembali (Hari ke-" + tanggalKembali + ")" : "Sedang dipinjam";
        return idTransaksi + " | " + buku.getJudul() + " dipinjam oleh " + peminjam.getNama()
                + " sejak hari ke-" + tanggalPinjam + " | " + status;
    }
}