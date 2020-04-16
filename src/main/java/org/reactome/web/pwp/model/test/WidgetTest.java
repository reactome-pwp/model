package org.reactome.web.pwp.model.test;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import org.reactome.web.pwp.model.client.classes.DBInfo;
import org.reactome.web.pwp.model.client.classes.Pathway;
import org.reactome.web.pwp.model.client.classes.Species;
import org.reactome.web.pwp.model.client.classes.TopLevelPathway;
import org.reactome.web.pwp.model.client.common.ContentClientHandler;
import org.reactome.web.pwp.model.client.content.ContentClient;
import org.reactome.web.pwp.model.client.content.ContentClientError;

import java.util.List;


/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class WidgetTest implements EntryPoint {

    private FlowPanel container = new FlowPanel();

    @Override
    public void onModuleLoad() {
        Scheduler.get().scheduleDeferred(() -> {
            RootPanel.get().add(container);
            addDatabaseName();
            addSpecies();
            addTopLvelPathway();
            getCitation("31691815");
//                //RAF/MAP -> 109869 (for v52)
//                DatabaseObjectFactory.get(109869L, new DatabaseObjectCreatedHandler() {
//                    @Override
//                    public void onDatabaseObjectLoaded(DatabaseObject databaseObject) {
//                        Event event = databaseObject.cast();
//                        RESTFulClient.getAncestors(event, new AncestorsCreatedHandler() {
//                            @Override
//                            public void onAncestorsLoaded(Ancestors ancestors) {
//                                System.out.println(ancestors);
//                            }
//
//                            @Override
//                            public void onAncestorsError(Throwable exception) {
//
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onDatabaseObjectError(Throwable exception) {
//
//                    }
//                });
//
//                DatabaseObjectFactory.get(Arrays.asList(1643685,5663202,1643713), new DatabaseObjectsCreatedHandler() {
//                    @Override
//                    public void onDatabaseObjectsLoaded(Map<String, DatabaseObject> databaseObjects) {
//                        for (DatabaseObject databaseObject : databaseObjects.values()) {
//                            System.out.println(databaseObject);
//                        }
//                    }
//
//                    @Override
//                    public void onDatabaseObjectError(Throwable exception) {
//
//                    }
//                });
//                DatabaseObjectFactory.get("R-HSA-199420", new DatabaseObjectCreatedHandler() {
//                    @Override
//                    public void onDatabaseObjectLoaded(DatabaseObject databaseObject) {
//                        Window.alert(databaseObject.getDisplayName());
//                    }
//
//                    @Override
//                    public void onDatabaseObjectError(Throwable exception) {
//                        Window.alert(exception.getMessage());
//                    }
//                });
        });
    }

    private void addDatabaseName(){
        ContentClient.getDatabaseInformation(new ContentClientHandler.DatabaseInfo() {
            @Override
            public void onDatabaseInfoLoaded(DBInfo dbInfo) {
                container.add(new Label("Database name: " + dbInfo.getName()));
                container.add(new Label("Version: " + dbInfo.getVersion()));
            }

            @Override
            public void onContentClientException(Type type, String message) {
                //TODO
            }

            @Override
            public void onContentClientError(ContentClientError error) {
                //TODO
            }
        });
    }

    private void addSpecies(){
        ContentClient.getSpeciesList(new ContentClientHandler.ObjectListLoaded<Species>() {
            @Override
            public void onObjectListLoaded(List<Species> species) {
                ListBox speciesList = new ListBox();
                for (Species s : species) {
                    speciesList.addItem(s.getDisplayName(), s.getReactomeIdentifier());
                }
                container.add(speciesList);
            }

            @Override
            public void onContentClientException(Type type, String message) {
                //TODO
            }

            @Override
            public void onContentClientError(ContentClientError error) {
                //TODO
            }
        });
    }

    private void addTopLvelPathway(){
        ContentClient.getTopLevelPathways("9606", new ContentClientHandler.ObjectListLoaded<TopLevelPathway>() {
            @Override
            public void onObjectListLoaded(List<TopLevelPathway> tpls) {
                ListBox listBox = new ListBox();
                for (Pathway tpl : tpls) {
                    listBox.addItem(tpl.getDisplayName(), tpl.getReactomeIdentifier());
                }
                container.add(listBox);
            }

            @Override
            public void onContentClientException(Type type, String message) {
                //TODO
            }

            @Override
            public void onContentClientError(ContentClientError error) {
                //TODO
            }
        });
    }

    private void getCitation(String id) {
        ContentClient.getStaticCitation(id, new ContentClientHandler.Citation() {
            @Override
            public void onCitationTextLoaded(String citation) {
                container.add(new Label(citation));
            }

            @Override
            public void onContentClientException(Type type, String message) {
                container.add(new Label("in content client exception"));
                container.add(new Label(type.toString()));
                container.add(new Label(message));
            }

            @Override
            public void onContentClientError(ContentClientError error) {
                container.add(new Label("in content client error"));
                container.add(new Label(error.toString()));
            }
        });
    }

}