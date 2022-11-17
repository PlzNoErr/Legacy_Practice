package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.Love;
import dto.Review;
import dto.User;
import dto.Video;
import util.DBUtil;

//Database Access Object
public class SsafitDaoImpl implements SsafitDao {
	// 만들어진 DBUtil 들고오기
	private final DBUtil util = DBUtil.getInstance();

	private static SsafitDaoImpl instance = new SsafitDaoImpl();

	private SsafitDaoImpl() {
	}

	public static SsafitDaoImpl getInstance() {
		return instance;
	}
//-------------------------------------------------------------------//

// 비디오 목록을 모두 가져오기 + 중복영상 체크 용으로도 활용
	@Override
	public List<Video> allVideo() {
		String sql = "SELECT * FROM video ORDER BY count DESC";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Video> list = new ArrayList<>();

		try {
			conn = util.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Video v = new Video();
				v.setVideoId(rs.getInt("videoId"));
				v.setUrl(rs.getString("url"));
				v.setPart(rs.getString("part"));
				v.setTitle(rs.getString("title"));
				v.setContent(rs.getString("content"));
				v.setCount(rs.getInt("count"));
				list.add(v);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			util.close(rs, stmt, conn);
		}
		return list;
	};

// videoId에 맞는 video 받아오기
	@Override
	public Video takeVideo(int videoId) {
		String sql = "SELECT * FROM video WHERE videoId = ? ";
		Video v = new Video();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = util.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, videoId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				v.setVideoId(rs.getInt("videoId"));
				v.setUrl(rs.getString("url"));
				v.setPart(rs.getString("part"));
				v.setTitle(rs.getString("title"));
				v.setContent(rs.getString("content"));
				v.setCount(rs.getInt("count"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pstmt, conn);
		}
		return v;
	};

	public void addVideoCount(int videoId) {
		String sql = "UPDATE video SET count = count+1 WHERE videoId = ? ";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = util.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, videoId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			util.close(rs, pstmt, conn);
		}
	}

// videoId를 받고 영상 삭제
	@Override
	public void removeVideo(int videoId) throws SQLException {
		String sql = "DELETE FROM video WHERE videoId = ? ";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = util.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, videoId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			util.close(rs, pstmt, conn);
		}
	};

// 비디오를 추가하기
	@Override
	public void addVideo(Video v) throws SQLException {
		String sql = "INSERT INTO video (url, part, title, content, count) VALUES (?,?,?,?,?)";

		// DB 접속해서 사용하게끔 쓰자.
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = util.getConnection();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, v.getUrl());
			pstmt.setString(2, v.getPart());
			pstmt.setString(3, v.getTitle());
			pstmt.setString(4, v.getContent());
			pstmt.setInt(5, 0);

			// 이걸 실행해야 실제로 쿼리를 업데이트 한다.
			int result = pstmt.executeUpdate();
			// System.out.println(result);
			// 출력해 보면 숫자가 나온다. 몇개 행이나 고쳤는지 보여주는것. 만약 0이나오면 뭔가 잘못된것.

			// 지금까지 작업한거 커밋날려라.
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt, conn);
		}
	};

// 회원가입
	@Override
	public void addUser(User u) throws SQLException {
		String sql = "INSERT INTO user VALUES (?,?,?,?)";

		// DB 접속해서 사용하게끔 쓰자.
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = util.getConnection();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, u.getUserId());
			pstmt.setString(2, u.getPassword());
			pstmt.setString(3, u.getUserName());
			pstmt.setString(4, u.getEmail());

			// 이걸 실행해야 실제로 쿼리를 업데이트 한다.
			int result = pstmt.executeUpdate();
			// System.out.println(result);
			// 출력해 보면 숫자가 나온다. 몇개 행이나 고쳤는지 보여주는것. 만약 0이나오면 뭔가 잘못된것.

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt, conn);
		}

	};

// 로그인시 or 회원가입시 아이디 중복을 check하기 위하여 회원정보를 모두 가져오기
	@Override
	public List<User> allUser() {
		String sql = "SELECT * FROM user";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<>();

		try {
			conn = util.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				User u = new User();
				u.setUserId(rs.getString("userId"));
				u.setPassword(rs.getString("password"));
				u.setUserName(rs.getString("userName"));
				u.setEmail(rs.getString("email"));
				list.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			util.close(rs, stmt, conn);
		}
		return list;
	};

// url에 맞는 리뷰를 모두 가져오기
	@Override
	public List<Review> allReview(String url) {
		String sql = "SELECT * FROM review WHERE url = ? ";
		List<Review> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = util.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, url);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Review r = new Review();
				r.setReviewId(rs.getInt("reviewId"));
				r.setWriter(rs.getString("writer"));
				r.setContent(rs.getString("content"));
				r.setUrl(rs.getString("url"));
				list.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pstmt, conn);
		}
		return list;
	};

// 리뷰를 등록하기
	@Override
	public void addReview(Review r) throws SQLException {
		String sql = "INSERT INTO review (writer, content, url) VALUES (?,?,?)";

		// DB 접속해서 사용하게끔 쓰자.
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = util.getConnection();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, r.getWriter());
			pstmt.setString(2, r.getContent());
			pstmt.setString(3, r.getUrl());

			// 이걸 실행해야 실제로 쿼리를 업데이트 한다.
			int result = pstmt.executeUpdate();
			// System.out.println(result);
			// 출력해 보면 숫자가 나온다. 몇개 행이나 고쳤는지 보여주는것. 만약 0이나오면 뭔가 잘못된것.

			// 지금까지 작업한거 커밋날려라.
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt, conn);
		}
	};

// 리뷰 id를 이용하여 리뷰 삭제하기
	@Override
	public void removeReview(int reviewId) throws SQLException {
		String sql = "DELETE FROM review WHERE reviewId = ? ";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = util.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			util.close(rs, pstmt, conn);
		}
	};

	// 아이디에 맞는 좋아요 싸그리 가져오기
	public List<Love> allLove(String userId) {
		String sql = "SELECT * FROM love WHERE userId = ? ";
		List<Love> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = util.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Love l = new Love();
				l.setUserId(rs.getString("userId"));
				l.setUrl(rs.getString("url"));
				list.add(l);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pstmt, conn);
		}
		return list;
	};

	// 좋아요 추가
	public void addLove(Love l) throws SQLException {
		String sql = "INSERT INTO love VALUES (?,?)";

		// DB 접속해서 사용하게끔 쓰자.
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = util.getConnection();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, l.getUserId());
			pstmt.setString(2, l.getUrl());

			// 이걸 실행해야 실제로 쿼리를 업데이트 한다.
			int result = pstmt.executeUpdate();
			// System.out.println(result);
			// 출력해 보면 숫자가 나온다. 몇개 행이나 고쳤는지 보여주는것. 만약 0이나오면 뭔가 잘못된것.

			// 지금까지 작업한거 커밋날려라.
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt, conn);
		}
	};

	// 좋아요 삭제
	public void removeLove(String userId, String url) throws SQLException {
		String sql = "DELETE FROM love WHERE userId = ? AND url = ? ";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = util.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, url);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			util.close(rs, pstmt, conn);
		}
	};

}