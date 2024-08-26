package com.cdac.training.sms.model;


import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.NonNull;

@Entity
public class Address {
	
	@Id
	@SequenceGenerator(name="Address_Id",initialValue = 250,allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator = "Address_Id")
	private Long addressid;
	
	@NonNull
	private String street;
	@NonNull
	private String city;
	@NonNull
	private int pincode;
	
	@OneToOne
	@JoinColumn(name="dealer_id")
	private Dealer dealer;

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(Long addressid, @NonNull String street, @NonNull String city, @NonNull int pincode, Dealer dealer) {
		super();
		this.addressid = addressid;
		this.street = street;
		this.city = city;
		this.pincode = pincode;
		this.dealer = dealer;
	}

	public Long getAddressid() {
		return addressid;
	}

	public void setAddressid(Long addressid) {
		this.addressid = addressid;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	
}
