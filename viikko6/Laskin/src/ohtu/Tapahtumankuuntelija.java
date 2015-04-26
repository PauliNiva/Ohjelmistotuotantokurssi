package ohtu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JTextField;

public final class Tapahtumankuuntelija implements ActionListener {

    private final JButton nollaa;
    private final JButton undo;
    private final Sovelluslogiikka sovellus;
    private final Map<Object, Komento> komennot;
    private Komento edellinen;

    public Tapahtumankuuntelija(JButton plus, JButton miinus, JButton nollaa, JButton undo, JTextField tuloskentta, JTextField syotekentta) {
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
        komennot = new HashMap<>();
        Laskutoimitus summa = l -> sovellus.plus(l);
        Laskutoimitus erotus = l -> sovellus.miinus(l);
        komennot.put(plus, new Laskunsuorittaja(sovellus, tuloskentta, syotekentta, summa));
        komennot.put(miinus, new Laskunsuorittaja(sovellus, tuloskentta, syotekentta, erotus));
        komennot.put(nollaa, new Nollaa(sovellus, tuloskentta, syotekentta));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Komento komento = komennot.get(ae.getSource());
        if (komento != null) {
            komento.suorita();
            edellinen = komento;
        } else {
            // toiminto oli undo
            edellinen.peru();
            edellinen = null;
        }

        nollaa.setEnabled(sovellus.tulos() != 0);
        undo.setEnabled(edellinen != null);
    }
}
