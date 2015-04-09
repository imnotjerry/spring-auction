package local.service;

import java.util.Date;
import java.util.List;
import local.model.User;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserService {

	public UserService()
	{
		Util.log("UserService constructor");
	}

	public List findAll()
	{
		Util.log("UserService: findAll()");

		List users = null;
		/* Session-per-request pattern. */
		Session session;
		try {
			/* Begin unit of work. */
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			/* Process the request; list all the users. */
//			users = session.createCriteria(User.class).list();
			users = session.createQuery("from User").list();
			
			/* End unit of work. */
			session.getTransaction().commit();

		} catch (Exception ex) {
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
			Util.log("ROLLBACK");
			Util.log(ex.getMessage());
		}
		return users;
	}
	
	public User find(String id)
	{
		Util.log("UserService: find()");
		Util.log("User id = " + id);

		User user = null;
		/* Session-per-request pattern. */
		Session session;
		try {
			/* Begin unit of work. */
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			/* Process the request. */
//			user = (User) session.load(User.class, id);
			user = (User) session.createQuery("from User where id=" + id).uniqueResult();
			
			/* End unit of work. */
			session.getTransaction().commit();
			
		} catch (Exception ex) {
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
			Util.log("ROLLBACK");
			Util.log(ex.getMessage());
		}

		return user;
	}

	public long create(User user)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		user.setRegDate(new Date());
		user.setLastActivity(new Date());
		
		session.save(user.getStatistics());
		long id = (long) session.save(user);

		session.getTransaction().commit();
		return id;
	}

	public long update(User user)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Util.log("UserService: User to update: " + user.getId());
		session.update(user);
		
		session.getTransaction().commit();

		Util.log("User was updated: " + user);

		return user.getId();
	}

	public void delete(User user)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		session.delete(user);
//		session.createQuery("delete from User where id=" + id).executeUpdate();

		session.getTransaction().commit();
	}
	
	@Deprecated
	private void addEmailToUser(String email, long userId)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		User user = (User) session.load(User.class, userId);
		user.setEmail(email);

		session.getTransaction().commit();
	}

}
