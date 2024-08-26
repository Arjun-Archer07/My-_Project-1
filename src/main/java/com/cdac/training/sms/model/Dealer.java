package com.cdac.training.sms.model;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="dealers")
public class Dealer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="did")
	private Long id;
	
	@Column(name="first_name")
	private String fname;
	
	@Column(name="last_name")
	private String lname;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dob;
	private String password;
	
	@Column(name="phone",unique = true)
	private String phoneNo;
	
	@Column(name="email_Id",unique = true)
	private String email;
	
	// one to One mapped between dealer And Address
	/*
	 * Yeh address field hai jo Dealer aur Address ke beech
	 * one-to-one mapping dikhati hai. cascade =
	 * CascadeType.ALL matlab Dealer ke saath Address bhi
	 * save/update/delete hoga.
	 */
	@OneToOne(mappedBy = "dealer",cascade = CascadeType.ALL)
	private Address address;

	public Dealer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dealer(Long id, String fname, String lname, Date dob, String password, String phoneNo, String email,
			Address address) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.dob = dob;
		this.password = password;
		this.phoneNo = phoneNo;
		this.email = email;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPassword() {
//		Base64.Decoder decoder = Base64.getDecoder();
//        byte[] decodedBytes = decoder.decode(this.password);
//        return new String(decodedBytes, StandardCharsets.UTF_8);
	return password;
	}

	public void setPassword(String password) {
		
		Base64.Encoder encoder=Base64.getEncoder();
		String normalString=password;
		String encodedString=encoder.encodeToString(normalString.getBytes(StandardCharsets.UTF_8));
		this.password=encodedString;
		//this.password = password;
		
	
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
