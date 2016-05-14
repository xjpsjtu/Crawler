package xjp_crawler4j;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import xjp_crawler_study.DownComTool;
import xjp_crawler_study.FileReaderWriter;

public class DownTool extends DownComTool{
	public void saveToLocal(String data, String filePath){
		try{
			if(FileReaderWriter.createNewFile(filePath)){
				BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(new File(filePath)));
				bufferWriter.write(data);
				bufferWriter.close();
				System.out.println("Susseed");
			}else{
				System.out.println("Failed");
			}
		}catch(IOException e){
			e.printStackTrace();
		}
			
	}
}
