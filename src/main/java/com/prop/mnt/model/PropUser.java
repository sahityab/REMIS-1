package com.prop.mnt.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
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
@Table(name="prop_user")
@Access(value=AccessType.FIELD)
public class PropUser implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RELATION_CODE")
	private PropUserRel propUserRel;
	

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PROP_CODE")
	private Property property;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USERID")
	private User user;
	
	@Column(name="STATUS")
	private String status;


/*	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="prop_div_id") 
	private PropertyDivision propertyDivision;
	
	@Column(name="rentamount")
	private Float rentamount;
	*/
	
	/*@OneToMany(mappedBy="propuser")
	private Set<PropRentPayments> propuserPayments;
	
	
	public PropertyDivision getPropertyDivision() {
		return propertyDivision;
	}

	public void setPropertyDivision(PropertyDivision propertyDivision) {
		this.propertyDivision = propertyDivision;
	}

	public Float getRentamount() {
		return rentamount;
	}

	public void setRentamount(Float rentamount) {
		this.rentamount = rentamount;
	}

	public Set<PropRentPayments> getPropuserPayments() {
		return propuserPayments;
	}

	public void setPropuserPayments(Set<PropRentPayments> propuserPayments) {
		this.propuserPayments = propuserPayments;
	}
	*/
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PropUserRel getPropUserRel() {
		return propUserRel;
	}

	public void setPropUserRel(PropUserRel propUserRel) {
		this.propUserRel = propUserRel;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	
	
	
	/*@Column(name = "RELATION_CODE")   
	private String relationCode;
	public String getRelationCode() {
		return relationCode;
	}

	public void setRelationCode(String relationCode) {
		this.relationCode = relationCode;
	}

	public Integer getPropCode() {
		return propCode;
	}

	@Column(name="PROP_CODE")
	private Integer propCode;
	public void setPropCode(Integer propCode) {
		this.propCode = propCode;
	}
	@Column(name="USERID")
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
*/

}
