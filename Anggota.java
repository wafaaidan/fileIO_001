abstract class Anggota {
    private String nama;
    private String id;
    private String kodeJenis;
    private int jumlahPinjam = 0;

    public Anggota(String nama, String id, String kodeJenis) {
        this.nama = nama;
        this.id = id;
        this.kodeJenis = kodeJenis;
    }

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
    }

    public int getJumlahPinjam() {
        return jumlahPinjam;
    }
}

class Mahasiswa extends Anggota {
    public Mahasiswa(String nama, String id) {
        super(nama, id, "M");
    }
}

class Dosen extends Anggota {
    public Dosen(String nama, String id) {
        super(nama, id, "D");
    }
}