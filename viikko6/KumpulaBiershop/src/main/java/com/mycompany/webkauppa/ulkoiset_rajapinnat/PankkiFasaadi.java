package com.mycompany.webkauppa.ulkoiset_rajapinnat;

import java.util.ArrayList;
import java.util.Date;

public class PankkiFasaadi {

    public PankkiFasaadi() {
        maksut = new ArrayList<String>();
    }

    private ArrayList<String> maksut;

    public boolean maksa(String nimi, String luottokortti, int hinta) {
        if (luottokortti.length() < 2) {
            return false;
        }

        maksut.add(new Date() + " " + nimi + " " + luottokortti + " " + hinta);

        return true;
    }

    public ArrayList<String> maksut() {
        return maksut;
    }
}
