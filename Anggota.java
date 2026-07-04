abstract class Anggota { //abstract class anggota
    private String nama;
    private String id;
    private String kodeJenis;
    private int jumlahPinjam = 0; //deklarasi variable
    //constructor untuk variable 
    public Anggota(String nama, String id, String kodeJenis) {
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
}
//inheritance class Mahasiswa dan Dosen dari abstract class
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