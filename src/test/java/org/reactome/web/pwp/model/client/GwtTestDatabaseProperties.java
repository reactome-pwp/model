package org.reactome.web.pwp.model.client;

import org.reactome.web.pwp.model.client.common.ContentClientHandler;
import org.reactome.web.pwp.model.client.content.ContentClient;
import org.reactome.web.pwp.model.client.content.ContentClientError;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class GwtTestDatabaseProperties extends GWTTestCaseCommon {

    public void testVersion() {
        // Since RPC calls are asynchronous, we will need to wait for a response
        // after this test method returns. This line tells the test runner to wait
        // up to 2.5 seconds before timing out.
        delayTestFinish(2500);

        ContentClient.getDatabaseVersion(new ContentClientHandler.Version() {
            @Override
            public void onVersionLoaded(String version) {
                assertTrue("Version cannot be null or empty", version != null && !version.isEmpty());
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

    public void testDbName() {
        // Since RPC calls are asynchronous, we will need to wait for a response
        // after this test method returns. This line tells the test runner to wait
        // up to 2.5 seconds before timing out.
        delayTestFinish(2500);

        ContentClient.getDatabaseName(new ContentClientHandler.DatabaseName() {
            @Override
            public void onDatabaseNameLoaded(String name) {
                assertTrue("Database name cannot be null or empty", name != null && !name.isEmpty());
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
