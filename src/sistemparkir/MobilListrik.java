package sistemparkir;

public class MobilListrik extends Mobil {

    private boolean pakaiCharging;

    public MobilListrik(String platNomor, boolean member, boolean pakaiCharging) {
        super(platNomor, member);
        this.pakaiCharging = pakaiCharging;
    }

    public boolean isPakaiCharging() {
        return pakaiCharging;
    }

    @Override
    public String getJenis() {
        return "Mobil Listrik";
    }

    // tarif mobil biasa (termasuk flat) ditambah biaya charging
    @Override
    public int hitungTarif(int jam) {
        int tarif = super.hitungTarif(jam);
        if (pakaiCharging) {
            tarif = tarif + 10000;
        }
        return tarif;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        if (pakaiCharging) {
            System.out.println("Charging    : Ya (Rp10000)");
        } else {
            System.out.println("Charging    : Tidak");
        }
    }
}
