public class KontingentBeregner {
    private MedlemsListe medlemsListe;

    public KontingentBeregner(MedlemsListe medlemsListe) {
        this.medlemsListe = medlemsListe;
    }

    // Beregn kontingent for et enkelt medlem
    public double beregnKontingent(Medlemmer medlem) {
        String aktivitetsform = medlem.getAktivitet();
        int alder = medlem.getAlder();

        if (aktivitetsform.equalsIgnoreCase("Passiv")) {
            return 500; // Fast pris for passive medlemmer
        } else {
            // Aktive medlemmer
            if (alder < 18) {
                return 1000; // Junior
            } else if (alder >= 60) {
                return 1600 * 0.75; // Senior med rabat (25% rabat)
            } else {
                return 1600; // Senior
            }
        }
    }

    // Beregn totalt kontingent for alle medlemmer
    public double beregnTotalKontingent() {
        double total = 0;
        for (Medlemmer medlem : medlemsListe.getSvoemmere()) {
            total += beregnKontingent(medlem);
        }
        return total;
    }

    // Vis kontingentoversigt for alle medlemmer
    public void visKontingentOversigt() {
        System.out.println("Kontingentoversigt:");
        System.out.println("----------------------------------------");
        System.out.printf("%-20s %-6s %-10s %-10s%n", "Navn", "Alder", "Type", "Kontingent");
        System.out.println("----------------------------------------");

        for (Medlemmer medlem : medlemsListe.getSvoemmere()) {
            double kontingent = beregnKontingent(medlem);
            System.out.printf("%-20s %-6d %-10s %-10.2f%n",
                    medlem.getNavn(),
                    medlem.getAlder(),
                    medlem.getMedlemstype(),
                    kontingent);
        }

        System.out.println("----------------------------------------");
        System.out.printf("Total kontingentindtægt: %.2f kr.%n", beregnTotalKontingent());
    }
}


