package dam.m6.uf2;

import java.sql.Connection;

public class Controller {
    public static void main(String[] args) {
        try (Connection conn = ConnectionManager.getConnection("database.xml")) {
            MainView view = new MainView();
            DeportesPgDAO deportesDAO = new DeportesPgDAO(conn);
            DeportistasPgDAO deportistasDAO = new DeportistasPgDAO(conn);

            int opcio;

            do {
                opcio = view.mainMenu();

                if (opcio == 1) {
                    view.LlistaEsports(deportesDAO.getAll());
                } else if (opcio == 2) {
                    deportesDAO.add(view.EsportForm());
                } else if (opcio == 3) {
                    deportistasDAO.add(view.AtletaForm(deportesDAO.getAll()));
                } else if (opcio == 4) {
                    view.LlistaAtletes(deportistasDAO.getDeportistaByName(view.DemanaNom()));
                } else if (opcio == 5) {
                    int id = view.DemanaEsport(deportesDAO.getAll());
                    view.LlistaAtletes(deportistasDAO.getDeportistaBySportID(id));
                } else if (opcio != 0) {
                    System.out.println("Opcio incorrecta");
                }
            } while (opcio != 0);

            System.out.println("Adeu!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
