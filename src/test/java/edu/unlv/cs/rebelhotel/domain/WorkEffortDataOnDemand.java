package edu.unlv.cs.rebelhotel.domain;

import org.springframework.roo.addon.dod.RooDataOnDemand;
import org.springframework.beans.factory.annotation.Autowired;

import edu.unlv.cs.rebelhotel.domain.enums.Validation;


@RooDataOnDemand(entity = WorkEffort.class)
public class WorkEffortDataOnDemand {

     
     @Autowired
     StudentDataOnDemand sdod;
     public  WorkEffort getNewTransientStudent(int index){
    	 edu.unlv.cs.rebelhotel.domain.WorkEffort obj = new edu.unlv.cs.rebelhotel.domain.WorkEffort();
    	 
    	 Employer employer = new Employer();
    	 Validation validation = Validation.VALIDATED;
    	 obj.setStudent(sdod.getNewTransientStudent(index));
    	 employer.setName("employerName_"+index);
    	 employer.setLocation("employerLocation_"+index);
    	 obj.setValidation(validation);
    	 
    	 return obj;
     }
	
	
	
	
}
