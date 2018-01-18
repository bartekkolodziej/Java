import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UnknownFormatConversionException;

public class Zad1 {


    public static void main(String[] args) throws IOException {
        int mezczyzni = 0;
        int kobiety = 0;
        List<String> kobietyC4 = new ArrayList<>();
        List<String> dwaTelefony = new ArrayList<>();
        CSVReader reader = new CSVReader("pracownicy-geo-agh.csv", ";",true);
        while (reader.next()) try {
            String imie = reader.get("imię");
            String nazwisko = reader.get("nazwisko");
            String pawilon = reader.get("pawilon");
            String telefon = reader.get("telefon + 617");

            if((imie.endsWith("a ") || imie.endsWith("a") )&& pawilon.equals("C4"))
                kobietyC4.add(imie + " " + nazwisko);

            if(!telefon.isEmpty() && telefon.matches("^.*[,].*$"))
                dwaTelefony.add(imie + " " + nazwisko);

            if (imie.endsWith("a"))
                kobiety++;
            else
                mezczyzni++;


        } catch (NumberFormatException | UnknownFormatConversionException e) {
            e.printStackTrace();
        }

    System.out.println("Liczba kobiet: " + kobiety + "\nLiczba mezczyzn: " + mezczyzni);
    System.out.println("\nKobiety pracujace w C4: ");
    kobietyC4.forEach(System.out::println);
    System.out.println("\nPracownicy majacy wiecej niz 2 numery: ");
    dwaTelefony.forEach(System.out::println);
    }


}
