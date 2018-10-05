package org.reactome.web.pwp.model.client;

import org.reactome.web.pwp.model.client.classes.DBInfo;
import org.reactome.web.pwp.model.client.common.ContentClientHandler;
import org.reactome.web.pwp.model.client.common.GWTTestCaseCommon;
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

        ContentClient.getDatabaseInformation(new ContentClientHandler.DatabaseInfo() {
            @Override
            public void onDatabaseInfoLoaded(DBInfo dbInfo) {
                String name = dbInfo.getName();
                assertTrue("Name cannot be null or empty", name != null && !name.isEmpty());
                Integer version = dbInfo.getVersion();
                assertNotNull("Version cannot be null", version);

                Long checksum = dbInfo.getChecksum();
                assertNotNull("Version cannot be null", checksum);

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
