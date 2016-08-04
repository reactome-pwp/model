package org.reactome.web.pwp.model.client;

import com.google.gwt.junit.client.GWTTestCase;
import org.reactome.web.pwp.model.client.common.ContentClientAbstract;


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

}
