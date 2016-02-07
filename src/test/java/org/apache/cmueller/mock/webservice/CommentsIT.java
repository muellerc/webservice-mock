package org.apache.cmueller.mock.webservice;

import org.apache.cmueller.mock.webservice.data.Comment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class CommentsIT {

    private static Service commentsService;

    @ArquillianResource
    private URL url;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addPackage("org.apache.cmueller.mock.webservice")
                .addPackage("org.apache.cmueller.mock.webservice.data")
                .addAsResource("getComment_1.xml", "getComment_1.xml");
    }

    @Before
    public void setupClass() throws MalformedURLException {
        commentsService = Service.create(
                new URL(url, "CommentsService/Comments?wsdl"),
                new QName("http://www.company.de/Service/1", "CommentsService"));
    }

    @Test
    public void textComment1() throws MalformedURLException, ServiceException {
        Comments comments = commentsService.getPort(Comments.class);

        Comment comment = comments.getComment(Long.valueOf(1L));

        assertEquals(Long.valueOf(1L), comment.getId());
        assertEquals("Comment for id 1", comment.getText());
    }
}