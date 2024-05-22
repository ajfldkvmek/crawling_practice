package kr.or.ddit.crawler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

public class FileTest {

	public static void createfile(String name, String contents) {

		String path = "D:/data/list/";
		File list = new File(path);
		
		if (!list.exists()) {
			try {
				list.mkdirs(); // 폴더 생성
			} catch (Exception e) {
				e.getStackTrace();
			}
		} else {
		}	
		
		//여기에 저장할 파일 이름을 url로
		String str = name.replaceAll("/", "@"); 
		
		File file = new File(path + str+".html");
		try {
			
			FileWriter fw = new FileWriter(file);
			PrintWriter writer = new PrintWriter(fw);
			if (file.createNewFile()) {
				System.out.println("File created");
				writer.write(contents);
				writer.close();
			} else {
				System.out.println("File already exists");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
