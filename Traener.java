import java.util.ArrayList;
import java.util.Comparator;

public class Traener {
    private MedlemsListe medlemsListe;

    public Traener(MedlemsListe medlemsListe) {
        this.medlemsListe = medlemsListe;
    }


    public void visTop5(String disciplin, String aldersgruppe) {
        // Filtrer medlemmerne baseret på disciplin og aldersgruppe
        ArrayList<Medlemmer> kandidater = medlemsListe.findMedlemmerEfterAlderOgDisciplin(aldersgruppe, disciplin);

        // Sorter medlemmerne efter deres bedste tid i disciplinen
        kandidater.sort(Comparator.comparingDouble(m -> bedsteTid(m, disciplin)));

        // Vis top 5 (eller færre, hvis der er mindre end 5)
        System.out.println("Top 5 for disciplin: " + disciplin + ", aldersgruppe: " + aldersgruppe);
        System.out.println("------------------------------------------------------");

        int count = 0;
        for (Medlemmer m : kandidater) {
            double tid = bedsteTid(m, disciplin);
            if (tid < Double.MAX_VALUE) {  // Hvis medlemmet har en tid
                System.out.printf("%d. %s - Bedste tid: %.2f sek%n", ++count, m.getNavn(), tid);
                if (count == 5) break;  // Stop efter top 5
            }
        }

        // Hvis der ikke er nogen resultater
        if (count == 0) {
            System.out.println("Ingen resultater for denne disciplin og aldersgruppe.");
        }

        System.out.println("------------------------------------------------------");
    }

    // Find bedste tid for et medlem i en bestemt disciplin
    private double bedsteTid(Medlemmer medlem, String disciplin) {
        double bedste = Double.MAX_VALUE;  // Start med en høj tid

        // Gennemgå alle konkurrenceresultater for at finde den bedste tid
        for (Konkurrenceresultat r : medlem.getKonkurrenceresultat()) {
            if (r.getDisciplin().equalsIgnoreCase(disciplin) && r.getTid() < bedste) {
                bedste = r.getTid();
            }
        }

        return bedste;
    }
}
