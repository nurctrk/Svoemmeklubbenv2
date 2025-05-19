import java.util.Scanner;
import java.util.Date;
import java.util.ArrayList;

public class Menu {
    private MedlemsListe medlemsListe;
    private KontingentBeregner kontingentBeregner;
    private Restance restance;
    private Traener traener;
    private Scanner scanner;

    public Menu(MedlemsListe medlemsListe) {
        this.medlemsListe = medlemsListe;
        this.kontingentBeregner = new KontingentBeregner(medlemsListe);
        this.restance = new Restance(medlemsListe);
        this.traener = new Traener(medlemsListe);
        this.scanner = new Scanner(System.in);
    }

    public void startMenu() {
        boolean runMenu = true;

        while (runMenu) {
            System.out.println("\nVælg en rolle:");
            System.out.println("1. Formand");
            System.out.println("2. Kasserer");
            System.out.println("3. Træner");
            System.out.println("4. Afslut");

            int rolleValg = scanner.nextInt();
            scanner.nextLine();

            switch (rolleValg) {
                case 1 -> formandMenu();
                case 2 -> kassererMenu();
                case 3 -> traenerMenu();
                case 4 -> {
                    runMenu = false;
                    System.out.println("Afslutter programmet...");
                }
                default -> System.out.println("Ugyldigt valg, prøv igen.");
            }
        }
    }

    private void formandMenu() {
        boolean runFormandMenu = true;

        while (runFormandMenu) {
            System.out.println("\nFormand Menu:");
            System.out.println("1. Se oversigt over svømmere");
            System.out.println("2. Beregn kontingent for et medlem");
            System.out.println("3. Beregn og vis total kontingent for alle medlemmer");
            System.out.println("4. Vis kontingentoversigt");
            System.out.println("5. Gem medlemsdata til fil");
            System.out.println("6. Tilbage til hovedmenu");

            int valg = scanner.nextInt();
            scanner.nextLine();

            switch (valg) {
                case 1 -> visOversigtOverSvoemmere();
                case 2 -> visKontingentForMedlem();
                case 3 -> beregnTotalKontingent();
                case 4 -> visKontingentOversigt();
                case 5 -> gemDataTilFil();
                case 6 -> runFormandMenu = false;
                default -> System.out.println("Ugyldigt valg, prøv igen.");
            }
        }
    }

    private void kassererMenu() {
        boolean runKassererMenu = true;

        while (runKassererMenu) {
            System.out.println("\nKasserer Menu:");
            System.out.println("1. Vis medlemmer i restance");
            System.out.println("2. Tilbage til hovedmenu");

            int valg = scanner.nextInt();
            scanner.nextLine();

            switch (valg) {
                case 1 -> visMedlemmerIRestance();
                case 2 -> runKassererMenu = false;
                default -> System.out.println("Ugyldigt valg, prøv igen.");
            }
        }
    }

    private void traenerMenu() {
        boolean runTraenerMenu = true;

        while (runTraenerMenu) {
            System.out.println("\nTræner Menu:");
            System.out.println("1. Vis top 5 for en disciplin og aldersgruppe");
            System.out.println("2. Tilføj konkurrenceresultat til en svømmer");
            System.out.println("3. Vis konkurrenceresultater for en svømmer");
            System.out.println("4. Tilbage til hovedmenu");

            int valg = scanner.nextInt();
            scanner.nextLine();

            switch (valg) {
                case 1 -> visTop5ForDisciplin();
                case 2 -> tilfoejKonkurrenceresultat();
                case 3 -> visKonkurrenceresultaterForMedlem();
                case 4 -> runTraenerMenu = false;
                default -> System.out.println("Ugyldigt valg, prøv igen.");
            }
        }
    }

    // === Funktioner ===

    private void visOversigtOverSvoemmere() {
        medlemsListe.udskrivAlleMedlemmer();
    }

    private void visKontingentForMedlem() {
        System.out.print("Indtast medlemnavn: ");
        String navn = scanner.nextLine();
        Medlemmer medlem = medlemsListe.findMedlem(navn);
        if (medlem != null) {
            double kontingent = kontingentBeregner.beregnKontingent(medlem);
            System.out.printf("Kontingent for %s: %.2f kr.%n", medlem.getNavn(), kontingent);
        } else {
            System.out.println("Medlem ikke fundet.");
        }
    }

    private void beregnTotalKontingent() {
        double total = kontingentBeregner.beregnTotalKontingent();
        System.out.printf("Total kontingentindtægt: %.2f kr.%n", total);
    }

    private void visKontingentOversigt() {
        kontingentBeregner.visKontingentOversigt();
    }

    private void visMedlemmerIRestance() {
        restance.visRestanceListe();
    }

    private void visTop5ForDisciplin() {
        System.out.print("Indtast disciplin: ");
        String disciplin = scanner.nextLine();
        System.out.print("Indtast aldersgruppe (Junior/Senior): ");
        String aldersgruppe = scanner.nextLine();
        traener.visTop5(disciplin, aldersgruppe);
    }

    private void tilfoejKonkurrenceresultat() {
        System.out.print("Indtast navn på medlem: ");
        String navn = scanner.nextLine();
        Medlemmer medlem = medlemsListe.findMedlem(navn);

        if (medlem != null) {
            System.out.print("Indtast disciplin: ");
            String disciplin = scanner.nextLine();

            System.out.print("Indtast tid i sekunder (fx 55.32): ");
            double tid = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Indtast dato (yyyy-mm-dd): ");
            String datoStr = scanner.nextLine();
            try {
                Date dato = java.sql.Date.valueOf(datoStr);
                Konkurrenceresultat resultat = new Konkurrenceresultat(disciplin, tid, dato);
                medlem.tilfoejKonkurrenceresultat(resultat);
                System.out.println("Resultat tilføjet.");
            } catch (IllegalArgumentException e) {
                System.out.println("Forkert datoformat. Brug yyyy-mm-dd.");
            }
        } else {
            System.out.println("Medlem ikke fundet.");
        }
    }

    private void visKonkurrenceresultaterForMedlem() {
        System.out.print("Indtast navn på medlem: ");
        String navn = scanner.nextLine();
        Medlemmer medlem = medlemsListe.findMedlem(navn);

        if (medlem != null) {
            ArrayList<Konkurrenceresultat> resultater = medlem.getKonkurrenceresultat();
            if (resultater.isEmpty()) {
                System.out.println("Ingen resultater.");
            } else {
                System.out.println("Resultater for " + medlem.getNavn() + ":");
                for (Konkurrenceresultat kr : resultater) {
                    System.out.println(kr);
                }
            }
        } else {
            System.out.println("Medlem ikke fundet.");
        }
    }

    private void gemDataTilFil() {
        System.out.print("Indtast filnavn (fx medlemsliste.csv): ");
        String filnavn = scanner.nextLine();
        medlemsListe.gemSomCSV(filnavn);
    }
}
