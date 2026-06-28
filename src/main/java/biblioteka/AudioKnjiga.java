package biblioteka;

public class AudioKnjiga extends Knjiga{

    private double trajanje;
    public AudioKnjiga(String naslov, String autor, int brStrana, double trajanje){
        super(naslov, autor, brStrana);
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
