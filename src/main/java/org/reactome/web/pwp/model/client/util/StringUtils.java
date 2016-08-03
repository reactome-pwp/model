package org.reactome.web.pwp.model.client.util;

import org.reactome.web.pwp.model.client.classes.DatabaseObject;

import java.util.Collection;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class StringUtils {

    public static String join(Collection<?> list, String separator){
        if(list == null) return null;
        if(separator == null) separator = "";
        StringBuilder sb = new StringBuilder("");
        for (Object s : list) {
            if(s instanceof String || s instanceof Long || s instanceof Integer) {
                sb.append(s.toString()).append(separator);
            }else if(s instanceof DatabaseObject){
                sb.append(((DatabaseObject) s).getDbId()).append(separator);
            }
        }
        sb.delete(sb.length() - separator.length(), sb.length());
        return sb.toString();
    }
}
