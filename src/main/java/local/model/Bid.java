package local.model;

import java.util.Date;
import javax.validation.constraints.DecimalMin;

public class Bid {
	
	private long id;
	@DecimalMin("0")
	private float amount;
	private Date bidDate;
	
	private User user;
	private Auction auction;

	public Bid()
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

	public Auction getAuction()
	{
		return auction;
	}

	public void setAuction(Auction auction)
	{
		this.auction = auction;
	}
	
	public Date getBidDate()
	{
		return bidDate;
	}

	public void setBidDate(Date bidDate)
	{
		this.bidDate = bidDate;
	}
	
	
	public float getAmount()
	{
		return amount;
	}

	public void setAmount(float amount)
	{
		this.amount = amount;
	}
	
	public long getId()
	{
		return id;
	}

	private void setId(long id)
	{
		this.id = id;
	}
}
