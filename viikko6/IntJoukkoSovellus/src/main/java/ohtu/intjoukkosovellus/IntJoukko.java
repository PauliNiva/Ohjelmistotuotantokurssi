
package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {

    public final static int KAPASITEETTI = 5,
            OLETUSKASVATUS = 5;
    private int kasvatuskoko;
    private int[] lukujono;
    private int alkioidenLukumaara = 0;

    public IntJoukko() {
        this(KAPASITEETTI);
    }

    public IntJoukko(int kapasiteetti) {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }


    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IllegalArgumentException("Kapasiteetti ei voi olla negatiivinen");
        }
        if (kasvatuskoko <= 0) {
            throw new IllegalArgumentException("kasvatuskoon pitää olla positiivinen");
        }
        lukujono = new int[kapasiteetti];
        this.kasvatuskoko = kasvatuskoko;
    }

    private void lisaa(IntJoukko joukko) {
        for (int i = 0; i < joukko.mahtavuus(); i++) {
            lisaa(joukko.lukujono[i]);
        }
    }

    public boolean lisaa(int luku) {
        if (kuuluuJoukkoon(luku)) {
            return false;
        }
        varmistaTilaLukuJonossa();
        lukujono[alkioidenLukumaara] = luku;
        alkioidenLukumaara++;
        return true;
    }

    private void varmistaTilaLukuJonossa() {
        if (lukujono.length == alkioidenLukumaara) {
            lukujono = Arrays.copyOf(lukujono, lukujono.length + kasvatuskoko);
        }
    }

    public boolean kuuluuJoukkoon(int luku) {
        return haeLuvunPaikka(luku) >= 0;
    }

    private int haeLuvunPaikka(int luku) {
        for (int i = 0; i < alkioidenLukumaara; i++) {
            if (luku == lukujono[i]) {
                return i;
            }
        }
        return -1;
    }

    public boolean poista(int luku) {
        int luvunKohta = haeLuvunPaikka(luku);
        if (luvunKohta < 0) {
            return false;
        }
        poistaAlkioLukuJonosta(luvunKohta);
        return true;
    }

    private void poistaAlkioLukuJonosta(int alkionPaikka) {
        alkioidenLukumaara--;
        boolean alkioOnViimeinen = alkionPaikka == alkioidenLukumaara;
        if (alkioOnViimeinen) {
            return;
        }
        int[] uusiJono = new int[lukujono.length];
        System.arraycopy(lukujono, 0, uusiJono, 0, alkionPaikka);
        System.arraycopy(lukujono, alkionPaikka + 1, uusiJono, alkionPaikka, lukujono.length - alkionPaikka - 1);
        lukujono = uusiJono;
    }

    public int mahtavuus() {
        return alkioidenLukumaara;
    }

    @Override
    public String toString() {
        StringBuilder tulos = new StringBuilder();
        tulos.append('{');
        if (alkioidenLukumaara > 0) {
            kirjoitaAlkiot(tulos);
        }
        tulos.append('}');
        return tulos.toString();
    }

    private void kirjoitaAlkiot(StringBuilder tulos) {
        tulos.append(lukujono[0]);
        for (int i = 1; i < alkioidenLukumaara; i++) {
            tulos.append(',').append(' ').append(lukujono[i]);
        }
    }

    public int[] toIntArray() {
        return Arrays.copyOf(lukujono, alkioidenLukumaara);
    }

    public static IntJoukko yhdiste(IntJoukko ensimmainen, IntJoukko toinen) {
        IntJoukko yhdiste = new IntJoukko(ensimmainen.mahtavuus() + toinen.mahtavuus());
        yhdiste.lisaa(ensimmainen);
        yhdiste.lisaa(toinen);
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko ensimmainen, IntJoukko toinen) {
        return lisaaNeJotkaKuuluvatEhdolla(ensimmainen, toinen, true);
    }

    public static IntJoukko erotus(IntJoukko lahto, IntJoukko erotettava) {
        return lisaaNeJotkaKuuluvatEhdolla(lahto, erotettava, false);
    }

    private static IntJoukko lisaaNeJotkaKuuluvatEhdolla(IntJoukko ensimmainen, IntJoukko toinen, boolean kuuluuJoukkoon) {
        IntJoukko kuuluvat = new IntJoukko();
        for (int i = 0; i < ensimmainen.mahtavuus(); i++) {
            int arvo = ensimmainen.lukujono[i];
            if (toinen.kuuluuJoukkoon(arvo) == kuuluuJoukkoon) {
                kuuluvat.lisaa(arvo);
            }
        }
        return kuuluvat;
    }
}