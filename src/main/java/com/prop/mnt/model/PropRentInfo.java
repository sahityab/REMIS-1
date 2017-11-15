package com.prop.mnt.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="prop_rentinfo")
public class PropRentInfo {
	@Id 
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="prop_user_id")
	private PropUser propUser;
	
	@Column(name = "duedate") 
	private Integer duedate;
	
	@Column(name = "prop_rentterms") 
	private String rentterms;
	
	@Column(name = "prop_rentamount") 
	private Float rentamount;
	
	@OneToMany(mappedBy="propRentInfo", cascade=CascadeType.ALL)  
	 private Set<PropTntPayments> propTntPays;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PropUser getPropUser() {
		return propUser;
	}

	public void setPropUser(PropUser propUser) {
		this.propUser = propUser;
	}

	public Integer getDuedate() {
		return duedate;
	}

	public void setDuedate(Integer duedate) {
		this.duedate = duedate;
	}

	public String getRentterms() {
		return rentterms;
	}

	public void setRentterms(String rentterms) {
		this.rentterms = rentterms;
	}

	public Float getRentamount() {
		return rentamount;
	}

	public void setRentamount(Float rentamount) {
		this.rentamount = rentamount;
	}

	public Set<PropTntPayments> getPropTntPays() {
		return propTntPays;
	}

	public void setPropTntPays(Set<PropTntPayments> propTntPays) {
		this.propTntPays = propTntPays;
	}  
	
	
	
	
}
