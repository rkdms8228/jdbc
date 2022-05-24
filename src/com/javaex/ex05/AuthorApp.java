package com.javaex.ex05;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		
		AuthorDao authorDao = new AuthorDao();
		
		//authorDao.authorInsert("이문열", "경북 영양");
		//int iCount = authorDao.authorInsert("박경리", "경상남도 통영");
		
		//////////////////////////////////////////////////////////////////////
		//authorInsert
		/*
		AuthorVo vo1 = new AuthorVo("이문열", "경북 영양");
		AuthorVo vo2 = new AuthorVo("박경리", "경상남도 통영");
		AuthorVo vo3 = new AuthorVo("유시민", "17대 국회의원");
		AuthorVo vo4 = new AuthorVo("기안84", "기안동에서 산 84년생");
		AuthorVo vo5 = new AuthorVo("강풀", "온라인 만화가 1세대");
		AuthorVo vo6 = new AuthorVo("김영하", "알쓸신잡");
		
		AuthorVo vo7 = new AuthorVo("정우성", "영화배우");
		
		authorDao.authorInsert(vo1);
		authorDao.authorInsert(vo2);
		authorDao.authorInsert(vo3);
		authorDao.authorInsert(vo4);
		authorDao.authorInsert(vo5);
		authorDao.authorInsert(vo6);
		authorDao.authorInsert(vo7);
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
		
		AuthorVo uVo = new AuthorVo(7, "유재석", "개그맨");

		authorDao.authorUpdate(uVo);
		
		//////////////////////////////////////////////////////////////////////
		//authorSelect
		//authorDao.authorSelect();
		
		List<AuthorVo> authorList = authorDao.authorSelect();
		
		for(int i = 0; i<authorList.size(); i++) {
			
			/*
			int authorId = authorList.get(i).getAuthorId();
			String authorName = authorList.get(i).getAuthorName();
			String authorDesc = authorList.get(i).getAuthorDesc();
			
			System.out.println(authorId+" | "+authorName+" | "+authorDesc);
			*/
			
			//AuthorVo authorVo = authorList.get(i);
			//System.out.println(authorVo.getAuthorId()+" | "+authorVo.getAuthorName()+" | "+authorVo.getAuthorDesc());
			
			System.out.println(authorList.get(i).getAuthorId()+" | "+authorList.get(i).getAuthorName()+" | "+authorList.get(i).getAuthorDesc());
			
		}

	}

}
