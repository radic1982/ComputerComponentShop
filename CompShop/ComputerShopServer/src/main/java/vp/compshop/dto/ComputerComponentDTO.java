package vp.compshop.dto;

import vp.compshop.model.ComputerComponent;

public class ComputerComponentDTO {
	private Long id;
	private String name;
	private double price;
	private BrandDTO brand;
	
	public ComputerComponentDTO() {
		
	}
	
	public ComputerComponentDTO(ComputerComponent component) {
		this.id = component.getId();
		this.name = component.getName();
		this.price = component.getPrice();
		
	}

	public ComputerComponentDTO(Long id, String name, double price, BrandDTO brand) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.brand = brand;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public BrandDTO getBrand() {
		return brand;
	}

	public void setBrand(BrandDTO brand) {
		this.brand = brand;
	}
	
	
	
	

}
