package xjp_crawler_study;

import java.util.Set;

public class Crawler1_3 {
	/**
	 * ʹ�����ӳ�ʼ��url����
	 */
	private void initCrawlerWithSeeds(String[] seeds){
		for(int i = 0; i < seeds.length; i++){
			CrawlerQueue1_2.addUnvisitedUrl(seeds[i]);
		}
	}
	//�������������ȡ��http://www.xxxx.com��ͷ������
	public void crawling(String[] seeds){
		System.out.println(seeds[0]);
		LinkFilter filter = new LinkFilter(){
			public boolean accept(String url){
				if(url.startsWith("http://")){
					return true;
				}else return false;
			}
		};
		//��ʼ��url����
		initCrawlerWithSeeds(seeds);
		//ѭ����������ץȡ�����Ӳ�����ץȡ����ҳ������1000
		while(!CrawlerQueue1_2.unVisitedUrl.isEmpty() && CrawlerQueue1_2.getVisitedUrlNum() <= 1000){
			String visitUrl = (String)CrawlerQueue1_2.unVisitedUrlDeQueue();
			if(visitUrl ==null)continue;
			DownComTool downloader = new DownComTool();
			//������ҳ
			downloader.downloadFile(visitUrl);
			//��url�����ѷ��ʵ�url��
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
