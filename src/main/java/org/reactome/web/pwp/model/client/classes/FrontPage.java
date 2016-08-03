package org.reactome.web.pwp.model.client.classes;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
@Deprecated
public class FrontPage { /* extends DatabaseObject {

    private List<Event> frontPageItem;

    public FrontPage() {
        super(SchemaClass.FRONT_PAGE);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.frontPageItem = new LinkedList<>();
        for (JSONObject object : DatabaseObjectUtils.getObjectList(jsonObject, "frontPageItem")) {
            this.frontPageItem.add((Event) DatabaseObjectFactory.create(object));
        }
    }

    public List<Event> getFrontPageItem() {
        return frontPageItem;
    }
    */
}
