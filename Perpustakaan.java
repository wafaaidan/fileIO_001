import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Perpustakaan {

    private List<Buku> daftarBuku;
    private List<Anggota> daftarAnggota;
    private List<Transaksi> dataTransaksi;

    private static String File_Buku = "data_buku.txt";
    private static String File_Anggota = "data_anggota.txt";

    public Perpustakaan() {
        this.daftarBuku = new ArrayList<>();
        this.daftarAnggota = new ArrayList<>();
        this.dataTransaksi = new ArrayList<>();
    }
    // Buku
    public void TambahBuku(Buku buku) {
    daftarBuku.add(buku);
        System.out.println("Buku berhasil ditambahkan: " + buku);
    }
    public Buku cariBukuByKode(String kode) {
        for (Buku b : daftarBuku) {
            if (b.getkodeBuku().equalsIgnoreCase(kode)) {
                return b;
            }
        }
        return null; // tidak ditemukan
    }
    public void tampilkanSemuaBuku() {
        if (daftarBuku.isEmpty()) {
            System.out.println("Belum ada data buku.");
            return;
        }
        System.out.println("=== DAFTAR BUKU ===");
        for (Buku b : daftarBuku) {
            System.out.println(b);
        }
    }
    //Anggota
    public void tambahAnggota(Anggota anggota) {
        daftarAnggota.add(anggota);
        System.out.println("Anggota berhasil ditambahkan.");
    }
    public Anggota cariAnggotaById(String id) {
        for (Anggota a : daftarAnggota) {
            if (a.getId().equalsIgnoreCase(id)) {
                return a;
            }
        }
        return null;
    }
    //Proses Transaksi
    public boolean pinjamBuku(String kodeBuku, String idAnggota, int tanggalPinjam) {
        Buku buku = cariBukuByKode(kodeBuku);
        Anggota anggota = cariAnggotaById(idAnggota);

        if (buku == null) {
            System.out.println("Buku dengan kode " + kodeBuku + " tidak ditemukan.");
            return false;
        }
        if (anggota == null) {
            System.out.println("Anggota dengan id " + idAnggota + " tidak ditemukan.");
            return false;
        }
        if (!buku.kurangiStok()) {  
            System.out.println("Stok buku \"" + buku.getJudul() + "\" habis, tidak bisa dipinjam.");
            return false;
        }

        anggota.pinjamBuku(buku);
        Transaksi transaksi = new Transaksi(buku, anggota, tanggalPinjam);
        dataTransaksi.add(transaksi);
        System.out.println("Peminjaman berhasil dicatat: " + transaksi.getIdTransaksi());
        return true;
    }

    public void kembalikanBuku(String idTransaksi, int tanggalKembali) {
        for (Transaksi t : dataTransaksi) {
            if (t.getIdTransaksi().equalsIgnoreCase(idTransaksi) && !t.isSudahKembali()) {
                double denda = t.kembalikanBuku(tanggalKembali);
                System.out.println("Buku \"" + t.getBuku().getJudul() + "\" berhasil dikembalikan.");
                if (denda > 0) {
                    System.out.println("Terlambat! Denda yang harus dibayar: Rp" + (int) denda);
                } else {
                    System.out.println("Tidak telat, denda: Rp0");
                }
                return;
            }
        }
        System.out.println("Transaksi dengan id " + idTransaksi + " tidak ditemukan / sudah dikembalikan.");
    }

    public void tampilkanSemuaTransaksi() {
        if (dataTransaksi.isEmpty()) {
            System.out.println("Belum ada transaksi peminjaman.");
            return;
        }
        System.out.println("=== DAFTAR TRANSAKSI ===");
        for (Transaksi t : dataTransaksi) {
            System.out.println(t);
        }
    }

    public void tampilkanSemuaAnggota() {
        if (daftarAnggota.isEmpty()) {
            System.out.println("Belum ada data anggota.");
            return;
        }
        System.out.println("=== DAFTAR ANGGOTA ===");

        for (Anggota a : daftarAnggota) {
            a.tampilkanInfo();
        }
    }


    public void simpanKeFile() {
        // 1. Simpan Buku
        try (FileWriter writer = new FileWriter(File_Buku)) {
            for (Buku b : daftarBuku) {
            
                writer.write(b.getkodeBuku() + " | " + b.getJudul() + " | " + b.getAuthor() + " | " + b.getStok() + "\n");
            }
            System.out.println("Data buku berhasil disimpan ke " + File_Buku);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan file buku: " + e.getMessage());
        }

        try (FileWriter writer = new FileWriter(File_Anggota)) {
            for (Anggota a : daftarAnggota) {
            
                String kodeUnik = "";
        
                if (a instanceof Mahasiswa) {
                    Mahasiswa mhs = (Mahasiswa) a;
                    kodeUnik = mhs.getNim(); 
                } else if (a instanceof Dosen) {
                    Dosen dsn = (Dosen) a;
                    kodeUnik = dsn.getNuptk(); 
                }
        
             
                writer.write(a.getNama() + " | " + a.getId() + " | " + a.getKodeJenis() + " | " + kodeUnik + "\n");
            }
            System.out.println("Data anggota berhasil disimpan ke " + File_Anggota);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan file anggota: " + e.getMessage());
        }
    }

    public void bacaDariFile() {
        
        // 1. Membaca Data Buku
        try (BufferedReader reader = new BufferedReader(new FileReader(File_Buku))) {
            daftarBuku.clear();
            String baris;
            while ((baris = reader.readLine()) != null) {
                if (baris.trim().isEmpty()) continue; // lewati baris kosong
                
                // Perbaikan Krusial: Menggunakan " \\| " karena "|" adalah karakter regex khusus
                String[] bagian = baris.split(" \\| ");
                
                if (bagian.length >= 4) {
                    String kodeBuku = bagian[0].trim();
                    String judul = bagian[1].trim();
                    String author = bagian[2].trim();
                    int stok = Integer.parseInt(bagian[3].trim());
                    daftarBuku.add(new Buku(kodeBuku, judul, author, stok));
                }
            }
            System.out.println("Data buku berhasil dimuat.");
        } catch (IOException e) {
            System.out.println("Belum ada file data buku atau file gagal dibaca.");
        }
        

        // 2. Membaca Data Anggota (Gabungan Logika Modal + Fleksibilitas Kode Jenis)
        try (BufferedReader reader = new BufferedReader(new FileReader(File_Anggota))) {  
            daftarAnggota.clear();  
            String baris;
            while ((baris = reader.readLine()) != null) {
                if (baris.trim().isEmpty()) continue; // lewati baris kosong
                
                try {
                    // Perbaikan Krusial: Menggunakan " \\| " untuk memotong String dengan aman
                    String[] bagian = baris.split(" \\| ");
                    
                    String nama = bagian[0].trim();
                    String id = bagian[1].trim();
                    String kodeJenis = bagian[2].trim();
                    
                    
                    // Mendukung kode pendek (modal) maupun kode panjang (kode saat ini)
                    if (kodeJenis.equals("M") ) {
                        String nim = bagian[3].trim();
                        // Sesuaikan parameter objek Mahasiswa Anda (contoh default: id, nama, tambahan npm/kode)
                        daftarAnggota.add(new Mahasiswa(id, nama, nim, "M")); 
                    } else if (kodeJenis.equals("D")) {
                        String nuptk = bagian[3].trim();
                        daftarAnggota.add(new Dosen(id, nama, nuptk, "D"));
                    } else if (kodeJenis.equals("S") ) {
                        
                        // Ditambahkan dari kode modal kelas AnggotaFileIO Anda
                        daftarAnggota.add(new Staff(id, nama, "S"));
                    } else {
                        System.out.println("Tipe anggota tidak dikenali, baris dilewati: " + baris);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Baris anggota rusak/dilewati: " + baris);
                }
            }
            System.out.println("Data anggota berhasil dimuat.");
        } catch (IOException e) {
            System.out.println("Belum ada file data anggota atau file gagal dibaca.");
        }

        System.out.println("Proses memuat semua data selesai.");
    }
}
    
    
    
    


