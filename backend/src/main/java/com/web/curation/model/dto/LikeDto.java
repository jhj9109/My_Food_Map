package com.web.curation.model.dto;

public class LikeDto {
	private int no;
	private int reviewid;
	private String userid;
	private int uid;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getReviewid() {
		return reviewid;
	}
	public void setReviewid(int reviewid) {
		this.reviewid = reviewid;
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public LikeDto() {
		super();
	}
	public LikeDto(int no, int reviewid, String userid) {
		super();
		this.no = no;
		this.reviewid = reviewid;
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "LikeDto [no=" + no + ", reviewid=" + reviewid + ", userid=" + userid + "]";
	}
}
