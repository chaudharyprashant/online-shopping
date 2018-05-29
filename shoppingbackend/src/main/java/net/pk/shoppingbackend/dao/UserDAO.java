package net.pk.shoppingbackend.dao;


import java.util.List;

import net.pk.shoppingbackend.dto.Address;
import net.pk.shoppingbackend.dto.Cart;
import net.pk.shoppingbackend.dto.User;

public interface UserDAO {

	// user related operation
	User getByEmail(String email);
	User get(int id);

	boolean add(User user);
	boolean updateCart(Cart cart);
	//alterative
	//Address getBillingAddress(int userId);
	//List<Address> listShippingAddresses(int userId);
	// adding and updating a new address
	Address getAddress(int addressId);
	boolean addAddress(Address address);
	boolean updateAddress(Address address);
	Address getBillingAddress(User user);
	List<Address> listShippingAddresses(User user);
	

	
}
