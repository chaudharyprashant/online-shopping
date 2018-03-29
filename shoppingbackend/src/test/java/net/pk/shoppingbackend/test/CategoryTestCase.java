package net.pk.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.pk.shoppingbackend.dao.CategoryDAO;
import net.pk.shoppingbackend.dto.Category;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;

	private static CategoryDAO categoryDAO;

	private Category category;

	@BeforeClass
	public static void init() {

		context = new AnnotationConfigApplicationContext();
		context.scan("net.pk.shoppingbackend");
		context.refresh();
		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
	}

	/*
	 * @Test public void testAddCategory(){ category=new Category();
	 * category.setName("Mobile");
	 * category.setDescription("This is some description for Mobile");
	 * category.setImageURL("cat3.png");
	 * assertEquals("Successfully added a category inside a table",true,
	 * categoryDAO.add(category)); }
	 */

	/*
	 * @Test public void getTestCategory() { category=categoryDAO.get(3);
	 * assertEquals("Successfully fetched a category from a table","Mobile",
	 * category.getName()); }
	 */

	/*
	 * @Test public void testUpdateCategory() { category=categoryDAO.get(3);
	 * category.setName("TV");
	 * assertEquals("Successfully updated a single category in the table",true,
	 * categoryDAO.update(category)); }
	 */

	/*
	 * @Test public void testDeleteCategory() { category=categoryDAO.get(3);
	 * assertEquals("Successfully deleted a single category in the table",true,
	 * categoryDAO.delete(category)); }
	 */

	/*
	 * @Test public void testListCategory() {
	 * 
	 * assertEquals("Successfully fetched the list category from the table",3,
	 * categoryDAO.list().size()); }
	 */
	@Test
	public void testCURDProperty() {

		category = new Category();
		category.setName("Laptop");
		category.setDescription("This is some description for Mobile");
		category.setImageURL("cat1.png");
		assertEquals("Successfully added a category inside a table", true, categoryDAO.add(category));

		category = new Category();
		category.setName("TELEVISION");
		category.setDescription("This is some description for Mobile");
		category.setImageURL("cat2.png");
		assertEquals("Successfully added a category inside a table", true, categoryDAO.add(category));

		category = new Category();
		category.setName("Mobile");
		category.setDescription("This is some description for Mobile");
		category.setImageURL("cat3.png");
		assertEquals("Successfully added a category inside a table", true, categoryDAO.add(category));
		  //fetching and updating the category
		category=categoryDAO.get(2);
		 category.setName("TV");
		 assertEquals("Successfully updated a single category in the table",true,categoryDAO.update(category)); 
		 //delete the category
		 assertEquals("Successfully deleted a single category in the table",true, categoryDAO.delete(category));
		 
		 assertEquals("Successfully fetched the list category from the table",2,categoryDAO.list().size());
		
	}
}
