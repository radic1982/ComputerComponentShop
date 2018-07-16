package vp.compshop.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vp.compshop.dto.BrandDTO;
import vp.compshop.model.Brand;
import vp.compshop.service.BrandService;

@RestController
@RequestMapping(value = "/api/brands")

public class BrandController {
	
	@Autowired
	BrandService brandService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<BrandDTO>> getAll() {
		List<Brand> brands = brandService.findAll();
		List<BrandDTO> retVal = brands
				.stream()
				.map(m-> new BrandDTO(m))
				.collect(Collectors.toList());
		return new ResponseEntity<>(retVal, HttpStatus.OK); 
	}


}
