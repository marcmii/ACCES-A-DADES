import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ex21 {
    public static void main(String[] args) throws FileNotFoundException {
        
        Scanner in = new Scanner(System.in);

        System.out.println("Introdueix el nom del fitxer:");
        String nomFitxer = in.nextLine();

        File fitxer = new File(nomFitxer);
        Scanner lector = new Scanner(fitxer);

        int comptador = 0;

        while (lector.hasNextLine()) {
            String linea = lector.nextLine();
            for (int i = 0; i < linea.length(); i++) {
                if (linea.charAt(i) == 'a' || linea.charAt(i) == 'A') {
                    comptador++;
                }
            }
        }

        lector.close();
        System.out.println("El fitxer " + nomFitxer + " té " + comptador + " lletres 'a'.");
        in.close();
    }
}
