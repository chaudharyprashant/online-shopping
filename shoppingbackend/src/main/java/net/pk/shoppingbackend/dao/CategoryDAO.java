package net.pk.shoppingbackend.dao;

import java.util.List;

import net.pk.shoppingbackend.dto.Category;

public interface CategoryDAO {

	
	List<Category> list();
	Category get(int id);
}
