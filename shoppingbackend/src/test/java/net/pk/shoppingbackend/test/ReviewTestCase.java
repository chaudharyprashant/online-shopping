package net.pk.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.pk.shoppingbackend.dao.ReviewDAO;
import net.pk.shoppingbackend.dto.ProductReview;

public class ReviewTestCase {

	
	private static AnnotationConfigApplicationContext context;
	
	private static ReviewDAO reviewDAO;
	
	private ProductReview productReview;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.pk.shoppingbackend");
		context.refresh();
		reviewDAO = (ReviewDAO) context.getBean("reviewDAO");
	}
	
	@Test
	public void testCURDReview()
	{
		/*
		//create review
		productReview=new ProductReview();
		productReview.setUserName("prashant kumar");
		productReview.setCategoryId(3);
		productReview.setProductId(1);
		productReview.setProductReview("this is a nice product");
		 assertEquals("review added succesfully",true,reviewDAO.add(productReview));*/
		
		//productReview=reviewDAO.get(1);
		productReview.setProductReview("very bad product");
		assertEquals("review updated successfully",true,reviewDAO.update(productReview));
		
	}
	
	
}
