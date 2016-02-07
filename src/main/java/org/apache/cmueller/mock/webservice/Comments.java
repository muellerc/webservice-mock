package org.apache.cmueller.mock.webservice;

import org.apache.cmueller.mock.webservice.data.Comment;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@Stateless
@WebService(targetNamespace = "http://www.company.de/Service/1", name = "Comments", serviceName = "CommentsService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class Comments {

    @WebMethod
    public Comment getComment(Long commentId) throws ServiceException {
        //String operationName = "getComment";
        //Long commentId = request.getCommentId();
        //String fileLookUpName = String.format("%s_%d.xml", operationName, commentId); // e.g. getComment_1.xml
        //String xmlConent = IOUtils.toString(getClass().getResource(fileLookUpName), "UTF-8");

        Comment comment = new Comment();
        comment.setCommentId(commentId);
        comment.setTest("Comment for id " + commentId);

        return comment;
    };
}
