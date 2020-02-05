package com.perfaware.sterlingtester;

import com.perfaware.sterlingtester.context.TesterContext;
import com.perfaware.sterlingtester.models.Flow;
import com.perfaware.sterlingtester.models.Order;
import com.perfaware.sterlingtester.selenium.DriverManager;
import com.perfaware.sterlingtester.service.FlowExecutor;
import com.perfaware.sterlingtester.service.WorkFlowInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "sterling")
public class InitTester {
    List<Flow> workFlowList;
    WorkFlowInitializer workFlowInitializer;
    FlowExecutor executor;
    TesterContext testerContext;
    DriverManager driverManager;

    public InitTester(@Autowired WorkFlowInitializer workFlowInitializer, @Autowired FlowExecutor executor, @Autowired TesterContext testerContext, @Autowired DriverManager driverManager) {
        this.workFlowInitializer = workFlowInitializer;
        this.executor = executor;
        this.testerContext = testerContext;
        this.driverManager = driverManager;
    }

    @PostConstruct
    public void print() {
        workFlowInitializer.init(workFlowList);
        Order order = testerContext.getOrder();
        while(order.hasNext()) {
            System.out.println(": workflow started for orderNO: " + order.getOrderNumber());
            workFlowList.forEach(workflow -> {
                executor.execute(workflow);
            });
            System.out.println(": workflow completed for orderNO: " + order.getOrderNumber());
        }
//        driverManager.closeDriver();
    }

    public List<Flow> getWorkFlow() {
        return workFlowList;
    }

    public void setWorkFlow(List<Flow> workFlow) {
        this.workFlowList = workFlow;
    }
}
