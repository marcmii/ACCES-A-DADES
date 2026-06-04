import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public class Main {
    // Constants generals del joc.
    static final int MIDA = 5;
    static final int TOTAL_VAIXELLS = 7;
    static final int REGISTRE = 5; // writeChar + writeChar + writeBoolean
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            // Llegim el titol i el nombre maxim de tirs del fitxer XML.
            Document xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("config.xml"));
            String titol = xml.getElementsByTagName("title").item(0).getTextContent();
            int maxTirs = Integer.parseInt(xml.getElementsByTagName("max-shots").item(0).getTextContent());

            // Primer l'usuari s'ha de registrar o iniciar sessio.
            System.out.println("=== " + titol + " ===");
            menuUsuari();

            // Despres pot comencar una partida nova o recuperar una partida anterior.
            System.out.print("J: Jugar\nR: Recuperar partida\nOpcio: ");
            if (sc.nextLine().trim().equalsIgnoreCase("R")) recuperar();
            else jugar(maxTirs);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void menuUsuari() throws Exception {
        // Repetim el menu fins que l'usuari inicia sessio correctament.
        while (true) {
            System.out.print("\n1: Registrar usuari\n2: Iniciar sessio\nOpcio: ");
            String opcio = sc.nextLine();
            if (opcio.equals("1")) registrar();
            else if (opcio.equals("2")) {
                if (login()) return;
            }
            else System.out.println("Opcio incorrecta");
        }
    }

    static void registrar() throws Exception {
        // Demanem les dades i les guardem en un fitxer d'objectes.
        System.out.print("Nom: ");
        String nom = sc.nextLine();
        System.out.print("Contrasenya: ");
        String pass = sc.nextLine();

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("user.dat"))) {
            out.writeObject(new Usuari(nom, pass));
        }
        System.out.println("Usuari guardat.");
    }

    static boolean login() throws Exception {
        // Si encara no hi ha usuari guardat, obliguem a registrar-ne un.
        if (!new File("user.dat").exists()) {
            System.out.println("Primer has de registrar un usuari.");
            registrar();
        }

        // Llegim l'usuari guardat del fitxer.
        Usuari u;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("user.dat"))) {
            u = (Usuari) in.readObject();
        }

        // Comprovem si el nom i la contrasenya coincideixen.
        System.out.print("Nom: ");
        String nom = sc.nextLine();
        System.out.print("Contrasenya: ");
        String pass = sc.nextLine();

        boolean ok = u.getNom().equals(nom) && u.getPass().equals(pass);
        if (!ok) System.out.println("Login incorrecte.");
        return ok;
    }

    static void jugar(int maxTirs) throws Exception {
        // Preparem els vaixells amagats i el taulell visible del jugador.
        boolean[][] vaixells = llegirVaixells();
        char[][] taulell = taulellNou();
        int tocats = 0;

        // Guardem cada tir per poder recuperar la partida despres.
        try (RandomAccessFile game = new RandomAccessFile("game.dat", "rw")) {
            game.setLength(0);
            for (int tir = 0; tir < maxTirs && tocats < TOTAL_VAIXELLS; tir++) {
                pintar(taulell);
                System.out.print("Jugada (ex: A2): ");
                String jugada = sc.nextLine().toUpperCase();

                // Convertim la jugada, per exemple A2, a posicions de l'array.
                int fila = jugada.charAt(0) - 'A';
                int col = jugada.charAt(1) - '1';

                // Marquem T si ha tocat un vaixell, o A si ha caigut a l'aigua.
                boolean tocat = vaixells[fila][col];
                taulell[fila][col] = tocat ? 'T' : 'A';
                if (tocat) tocats++;

                // Guardem la jugada al fitxer binari game.dat.
                game.seek((long) tir * REGISTRE);
                game.writeChar(jugada.charAt(0));
                game.writeChar(jugada.charAt(1));
                game.writeBoolean(tocat);
            }
        }

        pintar(taulell);
        System.out.println(tocats == TOTAL_VAIXELLS ? "Has guanyat!" : "Fi de la partida.");
    }

    static boolean[][] llegirVaixells() throws Exception {
        // Llegim el fitxer vaixells.txt i posem true on hi ha vaixell.
        boolean[][] vaixells = new boolean[MIDA][MIDA];
        try (BufferedReader br = new BufferedReader(new FileReader("vaixells.txt"))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                for (int i = 0; i < linia.length(); i += 2) {
                    vaixells[linia.charAt(i) - 'A'][linia.charAt(i + 1) - '1'] = true;
                }
            }
        }
        return vaixells;
    }

    static void recuperar() throws Exception {
        // Reconstruim el taulell llegint una a una les jugades guardades.
        char[][] taulell = taulellNou();
        try (RandomAccessFile game = new RandomAccessFile("game.dat", "r")) {
            for (int i = 0; i < game.length() / REGISTRE; i++) {
                System.out.print("Prem ENTER...");
                sc.nextLine();
                game.seek((long) i * REGISTRE);

                char filaChar = game.readChar();
                char colChar = game.readChar();
                // El boolean guardat diu si aquella jugada va tocar o no.
                taulell[filaChar - 'A'][colChar - '1'] = game.readBoolean() ? 'T' : 'A';

                System.out.println("Jugada: " + filaChar + colChar);
                pintar(taulell);
            }
        }
    }

    static char[][] taulellNou() {
        // Creem un taulell buit ple de guions.
        char[][] t = new char[MIDA][MIDA];
        for (char[] fila : t) Arrays.fill(fila, '-');
        return t;
    }

    static void pintar(char[][] t) {
        // Mostra el taulell amb files A-E i columnes 1-5.
        System.out.println("\n   1 2 3 4 5");
        for (int i = 0; i < MIDA; i++) {
            System.out.print((char) ('A' + i) + "  ");
            for (int j = 0; j < MIDA; j++) System.out.print(t[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
}
