package net.pk.shoppingbackend.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;




	@Override
	public String toString() {
		return "Product [id=" + id + ", code=" + code + ", name=" + name + ", brand=" + brand + ", description="
				+ description + ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", active=" + active
				+ ", categoryId=" + categoryId + ", supplierId=" + supplierId + ", purchase=" + purchase + ", views="
				+ views + "]";
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String code;
	@NotBlank(message="Please enter the Product Name!")
	private String name;
	@NotBlank(message="Please enter the Brand Name!")
	private String brand;
	@JsonIgnore
	@NotBlank(message="Please enter the description for product!")
	private String description;
	@Column(name="unit_price")
	@Min(value=1, message="The Price cannot be less than 1 !")
	private double unitPrice;
	private int quantity;
	@Column(name="is_active")
	@JsonIgnore
	private boolean active;
	@Column(name="category_id")
	@JsonIgnore
	private int categoryId;
	@Column(name="supplier_id")
	@JsonIgnore
	private int supplierId;
	@Column(name="purchases")
	private int purchase;
	private int views;
	private int positiveCount;
	private int negativeCount;
	
	
	
	
	public int getNegativeCount() {
		return negativeCount;
	}
	public void setNegativeCount(int negativeCount) {
		this.negativeCount = negativeCount;
	}
	public int getPositiveCount() {
		return positiveCount;
	}
	public void setPositiveCount(int positiveCount) {
		this.positiveCount = positiveCount;
	}
	@Transient
	private MultipartFile file;
	
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	//default constructer
	public Product()
	{
		this.code=UUID.randomUUID().toString().substring(26).toUpperCase();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public int getPurchase() {
		return purchase;
	}
	public void setPurchase(int purchase) {
		this.purchase = purchase;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}

}
