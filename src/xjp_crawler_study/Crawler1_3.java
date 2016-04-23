package xjp_crawler_study;

import java.util.Set;

public class Crawler1_3 {
	/**
	 * 使用种子初始化url队列
	 */
	private void initCrawlerWithSeeds(String[] seeds){
		for(int i = 0; i < seeds.length; i++){
			CrawlerQueue1_2.addUnvisitedUrl(seeds[i]);
		}
	}
	//定义过滤器，提取以http://www.xxxx.com开头的链接
	public void crawling(String[] seeds){
		System.out.println(seeds[0]);
		LinkFilter filter = new LinkFilter(){
			public boolean accept(String url){
				if(url.startsWith("http://")){
					return true;
				}else return false;
			}
		};
		//初始化url队列
		initCrawlerWithSeeds(seeds);
		//循环条件：待抓取的链接不空切抓取的网页不多于1000
		while(!CrawlerQueue1_2.unVisitedUrl.isEmpty() && CrawlerQueue1_2.getVisitedUrlNum() <= 1000){
			String visitUrl = (String)CrawlerQueue1_2.unVisitedUrlDeQueue();
			if(visitUrl ==null)continue;
			DownComTool downloader = new DownComTool();
			//下载网页
			downloader.downloadFile(visitUrl);
			//该url放入已访问的url中
			CrawlerQueue1_2.addVisitedUrl(visitUrl);
			Set<String> links = HtmlParserTool.extracLinks(visitUrl, filter);
			for(String link : links){
				CrawlerQueue1_2.addUnvisitedUrl(link);
			}
		}
		
	}
	public static void main(String[] args){
		Crawler1_3 crawler = new Crawler1_3();
		crawler.crawling(new String[]{"http://www.taobao.com"});
	}
}
