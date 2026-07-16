public class Mahasiswa extends Anggota { 

    private String nim;

    public Mahasiswa(String id, String nama, String nim, String kodeJenis) {
        super(id, nama, "M");
        this.nim = nim;
    }

    public String getNim(){
        return nim;
    }

    @Override
    public int getMaksHariPinjam() {
        return 7;
    }

    @Override
    public double getTarifDendaPerHari() {
        return 1000.0;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Mahasiswa | " + nama + " | NPM: " + nim
                + " | Maks pinjam: " + getMaksHariPinjam() + " hari"
                + " | Denda: Rp" + (int) getTarifDendaPerHari() + "/hari");
    }

    @Override
    public String toFileString() {
        return "MAHASISWA;" + id + ";" + nama + ";" + nim;
    }
}
