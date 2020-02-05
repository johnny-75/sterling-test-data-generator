package com.perfaware.sterlingtester.service;

import com.perfaware.sterlingtester.context.TesterContext;
import com.perfaware.sterlingtester.models.Flow;
import com.perfaware.sterlingtester.selenium.DriverManager;
import com.perfaware.sterlingtester.utils.DocToStringConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlowExecutor {

    TesterContext testerContext;
    DriverManager driverManager;
    WebDriver driver;

    public FlowExecutor(@Autowired TesterContext testerContext, @Autowired DriverManager driverManager) {
        this.testerContext = testerContext;
        this.driverManager = driverManager;
    }

    public boolean execute(Flow flow) {
        flow.getFlow().execute(flow.getInputDoc());

        initExecutor();

        if (flow.getIsService())
            initService(flow.getName());
        else
            initAPI(flow.getName());

        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]", driver.findElement(By.id("InteropApiData")), DocToStringConverter.convert(flow.getInputDoc()));

//        driver.findElement(By.name("btnTest")).click();

        return true;
    }

    private void initExecutor() {
        this.driver = driverManager.getDriver();
        driver.navigate().to(testerContext.getConnection().getUrl());
        WebElement txtUsername = driver.findElement(By.name("YFSEnvironment.userId"));
        WebElement txtPassword = driver.findElement(By.name("YFSEnvironment.password"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]", txtUsername, testerContext.getLogin().getUsername());
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]", txtPassword, testerContext.getLogin().getPassword());

    }

    private void initAPI(String name) {
        WebElement invokeFlowCheck = driver.findElement(By.name("InvokeFlow"));
        if (invokeFlowCheck.isSelected())
            invokeFlowCheck.click();
        Select selectApiName = new Select(driver.findElement(By.id("ApiName")));
        selectApiName.selectByValue(name);
    }

    private void initService(String serviceName) {
        WebElement invokeFlowCheck = driver.findElement(By.name("InvokeFlow"));
        if (!invokeFlowCheck.isSelected())
            invokeFlowCheck.click();
        WebElement txtServiceName = driver.findElement(By.name("ServiceName"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]", txtServiceName, serviceName);
    }
}
