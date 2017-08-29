package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class ReferenceTherapeutic extends ReferenceEntity {

    private String abbreviation;
    private List<String> approvalSource;
    private Boolean approved;
    private String inn;
    private String type;

    public ReferenceTherapeutic() {
        super(SchemaClass.REFERENCE_THERAPEUTIC);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.abbreviation = DatabaseObjectUtils.getStringValue(jsonObject, "abbreviation");
        this.approvalSource = DatabaseObjectUtils.getStringList(jsonObject, "approvalSource");
        this.approved = DatabaseObjectUtils.getBooleanValue(jsonObject, "approved");
        this.inn = DatabaseObjectUtils.getStringValue(jsonObject, "inn");
        this.type = DatabaseObjectUtils.getStringValue(jsonObject, "type");
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public List<String> getApprovalSource() {
        return approvalSource;
    }

    public Boolean getApproved() {
        return approved;
    }

    public String getInn() {
        return inn;
    }

    public String getType() {
        return type;
    }
}
