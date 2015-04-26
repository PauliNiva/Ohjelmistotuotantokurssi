package com.mycompany.webkauppa.ohjaus;

import com.mycompany.webkauppa.sovelluslogiikka.Ostoskori;
import com.mycompany.webkauppa.sovelluslogiikka.Varasto;
import com.mycompany.webkauppa.ulkoiset_rajapinnat.PankkiFasaadi;
import com.mycompany.webkauppa.ulkoiset_rajapinnat.ToimitusjarjestelmaFasaadi;

public class Komentotehdas {

    public Komento ostoksenLisaysKoriin(Ostoskori ostoskori, long tuoteId, Varasto varasto) {
        return new OstoksenLisaysKoriin(ostoskori, tuoteId, varasto);
    }

    public Komento ostoksenPoistoKorista(Ostoskori ostoskori, long tuoteId, Varasto varasto) {
        return new OstoksenPoistoKorista(ostoskori, tuoteId, varasto);
    }

    public Komento ostoksenSuoritus(String nimi, String osoite, String luottokorttinumero, Ostoskori kori, PankkiFasaadi pankki, Varasto varasto, ToimitusjarjestelmaFasaadi toimitusjarjestelma) {
        return new OstoksenSuoritus(nimi, osoite, luottokorttinumero, kori, pankki, varasto, toimitusjarjestelma);
    }
}
