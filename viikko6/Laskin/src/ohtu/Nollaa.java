package ohtu;

import javax.swing.JTextField;

public class Nollaa extends Sovelluskomento {

    public Nollaa(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        super(sovellus, tuloskentta, syotekentta);
    }

    @Override
    protected void suoritaEnnenPaivitysta() {
        sovellus.nollaa();
    }
}