package vp.compshop.controller;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vp.compshop.model.ComputerComponent;
import vp.compshop.model.ShoppingCart;
import vp.compshop.service.ShoppingCartService;

@RestController
public class ShoppingCartController {
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	
	@RequestMapping(value= "api/shoppingCart", method = RequestMethod.GET)
	public ResponseEntity<ShoppingCart> getShoppingCart() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ShoppingCart sc = shoppingCartService.findByUsername(authentication.getName());
		return new ResponseEntity<>(sc, HttpStatus.OK); 
	}

	@RequestMapping(value="api/shoppingCart", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShoppingCart> addComponent(@RequestBody ComputerComponent component) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ShoppingCart sc = shoppingCartService.findByUsername(authentication.getName());
		if(sc==null) {
			sc = new ShoppingCart(authentication.getName(), new HashSet<>());
		}
		if (!sc.getComponents().contains(component)) {
			sc.getComponents().add(component);
			sc = shoppingCartService.save(sc);			
		}
		return new ResponseEntity<>(sc, HttpStatus.CREATED);
	}


}
