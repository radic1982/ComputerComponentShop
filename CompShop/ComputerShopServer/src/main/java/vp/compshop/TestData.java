package vp.compshop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vp.compshop.model.Brand;
import vp.compshop.model.ComputerComponent;
import vp.compshop.service.BrandService;
import vp.compshop.service.ComputerComponentService;

@Component
public class TestData {

	@Autowired
	ComputerComponentService service;
	@Autowired
	BrandService brandService;

	@PostConstruct
	public void init() throws IOException {

		List<Brand> allBrands = new ArrayList<Brand>();
		List<ComputerComponent> allComponents = new ArrayList<ComputerComponent>();

		String putanjaMemorija = "src/main/java/vp/compshop/memorije.txt";
		BufferedReader inputStream = new BufferedReader(new FileReader(putanjaMemorija));

		String l;
		while ((l = inputStream.readLine()) != null) {
			String[] s = l.split("\\|");
			Brand brand = new Brand(s[0]);
			allBrands.add(brand);
			ComputerComponent component = new ComputerComponent(brand, s[1], Double.parseDouble(s[2]));
			allComponents.add(component);
		}
		inputStream.close();
		
		List<Brand> uniqueBrands = new ArrayList<Brand>();
		uniqueBrands = allBrands.stream().distinct().collect(Collectors.toList());
		
		for(Brand b: uniqueBrands) {
			brandService.save(b);
		}
		
		for(ComputerComponent c: allComponents) {
			String brandName = c.getBrand().getName();
			Brand b = brandService.findByName(brandName);
			c.setBrand(b);
			service.save(c);
		}
		
	}

}
