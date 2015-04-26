package com.mycompany.webkauppa.model.tietokantayhteydet;

import com.mycompany.webkauppa.sovelluslogiikka.Tuote;
import java.util.List;

public abstract class TuoteDAO {

    public abstract List<Tuote> findAll();

    public abstract void save(Tuote tuote);

}
