package org.reactome.web.pwp.model.client;

import com.google.gwt.http.client.*;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import org.reactome.web.pwp.model.classes.*;
import org.reactome.web.pwp.model.client.handlers.AncestorsCreatedHandler;
import org.reactome.web.pwp.model.client.handlers.LiteratureReferencesLoadedHandler;
import org.reactome.web.pwp.model.client.handlers.PathwaysForEntitiesLoadedHandler;
import org.reactome.web.pwp.model.factory.DatabaseObjectFactory;
import org.reactome.web.pwp.model.util.Ancestors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class RESTFulClient {

    public static String SERVER = ""; //Here "http://reactome.org" can be set to use CORS
    public static final String CONTENT_SERVICE_PATH = "/ReactomeRESTfulAPI/RESTfulWS/";

    public static void getAncestors(Event event, final AncestorsCreatedHandler handler){
        String url = RESTFulClient.SERVER + RESTFulClient.CONTENT_SERVICE_PATH + "queryEventAncestors/" + event.getDbId();
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url);
        requestBuilder.setHeader("Accept", "application/json");
        try {
            requestBuilder.sendRequest(null, new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {
                    try {
                        JSONArray list = JSONParser.parseStrict(response.getText()).isArray();
                        Ancestors ancestors = new Ancestors(list);
                        //For the time being the events in the ancestors ARE NOT cached
                        handler.onAncestorsLoaded(ancestors);
                    }catch (Exception ex){
                        handler.onAncestorsError(ex);
                    }
                }

                @Override
                public void onError(Request request, Throwable exception) {
                    handler.onAncestorsError(exception);
                }
            });
        } catch (RequestException ex) {
            handler.onAncestorsError(ex);
        }
    }

    public static void loadLiteratureReferences(final Person person, final LiteratureReferencesLoadedHandler handler){
        String url = RESTFulClient.SERVER + RESTFulClient.CONTENT_SERVICE_PATH + "queryReferences/" + person.getDbId();
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url);
        requestBuilder.setHeader("Accept", "application/json");
        try {
            requestBuilder.sendRequest(null, new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {
                    try{
                        JSONArray list = JSONParser.parseStrict(response.getText()).isArray();
                        List<LiteratureReference> references = new ArrayList<>();
                        for(int i=0; i<list.size(); ++i){
                            JSONObject object = list.get(i).isObject();
                            references.add((LiteratureReference) DatabaseObjectFactory.create(object));
                        }
                        person.setLiteratureReferences(references);
                        handler.onLiteratureReferencesLoaded(person);
                    }catch (Exception ex){
                        handler.onLiteratureReferencesError(ex);
                    }
                }

                @Override
                public void onError(Request request, Throwable exception) {
                    handler.onLiteratureReferencesError(exception);
                }
            });
        }
        catch (RequestException ex) {
            handler.onLiteratureReferencesError(ex);
        }
    }


    public static void loadPathwaysForEntities(PhysicalEntity pe, final PathwaysForEntitiesLoadedHandler handler){
        String url = RESTFulClient.SERVER + RESTFulClient.CONTENT_SERVICE_PATH + "pathwaysForEntities";
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url);
        requestBuilder.setHeader("Accept", "application/json");
        String post = "ID=" + pe.getDbId();
        try {
            requestBuilder.sendRequest(post, new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {
                    switch (response.getStatusCode()){
                        case Response.SC_OK:
                            try {
                                JSONArray list = JSONParser.parseStrict(response.getText()).isArray();
                                List<Pathway> pathways = new ArrayList<>();
                                for (int i = 0; i < list.size(); ++i) {
                                    JSONObject object = list.get(i).isObject();
                                    pathways.add((Pathway) DatabaseObjectFactory.create(object));
                                }
                                handler.onPathwaysForEntitiesLoaded(pathways);
                            }catch (Exception e){
                                handler.onPathwaysForEntitiesError(e);
                            }
                            break;
                        default:
                            handler.onPathwaysForEntitiesError(new Exception(response.getStatusText()));
                    }
                }

                @Override
                public void onError(Request request, Throwable exception) {
                    handler.onPathwaysForEntitiesError(exception);
                }
            });
        } catch (RequestException e) {
            handler.onPathwaysForEntitiesError(e);
        }
    }
}
