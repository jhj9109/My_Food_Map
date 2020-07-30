package com.web.curation.model.dto;

import java.sql.Date;

public class ReviewDto {
	private int no;
	private String email;
	private String content;
	private float rank;
	private Date create_date;
	private String image;
	private int userid;
	private boolean isLike;
	
	public boolean isLike() {
		return isLike;
	}
	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public float getRank() {
		return rank;
	}
	public void setRank(float rank) {
		this.rank = rank;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "ReviewDto [no=" + no + ", email=" + email + ", content=" + content + ", rank=" + rank + ", create_date="
				+ create_date + ", image=" + image + ", userid=" + userid + "]";
	}
	public ReviewDto(int no, String email, String content, float rank, Date create_date, String image, int userid) {
		super();
		this.no = no;
		this.email = email;
		this.content = content;
		this.rank = rank;
		this.create_date = create_date;
		this.image = image;
		this.userid = userid;
	}
	public ReviewDto() {
		super();
	}
	
	
}
