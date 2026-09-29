package sistemparkir;

public class Motor extends Kendaraan {

    public Motor(String platNomor) {
        super(platNomor);
    }

    public Motor(String platNomor, boolean member) {
        super(platNomor, member);
    }

    @Override
    public String getJenis() {
        return "Motor";
    }

    @Override
    public int hitungTarif(int jam) {
        int tarif;
        if (jam > 12) {
            tarif = 15000;
        } else if (jam <= 1) {
            tarif = 2000;
        } else {
            tarif = 2000 + (jam - 1) * 1000;
        }
        return tarif;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Jam pertama : Rp2000");
        System.out.println("Jam berikut : Rp1000 per jam");
    }
}
