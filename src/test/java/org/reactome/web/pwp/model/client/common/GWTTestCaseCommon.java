package org.reactome.web.pwp.model.client.common;

import com.google.gwt.junit.client.GWTTestCase;
import org.reactome.web.pwp.model.client.classes.DatabaseObject;
import org.reactome.web.pwp.model.client.content.ContentClientError;


/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public abstract class GWTTestCaseCommon extends GWTTestCase {

    /**
     * Must refer to a valid module that sources this class.
     */
    @Override
    public String getModuleName() {
        return "org.reactome.web.pwp.PWPModelJUnit";
    }

    @Override
    protected void gwtSetUp() throws Exception {
        ContentClientAbstract.SERVER = "http://localhost:8585";
        ContentClientAbstract.CONTENT_SERVICE = "/";
    }

    public abstract class ObjectLoadedTest<T extends DatabaseObject> implements ContentClientHandler.ObjectLoaded<T> {
        @Override
        public void onContentClientException(Type type, String message) {
            fail(type + " " + message);
        }

        @Override
        public void onContentClientError(ContentClientError error) {
            fail(error.getMessage().toString());
        }
    }

    public abstract class ObjectMapLoadedTest implements ContentClientHandler.ObjectMapLoaded {
        @Override
        public void onContentClientException(Type type, String message) {
            fail(type + " " + message);
        }

        @Override
        public void onContentClientError(ContentClientError error) {
            fail(error.getMessage().toString());
        }
    }

    public abstract class ObjectListLoadedTest<T extends DatabaseObject> implements ContentClientHandler.ObjectListLoaded<T> {
        @Override
        public void onContentClientException(Type type, String message) {
            fail(type + " " + message);
        }

        @Override
        public void onContentClientError(ContentClientError error) {
            fail(error.getMessage().toString());
        }
    }
}
