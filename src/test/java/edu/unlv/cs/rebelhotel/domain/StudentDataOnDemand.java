package edu.unlv.cs.rebelhotel.domain;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.dod.RooDataOnDemand;

import edu.unlv.cs.rebelhotel.domain.Student;
import edu.unlv.cs.rebelhotel.domain.enums.UserGroup;
import edu.unlv.cs.rebelhotel.file.RandomPasswordGenerator;
import edu.unlv.cs.rebelhotel.form.FormStudent;

@RooDataOnDemand(entity = Student.class)
public class StudentDataOnDemand {
	
	@Autowired
	UserAccountDataOnDemand uadod;
	
	public Student customGetNewTransientStudent(int index) {
     
		Student obj = new Student();
		obj.setUserId("userId_"+index);
		UserAccount userAccount = new UserAccount();
	 	userAccount.setUserId(obj.getUserId());
    	userAccount.setPassword("password_"+index);
    	userAccount.setEmail("email_"+index);
    	userAccount.setUserGroup(UserGroup.ROLE_STUDENT);
		userAccount.setEnabled(true);
		obj.setUserAccount(userAccount);
    	obj.setFirstName("firstName_"+index);
    	obj.setMiddleName("middleName_"+index);
    	obj.setLastName("lastName_"+index);
    	obj.setAdmitTerm(null);
    	obj.setGradTerm(null);
    	obj.setCodeOfConductSigned(false);
		return obj;
		
    }
}
