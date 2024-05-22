package kr.or.ddit.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

//중간에 바로 리스트 안나오는 것들 있음 ex)/24/Category/Display/001001016002
public class getLinkFromList {

	static Map<String, Integer> map = new HashMap<String, Integer>();
	
	public static void getList(List<String> list) {
		// middle에서 받아온 list에는 중간카테고리들의 주소가 모두 저장된 상태
		for (int l = 0; l < list.size(); l++) {

			try {
				String middleCate = list.get(l);
				String pNumber = "?PageNumber=";
				String url = "https://www.yes24.com"; /// 24/Category/Display/001001016019"; <<- url + middle

				Document doc = Jsoup.connect(url + middleCate).get();

				String str = doc.html();
				String[] line = str.split("\n");

				// 리스트의 페이지 코드
				boolean con = true;
				String endList = "";
				int count = 0; // bgYUI 없으면
				int count2 = 0; // cateSubListWrap
				for (String s : line) {
					count = 0;
					if (s.contains("cateSubListWrap"))
						count2++;
					if (s.contains("bgYUI end")) {
						endList = s;
						count++;
						break;
					}
					if (con)
						continue;
				}
				
				List<String> subList = new ArrayList();
				if (count == 0) {
					if (count2 == 0)
						continue;
					for (String s : line) {
						if (s.contains("<!-- 서브카테고리 끝 -->"))
							break;
						if (s.contains("<li"))
							continue;
						if (s.contains("/24/Category/Display")) {
							s = s.substring(17, 53);
							subList.add(s);
						}
						if (con)
							continue;
					}
				}
				
				int endPage;
				if (endList == "") {
					getList(subList);
				} else {
					endList = endList.replaceAll(" ", "").replace("\"class=\"bgYUIend\">끝</a>", "").replace("<ahref=\"" + middleCate + pNumber, "");
					endPage = Integer.parseInt(endList);
					
					System.out.println(middleCate+ " " +endPage);
					map.put(middleCate, endPage);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

//
//try {
//	//yes24 + middle + pagenumber + index 
//	String pNumber = "?PageNumber=";
//	String url = "https://www.yes24.com"; ///24/Category/Display/001001016019";
//	
//	Document doc = Jsoup.connect(url).get();
//
//	String str = doc.html();
//	String[] line = str.split("\n");
//	
//	
//	//리스트의 페이지 코드
//	String listPage = url.replace("https://www.yes24.com/24/Category/Display/", "");
//	
//	boolean con = true;
//	
//	//리스트별 끝 페이지 가져오기
//	String endList = "";
//	for (String s : line) {
//		if (s.contains("bgYUI end")) {
//			endList = s;
//			break;
//			//con = false;
//		}			
//		if (con) continue;
//	}
//				
//	
//	//마지막 페이지번호만 가져와서 parseInt (반복위함)
//	endList = endList.replaceAll(" ", "").replace("\"class=\"bgYUIend\">끝</a>", "").replace("<ahref=\"/24/Category/Display/"+listPage+"?PageNumber=", "");
//	
//	int endPage = Integer.parseInt(endList);
////	listPage <- 001001016004 (리스트 페이지 코드)
////	"https://www.yes24.com/24/Category/Display/" + listPage + pNumber;  ->  https://www.yes24.com/24/Category/Display/listPage?PageNumber=
//	
//	//1페이지 부터 끝페이지까지 저장
//	for(int i = 1; i <= endPage; i++) {
////		System.out.println("https://www.yes24.com/24/Category/Display/" + listPage + pNumber+i);
////		System.out.println(listPage + pNumber+i);
//		pageList.add(listPage + pNumber+i);
//	}
////	System.out.println(pageList.size());
//	
//} catch (IOException e) {
//	e.printStackTrace();
//}