package dto;

public class Review {
	int reviewId;
	String code;
	String writer;
	String content;
	
	public Review() {
		
	}
	
	public Review(int reviewId, String code, String writer, String content) {
		this.code = code;
		this.reviewId = reviewId;
		this.writer = writer;
		this.content = content;
	}
	
	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
		return "[ 작성자 : 리뷰 ] 나중에 쓸일 있으면 그때 고치자";
	}
	
}
