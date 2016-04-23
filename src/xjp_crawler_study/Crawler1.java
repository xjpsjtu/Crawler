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
		// ����һ���ַ������洢��ҳ����
		String result = "";
		// ����һ�������ַ�������
		BufferedReader in = null;
		try {
			// ��Stringת��ΪURL����
			URL realUrl = new URL(url);
			// ��ʼ��һ�����ӵ��Ǹ�URL������
			URLConnection connection = realUrl.openConnection();
			// ��ʼʵ�ʵ�����
			connection.connect();
			// ��ʼ��BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			// ������ʱ�洢ץȡ����ÿһ������
			String line;
			while ((line = in.readLine()) != null) {
				// ����ץȡ����ÿһ�в�����洢��result����
				result += line + "\n";
			}
		} catch (Exception e) {
			System.out.println("����GET��������쳣�� " + e);
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
		// ����һ����ʽģ�壬����ʹ��������ʽ����������Ҫץȡ������
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
