package vp.compshop.dto;

import java.util.ArrayList;
import java.util.List;

import vp.compshop.model.Brand;

public class BrandDTO {
	
	private Long id;
	private String name;
	private List<ComputerComponentDTO> components = new ArrayList<>();
	
	public BrandDTO() {
		
	}

	public BrandDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public BrandDTO(Brand brand) {
		this.id = brand.getId();
		this.name = brand.getName();
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

	public List<ComputerComponentDTO> getComponents() {
		return components;
	}

	public void setComponents(List<ComputerComponentDTO> components) {
		this.components = components;
	}
	
	

}
