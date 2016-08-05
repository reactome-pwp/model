package org.reactome.web.pwp.model.client.util;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.classes.DatabaseObject;
import org.reactome.web.pwp.model.client.classes.Event;
import org.reactome.web.pwp.model.client.classes.Pathway;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectFactory;

import java.util.*;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class Ancestors implements Iterable<Path>, Comparator<Path> {

    private List<Path> pathList;

    public Ancestors(JSONArray jsonArray) {
        this.pathList = new LinkedList<>();
        for (int i = 0; i < jsonArray.size(); ++i) {
            JSONArray jPath = jsonArray.get(i).isArray();
            Path path = new Path();
            for (int j = 0; j < jPath.size(); ++j) {
                JSONObject object = jPath.get(j).isObject();
                path.add((Event) DatabaseObjectFactory.create(object));
            }
            this.pathList.add(path);
        }
        this.removeOrphanPaths();
        Collections.sort(pathList, this);
    }

    public Path get(int index) {
        return this.pathList.get(index);
    }

    public void removeOrphanPaths() {
        List<Path> aux = new LinkedList<>();
        for (Path path : this.pathList) {
            if (path.rootHasDiagram()) {
                aux.add(path);
            }
        }
        this.pathList = aux;
    }

    public List<Path> getPathsContaining(List<Event> path) {
        List<Path> list = new LinkedList<>();
        for (Path aux : this.pathList) {
            if (aux.contains(path)) {
                list.add(aux);
            }
        }
        return list;
    }

    public List<Path> getPathsContaining(Event event) {
        List<Path> list = new LinkedList<>();
        for (Path path : this.pathList) {
            if (path.contains(event)) {
                list.add(path);
            }
        }
        return list;
    }

    public List<Pathway> getPathways() {
        List<Pathway> pathwayList = new LinkedList<>();
        for (Path path : this.pathList) {
            Pathway pathway = path.getLastPathway();
            if (pathway != null) {
                pathwayList.add(pathway);
            }
        }
        return pathwayList;
    }

    public int size() {
        return this.pathList.size();
    }

    @Override
    public Iterator<Path> iterator() {
        return this.pathList.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Paths\n");
        for (Path path : this.pathList) {
            sb.append("\t[ ");
            boolean addBar = false;
            for (DatabaseObject databaseObject : path) {
                if (addBar) sb.append(" | ");
                addBar = true;
                sb.append(databaseObject.getDisplayName());
            }
            sb.append("]\n");
        }
        return sb.toString();
    }

    @Override
    public int compare(Path o1, Path o2) {
        return o1.size().compareTo(o2.size());
    }
}
