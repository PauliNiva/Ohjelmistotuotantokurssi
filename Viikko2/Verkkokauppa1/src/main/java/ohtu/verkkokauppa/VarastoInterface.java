package main.java.ohtu.verkkokauppa;

/**
 * Created by Pauli Niva on 21/03/2015.
 */
public interface VarastoInterface {
    Tuote haeTuote(int id);

    int saldo(int id);

    void otaVarastosta(Tuote t);

    void palautaVarastoon(Tuote t);
}
