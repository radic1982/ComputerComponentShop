package vp.compshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import vp.compshop.model.ComputerComponent;
import vp.compshop.repository.ComputerComponentRepository;

@Component
public class ComputerComponentService {
	@Autowired
	ComputerComponentRepository repo;
	
	public Page<ComputerComponent> findAll(Pageable page) {
		return repo.findAll(page);
	}
	
	public ComputerComponent findOne(Long id) {
		return repo.findOne(id);
	}

	public ComputerComponent save(ComputerComponent component) {
		return repo.save(component);
	}

	public void remove(Long id) {
		// dozvoljeno brisanje samo studenata koji nemaju ispite
		//if (examService.findByStudentId(id).isEmpty()) {
			repo.delete(id);
		//}
	}

}
