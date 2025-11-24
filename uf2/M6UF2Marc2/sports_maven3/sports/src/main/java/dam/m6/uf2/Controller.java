package dam.m6.uf2;

import java.sql.Connection;
import java.util.List;
// pujat a github
public class Controller {

    Connection conn;

    public static void main(String[] args) {
        System.out.println("Current directory (poseu aquí el XML): " + System.getProperty("user.dir"));
        Controller controller = new Controller();
        controller.init();
    }

    private void init() {

        this.conn = ConnectionManager.getConnection("database.xml");
        MainView view = new MainView();

        DeportesPgDAO deportesDAO = new DeportesPgDAO(conn);
        DeportistasPgDAO deportistasDAO = new DeportistasPgDAO(conn);

       
        int option = view.mainMenu();

        switch (option) {

            case 1:
                // Llistar nous esprots
                List<Deportes> llistaEsports = deportesDAO.getAll();
                view.LlistaEsports(llistaEsports);
                break;

            case 2:
                // Afegir un nou esport
                Deportes nouEsport = view.EsportForm();
                deportesDAO.add(nouEsport);
                break;

            case 3:
                // Afegir un nou atleta
                Deportistas nouAtleta = view.AtletaForm(deportesDAO.getAll());
                deportistasDAO.add(nouAtleta);
                break;

            case 4:
                // Cercar el atleta per nom
                String nom = view.DemanaNom();
                view.LlistaAtletes(deportistasDAO.getDeportistaByName(nom));
                break;

            case 5:
                // Llistar atletes per cada esport
                int id = view.DemanaEsport(deportesDAO.getAll());
                view.LlistaAtletes(deportistasDAO.getDeportistaBySportID(id));
                break;

            default:
                System.out.println("Sortint del programa...");
                break;
        }
    }
}
