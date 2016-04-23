package xjp_crawler_study;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crawler1 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "https://www.zhihu.com/explore/recommendations";
		// String url = "http://www.zhihu.com/question/43534800";
		String result = SendGet(url);
		// System.out.println(result);
		// ArrayList<String> ContSrc = RegexString(result,
		// "<h2.*?>\n+(.+?)\n+</h2>");
		ArrayList<Zhihu> ContSrc = GetZhihuUrl(result);
		for(Zhihu zhihu : ContSrc){
			FileReaderWriter.writeIntoFile(zhihu.writeString(), "E:/zhihu.txt", true);
		}
//		for (int i = 0; i < ContSrc.size(); i++) {
//			ContSrc.get(i).print();
//		}

	}

	static String SendGet(String url) {
		// 定义一个字符串来存储网页内容
		String result = "";
		// 定义一个缓冲字符输入流
		BufferedReader in = null;
		try {
			// 将String转化为URL对象
			URL realUrl = new URL(url);
			// 初始化一个链接到那个URL的链接
			URLConnection connection = realUrl.openConnection();
			// 开始实际的连接
			connection.connect();
			// 初始化BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			// 用来临时存储抓取到的每一行数据
			String line;
			while ((line = in.readLine()) != null) {
				// 遍历抓取到的每一行并将其存储到result里面
				result += line + "\n";
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！ " + e);
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	static ArrayList RegexString(String targetStr, String patternStr) {
		// 定义一个样式模板，此种使用正则表达式，括号中是要抓取的内容
		ArrayList<String> reslist = new ArrayList<String>();
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(targetStr);
		boolean isFind = matcher.find();
		while (isFind) {
			reslist.add(matcher.group(1));
			isFind = matcher.find();
		}
		return reslist;
	}

	static ArrayList<Zhihu> GetZhihu(String content) {
		ArrayList<Zhihu> reslist = new ArrayList<Zhihu>();
		Pattern questionPattern = Pattern.compile("question_link.+?>(.+?)<");
		Matcher questionMatcher = questionPattern.matcher(content);
		Pattern urlPattern = Pattern.compile("question_link.+?href=\"(.+?)\"");
		Matcher urlMatcher = urlPattern.matcher(content);
		boolean isFind = questionMatcher.find() && urlMatcher.find();
		while (isFind) {
			Zhihu zhihu = new Zhihu();
			zhihu.question = questionMatcher.group(1);
			zhihu.zhihuUrl = "http://www.zhihu.com" + urlMatcher.group(1);
			reslist.add(zhihu);
			isFind = questionMatcher.find() && urlMatcher.find();
		}
		return reslist;
	}

	static ArrayList<Zhihu> GetZhihuUrl(String content) {
		ArrayList<Zhihu> reslist = new ArrayList<Zhihu>();
		Pattern urlPattern = Pattern.compile("<h2>.+?question_link.+?href=\"(.+?)\".+?</h2>");
		Matcher urlMatcher = urlPattern.matcher(content);
		boolean isFind = urlMatcher.find();
		while (isFind) {
			// System.out.println("This is the urlMatcher: " +
			// urlMatcher.group(1));
			Zhihu zhihu = new Zhihu(urlMatcher.group(1));
			reslist.add(zhihu);
			isFind = urlMatcher.find();
		}
		return reslist;
	}

}
