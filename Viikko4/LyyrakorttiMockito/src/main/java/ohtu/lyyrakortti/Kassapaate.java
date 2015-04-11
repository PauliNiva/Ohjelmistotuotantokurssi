package ohtu.lyyrakortti;

public class Kassapaate {
    private int myytyjaLounaita;
    public static final int HINTA = 5;

    public Kassapaate() {
        this.myytyjaLounaita = 0;
    }
    
    public void lataa(Lyyrakortti kortti, int summa){
        if (summa > 0) {
            kortti.lataa(summa);
        } else {

        }
    }
    
    public void ostaLounas(Lyyrakortti kortti) {
        if (kortti.getSaldo() >= HINTA) {
            kortti.osta(HINTA);
            myytyjaLounaita++;
        } else {

        }
    }

    public int getMyytyjaLounaita() {
        return myytyjaLounaita;
    }
}
