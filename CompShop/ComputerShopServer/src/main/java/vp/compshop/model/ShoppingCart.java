package vp.compshop.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class ShoppingCart {
	
	public ShoppingCart() {
		super();
	}

	public ShoppingCart(String username, Set<ComputerComponent> components) {
		super();
		this.username = username;
		this.components = components;
	}

	@Id
	@GeneratedValue
	Long id;

	String username;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	Set<ComputerComponent> components = new HashSet<ComputerComponent>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<ComputerComponent> getComponents() {
		return components;
	}

	public void setComponents(Set<ComputerComponent> components) {
		this.components = components;
	}
	
	


}
