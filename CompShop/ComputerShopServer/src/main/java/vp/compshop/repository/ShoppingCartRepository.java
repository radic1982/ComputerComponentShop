package vp.compshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import vp.compshop.model.ShoppingCart;

@Component
public interface ShoppingCartRepository extends JpaRepository<	ShoppingCart, Long>{
	ShoppingCart findByUsername(String username);

}
