package org.reactome.web.pwp.model.client.content;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public interface ContentClientError {

    Integer getCode();

    String getReason();

    List<String> getMessages();
}
