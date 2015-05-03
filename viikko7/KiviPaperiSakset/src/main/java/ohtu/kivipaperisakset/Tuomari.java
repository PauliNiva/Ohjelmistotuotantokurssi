package ohtu.kivipaperisakset;

public class Tuomari {

    private int ekanPisteet;
    private int tokanPisteet;
    private int tasapelit;
    private final String kivi;
    private final String paperi;
    private final String sakset;

    public Tuomari() {
        this.ekanPisteet = 0;
        this.tokanPisteet = 0;
        this.tasapelit = 0;

        this.kivi = "k";
        this.paperi = "p";
        this.sakset = "s";
    }

    public void kirjaaSiirto(String ekanSiirto, String tokanSiirto) {
        if (tasapeli(ekanSiirto, tokanSiirto)) {
            tasapelit++;
        } else if (ekaVoittaa(ekanSiirto, tokanSiirto)) {
            ekanPisteet++;
        } else {
            tokanPisteet++;
        }
    }

    private boolean tasapeli(String eka, String toka) {
        return eka.equals(toka);
    }

    private boolean ekaVoittaa(String eka, String toka) {
        if (this.kivi.equals(eka) && this.sakset.equals(toka)) {
            return true;
        } else if (this.sakset.equals(eka) && this.paperi.equals(toka)) {
            return true;
        } else if (this.paperi.equals(eka) && this.kivi.equals(toka)) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Pelitilanne: " + ekanPisteet + " - " + tokanPisteet + "\n"
                + "Tasapelit: " + tasapelit;
    }
}
