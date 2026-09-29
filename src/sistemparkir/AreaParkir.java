package sistemparkir;

import java.util.ArrayList;

public class AreaParkir {

    private ArrayList<Kendaraan> daftarKendaraan;
    private int kapasitas;
    private int totalPendapatan;
    private int jumlahKeluar;

    public AreaParkir(int kapasitas) {
        this.kapasitas = kapasitas;
        daftarKendaraan = new ArrayList<Kendaraan>();
    }

    public boolean isPenuh() {
        return daftarKendaraan.size() >= kapasitas;
    }

    public Kendaraan cariKendaraan(String platNomor) {
        for (int i = 0; i < daftarKendaraan.size(); i++) {
            Kendaraan k = daftarKendaraan.get(i);
            if (k.getPlatNomor().equalsIgnoreCase(platNomor)) {
                return k;
            }
        }
        return null;
    }

    public void tambahKendaraan(Kendaraan k) {
        daftarKendaraan.add(k);
    }

    public void keluarkanKendaraan(String platNomor, int lamaParkir) {
        Kendaraan k = cariKendaraan(platNomor);
        if (k == null) {
            System.out.println("Kendaraan dengan plat " + platNomor + " tidak ditemukan.");
            return;
        }

        k.setLamaParkir(lamaParkir);
        int tarif = k.hitungTarif(lamaParkir);
        int diskon = k.hitungDiskon(tarif, k.isMember());
        int totalBayar = k.hitungTarif();

        System.out.println();
        System.out.println("Struk Parkir");
        k.tampilkanInfo();
        System.out.println("Lama parkir : " + lamaParkir + " jam");
        System.out.println("Tarif       : Rp" + tarif);
        System.out.println("Diskon      : Rp" + diskon);
        System.out.println("Total bayar : Rp" + totalBayar);

        totalPendapatan = totalPendapatan + totalBayar;
        jumlahKeluar++;
        daftarKendaraan.remove(k);
    }

    public void tampilkanDaftar() {
        if (daftarKendaraan.isEmpty()) {
            System.out.println("Belum ada kendaraan yang parkir.");
            return;
        }

        System.out.println("Terisi " + daftarKendaraan.size() + " dari " + kapasitas + " tempat");
        for (int i = 0; i < daftarKendaraan.size(); i++) {
            System.out.println();
            System.out.println("Kendaraan ke-" + (i + 1));
            daftarKendaraan.get(i).tampilkanInfo();
        }
    }

    public void tampilkanDaftar(String jenis) {
        int jumlah = 0;
        for (int i = 0; i < daftarKendaraan.size(); i++) {
            Kendaraan k = daftarKendaraan.get(i);
            if (k.getJenis().equalsIgnoreCase(jenis)) {
                jumlah++;
                System.out.println();
                System.out.println(jenis + " ke-" + jumlah);
                k.tampilkanInfo();
            }
        }

        if (jumlah == 0) {
            System.out.println("Tidak ada " + jenis + " yang sedang parkir.");
        }
    }

    public void tampilkanLaporan() {
        System.out.println("Laporan Pendapatan");
        System.out.println("Sedang parkir     : " + daftarKendaraan.size() + " kendaraan");
        System.out.println("Sudah keluar      : " + jumlahKeluar + " kendaraan");
        System.out.println("Total pendapatan  : Rp" + totalPendapatan);
    }
}
