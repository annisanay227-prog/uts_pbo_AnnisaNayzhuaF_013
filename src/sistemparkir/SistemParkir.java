package sistemparkir;

import java.util.Scanner;

public class SistemParkir {

    static Scanner input = new Scanner(System.in);
    static AreaParkir area = new AreaParkir(20);

    public static void main(String[] args) {
        int pilihan = -1;

        while (pilihan != 0) {
            System.out.println();
            System.out.println("Sistem Parkir Mall");
            System.out.println("1. Kendaraan masuk");
            System.out.println("2. Kendaraan keluar");
            System.out.println("3. Lihat semua kendaraan");
            System.out.println("4. Lihat kendaraan per jenis");
            System.out.println("5. Cek estimasi tarif");
            System.out.println("6. Laporan pendapatan");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = inputAngka();
            System.out.println();

            switch (pilihan) {
                case 1:
                    kendaraanMasuk();
                    break;
                case 2:
                    kendaraanKeluar();
                    break;
                case 3:
                    area.tampilkanDaftar();
                    break;
                case 4:
                    lihatPerJenis();
                    break;
                case 5:
                    cekEstimasiTarif();
                    break;
                case 6:
                    area.tampilkanLaporan();
                    break;
                case 0:
                    System.out.println("Terima kasih.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    static int inputAngka() {
        // kalau bukan angka, buang inputnya lalu minta lagi
        while (!input.hasNextInt()) {
            input.next();
            System.out.println("Input harus berupa angka");
            System.out.print("Masukkan angka: ");
        }
        int angka = input.nextInt();
        // buang enter sisa nextInt supaya nextLine berikutnya tidak kosong
        input.nextLine();
        return angka;
    }

    static int inputJenis() {
        System.out.print("Jenis kendaraan (1 Motor, 2 Mobil, 3 Mobil Listrik): ");
        int jenis = inputAngka();
        return jenis;
    }

    static int inputLamaParkir() {
        System.out.print("Lama parkir (jam): ");
        int jam = inputAngka();
        while (jam < 1) {
            System.out.println("Lama parkir minimal 1 jam.");
            System.out.print("Lama parkir (jam): ");
            jam = inputAngka();
        }
        return jam;
    }

    static boolean inputYaTidak(String pertanyaan) {
        System.out.print(pertanyaan + " (y/n): ");
        String jawaban = input.nextLine();
        return jawaban.equalsIgnoreCase("y");
    }

    static void kendaraanMasuk() {
        if (area.isPenuh()) {
            System.out.println("Area parkir penuh.");
            return;
        }

        int jenis = inputJenis();
        if (jenis < 1 || jenis > 3) {
            System.out.println("Jenis kendaraan tidak valid.");
            return;
        }

        System.out.print("Plat nomor: ");
        String platNomor = input.nextLine().trim();
        if (platNomor.isEmpty()) {
            System.out.println("Plat nomor tidak boleh kosong.");
            return;
        }
        if (area.cariKendaraan(platNomor) != null) {
            System.out.println("Plat nomor " + platNomor + " sudah terdaftar.");
            return;
        }

        boolean member = inputYaTidak("Member");

        Kendaraan kendaraan;
        if (jenis == 1) {
            kendaraan = new Motor(platNomor, member);
        } else if (jenis == 2) {
            kendaraan = new Mobil(platNomor, member);
        } else {
            boolean pakaiCharging = inputYaTidak("Pakai charging");
            kendaraan = new MobilListrik(platNomor, member, pakaiCharging);
        }

        area.tambahKendaraan(kendaraan);
        System.out.println(kendaraan.getJenis() + " " + platNomor + " berhasil masuk.");
    }

    static void kendaraanKeluar() {
        System.out.print("Plat nomor: ");
        String platNomor = input.nextLine().trim();
        if (area.cariKendaraan(platNomor) == null) {
            System.out.println("Kendaraan dengan plat " + platNomor + " tidak ditemukan.");
            return;
        }

        int lamaParkir = inputLamaParkir();
        area.keluarkanKendaraan(platNomor, lamaParkir);
    }

    static void lihatPerJenis() {
        int jenis = inputJenis();
        if (jenis == 1) {
            area.tampilkanDaftar("Motor");
        } else if (jenis == 2) {
            area.tampilkanDaftar("Mobil");
        } else if (jenis == 3) {
            area.tampilkanDaftar("Mobil Listrik");
        } else {
            System.out.println("Jenis kendaraan tidak valid.");
        }
    }

    static void cekEstimasiTarif() {
        int jenis = inputJenis();
        if (jenis < 1 || jenis > 3) {
            System.out.println("Jenis kendaraan tidak valid.");
            return;
        }

        int jam = inputLamaParkir();
        boolean member = inputYaTidak("Member");

        // objek sementara cuma buat hitung, tidak disimpan ke area parkir
        Kendaraan contoh;
        if (jenis == 1) {
            contoh = new Motor("-");
        } else if (jenis == 2) {
            contoh = new Mobil("-");
        } else {
            boolean pakaiCharging = inputYaTidak("Pakai charging");
            contoh = new MobilListrik("-", member, pakaiCharging);
        }

        int tarif = contoh.hitungTarif(jam);
        int diskon = contoh.hitungDiskon(tarif, member);
        int totalBayar = contoh.hitungTarif(jam, member);

        System.out.println();
        System.out.println("Estimasi Tarif " + contoh.getJenis());
        System.out.println("Lama parkir : " + jam + " jam");
        System.out.println("Tarif       : Rp" + tarif);
        System.out.println("Diskon      : Rp" + diskon);
        System.out.println("Total bayar : Rp" + totalBayar);
    }
}
