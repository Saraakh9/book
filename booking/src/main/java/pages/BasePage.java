package pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
    private static Properties prop;
    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

    @BeforeSuite
    public void loadConfig() {
        try {
            // Load the configuration file
            prop = new Properties();
            String configPath = System.getProperty("user.dir") + "/src/main/resources/Config/Config.properties";
            FileInputStream file = new FileInputStream(configPath);
            prop.load(file);
            file.close();
        } catch (IOException e) {
            System.out.println("Failed to load Config.properties file: " + e.getMessage());
            throw new RuntimeException("Configuration file missing or unreadable");
        }
    }

    @BeforeMethod
    public void setup() {
        System.out.println("Setting up WebDriver for: " + this.getClass().getSimpleName());
        launchBrowser();
        configureBrowser();
        staticWait(2);
    }

    private void launchBrowser() {
        // Initialize the WebDriver based on the browser defined in Config.properties
        String browser = prop.getProperty("browser").toLowerCase();
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (Boolean.parseBoolean(prop.getProperty("headless", "false"))) {
                    chromeOptions.addArguments("--headless", "--disable-gpu");
                    chromeOptions.addArguments("--disable-extensions");
                    chromeOptions.addArguments("--disable-popup-blocking");
                    chromeOptions.addArguments("--disable-infobars");
                    chromeOptions.addArguments("--remote-debugging-port=9222");
                }
                threadDriver.set(new ChromeDriver(chromeOptions));
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                threadDriver.set(new FirefoxDriver());
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                threadDriver.set(new EdgeDriver());
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    private void configureBrowser() {
        WebDriver driver = getDriver();

        // Implicit Wait
        int implicitWait = Integer.parseInt(prop.getProperty("implicitWait", "10"));
        driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);

        // Maximize the browser window
        driver.manage().window().maximize();

        // Navigate to the URL
        try {
            driver.get(prop.getProperty("url"));
        } catch (Exception e) {
            System.out.println("Unable to navigate to the URL: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        WebDriver driver = getDriver();
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("Unable to quit the driver: " + e.getMessage());
            } finally {
                threadDriver.remove();
            }
        }
    }

    // Getter method for Properties
    public static Properties getProp() {
        return prop;
    }

    // Driver getter method
    public WebDriver getDriver() {
        return threadDriver.get();
    }

    // Static wait for pause
    public void staticWait(int seconds) {
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
    }
}
