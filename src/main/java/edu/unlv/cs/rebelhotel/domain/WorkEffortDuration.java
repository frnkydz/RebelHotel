package edu.unlv.cs.rebelhotel.domain;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@RooJavaBean
@RooToString
@Embeddable
public class WorkEffortDuration {

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date endDate;
    
    private Integer hours;
    
    /* modified the toString() so that the print to the view
     * is nicer.  Think
     */
    public String toString() {
        DateFormat formatter ; 
        formatter = new SimpleDateFormat("MMM. dd, yyyy");
 
        StringBuilder sb = new StringBuilder();
        sb.append((getStartDate() != null ? formatter.format(getStartDate()) : "null"));
        sb.append("-");
        sb.append((getEndDate() != null ? formatter.format(getEndDate()): "null"));
        if (getHours() != null) {
        	sb.append("\n("+getHours().toString()+" hours)");
        }
        return sb.toString();
    }
    
    public boolean isStartDateAfterEndDate() {
    	return (startDate.compareTo(endDate) > 0);
    }
}
