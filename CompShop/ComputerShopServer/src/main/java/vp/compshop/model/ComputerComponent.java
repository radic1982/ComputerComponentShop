package vp.compshop.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ComputerComponent {
	@Id
	@GeneratedValue
	private Long id;
	
	private double price;
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH) 
	private Brand brand;
	
	public ComputerComponent() {
		
	}
	
	
	public ComputerComponent(Long id, double price, String name, Brand brand) {
		super();
		this.id = id;
		this.price = price;
		this.name = name;
		this.brand = brand;
	}
	public ComputerComponent(Brand brand, String name, double price) {
		this.brand=brand;
		this.name=name;
		this.price=price;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
}
