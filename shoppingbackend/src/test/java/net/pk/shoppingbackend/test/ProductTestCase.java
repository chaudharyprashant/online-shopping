package net.pk.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.pk.shoppingbackend.dao.ProductDAO;
import net.pk.shoppingbackend.dto.Product;

public class ProductTestCase {

	private static AnnotationConfigApplicationContext context;

	private static ProductDAO productDAO;

	private Product product;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.pk.shoppingbackend");
		context.refresh();
		productDAO = (ProductDAO) context.getBean("productDAO");
	}

	@Test
	public void testCRUDProduct() {
		/*
		 * //create operation product=new Product();
		 * product.setName("oppo selfie s3"); product.setBrand("Oppo");
		 * product.setDescription("this is some description for oppo phone");
		 * product.setUnitPrice(25000); product.setActive(true);
		 * product.setCategoryId(3); product.setSupplierId(3);
		 * 
		 * assertEquals("something went wrong while inserting a new product!"
		 * ,true,productDAO.add(product));
		 * 
		 * //update product product=new Product(); product=productDAO.get(2);
		 * product.setName("samsung galaxy s7");
		 * assertEquals("something went wrong while upating the existing record"
		 * ,true,productDAO.update(product));
		 * 
		 * //delete product
		 * assertEquals("something went wrong while deleting the record",true,
		 * productDAO.delete(product)); //size
		 * assertEquals("something went wrong while fetching the list of products"
		 * ,6,productDAO.list().size());
		 */

	}

	@Test
	public void testListActiveProducts() {
		assertEquals("something went wrong while fetching list", 5, productDAO.listActiveProducts().size());

	}

	@Test
	public void testListActiveProductByCategory() {
		assertEquals("something went wrong while fetching list",3,productDAO.listActiveproductsByCategory(3).size());

	}
	
	@Test
	public void testLatestActiveProduct()
	{
		assertEquals("somethinf went wrong",3,productDAO.getLatestActiveproducts(3).size());
	}

}
