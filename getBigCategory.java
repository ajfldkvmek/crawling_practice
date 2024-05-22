package kr.or.ddit.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class getBigCategory {
	
	static List<String> bigCategory  = new ArrayList();
	
	public static void bigCategory() {
		try {
			//국내도서 메인에서는 안뽑히고 특정 카테고리로 들어가야함 수정필요
			String url = "https://www.yes24.com/24/Category/Display/001001001";
			
			Document doc = Jsoup.connect(url).get();

			String str = doc.html();
			String[] line = str.split("\n");
					
			boolean con = true;
		
			//리스트별 끝 페이지 가져오기
//			String endList = "";
			for (String s : line) {
				if (s.contains("\"cate2d\"> <a href=\"/24/Category/Display/")) {
					s = s.replaceAll(" ", "");					
					String[] line_  = s.split("\"");
//					
					for(String s2 : line_) {
						if(s2.contains("/24/Category/Display")) bigCategory.add(s2);						
					}					
				
				}				
				if (con) continue;
			}	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}