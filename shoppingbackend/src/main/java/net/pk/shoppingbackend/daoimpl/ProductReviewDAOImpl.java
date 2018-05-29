package net.pk.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.pk.shoppingbackend.dao.ReviewDAO;
import net.pk.shoppingbackend.dto.ProductReview;
@Repository("reviewDAO")
@Transactional
public class ProductReviewDAOImpl implements ReviewDAO{

	
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<ProductReview> getReviewList(int productId) {
		String getquery="FROM ProductReview where productId=:productId";
		
		return sessionFactory.getCurrentSession()
				.createQuery(getquery,ProductReview.class)
		           .setParameter("productId",productId).getResultList();
	}

	@Override
	public boolean add(ProductReview productReview) {
		try{
			sessionFactory.getCurrentSession().persist(productReview);
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(ProductReview productReview) {
		try{
			sessionFactory.getCurrentSession().update(productReview);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(ProductReview productReview) {
		try{
			sessionFactory.getCurrentSession().delete(productReview);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		
		return false;
	}

/*	@Override
	public boolean updateNegativePositiveCount(ProductReview productReview) {
		
		if(productReview.getReviewClass()=="positive")
		{
			String s="select positivecount from CountNegativePositive";
		}
		
		
		
		
		
		return false;
	}*/


}
