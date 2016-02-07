package org.apache.cmueller.mock.webservice;

import org.apache.cmueller.mock.webservice.data.Comment;
import org.apache.cmueller.mock.webservice.data.ServiceFault;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;

@Stateless
@WebService(targetNamespace = "http://www.company.de/Service/1", name = "Comments", serviceName = "CommentsService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class CommentsService implements Comments {

    private JAXBContext jc;
    private Unmarshaller u;

    @PostConstruct
    public void init() {
        try {
            jc = JAXBContext.newInstance(Comment.class.getPackage().getName());
            u = jc.createUnmarshaller();
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }

    @WebMethod
    public Comment getComment(Long id) throws ServiceException {
        String operationName = "getComment";
        String fileLookUpName = String.format("%s_%d.xml", operationName, id); // e.g. getComment_1.xml

        System.out.println("looking up: " + fileLookUpName);

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(fileLookUpName);
            int available = is.available();

            return (Comment) u.unmarshal(is);
        } catch (JAXBException e) {
            ServiceFault serviceFault = new ServiceFault();
            serviceFault.setFaultCode("0002");
            serviceFault.setFaultText("response file invalid");

            throw new ServiceException("response file invalid", serviceFault, e);
        } catch (IOException e) {
            ServiceFault serviceFault = new ServiceFault();
            serviceFault.setFaultCode("0001");
            serviceFault.setFaultText("response file not found");

            throw new ServiceException("response file not found", serviceFault, e);
        }
    };
}
