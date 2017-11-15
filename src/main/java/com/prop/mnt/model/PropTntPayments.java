package com.prop.mnt.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="prop_ten_payment")
public class PropTntPayments {
	@Id 
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "prop_rentamount") 
	private Float rentamount;
	
	@Column(name="pay_date")
	private Date payDate;
	
	@ManyToOne(fetch=FetchType.LAZY)  
    @JoinColumn(name = "prop_rentinfo_id") 
	private PropRentInfo propRentInfo; 
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Float getRentamount() {
		return rentamount;
	}

	public void setRentamount(Float rentamount) {
		this.rentamount = rentamount;
	}

	
}
