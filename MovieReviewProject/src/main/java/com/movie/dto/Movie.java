package com.movie.dto;

import java.util.Date;

public class Movie {
    private int movie_id;
    private String title;
    private String director;
    private Date release_date;
    private Double avgRating; // 선택 사항
	public Movie(int movie_id, String title, String director, Date release_date) {
		super();
		this.movie_id = movie_id;
		this.title = title;
		this.director = director;
		this.release_date = release_date;
		//this.avgRating = avgRating;
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public Date getRelease_date() {
		return release_date;
	}
	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}
	public Double getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(Double avgRating) {
		this.avgRating = avgRating;
	}

    
}
