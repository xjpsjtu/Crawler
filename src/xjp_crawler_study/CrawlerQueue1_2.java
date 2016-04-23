package xjp_crawler_study;

import java.util.*;

public class CrawlerQueue1_2 {
	/**
	 * 已访问的url集合
	 */
	private static Set<Object> visitedUrl = new HashSet();
	/**
	 * 添加到已访问的url集合中
	 */
	public static void addVisitedUrl(String url){
		visitedUrl.add(url);
	}
	/**
	 * 移除已访问的url
	 */
	public static void removeVisitedUrl(String url){
		visitedUrl.remove(url);
	}
	/**
	 * 获得已经访问的url数目
	 */
	public static int getVisitedUrlNum(){
		return visitedUrl.size();
	}
	/**
	 * 待访问的url集合
	 */
	public static Queue unVisitedUrl = new Queue();
	/**
	 * 获得unvisitedUrl队列
	 */
	public static Queue getUnVisitedUrl(){
		return unVisitedUrl;
	}
	/**
	 * 未访问的url出队列
	 */
	public static Object unVisitedUrlDeQueue(){
		return unVisitedUrl.deQueue();
	}
	/**
	 * 保证添加url到unVisitedUrl的时候每个url只被访问一次
	 */
	public static void addUnvisitedUrl(String url){
		if(url != null && !url.trim().equals("") && !visitedUrl.contains(url) && !unVisitedUrl.contains(url)){
			unVisitedUrl.enQueue(url);
		}
	}
	/**
	 * 判断未访问的url集合是否为空
	 */
	public boolean unVisitedUrlisEmpty(){
		return unVisitedUrl.isEmpty();
	}

}
