package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class GO_MolecularFunction extends GO_Term {

    private List<String> ecNumber;
    private GO_MolecularFunction componentOf;
    private GO_MolecularFunction negativelyRegulate;
    private GO_MolecularFunction positivelyRegulate;
    private GO_MolecularFunction regulate;

    public GO_MolecularFunction() {
        super(SchemaClass.GO_BIOLOGICAL_FUNCTION);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.ecNumber = DatabaseObjectUtils.getStringList(jsonObject, "ecNumber");

        setDatabaseObject(jsonObject.get("componentOf"), () ->
                componentOf = DatabaseObjectUtils.getDatabaseObject(jsonObject, "componentOf")
        );

        setDatabaseObject(jsonObject.get("negativelyRegulate"), () ->
                negativelyRegulate = DatabaseObjectUtils.getDatabaseObject(jsonObject, "negativelyRegulate")
        );

        setDatabaseObject(jsonObject.get("positivelyRegulate"), () ->
                positivelyRegulate = DatabaseObjectUtils.getDatabaseObject(jsonObject, "positivelyRegulate")
        );

        setDatabaseObject(jsonObject.get("regulate"), () ->
                regulate = DatabaseObjectUtils.getDatabaseObject(jsonObject, "regulate")
        );
    }

    public List<String> getEcNumber() {
        return ecNumber;
    }

    public GO_MolecularFunction getComponentOf() {
        return componentOf;
    }

    public GO_MolecularFunction getNegativelyRegulate() {
        return negativelyRegulate;
    }

    public GO_MolecularFunction getPositivelyRegulate() {
        return positivelyRegulate;
    }

    public GO_MolecularFunction getRegulate() {
        return regulate;
    }
}
