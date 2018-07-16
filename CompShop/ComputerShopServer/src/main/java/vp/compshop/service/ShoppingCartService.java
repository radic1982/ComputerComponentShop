package vp.compshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vp.compshop.model.ShoppingCart;
import vp.compshop.repository.ShoppingCartRepository;

@Component
public class ShoppingCartService {
	
	@Autowired
	ShoppingCartRepository shoppingCartRepository;
	
	public ShoppingCart findByUsername(String username) {
		return shoppingCartRepository.findByUsername(username);
	}
	
	public ShoppingCart save(ShoppingCart shoppingCart) {
		return shoppingCartRepository.save(shoppingCart);
	}

	

}
