package org.reactome.web.pwp.model.client.content;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class ContentClientFactory {

    interface AnalysisAutoBeanFactory extends AutoBeanFactory {
        AutoBean<ContentClientError> error();
    }

    public static ContentClientError getContentClientError(String json) throws ContentClientException {
        try{
            AutoBeanFactory factory = GWT.create(AnalysisAutoBeanFactory.class);
            AutoBean<ContentClientError> bean = AutoBeanCodex.decode(factory, ContentClientError.class, json);
            return bean.as();
        }catch (Throwable e){
            throw new ContentClientException("Error mapping json string for [" + ContentClientError.class + "]: " + json, e);
        }
    }
}
