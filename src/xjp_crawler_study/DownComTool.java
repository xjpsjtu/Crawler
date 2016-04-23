package xjp_crawler_study;

import java.io.*;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

public class DownComTool {
	/**
	 * ����url����ҳ����������Ҫ�������ҳ���ļ�����ȥ��url�еķ��ļ����ַ�
	 */
	private String getFileNameByUrl(String url, String contentType){
		url = url.substring(7);
		if(contentType.indexOf("html") != -1){
			//������url�е��������ת��Ϊ�»���
			url = url.replaceAll("[\\?/:*|<>\"]", "_") + ".html";
		}else{
			url = url.replaceAll("[\\?/:*|<>\"]", "_") + "." + contentType.substring(contentType.lastIndexOf("/") + 1);
		}
		return url;
	}
	/**
	 * ������ҳ�ֽ����鵽�����ļ���filePathΥҪ������ļ�����Ե�ַ
	 */
	private void saveToLocal(byte[] data, String filePath){
		try{
			if(FileReaderWriter.createNewFile(filePath)){
				DataOutputStream out = new DataOutputStream(new FileOutputStream(new File(filePath)));
				for(int i = 0; i < data.length; i++){
					out.write(data[i]);
					out.flush();
					out.close();
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private void saveToLocal(InputStream input, String filepath){
		OutputStream output = null;
		try {
			output = new FileOutputStream(filepath);
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * ����urlָ�����ҳ
	 */
	public String downloadFile(String url){
		String filePath = null;
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		//����http���ӳ�ʱ5s
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
		httpGet.setConfig(requestConfig);
		//�����������Դ���
		httpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(5,false));
		try {
			HttpResponse response = httpClient.execute(httpGet);
			int statusCode = response.getStatusLine().getStatusCode();
			if(statusCode != HttpStatus.SC_OK){
				System.err.println("Method failed: " + response.getStatusLine());
				filePath = null;
			}
			filePath = "D:temp\\" + getFileNameByUrl(url, response.getFirstHeader("Content-Type").getValue());
			InputStream input = response.getEntity().getContent();
			saveToLocal(input, filePath);
//			byte[] responseBody = EntityUtils.toByteArray(response.getEntity());
//			saveToLocal(responseBody, filePath);
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			httpGet.releaseConnection();
		}
		return filePath;
		
	}
	
	
}
