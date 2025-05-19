import java.util.Date;

public class Konkurrenceresultat {
    private String disciplin;
    private double tid; // Tid i sekunder (jo lavere, jo bedre)
    private Date dato;

    public Konkurrenceresultat(String disciplin, double tid, Date dato) {
        this.disciplin = disciplin;
        this.tid = tid;
        this.dato = dato;
    }

    public String getDisciplin() {
        return disciplin;
    }

    public double getTid() {
        return tid;
    }

    public Date getDato() {
        return dato;
    }

    @Override
    public String toString() {
        return String.format("Disciplin: %s, Tid: %.2f sekunder, Dato: %s", disciplin, tid, dato);
    }
}
