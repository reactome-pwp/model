package org.reactome.web.pwp.model.classes;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public enum EventStatus {
    REGULAR(""),
    NEW("NEW"),
    UPDATED("UPDATED");

    private String status;

    EventStatus(String status) {
        this.status = status;
    }

    public static EventStatus getEventStatus(String status) {
        for (EventStatus eventStatus : EventStatus.values()) {
            if (eventStatus.status.equals(status)) return eventStatus;
        }
        return REGULAR;
    }
}