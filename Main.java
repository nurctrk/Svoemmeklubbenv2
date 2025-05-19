public class Main {
    public static void main(String[] args) {
        try {
            // Opret tom medlemsliste
            MedlemsListe medlemsListe = new MedlemsListe();

            // Indlæs medlemmer fra CSV-fil (hvis filen findes)
            CSVReader reader = new CSVReader();
            reader.indlaesFraCSV("medlemsliste.csv", medlemsListe);

            // Alternativt: Tilføj hardcodede test-medlemmer (hvis CSV ikke bruges)
            // medlemsListe.opretStandardMedlemmer();

            // Start menuen
            Menu menu = new Menu(medlemsListe);
            menu.startMenu();

        } catch (Exception e) {
            System.out.println("Der opstod en fejl: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
