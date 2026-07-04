import java.util.ArrayList;
import java.util.List;

public class Registry {
    public static void main(String[] args) {
        List<Anggota> hasil = new ArrayList<>();
        hasil.add(new Mahasiswa("Dio", "M001"));
        hasil.add(new Mahasiswa("Yogi", "M002"));
        hasil.add(new Dosen("Shouta", "D201"));
        hasil.add(new Dosen("Benny", "D202"));
        hasil.add(new Dosen("Johnny", "D203"));
        AnggotaFileIO.simpanAnggota(hasil, "data_member.txt");
    }
}
