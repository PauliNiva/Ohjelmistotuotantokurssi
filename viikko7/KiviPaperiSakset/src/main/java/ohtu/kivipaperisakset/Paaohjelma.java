package ohtu.kivipaperisakset;

import java.util.*;

public class Paaohjelma {

    private static final IO io = new KomentoriviKali();
    private static Map<String, KPSPeli> valinnat;
    private static PeliTyyppi pelityyppi;

    public static void main(String[] args) {

        pelityyppi = new PeliTyyppi(io);
        luoValinnat();

        while (true) {
            String vastaus = paavalikko();

            if (valinnat.containsKey(vastaus)) {
                KPSPeli peli = valinnat.get(vastaus);
                peli.pelaa();
            } else {
                break;
            }
        }
    }

    private static String paavalikko() {
        io.tulosta("\nValitse pelataanko"
                + "\n (a) ihmistä vastaan "
                + "\n (b) tekoälyä vastaan"
                + "\n (c) parannettua tekoälyä vastaan"
                + "\nmuilla valinnoilla lopetataan\n");

        return io.lue();
    }

    private static void luoValinnat() {
        valinnat = new HashMap<String, KPSPeli>();

        valinnat.put("a", pelityyppi.kaksinpeli("eka", "toka"));
        valinnat.put("b", pelityyppi.yksinpeliHelppo("eka"));
        valinnat.put("c", pelityyppi.yksinpeliVaikeampi("eka"));
    }
}
