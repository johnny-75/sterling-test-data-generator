package com.perfaware.sterlingtester.models;

import com.perfaware.sterlingtester.api.API;
import org.w3c.dom.Document;


public class Flow {
    String name;
    String file;
    boolean isService;
    API flow;
    Document inputDoc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public boolean getIsService() {
        return isService;
    }

    public void setIsService(boolean isService) {
        this.isService = isService;
    }

    public API getFlow() {
        return flow;
    }

    public void setFlow(API flow) {
        this.flow = flow;
    }

    public Document getInputDoc() {
        return inputDoc;
    }

    public void setInputDoc(Document inputDoc) {
        this.inputDoc = inputDoc;
    }
}
