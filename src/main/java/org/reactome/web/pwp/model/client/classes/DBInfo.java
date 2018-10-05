package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;

/**
 * @author Antonio Fabregat (fabregat@ebi.ac.uk)
 */
public class DBInfo {

    private String name;
    private Integer version;
    private Long checksum;

    public DBInfo(JSONObject jsonObject) {
        this.name = DatabaseObjectUtils.getStringValue(jsonObject, "name");
        this.version = DatabaseObjectUtils.getIntValue(jsonObject, "version");
        this.checksum = DatabaseObjectUtils.getLongValue(jsonObject, "checksum");
    }

    public String getName() {
        return name;
    }

    public Integer getVersion() {
        return version;
    }

    public Long getChecksum() {
        return checksum;
    }
}
