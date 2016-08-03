package org.reactome.web.pwp.model.client;

import org.reactome.web.pwp.model.client.classes.Publication;
import org.reactome.web.pwp.model.client.common.ContentClientHandler;
import org.reactome.web.pwp.model.client.content.ContentClient;
import org.reactome.web.pwp.model.client.content.ContentClientError;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class GwtTestPersonPublications extends GWTTestCaseCommon {

    private static final int PERSON = 73447; //Bijay

    public void testPersonPublications(){
        // Since RPC calls are asynchronous, we will need to wait for a response
        // after this test method returns. This line tells the test runner to wait
        // up to 2.5 seconds before timing out.
        delayTestFinish(2500);

        ContentClient.getPersonPublications("" + PERSON, new ContentClientHandler.Publications() {
            @Override
            public void onPublicationsLoaded(List<Publication> publications) {
                assertTrue("Bijay has one or more publications", publications.size() > 0);
                boolean found = false;
                for (Publication publication : publications) {
                    found |= publication.getDbId().equals(5612460L);
                }
                assertTrue("Bijay has a reference with id 5612460", found);
                finishTest();
            }

            @Override
            public void onContentClientException(Type type, String message) {
                fail(type + " " + message);
            }

            @Override
            public void onContentClientError(ContentClientError error) {
                fail(error.getMessages().toString());
            }
        });
    }
}
