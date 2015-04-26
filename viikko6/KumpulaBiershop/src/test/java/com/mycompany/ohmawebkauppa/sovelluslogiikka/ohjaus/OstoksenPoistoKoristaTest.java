package com.mycompany.ohmawebkauppa.sovelluslogiikka.ohjaus;

import com.mycompany.webkauppa.model.tietokantayhteydet.TuoteDAOInMemory;
import com.mycompany.webkauppa.ohjaus.Komentotehdas;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.mycompany.webkauppa.sovelluslogiikka.*;

public class OstoksenPoistoKoristaTest {

    Varasto varasto = new Varasto(new TuoteDAOInMemory());
    Ostoskori kori;
    long tuoteid = 1;
    Komentotehdas komennot;

    @Before
    public void setUp() {
        Tuote tuote = varasto.etsiTuote(tuoteid);
        kori = new Ostoskori();
        kori.lisaaTuote(tuote);
    }

    @Test
    public void poistettuTuoteEiEnaaKorissa() {
        komennot = new Komentotehdas();

        komennot.ostoksenPoistoKorista(kori, tuoteid, varasto).suorita();

        assertEquals(0, kori.tuotteitaKorissa());
        assertEquals(0, kori.hinta());
        assertEquals(0, kori.ostokset().size());
    }

    @Test
    public void tuotteenMaaraKasvaa() {
        int varastossaAluksi = varasto.etsiTuote(tuoteid).getSaldo();
        komennot = new Komentotehdas();

        komennot.ostoksenPoistoKorista(kori, tuoteid, varasto).suorita();

        assertEquals(varastossaAluksi + 1, varasto.etsiTuote(tuoteid).getSaldo());
    }

}
