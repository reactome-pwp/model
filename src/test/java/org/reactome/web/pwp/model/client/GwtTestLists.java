package org.reactome.web.pwp.model.client;

import org.reactome.web.pwp.model.client.classes.Pathway;
import org.reactome.web.pwp.model.client.classes.Species;
import org.reactome.web.pwp.model.client.classes.TopLevelPathway;
import org.reactome.web.pwp.model.client.common.ContentClientHandler;
import org.reactome.web.pwp.model.client.common.GWTTestCaseCommon;
import org.reactome.web.pwp.model.client.content.ContentClient;
import org.reactome.web.pwp.model.client.content.ContentClientError;
import org.reactome.web.pwp.model.client.util.Ancestors;
import org.reactome.web.pwp.model.client.util.Path;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class GwtTestLists extends GWTTestCaseCommon {

    public void testTopLevelPathways() {
        // Since RPC calls are asynchronous, we will need to wait for a response
        // after this test method returns. This line tells the test runner to wait
        // up to 2.5 seconds before timing out.
        delayTestFinish(2500);

        ContentClient.getTopLevelPathways("9606", new ObjectListLoadedTest<TopLevelPathway>() {
            @Override
            public void onObjectListLoaded(List<TopLevelPathway> tlps) {
                assertTrue("There has to be more than 20 top level pathways", tlps.size() > 20);
                finishTest();
            }
        });
    }

    public void testSpeciesList() {
        // Since RPC calls are asynchronous, we will need to wait for a response
        // after this test method returns. This line tells the test runner to wait
        // up to 2.5 seconds before timing out.
        delayTestFinish(2500);

        ContentClient.getSpeciesList(new ObjectListLoadedTest<Species>() {
            @Override
            public void onObjectListLoaded(List<Species> species) {
                assertTrue("Species list cannot be empty", species.size() > 0);
                assertTrue("The first element in the species list has to be human", species.get(0).getTaxId().equals("9606"));
                finishTest();
            }
        });
    }

    public void testPathwaysWithDiagramForEntity(){
        ContentClient.getPathwaysWithDiagramForEntity("R-HSA-199420", true, new ObjectListLoadedTest<Pathway>() {
            @Override
            public void onObjectListLoaded(List<Pathway> pathways) {
                assertTrue(pathways!=null && !pathways.isEmpty());
            }
        });
    }

    public void testAncestors() {
        // Since RPC calls are asynchronous, we will need to wait for a response
        // after this test method returns. This line tells the test runner to wait
        // up to 2.5 seconds before timing out.
        delayTestFinish(2500);

        ContentClient.getAncestors("R-HSA-5673001", new ContentClientHandler.AncestorsLoaded() {
            @Override
            public void onAncestorsLoaded(Ancestors ancestors) {
                assertTrue(ancestors.size() > 0);
                Path path = ancestors.get(0);
                assertTrue(path.size() > 0);
                assertTrue(path.get(path.size() - 1) instanceof TopLevelPathway);
                finishTest();
            }

            @Override
            public void onContentClientException(Type type, String message) {
                fail(type + " " + message);
            }

            @Override
            public void onContentClientError(ContentClientError error) {
                fail(error.getMessage().toString());
            }
        });
    }
}
