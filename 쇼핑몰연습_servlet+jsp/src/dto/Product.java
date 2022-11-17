package dto;

public class Product {
	private String code;
	private String name;
	private int price;
	private int mount;
	private String brand;
	private String exp;

	// 기본 생성자
	public Product() {
	}

	// 인자를 받는 생성자
	public Product(String code, String name, int price, int mount, String brand, String exp) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.mount = mount;
		this.brand = brand;
		this.exp = exp;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getMount() {
		return mount;
	}

	public void setMount(int mount) {
		this.mount = mount;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	@Override
	public String toString() {
		return "상품정보 [code=" + code + ", name=" + name + ", price=" + price + ", mount=" + mount + 
				", brand=" + brand + "]";
	}
}
