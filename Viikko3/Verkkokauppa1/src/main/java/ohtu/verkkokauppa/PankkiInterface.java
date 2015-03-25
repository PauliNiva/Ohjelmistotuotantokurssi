package ohtu.verkkokauppa;

/**
 * Created by Pauli Niva on 21/03/2015.
 */
public interface PankkiInterface {
    public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa);
}
