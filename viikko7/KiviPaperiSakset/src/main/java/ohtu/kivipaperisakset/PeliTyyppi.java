package ohtu.kivipaperisakset;

public class PeliTyyppi {

    private IO io;

    public PeliTyyppi(IO io) {
        this.io = io;
    }

    public KPSPeli kaksinpeli(String ekaNimi, String tokaNimi) {
        return new KPSPeli(this.io, new Ihminen(this.io, ekaNimi), new Ihminen(this.io, tokaNimi));
    }

    public KPSPeli yksinpeliHelppo(String pelaajanNimi) {
        return new KPSPeli(this.io, new Ihminen(this.io, pelaajanNimi), new Tekoaly());
    }

    public KPSPeli yksinpeliVaikeampi(String pelaajanNimi) {
        return new KPSPeli(this.io, new Ihminen(this.io, pelaajanNimi), new TekoalyParannettu(20));
    }
}
