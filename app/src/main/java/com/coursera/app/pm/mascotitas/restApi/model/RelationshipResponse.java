package com.coursera.app.pm.mascotitas.restApi.model;

/**
 * Created by calyr on 1/2/17.
 */
public class RelationshipResponse {

    private String outgoingStatus;
    private Boolean targetUserIsPrivate;
    private String IncomingStatus;

    public RelationshipResponse() {
    }

    public String getOutgoingStatus() {
        return outgoingStatus;
    }

    public void setOutgoingStatus(String outgoingStatus) {
        this.outgoingStatus = outgoingStatus;
    }

    public Boolean getTargetUserIsPrivate() {
        return targetUserIsPrivate;
    }

    public void setTargetUserIsPrivate(Boolean targetUserIsPrivate) {
        this.targetUserIsPrivate = targetUserIsPrivate;
    }

    public String getIncomingStatus() {
        return IncomingStatus;
    }

    public void setIncomingStatus(String incomingStatus) {
        IncomingStatus = incomingStatus;
    }
}
