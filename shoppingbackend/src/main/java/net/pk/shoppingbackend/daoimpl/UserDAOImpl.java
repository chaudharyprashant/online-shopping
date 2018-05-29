package net.pk.shoppingbackend.daoimpl;


import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.pk.shoppingbackend.dao.UserDAO;
import net.pk.shoppingbackend.dto.Address;
import net.pk.shoppingbackend.dto.Cart;
import net.pk.shoppingbackend.dto.User;


@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User getByEmail(String email) {
		String selectQuery = "FROM User WHERE email = :email";
		try {
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectQuery,User.class)
						.setParameter("email",email)
							.getSingleResult();
		}
		catch(Exception ex) {
			return null;
		}
							
	}

	@Override
	public boolean add(User user) {
		try {			
			sessionFactory.getCurrentSession().persist(user);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}

	@Override
	public boolean addAddress(Address address) {
		try {			
			// will look for this code later and why we need to change it
			sessionFactory.getCurrentSession().persist(address);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}
	
	@Override
	public boolean updateAddress(Address address) {
		try {			
			sessionFactory.getCurrentSession().update(address);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}	
	

	@Override
	public List<Address> listShippingAddresses(User user) {
		String selectQuery = "FROM Address WHERE user = :user AND shipping = :shipping";
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectQuery,Address.class)
						.setParameter("user", user)
						.setParameter("shipping", true)
							.getResultList();
		
	}

	@Override
	public Address getBillingAddress(User user) {
		String selectQuery = "FROM Address WHERE user= :user AND billing = :billing";
		try{
		return sessionFactory.getCurrentSession()
					.createQuery(selectQuery,Address.class)
						.setParameter("user", user)
						.setParameter("billing",true)
						.getSingleResult();
		}
		catch(Exception ex) {
			return null;
		}
	}

	@Override
	public User get(int id) {
		try {			
			return sessionFactory.getCurrentSession().get(User.class, id);			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	@Override
	public Address getAddress(int addressId) {
		try {			
			return sessionFactory.getCurrentSession().get(Address.class, addressId);			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	@Override
	public boolean updateCart(Cart cart) {
		// TODO Auto-generated method stub
		try {			
			sessionFactory.getCurrentSession().update(cart);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}

}

