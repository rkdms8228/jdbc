package com.javaex.ex03;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		
		AuthorDao authorDao = new AuthorDao();
		
		//////////////////////////////////////////////////////////////////////
		//authorInsert
		/*
		authorDao.authorInsert("이문열", "경북 영양");
		authorDao.authorInsert("박경리", "경상남도 통영");
		authorDao.authorInsert("유시민", "17대 국회의원");
		authorDao.authorInsert("기안84", "기안동에서 산 84년생");
		authorDao.authorInsert("강풀", "온라인 만화가 1세대");
		authorDao.authorInsert("김영하", "알쓸신잡");
		*/
		
		/*
		if(iCount > 0) {	
			System.out.println(iCount + " 등록되었습니다.");
		}else {
			System.out.println(iCount + " 등록되지 않았습니다.");
		}
		*/
		
		//////////////////////////////////////////////////////////////////////
		//authorDelete
		/*
		int dCount = authorDao.authorDelete(4);
		System.out.println("삭제 건수 " + dCount + "건");
		*/
		
		//////////////////////////////////////////////////////////////////////
		//authorUpdate
		/*
		int uCount = authorDao.authorUpdate("이문열", "삼국지 작가", 1);
		System.out.println("수정 건수 " + uCount + "건");
		*/
		
		//////////////////////////////////////////////////////////////////////
		//authorSelect
		//authorDao.authorSelect();
		
		List<AuthorVo> authorList = authorDao.authorSelect();
		
		for(int i = 0; i<authorList.size(); i++) {

			
			AuthorVo authorVo = authorList.get(i);
			System.out.println(authorVo.getAuthorId()+" | "+authorVo.getAuthorName()+" | "+authorVo.getAuthorDesc());
						
		}

	}

}
