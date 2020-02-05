package com.perfaware.sterlingtester.service;

import com.perfaware.sterlingtester.api.API;
import com.perfaware.sterlingtester.models.Flow;
import javafx.application.Application;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Component
public class WorkFlowInitializer {

    ApplicationContext context;

    public WorkFlowInitializer(@Autowired ApplicationContext context) {
        this.context = context;
    }

    public void init(List<Flow> workFlowList) {
        for (Flow workFlow : workFlowList) {
            workFlow.setFlow(constructFlow(workFlow));
            workFlow.setInputDoc(readInputDocument(workFlow.getFile()));
        }
    }

    public API constructFlow(Flow workFlow) {
        API api = null;
        String fullPathOfTheClass = "com.perfaware.sterlingtester.api." + StringUtils.capitalize(workFlow.getName());
        try {
            api = (API) context.getBean(Class.forName(fullPathOfTheClass));
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + fullPathOfTheClass);
        }
        return api;
    }

    public Document readInputDocument(String file){
        Document doc = null;
        try {
            File xmlFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(file)).getFile());
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);
            doc.getDocumentElement().normalize();
        } catch (SAXException e) {
            System.out.println("Invalid input file");
        } catch (IOException e) {
            System.out.println("IO Exception");
        } catch (ParserConfigurationException e) {
            System.out.println("Parsing exception");
        }
        return doc;
    }

}
