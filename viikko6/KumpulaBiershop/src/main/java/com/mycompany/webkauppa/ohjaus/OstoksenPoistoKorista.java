package com.mycompany.webkauppa.ohjaus;

import com.mycompany.webkauppa.sovelluslogiikka.Ostoskori;
import com.mycompany.webkauppa.sovelluslogiikka.Tuote;
import com.mycompany.webkauppa.sovelluslogiikka.Varasto;

public class OstoksenPoistoKorista implements Komento {

    private Ostoskori ostoskori;
    private long tuoteId;
    private Varasto varasto;

    OstoksenPoistoKorista(Ostoskori ostoskori, long tuoteId, Varasto varasto) {
        this.ostoskori = ostoskori;
        this.tuoteId = tuoteId;
        this.varasto = varasto;
    }

    @Override
    public boolean suorita() {
        varasto.palautaVarastoon(tuoteId);
        Tuote poistettava = varasto.etsiTuote(tuoteId);
        ostoskori.poista(poistettava);

        return true;
    }
}
