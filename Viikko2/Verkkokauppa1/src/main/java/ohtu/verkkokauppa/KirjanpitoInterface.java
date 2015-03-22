package ohtu.verkkokauppa;

import java.util.ArrayList;

/**
 * Created by Pauli Niva on 22/03/2015.
 */
public interface KirjanpitoInterface {
    void lisaaTapahtuma(String tapahtuma);

    ArrayList<String> getTapahtumat();
}
