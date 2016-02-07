package org.apache.cmueller.mock.webservice;

import org.apache.cmueller.mock.webservice.data.ServiceFault;

import javax.xml.ws.WebFault;

@WebFault(name = "serviceFault", targetNamespace = "http://www.company.de/Service/1/data")
public class ServiceException extends Exception {
    
    private ServiceFault serviceFault;

    public ServiceException() {
        super();
    }
    
    public ServiceException(String message) {
        super(message);
    }
    
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message, ServiceFault serviceFault) {
        super(message);
        this.serviceFault = serviceFault;
    }

    public ServiceException(String message, ServiceFault serviceFault, Throwable cause) {
        super(message, cause);
        this.serviceFault = serviceFault;
    }

    public ServiceFault getFaultInfo() {
        return this.serviceFault;
    }
}
