import java.io.*;
import java.util.Scanner;

public class ex22 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        String userInput = "";
        String file = "usertext.txt";
        File f = new File(file);

        do {
            System.out.print("Entra una cadena: ");
            userInput = in.nextLine();

            if (userInput.equals("quit")) {
                break;
            }

            FileOutputStream fos = new FileOutputStream(f, true);
            fos.write(userInput.getBytes());
            fos.write("\n".getBytes());
            fos.close();

        } while (!userInput.equals("quit"));

    
        FileInputStream fis = new FileInputStream(f);
        int n = fis.read();
        while (n != -1) {
            System.out.print((char) n);
            n = fis.read();
        }
        fis.close();
    }
}