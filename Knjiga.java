package biblioteka;
/*1. Knjiga
Svaka knjiga ima:
naslov, autora, broj strana, broj pozajmica
Ponašanje:
mora da zna da kaže da li je “popularna”
mora da može da poveća broj pozajmica*/

public class Knjiga {
    private String naslov;
    private String autor;
    private int brStrana;
    private int brPozajmica;

    public Knjiga(String naslov, String autor, int brStrana, int brPozajmica){
        this.naslov=naslov;
        this.autor=autor;
        this.brStrana=brStrana;
        if(brPozajmica <0){
            throw new IllegalArgumentException("Broj pozajmica ne moze biti negativan!");
        }this.brPozajmica = brPozajmica;
    }
    public boolean popularan(){
        return brPozajmica>500;
    }

    public void povecajBrPozajmica(){ // ovo mora da se resi? kako se povezuje sa pozajmicom i zna kad je
                                     // izdata -reseno tako sto Pozajmica ima polje Knjiga i time i pristup ovoj metodi,
                                   // pa kad se pozove pozajmica iz biblioteke onda preko nje moze i knjiga i njene metode i varijable;
        brPozajmica++;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getBrStrana() {
        return brStrana;
    }

    public void setBrStrana(int brStrana) {
        this.brStrana = brStrana;
    }

    public int getBrPozajmica() {
        return brPozajmica;
    }

    public void setBrPozajmica(int brPozajmica) {
        if(getBrPozajmica() <0){
            throw new IllegalArgumentException("Broj pozajmica ne moze biti negativan!");
        }else {
            this.brPozajmica = brPozajmica;
        }
    }
    @Override
    public String toString() {
        return "Knjiga{" +
                "naslov='" + naslov + '\'' +
                ", autor='" + autor + '\'' +
                ", brStrana=" + brStrana +
                ", brPozajmica=" + brPozajmica +
                '}';
    }
}
