package ohtu.verkkokauppa;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class Kauppa {

    private Varasto varasto;
    private Pankki pankki;
    private Ostoskori ostoskori;
    private Viitegeneraattori viitegeneraattori;
    private String kaupanTili;

    @Autowired
    public Kauppa(VarastoInterface varastoInterface, PankkiInterface pankkiInterface, ViitegeneraattoriInterface viitegeneraattoriInterface) {
        this.varasto = (Varasto) varastoInterface;
        this.pankki = (Pankki) pankkiInterface;
        this.viitegeneraattori = (Viitegeneraattori) viitegeneraattoriInterface;
        kaupanTili = "33333-44455";
    }

    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        Tuote t = varasto.haeTuote(id); 
        varasto.palautaVarastoon(t);
    }

    public void lisaaKoriin(int id) {
        if (varasto.saldo(id)>0) {
            Tuote t = varasto.haeTuote(id);             
            ostoskori.lisaa(t);
            varasto.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattori.uusi();
        int summa = ostoskori.hinta();
        
        return pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

}
