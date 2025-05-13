package org.example;

import java.util.Date;
import java.util.Objects;

public class Membership {
    private String type;
    private Date startDate;
    private Date endDate;

    public Membership() {} // Для Jackson

    public Membership(String type, Date startDate, Date endDate) {
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getType() { return type; }
    public Date getStartDate() { return startDate; }
    public Date getEndDate() { return endDate; }

    public void setType(String type) { this.type = type; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Membership)) return false;
        Membership that = (Membership) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, startDate, endDate);
    }

    @Override
    public String toString() {
        return type + " (" + startDate + " - " + endDate + ")";
    }
}