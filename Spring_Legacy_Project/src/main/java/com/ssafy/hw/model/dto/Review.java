package com.ssafy.hw.model.dto;

public class Review {
	/** 리뷰 ID */
	private int reviewId;
    /** 상품 코드 */
	private String pCode;
    /** 작성자 */
	private String writer;
    /** 내용 */
	private String content;
    
    
	public Review() {
		super();
	}

	
	
	public Review(String pCode, String writer, String content) {
		this.pCode = pCode;
		this.writer = writer;
		this.content = content;
	}



	public int getReviewId() {
		return reviewId;
	}


	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}


	public String getpCode() {
		return pCode;
	}


	public void setpCode(String pCode) {
		this.pCode = pCode;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", pCode=" + pCode + ", writer=" + writer + ", content=" + content
				+ "]";
	}

}
