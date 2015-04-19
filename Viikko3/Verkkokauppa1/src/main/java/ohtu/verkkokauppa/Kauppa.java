package ohtu.verkkokauppa;

public class Kauppa {

    private Varasto varasto;
    private Pankki pankki;
    private Ostoskori ostoskori;
    private Viitegeneraattori viitegeneraattori;
    private String kaupanTili;

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

    public void sisakkaisiaIffeja(int id) {
        if (this.kaupanTili.Equals("3333-44455")) {
            if (id != 2) {
                if (id == 3) {
                    varasto.otaVarastosta(t);
                }
            }
        }
    }

    public void sisakkaisiaForeja(int d) {
        for (int i = 0; i < d; i++) {
            for (int j = 0; j <= 3; j++) {
                Tuote t = this.varasto.haeTuote(j);
            }
            ostoskori.lisaa(t);
        }
    }
}
