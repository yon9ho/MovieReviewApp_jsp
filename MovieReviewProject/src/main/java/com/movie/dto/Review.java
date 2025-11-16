package com.movie.dto;


public class Review {
	private int reviewId;
	private int movieId;
	private String userId;
	private int rate;
	private String content;
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Review(int reviewId, int movieId, String userId, int rate, String content) {
		super();
		this.reviewId = reviewId;
		this.movieId = movieId;
		this.userId = userId;
		this.rate = rate;
		this.content = content;
	}
	
}
