package dto;

public class Love {
	private String userId;
	private String url;
	
	public Love() {
		
	}

	public Love(String userId, String url) {
		super();
		this.userId = userId;
		this.url = url;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
