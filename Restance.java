import java.util.ArrayList;

public class Restance {

    private ArrayList<Medlemmer> MedlemmeriRestance;
    private MedlemsListe medlemsListe;
    private KontingentBeregner KontigentBeregner;

    public Restance(MedlemsListe medlemsListe) {
        this.medlemsListe = medlemsListe;
        this.MedlemmeriRestance = new ArrayList<>();
        this.KontigentBeregner = new KontingentBeregner(medlemsListe);


    }



    public void visRestanceListe() {
        System.out.println("BetalingsOversigt - Medlemmer i Restance");
        System.out.println("___________________________________________");
        System.out.printf("%-20s %-6s %-10s %-10s %-15s%n",
                "Navn", "Alder", "Type", "Aktivitet", "Skyldt beløb");
        System.out.println("__________________________________________");


        boolean RestanceFundet = false;


        for (Medlemmer Medlem : medlemsListe.getSvoemmere()) {
            if (erIRestance(Medlem)) {
                RestanceFundet = true;
                double SkyldtBeløb = KontigentBeregner.beregnKontingent(Medlem);
                System.out.printf(

                        Medlem.getNavn(),
                        Medlem.getAlder(),
                        Medlem.getMedlemstype(),
                        Medlem.getAktivitet(),
                        SkyldtBeløb);
                MedlemmeriRestance.add(Medlem);
            }
        }
        if (!RestanceFundet) {
            System.out.println("ingen medlemmer i Restence");

        }
        System.out.println("_________________________________");
    }
    private boolean erIRestance(Medlemmer medlem) {
        return medlem.getRestance().equalsIgnoreCase("er i Restence");

    }
    public ArrayList<Medlemmer> getMedlemmeriRestance(){
        return MedlemmeriRestance;
    }

}









