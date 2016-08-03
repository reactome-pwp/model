package org.reactome.web.pwp.model.client;

import org.reactome.web.pwp.model.client.classes.Pathway;
import org.reactome.web.pwp.model.client.classes.Species;
import org.reactome.web.pwp.model.client.common.ContentClientHandler;
import org.reactome.web.pwp.model.client.content.ContentClient;
import org.reactome.web.pwp.model.client.content.ContentClientError;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class GwtTestLists extends GWTTestCaseCommon {

    public void testTopLevelPathways(){
        // Since RPC calls are asynchronous, we will need to wait for a response
        // after this test method returns. This line tells the test runner to wait
        // up to 2.5 seconds before timing out.
        delayTestFinish(2500);

        ContentClient.getTopLevelPathways("9606", new ContentClientHandler.TopLevelPathways() {
            @Override
            public void onTopLevelPathwaysLoaded(List<Pathway> tpls) {
                assertTrue("There has to be more than 20 top level pathways", tpls.size() > 20);
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

    public void tetsSpeciesList(){
        // Since RPC calls are asynchronous, we will need to wait for a response
        // after this test method returns. This line tells the test runner to wait
        // up to 2.5 seconds before timing out.
        delayTestFinish(2500);

        ContentClient.getSpeciesList(new ContentClientHandler.SpeciesList() {
            @Override
            public void onSpeciesLoaded(List<Species> species) {
                assertTrue("Species list cannot be empty", species.size() > 0);
                assertTrue("The first element in the species list has to be human", species.get(0).getTaxId().equals("9606"));
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
