package com.mycompany.ohmawebkauppa.sovelluslogiikka.ohjaus;

import com.mycompany.webkauppa.model.tietokantayhteydet.TuoteDAOInMemory;
import com.mycompany.webkauppa.ohjaus.Komento;
import com.mycompany.webkauppa.ohjaus.Komentotehdas;
import com.mycompany.webkauppa.ohjaus.OstoksenSuoritus;
import com.mycompany.webkauppa.sovelluslogiikka.*;
import com.mycompany.webkauppa.ulkoiset_rajapinnat.*;
import java.lang.reflect.Field;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OstoksenSuoritusTest {

    PankkiFasaadi pankki = new PankkiFasaadi();
    PankkiFasaadi hylkaavaPankki = teeHylkaavaPankki();
    ToimitusjarjestelmaFasaadi toimitusJarjestelma = new ToimitusjarjestelmaFasaadi();
    Varasto varasto = new Varasto(new TuoteDAOInMemory());
    Komentotehdas komennot;

    long tuoteId1;
    long tuoteId2;
    Tuote tuote1;
    Tuote tuote2;
    Ostoskori kori;
    String nimi;
    String osoite;
    String luottokortti;

    OstoksenSuoritus ostoksenSuoritus;

    @Before
    public void setUp() {
        nimi = "Arto Vihavainen";
        osoite = "Herttoniemenranta 10 Helsinki";
        luottokortti = "12345";

        tuoteId1 = 1;
        tuote1 = varasto.etsiTuote(tuoteId1);
        tuoteId2 = 2;
        tuote2 = varasto.etsiTuote(tuoteId2);

        kori = new Ostoskori();
        kori.lisaaTuote(tuote1);
        kori.lisaaTuote(tuote2);
    }

    @Test
    public void josMaksuOnnistuuKoriTyhjenee() {
        komennot = new Komentotehdas();

        komennot.ostoksenSuoritus(nimi, osoite, luottokortti, kori, pankki, varasto, toimitusJarjestelma).suorita();

        assertEquals(0, kori.ostokset().size());
        assertEquals(0, kori.hinta());
        assertEquals(0, kori.tuotteitaKorissa());
    }

    @Test
    public void josMaksuOnnistuuPankinRajapintaaKaytetty() {
        komennot = new Komentotehdas();

        komennot.ostoksenSuoritus(nimi, osoite, luottokortti, kori, pankki, varasto, toimitusJarjestelma).suorita();
    }

    @Test
    public void josMaksuOnnistuuToiRajmituksenapintaaKaytetty() {
        komennot = new Komentotehdas();

        komennot.ostoksenSuoritus(nimi, osoite, luottokortti, kori, pankki, varasto, toimitusJarjestelma).suorita();
    }

    // - tyhjä kori, nimi tai osoite -> ei kutsuta pankkia, ei toimitusta
    @Test
    public void josPankkiEiHyvaksyMaksuaPalautetaanFalseToimitustaEiTehda() {
        komennot = new Komentotehdas();

        Komento komento = komennot.ostoksenSuoritus(nimi, osoite, luottokortti, kori, pankki, varasto, toimitusJarjestelma);

        // Muuta pankki kenttä testissä käytettävään hylkäävään pankkiin
        Class ostoksensuoritus = OstoksenSuoritus.class;
        try {
            Field pankkiKentta = ostoksensuoritus.getDeclaredField("pankki");
            pankkiKentta.setAccessible(true);
            pankkiKentta.set(komento, hylkaavaPankki);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        assertFalse(komento.suorita());

        // assertSomething
    }

    // epäonnistuessa kori säilyy ennallaan
    private PankkiFasaadi teeHylkaavaPankki() {
        return new PankkiFasaadi() {

            @Override
            public boolean maksa(String nimi, String luottokortti, int hinta) {
                return false;
            }

        };
    }
}
