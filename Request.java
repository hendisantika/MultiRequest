package com.hendi.main;

import static com.hendi.main.Quickstart.input;
import static com.hendi.main.Quickstart.prop;
import com.hendi.model.NBP;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

/**
 *
 * @author Senju Hashirama
 */
public class Request extends Thread {
    
    List<Shinobi> shinobi;

    public Request() {}
    
    public Request(List<Shinobi> shinobi) {
        this.shinobi = shinobi;
    }


    @Override
    public void run() {
        try {
            // Di sini bikin client web service yang
// mengirimkan list yang berukuran 100 row atau lebih
//        System.out.println("kirim request");
            Request send = new Request();
            send.PostData(shinobi);
            ReadCSV baca = new ReadCSV();
            baca.printnbpList(shinobi);
        } catch (IOException ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    public void PostData(List<Shinobi> node) throws IOException {
        
         input = new FileInputStream("conf/config.properties");
        // load a properties file
        prop.load(input);
        
        
        int rowCount = node.size();
                
        for(int i = 0; i < rowCount; i++){
        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            String urlF5 = prop.getProperty("urlF5");
            
            // Ti Create Request
        
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
        soapEnvelope.addNamespaceDeclaration("ns1", urlF5);
//                "http://10.251.230.201:8080/LvoWebSvcServer/services/LVOWebServiceSend?wsdl");
//        soapEnvelope.addNamespaceDeclaration("ns1", 
//                "http://10.251.38.171:8080/LvoWebSvcServer/services/LVOWebServiceSend?wsdl");
//    	         soapEnvelope.addNamespaceDeclaration("ns1", "http://server.webservice.lvo.convergys.com");
        SOAPBody soapBody = soapEnvelope.getBody();
        SOAPElement soapElement = soapBody.addChildElement("sendSync", "ns1");
        SOAPElement element1 = soapElement.addChildElement("Id");
        SOAPElement element2 = soapElement.addChildElement("Nama");
        SOAPElement element3 = soapElement.addChildElement("Jutsu");
        SOAPElement element4 = soapElement.addChildElement("Level");
        SOAPElement element5 = soapElement.addChildElement("Desa");
        SOAPElement element16 = soapElement.addChildElement("timeout");
        
                    element1.addTextNode("GenericEvent");
                    element2.addTextNode(node.get(i).getID());
                    element3.addTextNode(node.get(i).getNama());
                    element4.addTextNode(node.get(i).getJutsu());
                    element5.addTextNode(node.get(i).getLevel());
                    element6.addTextNode(node.get(i).getDesa());
                    element16.addTextNode("1000");
                    
        soapMessage.saveChanges();
        System.out.println("\n");
        System.out.println("----------SOAP Request------------");
        soapMessage.writeTo(System.out);
//        
            
//            SOAPMessage soapRequest = createSoapRequest();
            SOAPMessage soapRequest = soapMessage;
            //hit soapRequest to the server to get response
            SOAPMessage soapResponse = soapConnection.call(soapRequest, urlF5);
            createSoapResponse(soapResponse);
            soapConnection.close();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    }
    
    public void createSoapResponse(SOAPMessage soapResponse) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        System.out.println("\n");
        System.out.println("\n----------SOAP Response-----------");
        System.out.println("");
        StreamResult result = new StreamResult(System.out);
        transformer.transform(sourceContent, result);
        System.out.println("");
    }
}
