package org.apache.cmueller.mock.webservice;

import org.apache.cmueller.mock.webservice.data.Comment;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.company.de/Service/1", name = "Comments", serviceName = "CommentsService")
public interface Comments {

    @WebMethod
    public Comment getComment(Long id) throws ServiceException;
}
