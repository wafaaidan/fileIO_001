public class Dosen extends Anggota { 

    private String nuptk;

    public Dosen(String id, String nama, String nuptk, String kodeJenis) {
        super(id, nama, "D");
        this.nuptk = nuptk;
    }

    public String getNuptk(){
        return nuptk;
    }

    @Override
    public int getMaksHariPinjam() {
        return 14;
    }

    @Override
    public double getTarifDendaPerHari() {
        return 1000.0;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Dosen | " + nama + " | NPM: " + nuptk
                + " | Maks pinjam: " + getMaksHariPinjam() + " hari"
                + " | Denda: Rp" + (int) getTarifDendaPerHari() + "/hari");
    }

    @Override
    public String toFileString() {
        return "MAHASISWA;" + id + ";" + nama + ";" + nuptk;
    }
}
