import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws IOException {

        String file = "text.txt";
        File f = new File(file);
        FileOutputStream fos = new FileOutputStream(f);
        Scanner in = new Scanner(System.in);

        String kword = "quit";
        String frase = "";

        System.out.println("Escriu una frase (escriu 'quit' per acabar):");
        while (true) {
            frase = in.nextLine();
            if (frase.equals(kword)) {
                break;
            }
            fos.write(frase.getBytes());
            fos.write("\n".getBytes());
        }
        fos.close();

        FileInputStream fis = new FileInputStream(f);
        int n = fis.read();
        while (n != -1) {
            System.out.print((char) n);
            n = fis.read();
        }
        fis.close();
    }
}