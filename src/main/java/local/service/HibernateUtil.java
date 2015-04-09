package local.service;



import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author John
 */
public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	static {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
					configuration.getProperties()).build();

			sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		} catch (Throwable ex) {
			// Log the exception
			Util.log("HibernateUtil: Initial SessionFactory creation failed:");
			Util.log(ex.getMessage());
//			System.exit(1);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 *
	 * @return
	 */
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
}
