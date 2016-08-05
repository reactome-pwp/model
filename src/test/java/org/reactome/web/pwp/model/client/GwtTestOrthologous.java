package org.reactome.web.pwp.model.client;

import org.reactome.web.pwp.model.client.classes.DatabaseObject;
import org.reactome.web.pwp.model.client.classes.EntityWithAccessionedSequence;
import org.reactome.web.pwp.model.client.common.GWTTestCaseCommon;
import org.reactome.web.pwp.model.client.content.ContentClient;

import java.util.Collections;
import java.util.Map;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class GwtTestOrthologous extends GWTTestCaseCommon {

    public void testOrthologous(){
        // Since RPC calls are asynchronous, we will need to wait for a response
        // after this test method returns. This line tells the test runner to wait
        // up to 2.5 seconds before timing out.
        delayTestFinish(2500);

        ContentClient.getOrthologous("R-HSA-199420", 49633L, new ObjectLoadedTest<EntityWithAccessionedSequence>() {
            @Override
            public void onObjectLoaded(EntityWithAccessionedSequence ewas) {
                assertTrue(ewas.getStId().equals("R-SSC-199420"));
                finishTest();
            }
        });
    }

    public void testOrthologousMap(){
        // Since RPC calls are asynchronous, we will need to wait for a response
        // after this test method returns. This line tells the test runner to wait
        // up to 2.5 seconds before timing out.
        delayTestFinish(2500);

        String stId = "R-HSA-199420";
        ContentClient.getOrthologousMap(Collections.singletonList(stId), 49633L, new ObjectMapLoadedTest() {
            @Override
            public void onObjectMapLoaded(Map<String, DatabaseObject> map) {
                assertTrue(map!=null && !map.isEmpty());
                assertTrue(map.get(stId).getStId().equals("R-SSC-199420"));
                finishTest();
            }
        });
    }
}
