import java.util.Date;

public class TræningsResultater {
    private String disciplin;
    private double tid; // sekunder
    private Date dato;

    public TræningsResultater(String disciplin, double tid, Date dato) {
        this.disciplin = disciplin;
        this.tid = tid;
        this.dato = dato;
    }

    public String getDisciplin() { return disciplin; }
    public double getTid() { return tid; }
    public Date getDato() { return dato; }
    
}
