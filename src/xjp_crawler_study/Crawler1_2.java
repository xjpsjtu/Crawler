package xjp_crawler_study;

import java.io.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Crawler1_2 {

	private static HttpClient httpclient = HttpClients.createDefault();

	public static boolean downloadPage(String path) throws Exception {
		InputStream input = null;
		OutputStream output = null;
		HttpGet httpGet = new HttpGet(path);
		HttpResponse response = httpclient.execute(httpGet);
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == HttpStatus.SC_OK) {
			HttpEntity entity = response.getEntity();
			input = entity.getContent();
			String filename = path.substring(path.lastIndexOf('/') + 1) + ".txt";
			output = new FileOutputStream(filename);
			int tmpByte = -1;
			while ((tmpByte = input.read()) > 0) {
				output.write(tmpByte);
			}
			if (input != null) {
				input.close();
			}
			if (output != null) {
				output.close();
			}
			return true;
		}
		return false;

	}
	public static void main(String[] args){
		try {
			Crawler1_2.downloadPage("https://taobao.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
