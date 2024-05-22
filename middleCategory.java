package kr.or.ddit.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class middleCategory {
	
	// 링크는 bigcate에서 받아옴
	//static 으로 선언했기때문에 각 큰 카테고리별 중간 카테고리가 모두 저장됨
	static List<String> mCategory = new ArrayList();

	public static void middleCate(List<String> bigCategory) {
		
//		여기서 선언해주면 매번 새롭게 저장됨
//		List<String> middleCategory = new ArrayList();
		
//		for (int l = 0; l < list.size(); l++) { 전체 카테고리		
		
		for (int l = 9; l <= 14; l++) { //어린이 ~ 유아까지
			
			//bigcategory의 리스트를 list로 받아와서 n ~ m번 반복하는 동안 middle에 저장
			String bigcate = bigCategory.get(l);
			
			try {				
				String url = "https://www.yes24.com";
				
				Document doc = Jsoup.connect(url + bigcate).get();

				String str = doc.html();
				String[] line = str.split("\n");

				boolean con = true;

				for (String s : line) {
					if (s.contains("<!-- 서브카테고리 끝 -->"))
						break;
					if (s.contains("<li"))
						continue;
					if (s.contains("<a href=\"/24/Category/Display/")) {
						s = s.substring(17, 50);
						mCategory.add(s);
					}
					if (con)
						continue;
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}

//try {
//	
//	String url = "https://www.yes24.com/24/Category/Display/001001011";
//	
//	Document doc = Jsoup.connect(url).get();
//	
//	String str = doc.html();
//	String[] line = str.split("\n");
//	String pNumber = "?PageNumber=";
//	
//	//리스트의 페이지 코드
////	String listPage = url.replace("https://www.yes24.com/24/Category/Display/", "");
//	
//	boolean con = true;
//
//	for (String s : line) {
//		if(s.contains("<!-- 서브카테고리 끝 -->")) break;
//		if(s.contains("<li")) continue;
//		if (s.contains("<a href=\"/24/Category/Display/")) {
//			s = s.substring(38, 50);
//			middleCategory.add(s);
//			//System.out.println(s);
//		}			
//		if (con) continue;
//	}
//				
//	
//} catch (IOException e) {
//	e.printStackTrace();
//}