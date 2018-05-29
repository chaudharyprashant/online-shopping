package net.pk.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.pk.shoppingbackend.dao.ProductDAO;
import net.pk.shoppingbackend.dto.Product;
@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO{

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public Product get(int productId) {
		try{
			System.out.println("inside get");
			System.out.println(productId);
		return sessionFactory.getCurrentSession().get(Product.class,Integer.valueOf(productId));
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> list() {
      try
      {
    	return sessionFactory.getCurrentSession().createQuery("FROM Product",Product.class).getResultList();  
      }
      catch(Exception ex)
      {
    	  ex.printStackTrace();
      }
      return null;
		
	}

	@Override
	public boolean add(Product product) {
		try{
			sessionFactory.getCurrentSession().persist(product);
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Product product) {
		try{
			sessionFactory.getCurrentSession().update(product);
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Product product) {
		try{
			product.setActive(false);
			return this.update(product);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Product> listActiveProducts() {
		String selectActiveProducts="FROM Product WHERE active=:active";
		return sessionFactory.getCurrentSession()
				.createQuery(selectActiveProducts,Product.class)
				           .setParameter("active",true).getResultList();
	}

	@Override
	public List<Product> listActiveproductsByCategory(int categoryId) {

		String selectActiveProductsByCategory="FROM Product WHERE active=:active and categoryId=:categoryId";
		return sessionFactory.getCurrentSession()
				.createQuery(selectActiveProductsByCategory,Product.class)
				           .setParameter("active",true).setParameter("categoryId",categoryId).getResultList();
	}

	@Override
	public List<Product> getLatestActiveproducts(int count) {
		
		return sessionFactory.getCurrentSession()
				.createQuery("From Product WHERE active=:active ORDER BY id",Product.class)
				           .setParameter("active",true).setFirstResult(0).setMaxResults(count).getResultList();
	
		
	}

}
