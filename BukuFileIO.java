import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BukuFileIO {

    // Menulis daftar buku ke file, format tiap baris: judul;stok
    public static void simpanKeFile(List<Buku> daftarBuku, String namaFile) {
        try (FileWriter writer = new FileWriter(namaFile)) {
            for (Buku b : daftarBuku) {
                writer.write(b.getJudul() + " | " + b.getStok() + "\n");
            }
            System.out.println("Data berhasil disimpan ke " + namaFile);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan file: " + e.getMessage());
        }
    }

    // Membaca file dan mengembalikan List<Buku>
    public static List<Buku> bacaDariFile(String namaFile) {
        List<Buku> daftarBuku = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(namaFile))) {
            String baris;
            while ((baris = reader.readLine()) != null) {
                if (baris.isBlank()) continue; // lewati baris kosong
                String[] bagian = baris.split(" | ");
                String judul = bagian[0];
                int stok = Integer.parseInt(bagian[1]); // String -> int
                daftarBuku.add(new Buku(judul, stok));
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca file: " + e.getMessage());
        }

        return daftarBuku;
    }
}