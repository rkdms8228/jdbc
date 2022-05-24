package com.javaex.ex05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/********** 
 * Dao(Data Access Object)
 * DataBase(오라클) 관련된 일을 하는 클래스
 **********/

public class AuthorDao {
	
	//필드
	private String id = "webdb";
	private String pw = "webdb";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	
	// 0. import java.sql.*;
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
	
	//>자원 정리 메소드
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
	
	//>작가 등록 메소드
	public int authorInsert(AuthorVo authorVo) {
		
		int count = -1;
		
		//this.getConnection();
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			
			//SQL문 준비
			String query = "";
			query += " insert into author ";
			query += " values (seq_author_id.nextval, ?, ?) ";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthorDesc());
			
			//실행
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println("count " + count + "건이 생성되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		//this.close();
		close();
		
		return count;
		
	}
	
	//>작가 삭제 메소드
	public int authorDelete(int authorId) {
		
		int count = -1;
		
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			
			//SQL문 준비
			String query = "";
			query += " delete from author ";  //양옆 큰따옴표와 내용 사이 띄어쓰기**
			query += " where author_id = ? "; 
			
			//바인딩
			pstmt = conn.prepareStatement(query); //문자열 커리로 만들기
			pstmt.setInt(1, authorId);			  //?(물음표) 중 1번째 --> 순서 중요(n, ?)
			
			//실행
			count = pstmt.executeUpdate();	  //쿼리문 실행 --> 성공 갯수 리턴 [insert, update, delete]
			
			// 4.결과처리
			System.out.println("count " + count + "건이 삭제되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		close();
		
		return count;
		
	}
	
	//작가 수정 메소드
	public int authorUpdate(AuthorVo authorVo) {
		
		int count = -1;
		
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			
			//SQL문 준비
			String query = "";
			query += " update author "; //양옆 큰따옴표와 내용 사이 띄어쓰기**
			query += " set author_name = ? ";
			query += "     , author_desc = ? ";
			query += " where author_id = ? ";
			
			//바인딩
			pstmt = conn.prepareStatement(query); //문자열 커리로 만들기
			pstmt.setString(1, authorVo.getAuthorName());			  //?(물음표) 중 1번째 --> 순서 중요
			pstmt.setString(2, authorVo.getAuthorDesc());		      //?(물음표) 중 2번째 --> 순서 중요
			pstmt.setInt(3, authorVo.getAuthorId());
			
			//실행
			count = pstmt.executeUpdate();	  //쿼리문 실행 --> 성공 갯수 리턴 [insert, update, delete]
			
			// 4.결과처리
			System.out.println("count " + count + "건이 수정되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		close();
		
		return count;
		
	}
	
	//작가 리스트 전체 가져오기 메소드
	public List<AuthorVo> authorSelect() {
		
		//리스트 만들기
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			
			//SQL문 준비
			String query = "";
			query += " select author_id ";  //양옆 큰따옴표와 내용 사이 띄어쓰기**
			query += "        , author_name ";
			query += "        , author_desc ";
			query += " from author "; 
			
			//바인딩
			pstmt = conn.prepareStatement(query); //문자열 커리로 만들기
			
			//실행
			//ResultSet 가져오기
			rs = pstmt.executeQuery(); //select
			
			// 4.결과처리
			//반복문으로 Vo 만들기 List에 추가하기
			while (rs.next()) {
				
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				
				//int authorId = rs.getInt(1);
				//String authorName = rs.getString(2);
				//String authorDesc = rs.getString(3);
				
				AuthorVo authorVo = new AuthorVo(authorId, authorName, authorDesc);
				
				authorList.add(authorVo);
								
			}
			
			//리스트 출력해 보기
			//System.out.println(authorList.toString());
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		close();
		
		return authorList;
		
	}
	
	/*
	 public List<AuthorVo> authorSelect() {
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, id, pw);
			
			// 3. SQL문 준비 / 바인딩 / 실행
			
			//SQL문 준비
			String query = "";
			query += " select author_id ";  //양옆 큰따옴표와 내용 사이 띄어쓰기**
			query += "        , author_name ";
			query += "        , author_desc ";
			query += " from author "; 
			
			//바인딩
			pstmt = conn.prepareStatement(query); //문자열 커리로 만들기
			
			//실행
			//ResultSet 가져오기
			rs = pstmt.executeQuery(); //select
			
			// 4.결과처리
				
			//리스트 만들기
			List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		
			//반복문으로 Vo 만들기 List에 추가하기
			while (rs.next()) {
				
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				
				//int authorId = rs.getInt(1);
				//String authorName = rs.getString(2);
				//String authorDesc = rs.getString(3);
				
				AuthorVo authorVo = new AuthorVo(authorId, authorName, authorDesc);
				
				authorList.add(authorVo);
								
			}
			
			//리스트 출력해 보기
			//System.out.println(authorList.toString());
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

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
		
	}
	 */
	
}
