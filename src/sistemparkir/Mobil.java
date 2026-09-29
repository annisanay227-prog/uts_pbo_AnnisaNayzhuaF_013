package sistemparkir;

public class Mobil extends Kendaraan {

    public Mobil(String platNomor) {
        super(platNomor);
    }

    public Mobil(String platNomor, boolean member) {
        super(platNomor, member);
    }

    @Override
    public String getJenis() {
        return "Mobil";
    }

    @Override
    public int hitungTarif(int jam) {
        int tarif;
        if (jam > 12) {
            tarif = 40000;
        } else if (jam <= 1) {
            tarif = 5000;
        } else {
            tarif = 5000 + (jam - 1) * 3000;
        }
        return tarif;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Jam pertama : Rp5000");
        System.out.println("Jam berikut : Rp3000 per jam");
    }
}
