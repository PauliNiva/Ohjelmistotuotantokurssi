package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KomentoriviKali implements IO {

    private final Scanner lukija;

    public KomentoriviKali() {
        this.lukija = new Scanner(System.in);
    }

    @Override
    public void tulosta(String tuloste) {
        System.out.print(tuloste);
    }

    @Override
    public String lue() {
        return this.lukija.nextLine();
    }
}
