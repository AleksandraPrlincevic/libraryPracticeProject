package biblioteka;
/*Svaka knjiga ima:
naslov, autora, broj strana,broj pozajmica
Ponašanje:
mora da zna da kaže da li je “popularna”
mora da može da poveća broj pozajmica
 2. Audio knjiga
Isto kao knjiga, ali ima:
trajanje (u minutima)
Ponašanje:
ima svoj kriterijum popularnosti (nije isti kao kod knjige)*/

public class AudioKnjiga extends Knjiga{

    private double trajanje;
    public AudioKnjiga(String naslov, String autor, int brStrana, int brPozajmica, double trajanje){
        super(naslov, autor, brStrana, brPozajmica);
        if(trajanje <=0){
            throw new IllegalArgumentException("Trajanje mora biti vece od nule!");
        }
        this.trajanje=trajanje;
    }
  @Override
  public boolean popularan(){
      return getBrPozajmica()>200;
  }

    public double getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(double trajanje) {
        if(trajanje <=0){
            throw new IllegalArgumentException("Trajanje mora biti vece od nule!");
        }
        this.trajanje = trajanje;
    }
    @Override
    public String toString() {
        return "AudioKnjiga{" +
                "trajanje=" + trajanje +
                '}';
    }
}
