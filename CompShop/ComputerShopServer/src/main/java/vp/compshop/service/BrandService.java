package vp.compshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import vp.compshop.model.Brand;
import vp.compshop.repository.BrandRepository;

@Component
public class BrandService {
	
	@Autowired
	BrandRepository brandRepository;
	
	public List<Brand> findAll() {
		return brandRepository.findAll();
	}
	
	public Brand findOne(Long id) {
		return brandRepository.findOne(id);
	}

	public Brand save(Brand brand) {
		return brandRepository.save(brand);
	}

	public void remove(Long id) {
		// dozvoljeno brisanje samo studenata koji nemaju ispite
		//if (examService.findByStudentId(id).isEmpty()) {
			brandRepository.delete(id);
		//}
	}
	
	public Brand findByName(String name) {
		return brandRepository.findByName(name);
	}

}
