package com.jodelapp.features.photos.models;

public class UserPhotoAlbumPresentationModel {

	private String id;
	private String title;
	private String userId;

	public UserPhotoAlbumPresentationModel(String id, String title, String userId) {
		this.id = id;
		this.title = title;
		this.userId = userId;
	}

	public String getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public String getUserId(){
		return userId;
	}

}