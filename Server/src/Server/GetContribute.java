package Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GetContribute {
	private static ChromeOptions chromeOptions = new ChromeOptions();
	private static WebDriver driver;
	public ArrayList<String[]> contribution = new ArrayList<>();
	private String url = "https://github.com/";;
	GetContribute(String repository){
        System.setProperty("webdriver.chrome.driver", "/Users/5linesys/Documents/Eclipse_java_project/Server/chromedriver");

		chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
        chromeOptions.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
   		chromeOptions.addArguments("--disable-infobars"); //https://stackoverflow.com/a/43840128/1689770
  		chromeOptions.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770
   		chromeOptions.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
        chromeOptions.addArguments("--disable-gpu"); //https://stackoverflow.com/questions/51959986/how-to-solve-selenium-chromedriver-timed-out-receiving-message-from-renderer-exc
        url = url + repository + "/graphs/contributors";
	}

    public static void main(String[] args) throws InterruptedException, IOException {

	}

    public void getCommit() throws InterruptedException, IOException {
    	contribution.clear();
		driver = new ChromeDriver(chromeOptions);

        driver.get(url);
        
		Thread.sleep(3000);
		
        String ConStatsStr = "//*[@id='contributors']/ol/li";
        List<WebElement> ContriButionStats = driver.findElements(By.xpath(ConStatsStr));
        int size = ContriButionStats.size();

        Document doc = Jsoup.parse(driver.getPageSource());
        driver.quit();

		for(int i = 1; i <= size; i++) {
			String id = doc.select("#contributors > ol > li:nth-child(" + i + ") > span > h3 > a.text-normal").text();
			String commits = doc.select("#contributors > ol > li:nth-child(" + i + ") > span > h3 > span.f6.d-block.text-gray-light > span > a").text();
			commits = commits.split(" ")[0].replaceAll(",", "");
			contribution.add(new String[]{ String.valueOf(i) , id, String.valueOf(commits)});
		}
    }
}
