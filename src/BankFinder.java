import java.io.*;
import java.net.*;
import java.util.*;

public class BankFinder {

    public static void main(String[] args) {

        String urlString = "https://ewib.nbp.pl/plewibnra?dokNazwa=plewibnra.txt";


        try {

            URL url = new URL(urlString);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));


            Scanner scanner = new Scanner(System.in);
            System.out.print("Podaj trzy pierwsze cyfry numeru konta: ");
            String accountPrefix = scanner.nextLine();


            if (accountPrefix.length() != 3 || !accountPrefix.matches("\\d+")) {
                System.out.println("Niepoprawne dane. Proszę podać dokładnie 3 cyfry.");
                return;
            }


            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\\s+", 2);

                if (parts.length == 2) {
                    String bankCode = parts[0];
                    String bankName = parts[1];

                    if (bankCode.startsWith(accountPrefix)) {
                        System.out.println("Twoje konto należy do banku: " + bankName);
                        found = true;
                        break;
                    }
                }
            }

            if (!found) {
                System.out.println("Nie znaleziono banku dla podanych cyfr.");
            }

            reader.close();

        } catch (MalformedURLException e) {
            System.out.println("Błąd w adresie URL: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Błąd podczas wczytywania danych: " + e.getMessage());
        }
    }
}
