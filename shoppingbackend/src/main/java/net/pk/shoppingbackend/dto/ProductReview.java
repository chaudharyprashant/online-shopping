package net.pk.shoppingbackend.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductReview {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int reviewId;
	@Column(name="username")
	private String userName;	
	private int productId;
	private int categoryId;
	private String reviewClass;

	public String getReviewClass() {
		return reviewClass;
	}
	public void setReviewClass(String reviewClass) {
		this.reviewClass = reviewClass;
	}
	private String productReview;
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getProductReview() {
		return productReview;
	}
	public void setProductReview(String productReview) {
		this.productReview = productReview;
	}
	@Override
	public String toString() {
		return "ProductReview [reviewId=" + reviewId + ", userName=" + userName + ", productId=" + productId
				+ ", categoryId=" + categoryId + ", productReview=" + productReview + "]";
	}
	
	
}
