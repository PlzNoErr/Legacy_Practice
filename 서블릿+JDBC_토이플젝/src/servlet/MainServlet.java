package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SsafitDaoImpl;
import dto.Love;
import dto.Review;
import dto.User;
import dto.Video;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private SsafitDaoImpl instance = SsafitDaoImpl.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "login":
			doLogIn(request, response);
			break;
		case "logout":
			doLogOut(request, response);
			break;
		case "list":
			doList(request, response);
			break;
		case "videoRegist":
			doVideoRegist(request, response);
			break;
		case "detail":
			doDetail(request, response);
			break;
		case "addReview":
			addReview(request, response);
			break;
		case "signup":
			signUp(request, response);
			break;
		case "reviewModify":
			doReviewModify(request, response);
			break;
		case "showpart":
			showPart(request, response);
			break;
		case "reviewDelete":
			doReviewDelete(request, response);
			break;
		case "removeVideo":
			doRemoveVideo(request, response);
			break;
		case "addLike":
			doAddLike(request, response);
			break;
		case "myPage":
			doMyPage(request, response);
			break;
		case "removeLike":
			doremoveLike(request, response);
			break;
		case "gosignup":
			response.sendRedirect("signup.jsp");
			break;
		}
	}


	private void doRemoveVideo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int videoId = Integer.parseInt(request.getParameter("videoId"));
		try {
			instance.removeVideo(videoId);
			response.sendRedirect("main?action=list");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	private void doLogIn(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userid");
		String password = request.getParameter("userpass");

		List<User> list = instance.allUser();
		User user = null;
		for (User u : list) {
			if (userId.equals(u.getUserId()) && password.equals(u.getPassword())) {
				user = u;
				break;
			}
		}

		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
			response.sendRedirect("index.jsp");
		} else {
			request.setAttribute("msg", "로그인에 실패하였습니다.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}

	}

	private void doLogOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("index.jsp");
	}

	private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Video> list = instance.allVideo();
		List<Video> list2 = new ArrayList<>();
		list2.add(list.get(1));
		list2.add(list.get(2));
		request.setAttribute("videos", list);
		request.setAttribute("videos2", list2);
		request.getRequestDispatcher("videoList.jsp").forward(request, response);
	}

	private void doVideoRegist(HttpServletRequest request, HttpServletResponse response) {
		String url = request.getParameter("url");
		String title = request.getParameter("title");
		String part = request.getParameter("part");
		String content = request.getParameter("videoContent");

		Video video = new Video(url, part, title, content);

		List<Video> list = instance.allVideo();

		for (Video v : list) {
			if (v.getUrl().equals(url)) {
				String msg = "이미 존재하는 영상입니다.";
				request.setAttribute("msg", msg);
				try {
					request.getRequestDispatcher("videoRegist.jsp").forward(request, response);
					return;
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
		}

		try {
			instance.addVideo(video);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		list = instance.allVideo();

		int videoid = 0;
		for (Video v : list) {
			if (v.getUrl().equals(url)) {
				videoid = v.getVideoId();
				break;
			}
		}

		try {
			response.sendRedirect("main?action=detail&videoId=" + videoid);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void doDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 파라미터로 videoId(int) 들어옴
		int videoId = Integer.parseInt(request.getParameter("videoId"));
		// videoId를 이용해서 해당 비디오의 정보를 불러오고 Attribute에 넣어줌.
		Video video = instance.takeVideo(videoId);
		instance.addVideoCount(videoId);
		request.setAttribute("video", video);

		// 해당 url을 활용하여 리뷰를 가져오고 Attribute에 넣어준다.
		String url = request.getParameter("url");
		List<Review> list = instance.allReview(url);
		request.setAttribute("reviews", list);

		// 포워드로 전송
		request.getRequestDispatcher("videoDetail.jsp").forward(request, response);
	}

	private void addReview(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String url = request.getParameter("url");
		int videoId = Integer.parseInt(request.getParameter("videoId"));
		Review r = new Review();
		r.setWriter(writer);
		r.setContent(content);
		r.setUrl(url);

		try {
			instance.addReview(r);
			response.sendRedirect("main?action=detail&url=" + url + "&videoId=" + videoId);
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = (String) request.getParameter("id");
		String password = (String) request.getParameter("password");
		String userName = (String) request.getParameter("name");
		String email = (String) request.getParameter("email");
		List<User> list = instance.allUser();
		User u = new User(userId, password, userName, email);

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUserId().equals(userId)) {
				request.setAttribute("msg", "중복된 아이디입니다.");
				request.getRequestDispatcher("signup.jsp").forward(request, response);
				return;
			}
		}

		try {
			instance.addUser(u);
			request.setAttribute("msg", "회원가입 성공!");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void doReviewModify(HttpServletRequest request, HttpServletResponse response) {
		int reviewId = Integer.parseInt(request.getParameter("reviewId"));
		try {
			instance.removeReview(reviewId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String url = request.getParameter("url");
		Review review = new Review(writer, content, url);
		try {
			instance.addReview(review);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String videoId = request.getParameter("videoId");
		try {
			response.sendRedirect("main?action=detail&url=" + url + "&videoId=" + videoId);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showPart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String part = request.getParameter("part");
		List<Video> list = instance.allVideo();
		List<Video> RealList = new ArrayList<>();

		if (part.equals("full")) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getPart().equals("전신")) {
					RealList.add(list.get(i));
				}
			}
		}
		if (part.equals("up")) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getPart().equals("상체")) {
					RealList.add(list.get(i));
				}
			}
		}
		if (part.equals("bottom")) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getPart().equals("하체")) {
					RealList.add(list.get(i));
				}
			}
		}
		if (part.equals("core")) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getPart().equals("코어")) {
					RealList.add(list.get(i));
				}
			}
		}
		if (part.equals("etc")) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getPart().equals("기타")) {
					RealList.add(list.get(i));
				}
			}
		}
		request.setAttribute("videos", list);
		request.setAttribute("videos2", RealList);
		request.getRequestDispatcher("videoList.jsp").forward(request, response);
	}

	private void doReviewDelete(HttpServletRequest request, HttpServletResponse response) {
		int reviewId = Integer.parseInt(request.getParameter("reviewId"));
		try {
			instance.removeReview(reviewId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String url = request.getParameter("url");
		String videoId = request.getParameter("videoId");
		try {
			response.sendRedirect("main?action=detail&url=" + url + "&videoId=" + videoId);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private void doAddLike(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    String userId = ((User)request.getSession().getAttribute("loginUser")).getUserId();
	    String videoId = request.getParameter("videoId");
	    String url = request.getParameter("url");
	    Love l = new Love(userId, url);
	    List<Love> list = instance.allLove(userId);
	    
	    for(int i = 0; i<list.size(); i++) {
	        if(list.get(i).getUrl().equals(url)) {
	            response.sendRedirect("main?action=detail&url=" + url + "&videoId=" + videoId);
	            return;
	        }
	    }
	    
	    try {
	        instance.addLove(l);
	        response.sendRedirect("main?action=detail&url=" + url + "&videoId=" + videoId);
	        return;
	    } catch (SQLException e) {
	        // TODO Auto-generated catch block
	    }
	}

	private void doMyPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	    String userId = ((User)request.getSession().getAttribute("loginUser")).getUserId();
	    List<Love> love = instance.allLove(userId);
	    List<Video> All = instance.allVideo();
	    List<Video> list = new ArrayList<>();
	    
	    for(int i = 0; i<love.size(); i++) {
	        int videoId = 0;
	        for(int k = 0; k<All.size(); k++) {
	            if(love.get(i).getUrl().equals(All.get(k).getUrl())) {
	                videoId = All.get(k).getVideoId();
	                break;
	            }
	        }
	        System.out.println(videoId);
	        list.add(instance.takeVideo(videoId));
	    }
	    
	    request.setAttribute("videos", list);
	    request.getRequestDispatcher("myPage.jsp").forward(request, response);
	}
	
	private void doremoveLike(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
	    String userId = ((User)request.getSession().getAttribute("loginUser")).getUserId();
	    String url = request.getParameter("url");
	    
	    try {
	        instance.removeLove(userId, url);
	        response.sendRedirect("main?action=myPage");
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
