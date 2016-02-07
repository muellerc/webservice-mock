package org.apache.cmueller.mock.webservice.data;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public Comment createComment() {
        return new Comment();
    }

    public ServiceFault createServiceFault() {
        return new ServiceFault();
    }
}
