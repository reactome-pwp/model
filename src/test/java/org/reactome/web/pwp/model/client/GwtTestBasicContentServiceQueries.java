package org.reactome.web.pwp.model.client;

import org.reactome.web.pwp.model.client.classes.DatabaseObject;
import org.reactome.web.pwp.model.client.classes.EntityWithAccessionedSequence;
import org.reactome.web.pwp.model.client.classes.Pathway;
import org.reactome.web.pwp.model.client.common.GWTTestCaseCommon;
import org.reactome.web.pwp.model.client.content.ContentClient;
import org.reactome.web.pwp.model.client.content.ContentClientError;

import java.util.*;


/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class GwtTestBasicContentServiceQueries extends GWTTestCaseCommon {

    public void testBasicObjectFactoryUseCase() {
        // Since RPC calls are asynchronous, we will need to wait for a response
        // after this test method returns. This line tells the test runner to wait
        // up to 2.5 seconds before timing out.
        delayTestFinish(2500);

        String stId = "R-HSA-199420";
        ContentClient.query("R-HSA-199420", new ObjectLoadedTest<EntityWithAccessionedSequence>() {
            @Override
            public void onObjectLoaded(EntityWithAccessionedSequence ewas) {
                assertEquals("The stId has to be `" + stId + "'. Found: '" + ewas.getStId() + "'", ewas.getStId(), stId);
                finishTest();
            }
        });
    }

    public void testDatabaseObjectLoad() {
        // Since RPC calls are asynchronous, we will need to wait for a response
        // after this test method returns. This line tells the test runner to wait
        // up to 2.5 seconds before timing out.
        delayTestFinish(2500);

        ContentClient.query("R-HSA-1640170", new ObjectLoadedTest<Pathway>() {
            @Override
            public void onObjectLoaded(Pathway p) {
                assertFalse(p.getOrthologousEvent().isEmpty());

                Pathway orth = p.getOrthologousEvent().get(0).cast();
                assertNull(orth.getCreated());

                orth.load(new ObjectLoaded() {
                    @Override
                    public void onObjectLoaded(DatabaseObject databaseObject) {
                        Pathway orth = databaseObject.cast();
                        assertNotNull(orth.getCreated());
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
                finishTest();
            }
        });
    }

    public void testAttributesLoaded() {
        // Since RPC calls are asynchronous, we will need to wait for a response
        // after this test method returns. This line tells the test runner to wait
        // up to 2.5 seconds before timing out.
        delayTestFinish(2500);

        ContentClient.query("R-HSA-400253", "figure", new AttributesLoadedTest() {
            @Override
            public void onAttributesLoaded(List<String[]> attributes) {
                assertFalse(attributes.isEmpty());
                finishTest();
            }

            @Override
            public void onContentClientException(Type type, String message) {
                fail(type + " " + message);
            }

            @Override
            public void onContentClientError(ContentClientError error) {
                fail(error.getReason() + "\n" + String.join("\n", Optional.ofNullable(error.getMessage()).orElse(new ArrayList<>())));
            }
        });
    }

    public void testQueryIdsMap() {
        // Since RPC calls are asynchronous, we will need to wait for a response
        // after this test method returns. This line tells the test runner to wait
        // up to 2.5 seconds before timing out.
        delayTestFinish(2500);

        ContentClient.query(Arrays.asList("REACT_13", "R-HSA-199420", "199420"), new ObjectMapLoadedTest() {
            @Override
            public void onObjectMapLoaded(Map<String, ? extends DatabaseObject> map) {
                assertNotNull(map.get("REACT_13"));
                assertEquals("R-HSA-71291", map.get("REACT_13").getStId());

                assertNotNull(map.get("R-HSA-199420"));
                assertEquals(199420L, (long) map.get("R-HSA-199420").getDbId());

                assertNotNull(map.get("199420"));
                assertEquals("R-HSA-199420", map.get("199420").getStId());
                finishTest();
            }
        });

    }
}