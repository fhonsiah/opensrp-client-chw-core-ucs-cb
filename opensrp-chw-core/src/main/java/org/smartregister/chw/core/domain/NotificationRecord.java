package org.smartregister.chw.core.domain;

public class NotificationRecord {

    private String clientBaseEntityId;
    private String clientName;
    private String careGiverName;
    private String village;
    private String actionTaken;
    private String dangerSigns;
    private String diagnosis;
    private String results;
    private String visitDate;
    private String method;

    public NotificationRecord(String clientBaseEntityId) {
        this.clientBaseEntityId = clientBaseEntityId;
    }

    public String getClientBaseEntityId() {
        return clientBaseEntityId;
    }

    public void setClientBaseEntityId(String clientBaseEntityId) {
        this.clientBaseEntityId = clientBaseEntityId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getCareGiverName() {
        return careGiverName;
    }

    public void setCareGiverName(String careGiverName) {
        this.careGiverName = careGiverName;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public String getDangerSigns() {
        return dangerSigns;
    }

    public void setDangerSigns(String dangerSigns) {
        this.dangerSigns = dangerSigns;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
