import java.util.ArrayList;
import java.util.Date;

public class Medlemmer {
    private String navn;
    private int alder;
    private String email;
    private String telefonnummer;
    private String disciplin;
    private String medlemstype;  // f.eks. Junior / Senior
    private String aktivitet;    // Aktiv / Passiv
    private String restance;
    private boolean konkurrenceSvoemmer;
    private ArrayList<TræningsResultater> traeningsResultater = new ArrayList<>();
    private ArrayList<Konkurrenceresultat> konkurrenceresultater = new ArrayList<>();

    public Medlemmer(String navn, int alder, String email, String telefonnummer,
                     String disciplin, String medlemstype, String aktivitet, String restance) {
        this.navn = navn;
        this.alder = alder;
        this.email = email;
        this.telefonnummer = telefonnummer;
        this.disciplin = disciplin;
        this.medlemstype = medlemstype;
        this.aktivitet = aktivitet;
        this.restance = restance;
    }

    // Gettere
    public String getNavn() { return navn; }
    public int getAlder() { return alder; }
    public String getEmail() { return email; }
    public String getTelefonnummer() { return telefonnummer; }
    public String getDisciplin() { return disciplin; }
    public String getMedlemstype() { return medlemstype; }
    public String getAktivitet() { return aktivitet; }
    public String getRestance() { return restance; }

    // Settere
    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public void setRestance(String restance) {
        this.restance = restance;
    }

    public void setKonkurrenceSvoemmer(boolean konkurrenceSvoemmer) {
        this.konkurrenceSvoemmer = konkurrenceSvoemmer;
    }

    public boolean isKonkurrenceSvoemmer() {
        return konkurrenceSvoemmer;
    }

    public void tilfoejTraeningsResultat(TræningsResultater resultat) {
        traeningsResultater.add(resultat);
    }

    public void tilfoejKonkurrenceresultat(Konkurrenceresultat resultat) {
        konkurrenceresultater.add(resultat);
    }

    public ArrayList<TræningsResultater> getTraeningsResultater() {
        return traeningsResultater;
    }

    public ArrayList<Konkurrenceresultat> getKonkurrenceresultat() {
        return konkurrenceresultater;
    }

    @Override
    public String toString() {
        return String.format("Navn: %s, Alder: %d, Email: %s, Telefon: %s, Disciplin: %s, Type: %s, Aktivitet: %s, Restance: %s",
                navn, alder, email, telefonnummer, disciplin, medlemstype, aktivitet, restance);
    }
}