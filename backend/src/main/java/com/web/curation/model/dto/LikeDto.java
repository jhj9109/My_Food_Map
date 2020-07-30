package com.web.curation.model.dto;

public class LikeDto {
	private int no;
	private int reviewid;
<<<<<<< HEAD
	private int userid;
=======
	private String userid;
	private int uid;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
>>>>>>> 383c6138f0bede7017bc4921ff9861d0bd31cee0
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
<<<<<<< HEAD
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
=======
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
>>>>>>> 383c6138f0bede7017bc4921ff9861d0bd31cee0
		this.userid = userid;
	}
	public LikeDto() {
		super();
	}
<<<<<<< HEAD
	public LikeDto(int no, int reviewid, int userid) {
=======
	public LikeDto(int no, int reviewid, String userid) {
>>>>>>> 383c6138f0bede7017bc4921ff9861d0bd31cee0
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
