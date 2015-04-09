package local.service;

import java.util.Date;
import java.util.List;
import local.model.Auction;
import local.model.Bid;
import local.model.Category;
import local.model.User;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

@Service("AuctionService")
public class AuctionService {

	public AuctionService()
	{
		Util.log("AuctionService constructor");
	}

	public List findAll()
	{
		Util.log("AuctionService: findAll()");

		List auctions = null;
		/* Session-per-request pattern. */
		Session session;
		try {
			/* Begin unit of work. */
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			/* Process the request. */
			/* List all the auctions. */
			auctions = HibernateUtil.getSessionFactory().getCurrentSession()
					.createCriteria(Auction.class).list();
			//List auctions = session.createQuery("from Auction").list();

			for (int i = 0; i < auctions.size(); i++) {
				Auction auction = (Auction) auctions.get(i);
				System.out.println("Auction: " + auction);
			}
			
			/* End unit of work. */
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();

		} catch (Exception ex) {
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
			Util.log("ROLLBACK");
			Util.log(ex.getMessage());
		}
		
		return auctions;
	}
	
	public Auction find(String id)
	{
		Util.log("AuctionService: find()");
		Util.log("Auction id = " + id);

		Auction auction = null;
		/* Session-per-request pattern. */
		Session session = null;
		try {
			/* Begin unit of work. */
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			/* Process the request. */
//			auction = (Auction) HibernateUtil.getSessionFactory().getCurrentSession().load(Auction.class, id);
			auction = (Auction) session.createQuery("from Auction where id=" + id).uniqueResult();
			
			System.out.println("HQL\n");
			System.out.println(session.createQuery("select auction.user from Auction as auction "
							+ "where auction.id=" + id).uniqueResult());
			
			/* End unit of work. */
			session.getTransaction().commit();
			
		} catch (Exception ex) {
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
			Util.log("ROLLBACK");
			Util.log(ex.getMessage());
		}

		return auction;
	}

	public long create(Auction auction)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		session.save(auction);
		
		session.getTransaction().commit();

		return auction.getId();
	}

	public long update(Auction auction)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Util.log("AuctionService: Auction to update: " + auction.getId());
		session.update(auction);
		
		session.getTransaction().commit();

		Util.log("Auction was updated: " + auction);

		/* Redirect to the updated auction. */
		return auction.getId();
	}

	public void delete(String id)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		session.createQuery("delete from Auction where id=" + id).executeUpdate();

		session.getTransaction().commit();
	}

	public void addAuctionToUser(long auctionId, long userId)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Auction auction = (Auction) session.load(Auction.class, auctionId);
		User user = (User) session.load(User.class, userId);
		user.addAuction(auction);
	
		session.getTransaction().commit();
	}

	public long createAuction(String title, Date date)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Auction auction = new Auction();
		auction.setTitle(title);
		auction.setStartTime(date);
		long id = (long) session.save(auction);

		session.getTransaction().commit();
		return id;
	}


	public long createCategory(String name)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Category category = new Category();
		category.setName(name);
		long id = (long) session.save(category);

		session.getTransaction().commit();
		return id;
	}
			
	public void addAuctionToCategory(long auctionId, long categoryId)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Category category = (Category) session.load(Category.class, categoryId);
		Auction auction = (Auction) session.load(Auction.class, auctionId);
		auction.addCategory(category);
	
		session.getTransaction().commit();
	}

	public void addBid(Bid bid, long auctionId, long userId)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Auction auction = (Auction) session.load(Auction.class, auctionId);
		User user = (User) session.load(User.class, userId);
		
		/* Check if auction is still active. */
		if (auction.getEndTime().before(new Date())) {
			Util.log("Auction has been over. id=" + auctionId);
			auction.setActive(false);
			session.getTransaction().commit();
			return;
		}
		
		bid.setBidDate(new Date());
		user.addBid(bid);
		auction.addBid(bid);
		auction.setCurrentPrice(bid.getAmount());

		session.getTransaction().commit();
	}
	
}
