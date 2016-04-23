package xjp_crawler_study;

import java.util.*;

public class CrawlerQueue1_2 {
	/**
	 * �ѷ��ʵ�url����
	 */
	private static Set<Object> visitedUrl = new HashSet();
	/**
	 * ��ӵ��ѷ��ʵ�url������
	 */
	public static void addVisitedUrl(String url){
		visitedUrl.add(url);
	}
	/**
	 * �Ƴ��ѷ��ʵ�url
	 */
	public static void removeVisitedUrl(String url){
		visitedUrl.remove(url);
	}
	/**
	 * ����Ѿ����ʵ�url��Ŀ
	 */
	public static int getVisitedUrlNum(){
		return visitedUrl.size();
	}
	/**
	 * �����ʵ�url����
	 */
	public static Queue unVisitedUrl = new Queue();
	/**
	 * ���unvisitedUrl����
	 */
	public static Queue getUnVisitedUrl(){
		return unVisitedUrl;
	}
	/**
	 * δ���ʵ�url������
	 */
	public static Object unVisitedUrlDeQueue(){
		return unVisitedUrl.deQueue();
	}
	/**
	 * ��֤���url��unVisitedUrl��ʱ��ÿ��urlֻ������һ��
	 */
	public static void addUnvisitedUrl(String url){
		if(url != null && !url.trim().equals("") && !visitedUrl.contains(url) && !unVisitedUrl.contains(url)){
			unVisitedUrl.enQueue(url);
		}
	}
	/**
	 * �ж�δ���ʵ�url�����Ƿ�Ϊ��
	 */
	public boolean unVisitedUrlisEmpty(){
		return unVisitedUrl.isEmpty();
	}

}
