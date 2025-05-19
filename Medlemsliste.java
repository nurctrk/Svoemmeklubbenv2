import java.util.ArrayList;
import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;


class MedlemsListe {
    private ArrayList<Medlemmer> svoemmere;

    public MedlemsListe() {
        this.svoemmere = new ArrayList<>();

        // Opret medlemsobjekter
        Medlemmer m1 = new Medlemmer("Mads Jensen", 16, "madsjensen@mail.dk", "123456789", "Crawl", "Junior", "Aktiv", "er i restance");
        m1.tilfoejKonkurrenceresultat(new Konkurrenceresultat("Crawl", 55.12, new Date()));

        Medlemmer m2 = new Medlemmer("Emma Sørensen", 17, "emma@mail.dk", "123456780", "Rygcrawl", "Junior", "Aktiv", "ej i restance");
        m2.tilfoejKonkurrenceresultat(new Konkurrenceresultat("Rygcrawl", 60.10, new Date()));

        Medlemmer m3 = new Medlemmer("Anton Larsen", 18, "anton@mail.dk", "123456781", "Butterfly", "Senior", "Aktiv", "ej i restance");
        m3.tilfoejKonkurrenceresultat(new Konkurrenceresultat("Butterfly", 52.34, new Date()));

        Medlemmer m4 = new Medlemmer("Laura Mikkelsen", 20, "laura@mail.dk", "123456782", "Crawl", "Senior", "Aktiv", "ej i restance");
        m4.tilfoejKonkurrenceresultat(new Konkurrenceresultat("Crawl", 58.00, new Date()));

        Medlemmer m5 = new Medlemmer("Tobias Andersen", 14, "tobias@mail.dk", "123456783", "Brystsvømning", "Junior", "Aktiv", "ej i restance");
        m5.tilfoejKonkurrenceresultat(new Konkurrenceresultat("Brystsvømning", 70.00, new Date()));

        Medlemmer m6 = new Medlemmer("Freja Holm", 21, "freja@mail.dk", "123456784", "Butterfly", "Senior", "Aktiv", "er i restance");
        m6.tilfoejKonkurrenceresultat(new Konkurrenceresultat("Butterfly", 60.50, new Date()));

        Medlemmer m7 = new Medlemmer("Victor Kruse", 12, "victor@mail.dk", "123456785", "Crawl", "Junior", "Aktiv", "ej i restance");
        m7.tilfoejKonkurrenceresultat(new Konkurrenceresultat("Crawl", 56.10, new Date()));

        Medlemmer m8 = new Medlemmer("Sara Thomsen", 19, "sara@mail.dk", "123456786", "Rygcrawl", "Senior", "Aktiv", "er i restance");
        m8.tilfoejKonkurrenceresultat(new Konkurrenceresultat("Rygcrawl", 62.20, new Date()));

        Medlemmer m9 = new Medlemmer("Malthe Ravn", 13, "malthe@mail.dk", "123456787", "Butterfly", "Junior", "Aktiv", "ej i restance");
        m9.tilfoejKonkurrenceresultat(new Konkurrenceresultat("Butterfly", 65.40, new Date()));

        Medlemmer m10 = new Medlemmer("Louise Bækgaard", 17, "louise@mail.dk", "123456788", "Crawl", "Junior", "Aktiv", "er i restance");
        m10.tilfoejKonkurrenceresultat(new Konkurrenceresultat("Crawl", 59.00, new Date()));

        // Tilføj alle medlemmer til medlemslisten
        svoemmere.add(m1);
        svoemmere.add(m2);
        svoemmere.add(m3);
        svoemmere.add(m4);
        svoemmere.add(m5);
        svoemmere.add(m6);
        svoemmere.add(m7);
        svoemmere.add(m8);
        svoemmere.add(m9);
        svoemmere.add(m10);
    }

    public ArrayList<Medlemmer> getSvoemmere() {
        return svoemmere;
    }

    public void tilfoejMedlem(Medlemmer medlem) {
        svoemmere.add(medlem);
    }

    public ArrayList<Medlemmer> getKonkurrenceSvoemmere() {
        ArrayList<Medlemmer> konkurrenceSvoemmere = new ArrayList<>();
        for (Medlemmer m : svoemmere) {
            if (m.isKonkurrenceSvoemmer()) {
                konkurrenceSvoemmere.add(m);
            }
        }
        return konkurrenceSvoemmere;
    }

    public Medlemmer findMedlem(String navn) {
        for (Medlemmer medlem : svoemmere) {
            if (medlem.getNavn().equalsIgnoreCase(navn)) {
                return medlem;
            }
        }
        return null;  // Hvis ingen blev fundet
    }

    public ArrayList<Medlemmer> findMedlemmerEfterAlderOgDisciplin(String aldersgruppe, String disciplin) {
        ArrayList<Medlemmer> resultat = new ArrayList<>();
        for (Medlemmer m : svoemmere) {
            if (m.isKonkurrenceSvoemmer() &&
                    m.getMedlemstype().equalsIgnoreCase(aldersgruppe) &&
                    m.getDisciplin().equalsIgnoreCase(disciplin)) {
                resultat.add(m);
            }
        }
        return resultat;
    }

    public void udskrivAlleMedlemmer() {
        System.out.println("Medlemsliste:");
        System.out.println("---------------------------------------------------------");
        System.out.printf("%-25s %-6s %-15s %-20s %-12s %-10s %-10s%n",
                "Navn", "Alder", "Email", "Telefonnummer", "Disciplin", "Type", "Aktivitet");
        System.out.println("---------------------------------------------------------");
        for (Medlemmer medlem : svoemmere) {
            System.out.printf("%-25s %-6d %-15s %-20s %-12s %-10s %-10s%n",
                    medlem.getNavn(),
                    medlem.getAlder(),
                    medlem.getEmail(),
                    medlem.getTelefonnummer(),
                    medlem.getDisciplin(),
                    medlem.getMedlemstype(),
                    medlem.getAktivitet());
        }

    }
    public void gemSomCSV(String filnavn) {
        try (FileWriter writer = new FileWriter(filnavn)) {
            writer.write("Navn,Alder,Email,Telefon,Disciplin,Type,Aktivitet,Restance\n");
            for (Medlemmer medlem : svoemmere) {
                writer.write(medlem.getNavn() + "," +
                        medlem.getAlder() + "," +
                        medlem.getEmail() + "," +
                        medlem.getTelefonnummer() + "," +
                        medlem.getDisciplin() + "," +
                        medlem.getMedlemstype() + "," +
                        medlem.getAktivitet() + "," +
                        medlem.getRestance() + "\n");
            }
            System.out.println("Medlemsdata gemt til fil: " + filnavn);
        } catch (IOException e) {
            System.out.println("Fejl ved skrivning til fil: " + e.getMessage());
        }
    }
}