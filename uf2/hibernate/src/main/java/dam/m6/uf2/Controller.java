package dam.m6.uf2;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Controller {
    public static void main(String[] args) {
        amagaMissatgesHibernate();
        MainView view = new MainView();

        try {
            int opcio;

            do {
                // Segons l'opcio del menu fem una consulta o una modificacio amb Hibernate.
                opcio = view.mainMenu();

                if (opcio == 1) {
                    view.LlistaEsports(getAllEsports());
                } else if (opcio == 2) {
                    addEsport(view.EsportForm());
                } else if (opcio == 3) {
                    addAtleta(view.AtletaForm(getAllEsports()));
                } else if (opcio == 4) {
                    view.LlistaAtletes(getAtletesByName(view.DemanaNom()));
                } else if (opcio == 5) {
                    int id = view.DemanaEsport(getAllEsports());
                    view.LlistaAtletes(getAtletesBySportID(id));
                } else if (opcio == 6) {
                    int id = view.DemanaEsport(getAllEsports());
                    updateEsport(id, view.DemanaNouNom());
                } else if (opcio == 7) {
                    int id = view.DemanaAtleta(getAllAtletes());
                    updateAtleta(id, view.DemanaNouNom());
                } else if (opcio != 0) {
                    System.out.println("Opcio incorrecta");
                }
            } while (opcio != 0);

            System.out.println("Adeu!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            HibernateUtil.close();
        }
    }

    private static void amagaMissatgesHibernate() {
        // Aixi la consola nomes mostra el menu i els resultats de la practica.
        Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        Logger.getLogger("org.jboss").setLevel(Level.OFF);
    }

    private static List<Deportes> getAllEsports() {
        // Consulta HQL: es treballa amb la classe Deportes, no amb SQL directe.
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Deportes order by cod", Deportes.class).list();
        }
    }

    private static void addEsport(Deportes esport) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Les insercions i modificacions es fan sempre dins una transaccio.
            transaction = session.beginTransaction();
            session.persist(esport);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    private static void addAtleta(Deportistas atleta) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            // Recuperem l'esport real de la base de dades abans de guardar l'atleta.
            Deportes esport = session.get(Deportes.class, atleta.getCodDeporte());
            atleta.setDeporte(esport);
            session.persist(atleta);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    private static void updateEsport(int id, String nouNom) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            // Quan l'objecte esta dins la sessio, Hibernate detecta el canvi al commit.
            Deportes esport = session.get(Deportes.class, id);
            esport.setNombre(nouNom);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    private static void updateAtleta(int id, String nouNom) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Deportistas atleta = session.get(Deportistas.class, id);
            atleta.setNombre(nouNom);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    private static List<Deportistas> getAllAtletes() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Deportistas order by nombre", Deportistas.class).list();
        }
    }

    private static List<Deportistas> getAtletesByName(String name) {
        // Cerca per nom utilitzant parametres per evitar concatenar dades de l'usuari.
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "from Deportistas d where lower(d.nombre) like lower(:name) order by d.nombre",
                    Deportistas.class)
                    .setParameter("name", "%" + name + "%")
                    .list();
        }
    }

    private static List<Deportistas> getAtletesBySportID(int sportID) {
        // Hibernate sap fer el join gracies a la relacio definida a Deportistas.
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "from Deportistas d where d.deporte.cod = :sportID order by d.nombre",
                    Deportistas.class)
                    .setParameter("sportID", sportID)
                    .list();
        }
    }
}
