import java.io.File;

public class exercici1 {
    public static void main(String[] args) {

        String ruta = args[0];

        System.out.println("Tinc un paràmetre: " + ruta);

        File cami = new File(ruta);

        File[] files = cami.listFiles();
        String resultat = " ";

        for(File file : files){
            if (file.isDirectory()) {
                resultat = "Directori";
            } else {
                resultat = "Fitxer";
            }
            String p1 = file.isFile()? "-" : "d";
            p1 += file.canRead()? "r" : "-";
            p1 += file.canWrite()? "w" : "-";   
            p1 += file.canExecute()? "x" : "-";

            System.out.print(resultat + " :");
            System.out.print(p1 + " ");
            System.out.println(file.getName());
        }
        
    }
}