package dto;

public class Basket {
	private int basketNo;
	private String userId;
	private String pCode;
	private String pName;
	private int pPrice;
	private int cnt;
	
	public Basket(){
		
	}
	
	public Basket(int basketNo, String userId, String pCode, String pName, int pPrice, int cnt) {
		super();
		this.basketNo = basketNo;
		this.userId = userId;
		this.pCode = pCode;
		this.pName = pName;
		this.pPrice = pPrice;
		this.cnt = cnt;
	}
	
	public int getBasketNo() {
		return basketNo;
	}

	public void setBasketNo(int basketNo) {
		this.basketNo = basketNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getpCode() {
		return pCode;
	}

	public void setpCode(String pCode) {
		this.pCode = pCode;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
}
