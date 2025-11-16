package com.movie.dto;

public class Member {
    private String userid;
    private String pwd;
    private String name;
    private String email;
    private String birth; // yyyy-mm-dd
	public Member(String userid, String pwd, String name, String email, String birth) {
		super();
		this.userid = userid;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.birth = birth;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}

}
