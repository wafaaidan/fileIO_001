import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Perpustakaan perpustakaan = new Perpustakaan();

        boolean berjalan = true;

        while (berjalan) {
            tampilkanMenu();
            int pilihan;

            try {
                pilihan = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid, masukkan angka menu yang benar.\n");
                continue; // kembali ke awal loop, tidak crash
            }

            switch (pilihan) {
                case 1:
                    tambahBuku(scanner, perpustakaan);
                    break;
                case 2:
                    tambahAnggota(scanner, perpustakaan);
                    break;
                case 3:
                    pinjamBuku(scanner, perpustakaan);
                    break;
                case 4:
                    kembalikanBuku(scanner, perpustakaan);
                    break;
                case 5:
                    perpustakaan.tampilkanSemuaBuku();
                    break;
                case 6:
                    perpustakaan.tampilkanSemuaAnggota();
                    break;
                case 7:
                    perpustakaan.simpanKeFile();
                    break;
                case 8:
                    perpustakaan.bacaDariFile();
                    break;
                case 0:
                    berjalan = false;
                    System.out.println("Terima kasih, sampai jumpa!");
                    break;
                default:
                    System.out.println("Menu tidak dikenali, coba lagi.");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void tampilkanMenu() {
        System.out.println("=== SISTEM PERPUSTAKAAN STEIN ===");
        System.out.println("1. Tambah Buku");
        System.out.println("2. Tambah Anggota");
        System.out.println("3. Pinjam Buku");
        System.out.println("4. Kembalikan Buku (hitung denda)");
        System.out.println("5. Tampilkan Semua Buku");
        System.out.println("6. Tampilkan Semua Anggota");
        System.out.println("7. Simpan Data ke File");
        System.out.println("8. Muat Data dari File");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu: ");
    }

    private static void tambahBuku(Scanner scanner, Perpustakaan perpustakaan) {
        System.out.print("Kode buku: ");
        String kodeBuku = scanner.nextLine().trim();
        System.out.print("Judul buku: ");
        String judul = scanner.nextLine().trim();
        System.out.print("Penulis: ");
        String author = scanner.nextLine().trim();
        System.out.print("Stok: ");

        int stok;
        try {
            stok = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Stok harus angka, buku disimpan dengan stok 0.");
            stok = 0;
        }

        perpustakaan.TambahBuku(new Buku(kodeBuku, judul, author, stok));
    }

    private static void tambahAnggota(Scanner scanner, Perpustakaan perpustakaan) {
        System.out.print("Tipe anggota (1=Mahasiswa, 2=Dosen): ");
        String tipe = scanner.nextLine().trim();

        System.out.print("ID anggota: ");
        String id = scanner.nextLine().trim();
        System.out.print("Nama: ");
        String nama = scanner.nextLine().trim();

        if (tipe.equals("1")) {
            System.out.print("NIM: ");
            String nim = scanner.nextLine().trim();
            perpustakaan.tambahAnggota(new Mahasiswa(id, nama, nim, "M"));
        } else if (tipe.equals("2")) {
            System.out.print("NUPTK: ");
            String nuptk = scanner.nextLine().trim();
            perpustakaan.tambahAnggota(new Dosen(id, nama, nuptk, "D"));
        } else {
            System.out.println("Tipe tidak dikenali, anggota tidak ditambahkan.");
        }
    }

    private static void pinjamBuku(Scanner scanner, Perpustakaan perpustakaan) {
        System.out.print("Kode buku yang dipinjam: ");
        String kodeBuku = scanner.nextLine().trim();
        System.out.print("ID anggota peminjam: ");
        String id = scanner.nextLine().trim();
        
        System.out.print("Tanggal pinjam (contoh angka hari ke-1, ke-5): ");
        int tanggalPinjam;
        try {
            tanggalPinjam = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Input tanggal harus berupa angka. Peminjaman dibatalkan.");
            return;
        }

        perpustakaan.pinjamBuku(kodeBuku, id, tanggalPinjam);
    }

   
    private static void kembalikanBuku(Scanner scanner, Perpustakaan perpustakaan) {
        System.out.print("ID transaksi yang dikembalikan (contoh: TRX1): ");
        String idTransaksi = scanner.nextLine().trim();
        
        System.out.print("Tanggal kembali (contoh angka hari ke-10): ");
        int tanggalKembali;
        try {
            tanggalKembali = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Input tanggal harus berupa angka. Pengembalian dibatalkan.");
            return;
        }

        perpustakaan.kembalikanBuku(idTransaksi, tanggalKembali);
    }
}