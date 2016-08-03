package org.reactome.web.pwp.model.client;

import org.reactome.web.pwp.model.client.classes.DatabaseObject;
import org.reactome.web.pwp.model.client.classes.Event;
import org.reactome.web.pwp.model.client.classes.PhysicalEntity;
import org.reactome.web.pwp.model.client.classes.ReactionLikeEvent;
import org.reactome.web.pwp.model.client.common.ContentClientHandler;
import org.reactome.web.pwp.model.client.content.ContentClientError;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class GwtTestExpectedDuplicates extends GWTTestCaseCommon {

    public void testDuplicatesInReactionLikeEvent() {
        // Since RPC calls are asynchronous, we will need to wait for a response
        // after this test method returns. This line tells the test runner to wait
        // up to 2.5 seconds before timing out.
        delayTestFinish(2500);

        DatabaseObjectFactory.get("R-HSA-5693551", new ContentClientHandler.ObjectCreated() {
            @Override
            public void onObjectCreated(DatabaseObject databaseObject) {
                ReactionLikeEvent rle = databaseObject.cast();
                assertTrue("Expecting more than 2 inputs and found " + rle.getInputs().size(), rle.getInputs().size() > 2);
                assertTrue("Expecting more than 2 outputs and found " + rle.getOutputs().size(), rle.getOutputs().size() > 2);

                int duplicates = 0;
                Set<PhysicalEntity> aux = new HashSet<>();
                for (PhysicalEntity physicalEntity : rle.getInputs()) {
                    if(!aux.contains(physicalEntity)) duplicates++;
                    aux.add(physicalEntity);
                }
                assertTrue("Expecting duplicates in inputs", duplicates > 0);

                duplicates = 0; aux = new HashSet<>();
                for (PhysicalEntity physicalEntity : rle.getOutputs()) {
                    if(!aux.contains(physicalEntity)) duplicates++;
                    aux.add(physicalEntity);
                }
                assertTrue("Expecting duplicates in outputs", duplicates > 0);


                for (Event event : rle.getOrthologousEvent()) {
                    assertTrue("The inferred from has to be the actual object", event.getInferredFrom().size() > 0);
                }
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

//    public void tetsSpeciesList() {
//        // Since RPC calls are asynchronous, we will need to wait for a response
//        // after this test method returns. This line tells the test runner to wait
//        // up to 2.5 seconds before timing out.
//        delayTestFinish(2500);
//
//        ContentClient.getSpeciesList(new ContentClientHandler.SpeciesList() {
//            @Override
//            public void onSpeciesLoaded(List<Species> species) {
//                assertTrue("Species list cannot be empty", species.size() > 0);
//                assertTrue("The first element in the species list has to be human", species.get(0).getTaxId().equals("9606"));
//                finishTest();
//            }
//
//            @Override
//            public void onContentClientException(Type type, String message) {
//                fail(type + " " + message);
//            }
//
//            @Override
//            public void onContentClientError(ContentClientError error) {
//                fail(error.getMessages().toString());
//            }
//        });
//    }
}
