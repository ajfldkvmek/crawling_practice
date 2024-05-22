package kr.or.ddit.crawler;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsopTest2 {
	public static void main(String[] args) {

		try {
			
			String url = "https://www.yes24.com/24/Category/Display/001001016019";
			
			Document doc = Jsoup.connect(url).get();

			String str = doc.html();
			String[] line = str.split("\n");
			String pNumber = "?PageNumber=";
			
			//리스트의 페이지 코드
			String listPage = url.replace("https://www.yes24.com/24/Category/Display/", "");
			
			boolean con = true;

			
			//리스트별 끝 페이지 가져오기
			String endList = "";
			for (String s : line) {
				if (s.contains("cate2d")) {
					System.out.println(s);
//					endList = s;
//					break;
//					System.out.println(endList);
					//if(s.contains("end\">끝")) break;					
					//con = false;
				}			
				if (con) continue;
			}
			//리스트 끝 페이지 찾으면 가져오고 break;
						
			
			//마지막 페이지번호만 가져와서 parseInt (반복위함)
//			endList = endList.replaceAll(" ", "").replace("\"class=\"bgYUIend\">끝</a>", "").replace("<ahref=\"/24/Category/Display/"+listPage+"?PageNumber=", "");
			
//			int endPage = Integer.parseInt(endList);
//			listPage <- 001001016004 (리스트 페이지 코드)
//			"https://www.yes24.com/24/Category/Display/" + listPage + pNumber;  ->  https://www.yes24.com/24/Category/Display/listPage?PageNumber=
			
//		
//			for(int i = 1; i <= endPage; i++) {
////				System.out.println("https://www.yes24.com/24/Category/Display/" + listPage + pNumber+i);
//				System.out.println(listPage + pNumber+i);
//			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}