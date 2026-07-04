import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Buku> daftarBuku = new ArrayList<>();
        daftarBuku.add(new Buku("Mein Kampf", 1));
        daftarBuku.add(new Buku("Pedoman Ayat-Ayat Kiri", 3));
        daftarBuku.add(new Buku("K-On vol. 4", 9));
        daftarBuku.add(new Buku("Deathman's Question", 1));
        daftarBuku.add(new Buku("Yukiyukite", 2));
        BukuFileIO.simpanKeFile(daftarBuku, "data_buku.txt");
        
    }
}