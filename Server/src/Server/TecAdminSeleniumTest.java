package Server;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TecAdminSeleniumTest {
	 
    public static void main(String[] args) throws IOException, InterruptedException {
            String conurl = "https://github.com/fancuk/MessengerProject";//"https://github.com/MicrosoftLearning/AZ-103-MicrosoftAzureAdministrator";
        	String conloc = "#js-repo-pjax-container > div.container-lg.clearfix.new-discussion-timeline.experiment-repo-nav.px-3 > div > div.overall-summary.overall-summary-bottomless > ul > li:nth-child(5) > a > span"; 
          
        	String comurl = "https://github.com/fancuk/MessengerProject/graphs/contributors";//"https://github.com/MicrosoftLearning/AZ-103-MicrosoftAzureAdministrator/graphs/contributors";
            
            System.setProperty("webdriver.chrome.driver", "/Users/5linesys/Documents/Eclipse_java_project/Server/chromedriver");

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--no-sandbox");
            
            chromeOptions.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
            chromeOptions.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
            chromeOptions.addArguments("--disable-infobars"); //https://stackoverflow.com/a/43840128/1689770
            chromeOptions.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770
            chromeOptions.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
            chromeOptions.addArguments("--disable-gpu"); //https://stackoverflow.com/questions/51959986/how-to-solve-selenium-chromedriver-timed-out-receiving-message-from-renderer-exc
            
            WebDriver driver = new ChromeDriver(chromeOptions);
            
            driver.get(comurl);
            
            Thread.sleep(3000);
            
       
            
            //Document doc = Jsoup.connect(conurl).get();
            String ConStatsStr = "//*[@id='contributors']/ol/li";
            List<WebElement> ContriButionStats = driver.findElements(By.xpath(ConStatsStr));
            int size = ContriButionStats.size();
            
            System.out.println("repository url : " + conurl);
            System.out.println("number of contributors : " + size);
            
            Document doc = Jsoup.parse(driver.getPageSource());
            driver.quit();
            
            for(int i = 1; i <= size; i++) {
                    String id = doc.select("#contributors > ol > li:nth-child(" + i + ") > span > h3 > a.text-normal").text();
                    String commits = doc.select("#contributors > ol > li:nth-child(" + i + ") > span > h3 > span.f6.d-block.text-gray-light > span > a").text();
                    

                    String src = doc.select("#contributors > ol > li:nth-child(" + i + ") > span > h3").text();
                    //System.out.println(src);
                    String com[] = commits.split(" ");
                    com[0] = com[0].replaceAll(",", "");
                    int commit = Integer.parseInt(com[0]);
                    System.out.println("rank: " + i + " id: " + id + " commits: " + commit);
            }       
    }
}       