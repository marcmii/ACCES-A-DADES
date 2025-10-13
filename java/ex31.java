import java.io.*;
import java.util.Random;

public class ex31 {
    public static void main(String[] args) {
        String filename = "secret.bin";
        Random rand = new Random();

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename))) {
            int code = rand.nextInt(3) + 1; 

            for (int i = 0; i < 10; i++) {
                
                StringBuilder secret = new StringBuilder();
                for (int j = 0; j < 3; j++) {
                    char c = (char) ('a' + rand.nextInt(26));
                    secret.append(c);
                }

                
                dos.writeInt(code);
                dos.writeChars(secret.toString());

                
                System.out.println(code + ":" + secret);

                
                code += rand.nextInt(3) + 1; 
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
