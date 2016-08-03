package org.reactome.web.pwp.model.client.factory;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class ModelFactoryException extends RuntimeException{

    public ModelFactoryException() {
    }

    public ModelFactoryException(String message) {
        super(message);
    }

    public ModelFactoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
