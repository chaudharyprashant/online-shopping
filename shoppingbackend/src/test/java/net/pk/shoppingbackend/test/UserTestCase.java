package net.pk.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import net.pk.shoppingbackend.dao.UserDAO;
import net.pk.shoppingbackend.dto.Address;
import net.pk.shoppingbackend.dto.Cart;
import net.pk.shoppingbackend.dto.User;
public class UserTestCase {

	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user = null;
	private Cart cart = null;
	private Address address = null;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.pk.shoppingbackend");
		context.refresh();
		
		userDAO = (UserDAO) context.getBean("userDAO");
	}
	

/*	@Test
	public void testAddUser() {
		
		user = new User() ;
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("USER");
		user.setEnabled(true);
		user.setPassword("12345");
		if(user.getRole().equals("USER"))
		{
			//create a cart for the user
			cart=new Cart();
			cart.setUser(user);
			
			//attach cart with the user
			user.setCart(cart);
			
		
			
		}
		//add user
		assertEquals("Failed to add the user!", true, userDAO.add(user));

		
		// linked the cart with the user
		cart.setUser(user);
		// link the user with the cart
		user.setCart(cart);
		
		// add the user
			
		// add the address
		

				
		// add the shipping address
		address = new Address();
		address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
		address.setAddressLineTwo("Near Kudrat Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setUserId(user);
		assertEquals("Failed to add the shipping address!", true, userDAO.addAddress(address));
		
	}*/

	

	// working for uni-directional
/*
	@Test
	public void testAddAddress() {
		
		//we need to add user

		user = new User() ;
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("USER");
		user.setEnabled(true);
		user.setPassword("12345");
		assertEquals("Failed to add the user!", true, userDAO.add(user));
		//we are going to add the address
		
		address = new Address();
		address.setAddressLineOne("301/B Jadoo Society, King Uncle Nagar");
		address.setAddressLineTwo("Near Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setBilling(true);
	   //attached the user to the address
		address.setUser(user);
		// add the address
		assertEquals("Failed to add the address!", true, userDAO.addAddress(address));	
		
		// add the shipping address
		address = new Address();
		address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
		address.setAddressLineTwo("Near Kudrat Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setShipping(true);
		//attached the user to the address
		address.setUser(user);
		assertEquals("Failed to add the shipping address!", true, userDAO.addAddress(address));
	}*/
	
	/*@Test
	public void testUpdateCart() {
		user = userDAO.getByEmail("hr@gmail.com");
		//get the cart of the user
		cart = user.getCart();
		cart.setGrandTotal(10000);
		cart.setCartLines(2);
		assertEquals("Failed to update the cart!", true, userDAO.updateCart(cart));			
	} 
*/
	@Test
	public void testAddAdress(){
		user=userDAO.getByEmail("hr@gmail.com");
		address = new Address();
		address.setAddressLineOne("301/B Jadoo Society, King Uncle Nagar");
		address.setAddressLineTwo("Near Store");
		address.setCity("Mumbai");
		address.setState("karnataka");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setShipping(true);
	   //attached the user to the address
		address.setUser(user);
		// add the address
		assertEquals("Failed to add the address!", true, userDAO.addAddress(address));	
		
		
	}

	

	
}
