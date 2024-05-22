package kr.or.ddit.crawler;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class SaveSpecPage {

	
	public static void main(String[] args) {
		
		getBigCategory.bigCategory();		
		
		middleCategory.middleCate(getBigCategory.bigCategory);
		
		getLinkFromList.getList(middleCategory.mCategory);
		
		getSpecificList.getSpec(getLinkFromList.map);

//		savePage(getSpecificList.specPage);
	}
	
	public static void savePage(List<String> specPage) {

		try {
			for (int p = 0; p < specPage.size(); p++) {

				String goods = specPage.get(p);
				String url = "https://www.yes24.com" + goods;
				Document doc = Jsoup.connect(url).get();
				String str = doc.html();

				str = str.replaceAll("euc-kr", "utf-8");

				FileTest.createfile(goods, str);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
