package org.reactome.web.pwp.model.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.factory.SchemaClass;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class Book extends Publication {

    private String isbn;
    private String chapterTitle;
    private String pages;
    private Integer publisher;
    private String publisherClass;
    private Integer year;

    public Book() {
        super(SchemaClass.BOOK);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        if (jsonObject.containsKey("isbn")) {
            this.isbn = DatabaseObjectUtils.getStringValue(jsonObject, "isbn");
        }

        if (jsonObject.containsKey("chapterTitle")) {
            this.chapterTitle = DatabaseObjectUtils.getStringValue(jsonObject, "chapterTitle");
        }

        if (jsonObject.containsKey("pages")) {
            this.pages = DatabaseObjectUtils.getStringValue(jsonObject, "pages");
        }

        if (jsonObject.containsKey("publisher")) {
            this.publisher = DatabaseObjectUtils.getIntValue(jsonObject, "publisher");
        }

        if (jsonObject.containsKey("publisherClass")) {
            this.publisherClass = DatabaseObjectUtils.getStringValue(jsonObject, "publisherClass");
        }

        if (jsonObject.containsKey("year")) {
            this.year = DatabaseObjectUtils.getIntValue(jsonObject, "year");
        }
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

    public Integer getPublisher() {
        return publisher;
    }

    public String getPublisherClass() {
        return publisherClass;
    }

    public Integer getYear() {
        return year;
    }
}
