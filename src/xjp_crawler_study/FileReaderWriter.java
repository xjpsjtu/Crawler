package xjp_crawler_study;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderWriter {
	// 创建一个文件
	public static boolean createNewFile(String filePath) {
		boolean isSuccess = true;
		String filePathTurn = filePath.replaceAll("\\\\", "/");
		int index = filePathTurn.lastIndexOf("/");
		String dir = filePathTurn.substring(0, index);
//		System.out.println("the dir is: " + dir);
		File fileDir = new File(dir);
		isSuccess = fileDir.mkdirs();
		File file = new File(filePathTurn);
		try {
			isSuccess = file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			isSuccess = false;
			e.printStackTrace();
		}
		return isSuccess;
	}

	// 写入文件
	public static boolean writeIntoFile(String content, String filePath, boolean isAppend) {
		boolean isSuccess = true;
		int index = filePath.indexOf("/");
		String dir = filePath.substring(0, index);
		File fileDir = new File(dir);
		fileDir.mkdirs();
		File file = null;
		try {
			file = new File(filePath);
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			isSuccess = false;
			e.printStackTrace();
		}
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(file, isAppend);
			fileWriter.write(content);
			fileWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			isSuccess = false;
			e.printStackTrace();
		} finally {
			try {
				if (fileWriter != null) {
					fileWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return isSuccess;
	}
}
