package com.perfaware.sterlingtester.api;

import com.perfaware.sterlingtester.context.TesterContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@Component
public class TWCCValidateOrderMessage implements Service {

    TesterContext testerContext;

    public TWCCValidateOrderMessage(@Autowired TesterContext testerContext) {
        this.testerContext = testerContext;
    }

    @Override
    public void execute(Document inputDoc) {
        Element root = inputDoc.getDocumentElement();
        root.setAttribute("OrderNo", testerContext.getOrder().getOrderNumber());
        inputDoc.normalize();
    }
}