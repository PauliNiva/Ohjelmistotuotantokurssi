package ohtu.kivipaperisakset;

public class Ihminen implements Pelaaja {

    private final IO io;
    private final String pelaajanTunnus;

    public Ihminen(IO ioOlio, String tunnus) {
        this.io = ioOlio;
        this.pelaajanTunnus = tunnus;
    }

    @Override
    public String annaSiirto() {
        io.tulosta("Pelaajan " + this.pelaajanTunnus + " siirto: ");
        return io.lue();
    }

    @Override
    public void asetaSiirto(String siirto) {}
}
