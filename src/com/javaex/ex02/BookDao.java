package com.javaex.ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

	//필드
	private String id = "webdb";
	private String pw = "webdb";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//생성자
	
	
	//메소드-gs
	
	
	//메소드-일반
	//>DB 연결 메소드
	public void getConnection() {

		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
	}
	
	//자원 정리 메소드
	public void close() {
		
		// 5. 자원정리
		try {
			
			if (rs != null) {
				rs.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
	}
	
	//책 등록 메소드
	public int bookInsert(String title, String pubs, String pubDate, int authorId) {
		
		int count = -1;
		
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			
			//SQL문 준비
			String query = "";
			query += " insert into book ";
			query += " values (seq_book_id.nextval, ?, ?, ?, ?) ";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pubDate);
			pstmt.setInt(4, authorId);
			
			//실행
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println("count " + count + "건이 생성되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		close();
		
		return count;
		
	}
	
	
	//책 삭제 메소드
	public int bookDelete(int bookId) {
		
		int count = -1;
		
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			
			//SQL문 준비
			String query = "";
			query += " delete from book ";
			query += " where book_id = ? ";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookId);
			
			//실행
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println("count " + count + "건이 삭제되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		close();
		
		return count;
		
	}
	
	//책 수정 메소드
	public int bookUpdate(String bookDate, int bookId) {
		
		int count = -1;
		
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			
			//SQL문 준비
			String query = "";
			query += " update book ";
			query += " set pub_date = ? ";
			query += " where book_id = ? ";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bookDate);
			pstmt.setInt(2, bookId);
			
			//실행
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println("count " + count + "건이 수정되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		close();
		
		return count;
		
	}
	
	//책 리스트 전체 가져오기 메소드
	public List<BookVo> bookSelect() {
		
		//리스트 만들기
		List<BookVo> bookList = new ArrayList<BookVo>();
		
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			
			//SQL문 준비
			String query = "";
			query += " select book_id ";
			query += "        , title ";
			query += "        , pubs ";
			query += "        , to_char (pub_date, 'YYYY-MM-DD') pub_date ";
			query += "        , author_name ";
			query += " from book b, author a ";
			query += " where a.author_id = b.author_id ";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			
			//실행
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			//반복문으로 Vo 만들기 List에 추가하기
			while(rs.next()) {
				
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				String authorName = rs.getString("author_name");
				
				//System.out.println(bookId + "번 책 | 제목: " + title + " | 출판사: " + pubs + " | 출간일: " + pubDate + " | 작가 번호: " + authorId +"번");
				//System.out.println();
				
				BookVo bookVo = new BookVo(bookId, title, pubs, pubDate, authorName);
				
				bookList.add(bookVo);
				
			}
			
			//리스트 출력해 보기
			//System.out.println(bookList.toString());

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		close();
		
		return bookList;
		
	}
	
}
