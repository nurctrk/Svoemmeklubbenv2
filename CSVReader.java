import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    public void indlaesFraCSV(String filnavn, MedlemsListe medlemsListe) {
        try (BufferedReader br = new BufferedReader(new FileReader(filnavn))) {
            String linje;

            while ((linje = br.readLine()) != null) {
                String[] data = linje.split(",");

                if (data.length >= 8) {
                    String navn = data[0];
                    int alder = Integer.parseInt(data[1]);
                    String email = data[2];
                    String telefon = data[3];
                    String disciplin = data[4];
                    String medlemstype = data[5];
                    String aktivitet = data[6];
                    String restance = data[7];

                    Medlemmer medlem = new Medlemmer(navn, alder, email, telefon, disciplin, medlemstype, aktivitet, restance);
                    medlemsListe.tilfoejMedlem(medlem);
                }
            }

            System.out.println("CSV-fil indlæst korrekt!");
        } catch (IOException e) {
            System.out.println("Fejl under læsning af CSV-fil: " + e.getMessage());
        }
    }
}
