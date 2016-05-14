package xjp_crawler4j;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestFile {
	
	public static void main(String[] args) throws IOException{
//		String dir = "D:/tmp";
//		File fileDir = new File(dir);
//		fileDir.mkdirs();
		String data = "abcdedghijklmnopqrstuvwxyz";
		String url = "http://www.nju.edu.cn";
		DownTool downTool = new DownTool();
		String filePath = "D:\\tmp\\" + downTool.getFileNameByUrl(url, "text/html") + ".txt";
		System.out.println(filePath);
		downTool.saveToLocal(data, filePath);
//		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(filePath)));
//		bufferedWriter.write(data);
//		bufferedWriter.close();
	}
}

