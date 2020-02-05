package com.perfaware.sterlingtester.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DriverManager {
    WebDriver chromeDriver = null;
    @PostConstruct
    public void init(){
        WebDriverManager.chromedriver().version("79.0.3945.36").setup();
        chromeDriver = new ChromeDriver();
    }

    public WebDriver getDriver(){
        return this.chromeDriver;
    }

    public void closeDriver(){
        chromeDriver.quit();
    }
}
