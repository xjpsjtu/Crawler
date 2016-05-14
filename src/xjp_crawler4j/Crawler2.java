package xjp_crawler4j;

import java.util.Set;
import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class Crawler2 extends WebCrawler{
	DownTool downTool = new DownTool();
	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|mp3|mp3|zip|gz))$");
	/*
	 * filt the urls to be visited in the page.
	 */
	public boolean shouldVisit(Page referringPage, WebURL url){
		String href = url.getURL().toLowerCase();
		return !FILTERS.matcher(href).matches();
	}
	/*
	 * visit a page
	 */
	public void visit(Page page){
		String url = page.getWebURL().getURL();
		System.out.println("URL: " + url);
		
		if(page.getParseData() instanceof HtmlParseData){
			HtmlParseData htmlParseData = (HtmlParseData)page.getParseData();
			String text = htmlParseData.getText();
			String html = htmlParseData.getHtml();
			Set<WebURL> links = htmlParseData.getOutgoingUrls();
			
			//download this page
			String contentType = page.getContentType();
			String filePath = "D:\\tmp_en\\" + downTool.getFileNameByUrl(url, contentType) + ".txt";
			System.out.println("File path is: " + filePath);
			downTool.saveToLocal(text, filePath);
			
			System.out.println("Text length: " + text.length());
			System.out.println("Html length: " + html.length());
			System.out.println("Number of outgoing links: " + links.size());
		}
	}
}
