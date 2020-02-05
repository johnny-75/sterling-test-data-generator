package com.perfaware.sterlingtester.api;

import com.perfaware.sterlingtester.context.TesterContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@Component
public class TWCCConfirmShipmentApi implements Service {

    TesterContext testerContext;

    public TWCCConfirmShipmentApi(@Autowired TesterContext testerContext) {
        this.testerContext = testerContext;
    }

    @Override
    public void execute(Document inputDoc) {
        Element root = inputDoc.getDocumentElement();
        String orderNO = testerContext.getOrder().getOrderNumber();
        NodeList shipmentLines = ((Element)root.getElementsByTagName("ShipmentLines").item(0)).getElementsByTagName("ShipmentLine");

        for (int i = 0; i < shipmentLines.getLength(); i++) {
            Element shipmentLine = (Element) shipmentLines.item(i);
            shipmentLine.setAttribute("OrderNo", orderNO);
            shipmentLine.setAttribute("ShixpAdviceNo", orderNO);
        }
        inputDoc.normalize();
    }
}
