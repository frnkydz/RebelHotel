package edu.unlv.cs.rebelhotel.domain;

import javax.persistence.Embeddable;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;



@Embeddable
public class Employer {

    private String location;

    private String name;
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append(", ");
        sb.append(getLocation());
        return sb.toString();
    }
    
    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    
}
