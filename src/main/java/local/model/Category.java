package local.model;

import java.util.HashSet;
import java.util.Set;

public class Category {

	private Long id;
	private String name;

	private Set auctions = new HashSet();
	
	public Category()
	{
	}

	public void addAuction(Auction auction)
	{
		auction.getCategories().add(this);
		getAuctions().add(auction);
	}

	public void removeUser(Auction auction)
	{
		auction.getCategories().remove(this);
		getAuctions().remove(auction);
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

	private void setId(Long id)
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
	
	
}
