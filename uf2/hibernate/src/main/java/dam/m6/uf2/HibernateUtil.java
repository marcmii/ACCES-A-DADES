package dam.m6.uf2;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    // Es crea una sola fabrica de sessions per tota l'aplicacio.
    private static final SessionFactory SESSION_FACTORY = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

    private HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static void close() {
        // Es tanca al final del programa per alliberar la connexio.
        SESSION_FACTORY.close();
    }
}
