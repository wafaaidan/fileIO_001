import java.util.ArrayList;
import java.util.List;

public class Registry {
    public static void main(String[] args) {
        List<Anggota> hasil = new ArrayList<>();
        hasil.add(new Mahasiswa("Dio", "M001"));
        hasil.add(new Mahasiswa("Yogi", "M002"));
        hasil.add(new Mahasiswa("Zeppeli", "M003"));
        hasil.add(new Dosen("Shouta", "D201"));
        hasil.add(new Dosen("Benny", "D202"));
        hasil.add(new Dosen("Johnny", "D203"));
        hasil.add(new Staff("Leia", "S001"));
        hasil.add(new Staff("Han Solo", "S010"));

        List<Anggota> daftarMahasiswa = new ArrayList<>();
        List<Anggota> daftarDosen = new ArrayList<>();
        List<Anggota> daftarStaff = new ArrayList<>();

        for (Anggota s : hasil) {
            if (s.getKodeJenis().equals("M")) {
                daftarMahasiswa.add(s);
            } else if (s.getKodeJenis().equals("D")) {
                daftarDosen.add(s);
            } else if (s.getKodeJenis().equals("S")) {
                daftarStaff.add(s);
            }
        }
        AnggotaFileIO.simpanAnggota(daftarMahasiswa, "data_mahasiswa.txt");
        AnggotaFileIO.simpanAnggota(daftarDosen, "data_dosen.txt");
        AnggotaFileIO.simpanAnggota(daftarStaff, "data_staff.txt");
    }
}
