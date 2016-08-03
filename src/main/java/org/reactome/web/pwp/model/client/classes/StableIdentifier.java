package org.reactome.web.pwp.model.client.classes;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
@Deprecated
public class StableIdentifier {/* extends DatabaseObject {
    private String identifier;
    private String identifierVersion;
    private ReferenceDatabase referenceDatabase;
    private String referenceDatabaseClass;

    public StableIdentifier() {
        super(SchemaClass.STABLE_IDENTIFIER);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.identifier = DatabaseObjectUtils.getStringValue(jsonObject, "identifier");

        this.identifierVersion = DatabaseObjectUtils.getStringValue(jsonObject, "identifierVersion");

        this.referenceDatabase = DatabaseObjectUtils.getDatabaseObject(jsonObject, "referenceDatabase");

        this.referenceDatabaseClass = DatabaseObjectUtils.getStringValue(jsonObject, "referenceDatabaseClass");
    }

    public String getIdentifier() {
        return identifier != null ? identifier : getDisplayName().split("\\.")[0];
    }

    public String getIdentifierVersion() {
        return identifierVersion;
    }

    public ReferenceDatabase getReferenceDatabase() {
        return referenceDatabase;
    }

    public String getReferenceDatabaseClass() {
        return referenceDatabaseClass;
    }
    */
}
