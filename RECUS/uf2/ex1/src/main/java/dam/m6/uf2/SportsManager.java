package dam.m6.uf2;

import java.sql.Connection;

public class SportsManager {
    public static void main(String[] args) {
        try (Connection conn = ConnectionManager.getConnection("database.xml")) {
            View view = new View();
            EsportDAO esportDAO = new EsportDAO(conn);
            AtletaDAO atletaDAO = new AtletaDAO(conn);

            int opcio;
            do {
                opcio = view.menu();
                switch (opcio) {
                    case 1:
                        view.llistaEsports(esportDAO.getAll());
                        break;
                    case 2:
                        esportDAO.add(view.esportForm());
                        break;
                    case 3:
                        atletaDAO.add(view.atletaForm(esportDAO.getAll()));
                        break;
                    case 4:
                        view.llistaAtletes(atletaDAO.buscarPerNom(view.demanaNom()));
                        break;
                    case 5:
                        int id = view.demanaEsport(esportDAO.getAll());
                        view.llistaAtletes(atletaDAO.buscarPerEsport(id));
                        break;
                    case 0:
                        System.out.println("Adeu!");
                        break;
                    default:
                        System.out.println("Opcio incorrecta.");
                        break;
                }
            } while (opcio != 0);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
