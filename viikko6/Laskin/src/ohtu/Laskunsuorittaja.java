package ohtu;

import javax.swing.JTextField;

public class Laskunsuorittaja extends Sovelluskomento {

    private final Laskutoimitus toimitus;

    public Laskunsuorittaja(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta, Laskutoimitus toimitus) {
        super(sovellus, tuloskentta, syotekentta);
        this.toimitus = toimitus;
    }

    @Override
    protected void suoritaEnnenPaivitysta() {
        int arvo = 0;
        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }
        toimitus.laske(arvo);
    }
}