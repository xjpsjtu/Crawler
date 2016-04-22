package xjp_crawler_study;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Zhihu {
	public String question; //��������
	public String zhihuUrl; //��ҳ����
	public String questionDescription;
	public ArrayList<String> answers; //�洢���лش������
	public Zhihu(){
		question = "";
		zhihuUrl = "";
		questionDescription = "";
		answers = new ArrayList<String>();
	}
	public Zhihu(String url){
		if(getRealUrl(url)){
//			System.out.println("zhihuUrl is " + zhihuUrl);
			String result = Crawler1.SendGet(zhihuUrl);
//			System.out.println(result);
			question = getQuestion(result);
//			System.out.println("quesiton is: " + question);
			questionDescription = getQuestionDescription(result);
			answers = getAnswer(result);
		}
	}
	public String getQuestionDescription(String result){
		Pattern pattern = Pattern.compile("zm-editable-content\">(.+?)<");
		Matcher matcher = pattern.matcher(result);
		boolean isFind = matcher.find();
		if(isFind){
			String res = matcher.group(1);
			return res;
		}else{
			return "";
		}

	}
	public String getQuestion(String result){
//		System.out.println("question_result is: " + result);
		Pattern pattern = Pattern.compile("<h2.*?>\n+(.+?)\n+</h2>");
		Matcher matcher = pattern.matcher(result);
//		System.out.println(matcher.group(1));
		boolean isFind = matcher.find();
		if(isFind){
			String res = matcher.group(1);
			return res;
		}else{
			return "";
		}
	}
	public ArrayList getAnswer(String result){
		ArrayList res = new ArrayList<String>();
		Pattern pattern = Pattern.compile("/answer/content.+?\n+<div.+?>\n+(.*?)\n+</div>");
		Matcher matcher = pattern.matcher(result);
		boolean isFind = matcher.find();
		while(isFind){
//			System.out.println("answer :" + matcher.group(1));
			res.add(matcher.group(1));
			isFind = matcher.find();
		}
		return res;
	}
	boolean getRealUrl(String url){
		Pattern pattern = Pattern.compile("question/(.+?)/");
		Matcher matcher = pattern.matcher(url);
		if(matcher.find()){
			zhihuUrl = "http://www.zhihu.com/question/" + matcher.group(1);
		}else{
			return false;
		}
		return true;
	}
	public String toString(){
		return "����: " + question + "\n����: " + questionDescription + "\n����: " + zhihuUrl + "\n�ش�: \n";
	}
	public void print(){
		System.out.println("����: " + question);
		System.out.println("����: " + questionDescription);
		System.out.println("����: " + zhihuUrl);
		for(int i = 0; i < answers.size(); i++){
			System.out.println("�ش�" + (i+1) + ": " + answers.get(i));
		}
		System.out.println("-------------------------------------------");
	}
}
