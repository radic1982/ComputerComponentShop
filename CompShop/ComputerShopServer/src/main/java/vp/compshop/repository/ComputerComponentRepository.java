package vp.compshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import vp.compshop.model.ComputerComponent;

@Component
public interface ComputerComponentRepository extends
	JpaRepository<ComputerComponent, Long>{

}
