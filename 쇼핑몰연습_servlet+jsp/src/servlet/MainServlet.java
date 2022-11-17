package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Basket;
import dto.Product;
import dto.Review;
import dto.User;
import manager.ProductManager;
import manager.UserManager;

@WebServlet("/main")
// @WebServlet({ "/main", "/MainServlet" }) 다수의 action 요청이 있을때 표현 법

public class MainServlet extends HttpServlet {
	// 이 서블릿이 호출되기 위해서는 url 상에 http://server_ip:port/context_name/main 이 필요하다.

	private static final long serialVersionUID = 1L;

	/**
	 * get 방식의 요청에 대해 응답하는 메서드이다. front controller pattern을 적용하기 위해 내부적으로 process를
	 * 호출한다.
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * post 방식의 요청에 대해 응답하는 메서드이다. front controller pattern을 적용하기 위해 내부적으로 process를
	 * 호출한다.
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// post 요청 시 한글 파라미터의 처리를 위해 encoding을 처리한다.
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}

	/**
	 * request 객체에서 action 파라미터를 추출해서 실제 비지니스 로직 메서드(ex: doRegist) 호출해준다.
	 */
	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "ProductRegist":
			doRegist(request, response);
			break;
		case "ProductList":
			showList(request, response);
			break;
		case "showRegistPage":
			showRegistPage(request, response);
			break;
		case "showDetail":
			showDetail(request, response);
			break;
		case "registReview":
			registReview(request, response);
			break;
		case "signup":
			doSignup(request, response);
			break;
		case "login":
			doLogin(request, response);
			break;
		case "logout":
			doLogout(request, response);
			break;
		case "myBasket":
			domyBasket(request, response);
			break;
		case "goaddBasket":
			dogoaddBasket(request, response);
			break;
		case "addBasket":
			doaddBasket(request, response);
			break;
		}
	}

	/**
	 * 자동차 정보를 등록하기 위해 파라미터가 잘 전달되는지 확인하고 전달 결과를 화면에 출력한다. 이를 위해 request 전달 받은 내용을
	 * 추출해서 Car 객체를 생성한 후 response 출력한다. 특히 response 시 content의 형식에 주의한다.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doRegist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// request 객체에서 전달된 parameter를 추출한다.
		String name = request.getParameter("name");
		String brand = request.getParameter("brand");
		String exp = request.getParameter("exp");
		String code = request.getParameter("code");

		// 문자열로 전달된 mileage는 숫자로 변환
		int price = Integer.parseInt(request.getParameter("price"));
		int mount = Integer.parseInt(request.getParameter("mount"));

		// 전달받은 parameter를 이용해서 User 객체를 생성한다.
		Product p = new Product(code, name, price, mount, brand, exp);
		ProductManager manager = ProductManager.getInstance();
		ArrayList<Product> list = manager.getList();
		manager.regist(p);
		RequestDispatcher disp = request.getRequestDispatcher("/ProductResult.jsp");
		disp.forward(request, response);

	}

	private void showList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/ProductList.jsp");
		// 포워도 방식말고 리다이랙트 방식으로 고쳐보자.
	}

	private void showRegistPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/ProductRegist.jsp");
	}

	private void showDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		ProductManager manager = ProductManager.getInstance();
		Product Product;
		ArrayList<Product> Plist = manager.getList();
		for (int i = 0; i < Plist.size(); i++) {
			if (Plist.get(i).getCode().equals(code)) {
				Product = Plist.get(i);
				request.setAttribute("Product", Product);
				break;
			}
		}
		ArrayList<Review> Rlist = manager.getReview(code);
		request.setAttribute("Rlist", Rlist);
		request.getRequestDispatcher("/ProductDetail.jsp").forward(request, response);
	}

	private void registReview(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int reviewId = 1;
		String code = request.getParameter("code");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		Review r = new Review(reviewId, code, writer, content);

		ProductManager manager = ProductManager.getInstance();
		manager.registReview(r);
		showDetail(request, response);
	}

	private void doSignup(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String passwordCheck = request.getParameter("passwordCheck");
		String userName = request.getParameter("userName");
		String nickName = request.getParameter("nickName");
		String email = request.getParameter("email");

		User user = new User(0, userId, password, userName, nickName, email);
		UserManager.getInstance().addUser(user);
		// 로그인 처리
		// session에 loginUser를 저장.
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", UserManager.getInstance().getUser(userId, password));
		response.sendRedirect("index.jsp");
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		User user = UserManager.getInstance().getUser(userId, password);
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
			response.sendRedirect("index.jsp");
		} else {
			request.setAttribute("msg", "로그인 실패");
			request.getRequestDispatcher("user/fail.jsp").forward(request, response);
		}
	}

	private void doLogout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("index.jsp");
	}

	private void domyBasket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		ProductManager manager = ProductManager.getInstance();
		ArrayList<Basket> temp = new ArrayList<>();
		for (int i = 0; i < manager.getBlist().size(); i++) {
			if (manager.getBlist().get(i).getUserId().equals(userId)) {
				temp.add(manager.getBlist().get(i));
			}
		}
		int sum = 0;
		for (int i = 0; i < temp.size(); i++) {
			sum += temp.get(i).getpPrice()*temp.get(i).getCnt();
		}
		request.setAttribute("Sum", sum);
		request.setAttribute("myBasket", temp);
		request.getRequestDispatcher("myBasket.jsp").forward(request, response);
	}

	private void dogoaddBasket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		ProductManager manager = ProductManager.getInstance();
		Product Product;
		ArrayList<Product> Plist = manager.getList();
		for (int i = 0; i < Plist.size(); i++) {
			if (Plist.get(i).getCode().equals(code)) {
				Product = Plist.get(i);
				request.setAttribute("Product", Product);
				break;
			}
		}
		request.getRequestDispatcher("addBasket.jsp").forward(request, response);
	}

	private void doaddBasket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		User user = (User)request.getSession().getAttribute("loginUser");
		String userId = user.getUserId();
		int mount = Integer.parseInt(request.getParameter("mount"));
		ProductManager manager = ProductManager.getInstance();
		Product Product;
		ArrayList<Product> Plist = manager.getList();
		for (int i = 0; i < Plist.size(); i++) {
			if (Plist.get(i).getCode().equals(code)) {
				Product = Plist.get(i);
				if (manager.getBlist() == null || manager.getBlist().size() == 0) {
					Basket b = new Basket();
					b.setBasketNo(1);
					b.setCnt(mount);
					b.setpCode(Product.getCode());
					b.setpName(Product.getName());
					b.setpPrice(Product.getPrice());
					b.setUserId(userId);
					manager.bRegist(b);
					break;
					
				}
				Basket b = new Basket();
				b.setBasketNo(manager.getBlist().size()+1);
				b.setCnt(mount);
				b.setpCode(Product.getCode());
				b.setpName(Product.getName());
				b.setpPrice(Product.getPrice());
				b.setUserId(userId);
				manager.bRegist(b);
				break;
			}
		}
		
		request.getRequestDispatcher("ProductList.jsp").forward(request, response);
	}

}//