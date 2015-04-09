package local.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

public class User {
	
	private Long id;
	@Size(min = 2, max = 32)
	private String name;
	@Size(min = 0, max = 1024)
	private String description;
	@Size(min = 0, max = 256)
	private String imageURL;
	@Size(min = 0, max = 256)
	private String email;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd hh:mm")
	private Date birthday;
	private Date regDate;
	private Date lastActivity;

	private Set auctions = new HashSet();
	
	private Set bids = new HashSet();
	
	private Set groups = new HashSet();
	
	private Statistics statistics;
	
	public User()
	{
		setStatistics(new Statistics());
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public void addGroup(Group group)
	{
		group.getUsers().add(this);
		groups.add(group);
	}

	public void removeGroup(Group group)
	{
		group.getUsers().remove(this);
		groups.remove(group);
	}
	
	public Set getGroups()
	{
		return groups;
	}

	public void setGroups(Set groups)
	{
		this.groups = groups;
	}
	
	public void addBid(Bid bid)
	{
		bid.setUser(this);
		bids.add(bid);
	}

	public void removeBid(Bid bid)
	{
		bid.setUser(null);
		bids.remove(bid);
	}

	public Statistics getStatistics()
	{
		return statistics;
	}

	private void setStatistics(Statistics statistics)
	{
		statistics.setUser(this);
		this.statistics = statistics;
	}

	public String getImageURL()
	{
		return imageURL;
	}

	public void setImageURL(String imageURL)
	{
		this.imageURL = imageURL;
	}

	public Date getRegDate()
	{
		return regDate;
	}

	public void setRegDate(Date regDate)
	{
		this.regDate = regDate;
	}

	public Date getLastActivity()
	{
		return lastActivity;
	}

	public void setLastActivity(Date lastActivity)
	{
		this.lastActivity = lastActivity;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Date getBirthday()
	{
		return birthday;
	}

	public void setBirthday(Date birthday)
	{
		this.birthday = birthday;
	}
	
	public Set getBids()
	{
		return bids;
	}

	public void setBids(Set bids)
	{
		this.bids = bids;
	}
	
	public void addAuction(Auction auction)
	{
		auctions.add(auction);
	}

	public void removeAuction(Auction auction)
	{
		auctions.remove(auction);
	}
	
	protected Set getAuctions()
	{
		return auctions;
	}

	protected void setAuctions(Set auctions)
	{
		this.auctions = auctions;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	
	}
	@Override
	public String toString()
	{
		return String.format("User [id: %d, name: %s]", id, name);
	}
	
}
