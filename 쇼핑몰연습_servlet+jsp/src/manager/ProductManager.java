package manager;

import java.util.ArrayList;
import java.util.HashMap;

import dto.Basket;
import dto.Product;
import dto.Review;

public class ProductManager {

	private ArrayList<Product> list = new ArrayList<>();
	private ArrayList<Basket> bList = new ArrayList<>();
	private HashMap<String, ArrayList<Review>> map = new HashMap<>();

	// 싱글턴 패턴을 이용하여 manager 만들고 쓸 수 있도록 했다.
	private static ProductManager manager = new ProductManager();

	// 싱글턴 패턴이므로 또다른 인스턴스를 생성하지 못하게 생성자를 private로 막아버림.
	private ProductManager() {
	}

	public static ProductManager getInstance() {
		return manager;
	}

	// 상품 등록
	public void regist(Product p) {
		list.add(p);
		// map.put(p.getName(), p.getExp());
	}
	
	// 장바구니 등록
	public void bRegist(Basket b) {
		bList.add(b);
		// map.put(p.getName(), p.getExp());
	}

	// 리뷰 등록
	public void registReview(Review r) {
		if (map.get(r.getCode()) == null) {
			ArrayList<Review> temp = new ArrayList<>();
			temp.add(r);
			map.put(r.getCode(), temp);
			return;
		}
		ArrayList<Review> temp = map.get(r.getCode());
		r.setReviewId(temp.size()+1);
		temp.add(r);
		map.put(r.getCode(), temp);
	}

	// 리스트 몽땅 (getter)
	public ArrayList<Product> getList() {
		return list;
	}
	
	// 장바구니 리스트 몽땅
	public ArrayList<Basket> getBlist() {
		return bList;
	}

	// 상품명에 맞는 리뷰들 받기
	public ArrayList<Review> getReview(String code) {
		return map.get(code);
	}

}
