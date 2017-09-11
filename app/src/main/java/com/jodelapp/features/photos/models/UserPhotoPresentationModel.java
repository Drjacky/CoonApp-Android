package com.jodelapp.features.photos.models;

public class UserPhotoPresentationModel {

	private String albumId;
	private String id;
	private String title;
	private String url;
	private String thumbnailUrl;

	public UserPhotoPresentationModel(String albumId, String id, String title, String url, String thumbnailUrl) {
		this.albumId = albumId;
		this.id = id;
		this.title = title;
		this.url = url;
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getAlbumId(){
		return albumId;
	}

	public String getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public String getUrl(){
		return url;
	}

	public String getThumbnailUrl(){
		return thumbnailUrl;
	}

}