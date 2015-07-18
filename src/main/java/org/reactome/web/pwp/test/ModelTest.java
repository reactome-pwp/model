package org.reactome.web.pwp.test;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import org.reactome.web.pwp.model.classes.*;
import org.reactome.web.pwp.model.factory.DatabaseObjectFactory;
import org.reactome.web.pwp.model.handlers.AncestorsCreatedHandler;
import org.reactome.web.pwp.model.handlers.DatabaseObjectCreatedHandler;
import org.reactome.web.pwp.model.handlers.DatabaseObjectLoadedHandler;
import org.reactome.web.pwp.model.handlers.DatabaseObjectsCreatedHandler;
import org.reactome.web.pwp.model.util.Ancestors;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class ModelTest implements EntryPoint {
    @Override
    public void onModuleLoad() {
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
                System.out.println("");

                //RAF/MAP -> 109869 (for v52)
                DatabaseObjectFactory.get(109869L, new DatabaseObjectCreatedHandler() {
                    @Override
                    public void onDatabaseObjectLoaded(DatabaseObject databaseObject) {
                        Event event = databaseObject.cast();
                        DatabaseObjectFactory.getAncestors(event, new AncestorsCreatedHandler() {
                            @Override
                            public void onAncestorsLoaded(Ancestors ancestors) {
                                System.out.println(ancestors);
                            }

                            @Override
                            public void onAncestorsError(Throwable exception) {

                            }
                        });
                    }

                    @Override
                    public void onDatabaseObjectError(Throwable exception) {

                    }
                });

                DatabaseObjectFactory.get(Arrays.asList(1643685,5663202,1643713), new DatabaseObjectsCreatedHandler() {
                    @Override
                    public void onDatabaseObjectsLoaded(Map<String, DatabaseObject> databaseObjects) {
                        for (DatabaseObject databaseObject : databaseObjects.values()) {
                            System.out.println(databaseObject);
                        }
                    }

                    @Override
                    public void onDatabaseObjectError(Throwable exception) {

                    }
                });
            }
        });
    }

    private void testPathway(Pathway pathway){
        for (Event event : pathway.getOrthologousEvent()) {
            System.out.println(event.getSpecies());
        }
    }

    private void testEWAS(final EntityWithAccessionedSequence ewas){
        for (PhysicalEntity pe : ewas.getInferredTo()) {
            pe.load(new DatabaseObjectLoadedHandler() {
                @Override
                public void onDatabaseObjectLoaded(DatabaseObject databaseObject) {
                    PhysicalEntity pe = databaseObject.cast();
                    printInferred(pe);
                }

                @Override
                public void onDatabaseObjectError(Throwable trThrowable) {
                    System.err.println(trThrowable.getMessage());
                }
            });
        }
    }

    private void printInferred(PhysicalEntity pe){
        StringBuilder sb = new StringBuilder("[");
        for (Species species : pe.getSpecies()) {
            sb.append(species.getDisplayName()).append(",");
        }
        sb.delete(sb.length()-1, sb.length()).append("]");
        System.out.println(pe.getDisplayName() + sb.toString());
    }
}