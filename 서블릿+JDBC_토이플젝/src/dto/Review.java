package dto;

public class Review {
	int reviewId;
	String writer;
	String content;
	String url;
	
	public Review() {
		
	}
	
	//리뷰 받기용
	public Review(int reviewId, String writer, String content, String url) {
		super();
		this.reviewId = reviewId;
		this.writer = writer;
		this.content = content;
		this.url = url;
	}
	
	//리뷰 등록용
	public Review(String writer, String content, String url) {
		super();
		this.writer = writer;
		this.content = content;
		this.url = url;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	
}
