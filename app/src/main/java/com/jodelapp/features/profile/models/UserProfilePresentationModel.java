package com.jodelapp.features.profile.models;

import com.jodelapp.data.models.Address;
import com.jodelapp.data.models.Company;

public class UserProfilePresentationModel {

	private String website;
	private Address address;
	private String phone;
	private String name;
	private Company company;
	private String id;
	private String email;
	private String username;

	public UserProfilePresentationModel(String id, String name, String username, String email, Address address, String phone, String website, Company company){
		this.website = website;
		this.address = address;
		this.phone = phone;
		this.name = name;
		this.company = company;
		this.id = id;
		this.email = email;
		this.username = username;
	}

	public String getWebsite(){
		return website;
	}

	public Address getAddress(){
		return address;
	}

	public String getPhone(){
		return phone;
	}

	public String getName(){
		return name;
	}

	public Company getCompany(){
		return company;
	}

	public String getId(){
		return id;
	}

	public String getEmail(){
		return email;
	}

	public String getUsername(){
		return username;
	}

}