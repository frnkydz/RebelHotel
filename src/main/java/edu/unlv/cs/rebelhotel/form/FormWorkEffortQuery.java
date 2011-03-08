package edu.unlv.cs.rebelhotel.form;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import edu.unlv.cs.rebelhotel.domain.enums.Validation;
import javax.persistence.Enumerated;

@RooJavaBean
@RooToString
public class FormWorkEffortQuery {

	private String userId;
	private boolean userIdSelected;
	
    private String companyName;
    private boolean companyNameSelected;
    
    private String companyLocation;
    private boolean companyLocationSelected;

    @Enumerated
    private Validation validation;
    private boolean validationSelected;
    
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date endDate;
    
    private boolean datesSelected;
    
    


   
}


