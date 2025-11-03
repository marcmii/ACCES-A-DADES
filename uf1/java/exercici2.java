import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class exercici2 {

    public static void main(String args[]) throws IOException {

        String file = "text.txt";

        File f = new File(file);

        FileOutputStream fos = new FileOutputStream(f);

        fos.write("Hola ESRIMATS ALUMNES".getBytes());
        fos.write("\n".getBytes());

        String t = "Som a classe";

        for (char c: t.toCharArray()){
            fos.write(c);
        }

        fos.close();

        FileInputStream fis = new FileInputStream(f);

        int n = fis.read();
        while (n != -1){
            System.out.println((char)n);
            n = fis.read();
        }
        fis.close();

    }
}