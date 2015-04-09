package local.model;

import java.util.HashSet;
import java.util.Set;

public class Group {

	private Long id;
	private String name;

	private Set users = new HashSet();
	
	public Group()
	{
	}

	public void addUser(User user)
	{
		user.getGroups().add(this);
		getUsers().add(user);
	}

	public void removeUser(User user)
	{
		user.getGroups().remove(this);
		getUsers().remove(user);
	}
	
	protected Set getUsers()
	{
		return users;
	}

	protected void setUsers(Set users)
	{
		this.users = users;
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
