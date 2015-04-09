package local.service;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event)
	{
		Util.log("HibernateServletContextListener: initialize SessionFactory");
		// Call the static initializer
		HibernateUtil.getSessionFactory();
	}

	@Override
	public void contextDestroyed(ServletContextEvent event)
	{
		Util.log("HibernateServletContextListener: close SessionFactory");
		/* Free all resources. */
		HibernateUtil.getSessionFactory().getCurrentSession().close();
		HibernateUtil.getSessionFactory().close();
	}
}
