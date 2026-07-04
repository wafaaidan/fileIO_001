import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnggotaFileIO {

    // Format tiap baris: nama;id;jenis (contoh: "Rangga;M001;M")
    public static void simpanAnggota(List<Anggota> daftar, String namaFile) {
        try (FileWriter writer = new FileWriter(namaFile)) {
            for (Anggota a : daftar) {
                writer.write(a.getNama() + " | " + a.getId() + " | " + a.getKodeJenis() + "\n");
            }
            System.out.println("Data anggota berhasil disimpan ke " + namaFile);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan file: " + e.getMessage());
        }
    }

    public static List<Anggota> bacaAnggota(String namaFile) {
        List<Anggota> hasil = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(namaFile))) {
            String baris;
            while ((baris = reader.readLine()) != null) {
                if (baris.isBlank())
                    continue;
                String[] bagian = baris.split(" | ");
                String nama = bagian[0];
                String id = bagian[1];
                String kodeJenis = bagian[2];

                if (kodeJenis.equals("M")) {
                    hasil.add(new Mahasiswa(nama, id));
                } else if (kodeJenis.equals("D")) {
                    hasil.add(new Dosen(nama, id));
                } else {
                    System.out.println("Jenis anggota tidak dikenal: " + kodeJenis + " (baris dilewati)");
                }
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca file: " + e.getMessage());
        }

        return hasil;
    }
}