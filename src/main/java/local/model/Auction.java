package local.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

public class Auction {
	
	private Long id;

	@Size(min = 2, max = 32)
	private String title;

	@Size(min = 0, max = 1024)
	private String description;

	@Size(min = 0, max = 256)
	private String imageURL;

	@DateTimeFormat(pattern = "yyyy/MM/dd hh:mm")
	private Date startTime;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd hh:mm")
	private Date endTime;

	@DecimalMin("0")
	private float startPrice;
	@DecimalMin("0")
	private float currentPrice;
	
	private boolean active;
	
	private Set bids = new HashSet();
	
	private Set categories = new HashSet();
	
	private User user;
	
	
	public Auction()
	{
	}
	
	public void addBid(Bid bid)
	{
		bid.setAuction(this);
		bids.add(bid);
	}

	public void removeBid(Bid bid)
	{
		bid.setAuction(null);
		bids.remove(bid);
	}
	
	public void addCategory(Category category)
	{
		category.getAuctions().add(this);
		categories.add(category);
	}

	public void removeCategory(Category category)
	{
		category.getAuctions().remove(this);
		categories.remove(category);
	}

	protected Set getCategories()
	{
		return categories;
	}

	protected void setCategories(Set categories)
	{
		this.categories = categories;
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

	public void setId(Long id)
	{
		this.id = id;
	}

	public Set getBids()
	{
		return bids;
	}

	public void setBids(Set bids)
	{
		this.bids = bids;
	}
	
	public Auction(String title, String description, String imageURL,
			Date startTime, Date endTime, float startPrice, float currentPrice)
	{
		super();
		this.title = title;
		this.description = description;
		this.imageURL = imageURL;
		this.startTime = startTime;
		this.endTime = endTime;
		this.startPrice = startPrice;
		this.currentPrice = currentPrice;
	}

	@Override
	public String toString()
	{
		return String.format("Auction[id: %d, title: %s]", id, title);
	}


	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getImageURL()
	{
		return imageURL;
	}

	public void setImageURL(String imageURL)
	{
		this.imageURL = imageURL;
	}

	public Date getStartTime()
	{
		return startTime;
	}

	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}

	public Date getEndTime()
	{
		return endTime;
	}

	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}

	public float getStartPrice()
	{
		return startPrice;
	}

	public void setStartPrice(float startPrice)
	{
		this.startPrice = startPrice;
	}

	public float getCurrentPrice()
	{
		return currentPrice;
	}

	public void setCurrentPrice(float currentPrice)
	{
		this.currentPrice = currentPrice;
	}

	/**
	 * @return the active
	 */
	public boolean isActive()
	{
		return active;
	}
	
	/**
	 * @return the active
	 */
	public boolean getActive()
	{
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active)
	{
		this.active = active;
	}
}
