package com.javaex.ex05;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {
		
		BookDao bookDao = new BookDao();
		
		/*
		bookDao.bookInsert("우리들의 일그러진 영웅", "다림", "1998-02-22", 1);
		bookDao.bookInsert("삼국지", "민음사", "2002-03-01", 1);
		bookDao.bookInsert("토지", "마로니에북스", "2012-08-15", 2);
		bookDao.bookInsert("유시민의 글쓰기 특강", "생각의길", "2015-04-01", 3);
		bookDao.bookInsert("패션왕", "중앙북스(books)", "2012-02-22", 4);
		bookDao.bookInsert("순정만화", "재미주의", "2011-08-03", 5);
		bookDao.bookInsert("오직 두 사람", "문학동네", "2017-05-04", 6);
		bookDao.bookInsert("26년", "재미주의", "2012-08-04", 5);
		bookDao.bookInsert("황일영의 풀스택 교실", "글로벌IT", "2022-03-28", 1);
		*/
		
		/*
		BookDao bo1 = new BookDao("우리들의 일그러진 영웅", "다림", "1998-02-22", 1);
		BookDao bo2 = new BookDao("삼국지", "민음사", "2002-03-01", 1);
		BookDao bo3 = new BookDao("토지", "마로니에북스", "2012-08-15", 2);
		BookDao bo4 = new BookDao("유시민의 글쓰기 특강", "생각의길", "2015-04-01", 3);
		BookDao bo5 = new BookDao("패션왕", "중앙북스(books)", "2012-02-22", 4);
		BookDao bo6 = new BookDao("순정만화", "재미주의", "2011-08-03", 5);
		BookDao bo7 = new BookDao("26년", "재미주의", "2012-08-04", 5);
		BookDao bo8 = new BookDao("황일영의 풀스택 교실", "글로벌IT", "2022-03-28", 1);
		
		bookDao.bookInsert(bo1);
		bookDao.bookInsert(bo2);
		bookDao.bookInsert(bo3);
		bookDao.bookInsert(bo4);
		bookDao.bookInsert(bo5);
		bookDao.bookInsert(bo6);
		bookDao.bookInsert(bo7);
		bookDao.bookInsert(bo8);
		*/
		
		//bookDao.bookUpdate("2022-05-23", 9);
		
		BookVo uBo = new BookVo(9, "2022-05-23");
		
		bookDao.bookUpdate(uBo);
		
		//bookDao.bookDelete(9);
		
		//bookDao.bookSelect();
		
		List<BookVo> bookList = bookDao.bookSelect();
		
		for(int i = 0; i<bookList.size(); i++) {
			
			BookVo bookVo = bookList.get(i);
			System.out.println(bookVo.getBookId()+" | "+bookVo.getTitle()+" | "+bookVo.getPubs()+" | "+bookVo.getPubDate()+" | "+bookVo.getAuthorName());
			
		}

	}

}
