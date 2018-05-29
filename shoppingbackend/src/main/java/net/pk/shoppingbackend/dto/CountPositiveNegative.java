package net.pk.shoppingbackend.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CountPositiveNegative {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="positivecount")
	private int positiveCount;
	@Column(name="negativecount")
	private int negativeCount;
	
	
	public int getPositiveCount() {
		return positiveCount;
	}
	public void setPositiveCount(int positiveCount) {
		this.positiveCount = positiveCount;
	}
	public int getNegativeCount() {
		return negativeCount;
	}
	public void setNegativeCount(int negativeCount) {
		this.negativeCount = negativeCount;
	}
	

}
