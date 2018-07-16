package vp.compshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import vp.compshop.model.Brand;

@Component
public interface BrandRepository extends JpaRepository<Brand, Long >{

	Brand findByName(String name);
}
