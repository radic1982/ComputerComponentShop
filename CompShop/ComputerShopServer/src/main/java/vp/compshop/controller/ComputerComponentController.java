package vp.compshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vp.compshop.dto.BrandDTO;
import vp.compshop.dto.ComputerComponentDTO;
import vp.compshop.dto.PageDTO;
import vp.compshop.model.Brand;
import vp.compshop.model.ComputerComponent;
import vp.compshop.service.ComputerComponentService;


@RestController
public class ComputerComponentController {
	@Autowired
	ComputerComponentService service;
	
	@RequestMapping(value = "api/components", method = RequestMethod.GET)
	public ResponseEntity<PageDTO<ComputerComponentDTO>> findAll(Pageable page) {
		Page<ComputerComponent> components= service.findAll(page);
		
		
		PageDTO<ComputerComponentDTO> page1 = new PageDTO();
		page1.setTotalPages(components.getTotalPages());
		page1.setNumberOfElements(components.getNumberOfElements());
		page1.setTotalElements(components.getTotalElements());
		
		
		//List<ComputerComponentDTO> componentsDTO = convertToDto(components.getContent());
		List<ComputerComponentDTO> componentsDTO = components.getContent()
			.stream()
			.map(c ->{
				ComputerComponentDTO cDTO = new ComputerComponentDTO(c);
				//cDTO.setBrand(new BrandDTO(c.getBrand()));
				cDTO.setBrand(c.getBrand()==null?null:new BrandDTO(c.getBrand()));// vraca komp i ako rac. komponenta nema brand
				return cDTO;
			})
			.collect(Collectors.toList());
		page1.setContent(componentsDTO);
		return new ResponseEntity<>(page1, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/components/{id}", method = RequestMethod.GET)
	public ResponseEntity<ComputerComponentDTO> getById(@PathVariable Long id) {
		ComputerComponent component = service.findOne(id);
		return Optional.ofNullable(component)
				.map(e -> new ResponseEntity<>(new ComputerComponentDTO(e), HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		
		/*if (component != null) {
			return new ResponseEntity<>(component, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}*/
	}

	@RequestMapping(value = "api/components", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ComputerComponentDTO> create(@RequestBody ComputerComponentDTO componentDTO) {
		ComputerComponent component = new ComputerComponent();
		component.setName(componentDTO.getName());
		component.setPrice(componentDTO.getPrice());
		Brand brand = new Brand();
		brand.setId(componentDTO.getBrand().getId());
		component.setBrand(brand);
		component = service.save(component);
		//ComputerComponent retVal = service.save(component);

		return new ResponseEntity<>(new ComputerComponentDTO(component), HttpStatus.CREATED);
	}

	@RequestMapping(value = "api/components/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ComputerComponentDTO> update(@PathVariable Long id, @RequestBody ComputerComponentDTO componentDTO) {
		ComputerComponent component = service.findOne(id);
		if (component == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		component.setId(id);
		component.setName(componentDTO.getName());
		component.setPrice(componentDTO.getPrice());
		Brand brand = new Brand();
		brand.setId(componentDTO.getBrand().getId());
		component.setBrand(brand);
		component = service.save(component);

		return new ResponseEntity<>(new ComputerComponentDTO(component), HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/components/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		ComputerComponent comp = service.findOne(id);
		if (comp != null) {
			service.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	public List<ComputerComponentDTO> convertToDto(List<ComputerComponent> components) {
		List<ComputerComponentDTO> componentsDTO = new ArrayList<ComputerComponentDTO>();
		for(ComputerComponent c: components) {
			ComputerComponentDTO dto = new ComputerComponentDTO();
			dto.setId(c.getId());
			dto.setName(c.getName());
			dto.setPrice(c.getPrice());
			componentsDTO.add(dto);
		}
		
		return componentsDTO;
	}

	
}
