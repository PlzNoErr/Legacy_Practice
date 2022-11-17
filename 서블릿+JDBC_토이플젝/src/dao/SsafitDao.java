package dao;

import java.sql.SQLException;
import java.util.List;

import dto.Review;
import dto.User;
import dto.Video;


public interface SsafitDao {
	//비디오 목록을 모두 가져오기 + 중복영상 체크 용으로도 활용
	public List<Video> allVideo();
		
	//videoId에 맞는 video 받아오기
	public Video takeVideo(int videoId);
	
	//videoId를 받고 영상 삭제
	public void removeVideo(int videoId) throws SQLException;
	
	//비디오를 추가하기
	public void addVideo(Video v) throws SQLException;
	
	//회원가입
	public void addUser(User u) throws SQLException;
	
	//로그인시 or 회원가입시 아이디 중복을 check하기 위하여 회원정보를 모두 가져오기
	public List<User> allUser();
	
	//url에 맞는 리뷰를 모두 가져오기 
	public List<Review> allReview(String url);
	
	//리뷰를 등록하기
	public void addReview(Review r) throws SQLException;
	
	//리뷰 id를 이용하여 리뷰 삭제하기
	public void removeReview(int reviewId) throws SQLException;

}
