package net.pk.shoppingbackend.dao;

import java.util.List;

import net.pk.shoppingbackend.dto.ProductReview;

public interface ReviewDAO {

	List<ProductReview> getReviewList(int productId);
	boolean add(ProductReview productReview);
	boolean update(ProductReview productReview);
	boolean delete(ProductReview productReview);
   // boolean updateNegativePositiveCount(ProductReview productReview);
}
