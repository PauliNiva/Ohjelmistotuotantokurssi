package ohtu.kivipaperisakset;

import java.util.*;

public class KPSPeli {

    protected IO io;
    protected Tuomari tuomari;
    protected Pelaaja pelaajaYksi;
    protected Pelaaja pelaajaKaksi;
    private List<String> laillisetSiirrot;

    public KPSPeli(IO ioOlio, Pelaaja ekaPelaaja, Pelaaja tokaPelaaja) {
        this.io = ioOlio;
        this.tuomari = new Tuomari();
        this.pelaajaYksi = ekaPelaaja;
        this.pelaajaKaksi = tokaPelaaja;

        luoLaillisetSiirrot();
    }

    private void luoLaillisetSiirrot() {
        laillisetSiirrot = new ArrayList<String>();
        laillisetSiirrot.add("s");
        laillisetSiirrot.add("k");
        laillisetSiirrot.add("p");
    }

    public void pelaa() {
        tulostaOhje();

        pelaaPeli();

        loppuTuloste();
    }

    private void pelaaPeli() {
        String ekaSiirto = this.pelaajaYksi.annaSiirto();
        if (onkoOkSiirto(ekaSiirto) == false) {
            return;
        }
        String tokaSiirto = this.pelaajaKaksi.annaSiirto();

        while (onkoOkSiirto(ekaSiirto) && onkoOkSiirto(tokaSiirto)) {
            tuomaroi(ekaSiirto, tokaSiirto);

            ekaSiirto = this.pelaajaYksi.annaSiirto();
            if (onkoOkSiirto(ekaSiirto) == false) {
                break;
            }
            tokaSiirto = this.pelaajaKaksi.annaSiirto();

            this.pelaajaKaksi.asetaSiirto(ekaSiirto);
        }
    }

    private boolean onkoOkSiirto(String siirto) {
        return laillisetSiirrot.contains(siirto);
    }

    private void loppuTuloste() {
        io.tulosta("\nKiitos!\n" + tuomari + "\n");
    }

    private void tulostaOhje() {
        io.tulosta("\nPeli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s\n\n");
    }

    private void tuomaroi(String eka, String toka) {
        tuomari.kirjaaSiirto(eka, toka);
        io.tulosta(tuomari + "\n\n");
    }
}
