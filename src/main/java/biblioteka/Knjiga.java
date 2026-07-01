package biblioteka;

public class Knjiga {
    private String naslov;
    private String autor;
    private int brStrana;
    private int brPozajmica;
    private boolean dostupna;

    public Knjiga(String naslov, String autor, int brStrana){
        this.naslov=naslov;
        this.autor=autor;
        this.brStrana=brStrana;
        this.brPozajmica = 0;
        this.dostupna=true;
    }
    public boolean popularan(){
        return brPozajmica>500;
    }

    public void povecajBrPozajmica(){
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
    public boolean isDostupna() {
        return dostupna;
    }

    public void setDostupna(boolean dostupna) {
        this.dostupna = dostupna;
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
