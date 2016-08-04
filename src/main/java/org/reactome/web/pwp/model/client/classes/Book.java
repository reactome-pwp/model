package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class Book extends Publication {

    private String isbn;
    private String chapterTitle;
    private String pages;
    private Affiliation publisher;
    private String publisherClass;
    private Integer year;

    public Book() {
        super(SchemaClass.BOOK);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.isbn = DatabaseObjectUtils.getStringValue(jsonObject, "ISBN");

        this.chapterTitle = DatabaseObjectUtils.getStringValue(jsonObject, "chapterTitle");

        this.pages = DatabaseObjectUtils.getStringValue(jsonObject, "pages");

        setDatabaseObject(jsonObject.get("publisher"), () ->
                publisher = DatabaseObjectUtils.getDatabaseObject(jsonObject, "publisher")
        );

        this.publisherClass = DatabaseObjectUtils.getStringValue(jsonObject, "publisherClass");

        this.year = DatabaseObjectUtils.getIntValue(jsonObject, "year");
    }

    public String getIsbn() {
        return isbn;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public String getPages() {
        return pages;
    }

    public Affiliation getPublisher() {
        return publisher;
    }

    public String getPublisherClass() {
        return publisherClass;
    }

    public Integer getYear() {
        return year;
    }
}
