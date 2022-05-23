package com.javaex.ex03;

import java.util.List;
import java.util.Scanner;

public class BookApp {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		/*
		BookDao bookDao = new BookDao();

		List<BookVo> bookList = bookDao.bookSelect();
		
		for(int i = 0; i<bookList.size(); i++) {
			
			BookVo bookVo = bookList.get(i);
			System.out.println(bookVo.getBookId()+" | "+bookVo.getTitle()+" | "+bookVo.getPubs()+" | "+bookVo.getPubDate()+" | "+bookVo.getAuthorName());
			
		}
		*/
		BookDao bookDao = new BookDao();
		List<BookVo> bookList = bookDao.bookSelect();
		
		System.out.print("검색: ");
		String keyword = sc.next();
		
		for(int i = 0; i<bookList.size(); i++) {
			
			String Tsearch = bookList.get(i).getTitle();
			String Psearch = bookList.get(i).getPubs();
			String Asearch = bookList.get(i).getAuthorName();
			
			if(Tsearch.contains(keyword)) {
				BookVo bookVo = bookList.get(i);
				System.out.println(bookVo.getBookId()+" | "+bookVo.getTitle()+" | "+bookVo.getPubs()+" | "+bookVo.getPubDate()+" | "+bookVo.getAuthorName());

			}else if(Psearch.contains(keyword)) {
				BookVo bookVo = bookList.get(i);
				System.out.println(bookVo.getBookId()+" | "+bookVo.getTitle()+" | "+bookVo.getPubs()+" | "+bookVo.getPubDate()+" | "+bookVo.getAuthorName());

			}else if(Asearch.contains(keyword)) {
				BookVo bookVo = bookList.get(i);
				System.out.println(bookVo.getBookId()+" | "+bookVo.getTitle()+" | "+bookVo.getPubs()+" | "+bookVo.getPubDate()+" | "+bookVo.getAuthorName());

			}
			
		}
		
		sc.close();

	}

}
