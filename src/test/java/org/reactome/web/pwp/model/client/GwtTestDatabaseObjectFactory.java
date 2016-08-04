package org.reactome.web.pwp.model.client;

import org.reactome.web.pwp.model.client.classes.DatabaseObject;
import org.reactome.web.pwp.model.client.common.ContentClientHandler;
import org.reactome.web.pwp.model.client.content.ContentClientError;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectFactory;


/**
 * GWT JUnit <b>integration</b> tests must extend GWTTestCase.
 * Using <code>"GwtTest*"</code> naming pattern exclude them from running with
 * surefire during the test phase.
 * <p/>
 * If you run the tests using the Maven command line, you will have to
 * navigate with your browser to a specific url given by Maven.
 * See https://gwt-maven-plugin.github.io/gwt-maven-plugin/user-guide/testing.html
 * for details.
 */
public class GwtTestDatabaseObjectFactory extends GWTTestCaseCommon {

    private static String stId = "R-HSA-199420";

    /**
     * Tests the ...
     */
    public void testBasicObjectFactoryUseCase() {
        // Since RPC calls are asynchronous, we will need to wait for a response
        // after this test method returns. This line tells the test runner to wait
        // up to 2.5 seconds before timing out.
        delayTestFinish(2500);

        DatabaseObjectFactory.get(stId, new ContentClientHandler.ObjectReady() {
            @Override
            public void onObjectReady(DatabaseObject databaseObject) {
                assertTrue("The stId has to be `" + stId + "'. Found: '" + databaseObject.getStId() + "'", databaseObject.getStId().equals(stId));
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

//    /**
//     * This test will send a request to the server using the greetServer method in
//     * GreetingService and verify the response.
//     */
//    public void testPathwaysSummariesNoProjection() {
//        // Since RPC calls are asynchronous, we will need to wait for a response
//        // after this test method returns. This line tells the test runner to wait
//        // up to 10 seconds before timing out.
//        delayTestFinish(10000);
//
//        AnalysisClient.SERVER = "http://reactomedev.oicr.on.ca";
//        List<String> pathways = Arrays.asList("1257604","166520","187037","000000");
//        AnalysisClient.getPathwaySummaries(token, "TOTAL", pathways, new ContentClientHandler.Summaries() {
//            @Override
//            public void onPathwaySummariesLoaded(List<PathwaySummary> pathwaySummaries, long time) {
//                assertTrue("Only three of them should be there", pathwaySummaries.size() == 3);
//                finishTest();
//            }
//
//            @Override
//            public void onPathwaySummariesNotFound(long time) {
//                fail("No pathways summaries found for " + token);
//            }
//
//            @Override
//            public void onPathwaySummariesError(AnalysisError error) {
//                fail(error.getReason());
//            }
//
//            @Override
//            public void onAnalysisServerException(String message) {
//                fail(message);
//            }
//        });
//    }

}