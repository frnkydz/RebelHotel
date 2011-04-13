package edu.unlv.cs.rebelhotel.domain;

import java.util.Date;

import org.springframework.roo.addon.dod.RooDataOnDemand;
import org.springframework.beans.factory.annotation.Autowired;



@RooDataOnDemand(entity = WorkEffort.class)
public class WorkEffortDataOnDemand {

     
     @Autowired
     StudentDataOnDemand sdod;
     public  WorkEffort customGetNewTransientWorkEffort(int index){
    	 WorkEffort obj = new WorkEffort();
    	 Student student = sdod.customGetNewTransientStudent(index);
    	 student.persist();
    	 student.flush();
         obj.setStudent(student);
    	 Employer e = new Employer();
    	 e.setName("employer_"+ index);
    	 e.setLocation("employerLocation_" + index);
         obj.setEmployer(e);
    	 WorkEffortDuration wed = new WorkEffortDuration();
    	 wed.setStartDate(new Date(index));
 		 wed.setEndDate(new Date(index+1));
 		 wed.setHours(index);
         obj.setWorkPosition("workPosition_" + index);
         obj.setComment("comment_" + index);
         obj.setDuration(wed);
         obj.setSupervisor(null);
         obj.setVerificationType(null);
         obj.setValidation(null);
         obj.setVerification(null);
         obj.setPayStatus(null);
         return obj;
   
     }
	
	
	
	
}
