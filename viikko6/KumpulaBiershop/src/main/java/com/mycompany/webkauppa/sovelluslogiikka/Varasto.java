package com.mycompany.webkauppa.sovelluslogiikka;

import com.mycompany.webkauppa.model.tietokantayhteydet.TuoteDAO;
import java.util.*;

public class Varasto {

    private TuoteDAO tuoteDAO;

    private List<Tuote> tuotteet;

    public Varasto(TuoteDAO tuoteDao) {
        tuoteDAO = tuoteDao;
        tuotteet = tuoteDAO.findAll();
    }

    public List<Tuote> tuotteidenLista() {
        return tuotteet;
    }

    public Tuote etsiTuote(long id) {
        for (Tuote tuote : tuotteet) {
            if (tuote.getId() == id) {
                return tuote;
            }
        }

        return null;
    }

    public boolean otaVarastosta(long id) {
        Tuote tuote = etsiTuote(id);
        if (tuote.getSaldo() == 0) {
            return false;
        }
        tuote.setSaldo(tuote.getSaldo() - 1);

        return true;
    }

    public void palautaVarastoon(long id) {
        Tuote tuote = etsiTuote(id);
        tuote.setSaldo(tuote.getSaldo() + 1);
        tuoteDAO.save(tuote);
    }

    public void palautaVarastoon(Ostos ostos) {
        Tuote tuote = etsiTuote(ostos.tuotteenId());
        tuote.setSaldo(tuote.getSaldo() + ostos.lukumaara());
        tuoteDAO.save(tuote);
    }

    public void paivitaTuotteenTiedot(long tuoteId, int uusiHinta, int uusiSaldo) {
        Tuote tuote = etsiTuote(tuoteId);
        tuote.setSaldo(uusiSaldo);
        tuote.setHinta(uusiHinta);
        tuoteDAO.save(tuote);
    }

    public boolean lisaaTuote(String nimi, int hinta, int saldo) {
        for (Tuote tuote : tuotteet) {
            if (tuote.getNimi().equals(nimi)) {
                return false;
            }
        }

        Tuote tuote = new Tuote(nimi, hinta);
        tuote.setSaldo(saldo);
        tuoteDAO.save(tuote);
        tuotteet.add(tuote);
        return true;
    }
}
