package local.model;

public class Statistics {

	private Long id;
	private long nVisits;
	private User user;

	public Statistics()
	{
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Long getId()
	{
		return id;
	}

	private void setId(Long id)
	{
		this.id = id;
	}

	public long getnVisits()
	{
		return nVisits;
	}

	public void setnVisits(long nVisits)
	{
		this.nVisits = nVisits;
	}
	
	public void addVisit()
	{
		nVisits++;
	}
}
