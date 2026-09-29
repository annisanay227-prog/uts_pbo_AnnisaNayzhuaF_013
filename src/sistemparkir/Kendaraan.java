package sistemparkir;

public class Kendaraan {

    private String platNomor;
    private boolean member;
    private int lamaParkir;

    public Kendaraan(String platNomor) {
        this(platNomor, false);
    }

    public Kendaraan(String platNomor, boolean member) {
        this.platNomor = platNomor;
        this.member = member;
    }

    public String getPlatNomor() {
        return platNomor;
    }

    public boolean isMember() {
        return member;
    }

    public int getLamaParkir() {
        return lamaParkir;
    }

    public void setLamaParkir(int lamaParkir) {
        this.lamaParkir = lamaParkir;
    }

    public String getJenis() {
        return "Kendaraan";
    }

    // tarif aslinya ditentukan di class turunan
    public int hitungTarif(int jam) {
        return 0;
    }

    public int hitungTarif(int jam, boolean member) {
        int tarif = hitungTarif(jam);
        return tarif - hitungDiskon(tarif, member);
    }

    public int hitungTarif() {
        return hitungTarif(lamaParkir, member);
    }

    public int hitungDiskon(int tarif, boolean member) {
        if (member) {
            return tarif * 20 / 100;
        } else {
            return 0;
        }
    }

    public void tampilkanInfo() {
        System.out.println("Plat nomor  : " + platNomor);
        System.out.println("Jenis       : " + getJenis());
        if (member) {
            System.out.println("Member      : Ya");
        } else {
            System.out.println("Member      : Tidak");
        }
    }
}
