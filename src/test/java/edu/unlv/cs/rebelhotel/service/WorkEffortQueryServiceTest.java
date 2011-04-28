package edu.unlv.cs.rebelhotel.service;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.unlv.cs.rebelhotel.domain.Employer;
import edu.unlv.cs.rebelhotel.domain.MajorDataOnDemand;
import edu.unlv.cs.rebelhotel.domain.Student;
import edu.unlv.cs.rebelhotel.domain.UserAccount;
import edu.unlv.cs.rebelhotel.domain.WorkEffort;
import edu.unlv.cs.rebelhotel.domain.WorkEffortDuration;
import edu.unlv.cs.rebelhotel.domain.WorkRequirement;
import edu.unlv.cs.rebelhotel.form.FormStudentQuery;
import edu.unlv.cs.rebelhotel.form.FormWorkEffortQuery;
import edu.unlv.cs.rebelhotel.form.QuerySortOptions;
import edu.unlv.cs.rebelhotel.domain.WorkEffortDataOnDemand;
import edu.unlv.cs.rebelhotel.domain.enums.UserGroup;
//import edu.unlv.cs.rebelhotel.util.ServiceTest;
import static org.easymock.EasyMock.*;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml")



public class WorkEffortQueryServiceTest {
	
	WorkEffortQueryService instance;
	private FormWorkEffortQuery formworkeffortquery;
	
	
	@Autowired
    private  WorkEffortDataOnDemand wedod;
   
	
	
	
	@Before
	public void setUp() throws Exception {
		instance = new WorkEffortQueryService();
		formworkeffortquery = createMock(FormWorkEffortQuery.class);
	
	}
	
	/*
	
	@Test 
	public void testPersistUserAccount(){
		UserAccount userAccount = new UserAccount();
		int index = 1;
		userAccount.setUserId("userId_"+index);
    	userAccount.setPassword("password_"+index);
    	userAccount.setEmail("email_"+index);
    	userAccount.setUserGroup(UserGroup.ROLE_STUDENT);
		userAccount.setEnabled(true);
		//userAccount.persist();
		//userAccount.flush();
		
		
		Student obj = new Student();
		obj.setUserId("userId_"+index);
		obj.setUserAccount(userAccount);
    	
		//obj.setEmail("email_"+index);
    	obj.setFirstName("firstName_"+index);
    	obj.setMiddleName("middleName_"+index);
    	obj.setLastName("lastName_"+index);
    	obj.setAdmitTerm(null);
    	obj.setGradTerm(null);
    	obj.setCodeOfConductSigned(false);
    	
		obj.persist();
		obj.flush();
		
		
		
		
		
	}
	*/
	
	
	@Test
	public void testWorkEffortQuerySort() {
		
	    Random rand = new java.security.SecureRandom();
	    WorkEffort we;
	    int size = 10;
	    int[] index = new int[size];
	  //--- Initialize the array 
	    for (int i=0; i<index.length; i++) {
	        index[i] = i;
	    }

	    //--- Shuffle by exchanging each element randomly
	    for (int i=0; i<index.length; i++) {
	        int randomPosition = rand.nextInt(index.length);
	        int temp = index[i];
	        index[i] = index[randomPosition];
	        index[randomPosition] = temp;
	    }
	    
	    
	    //create new random work efforts without repetitions
	    for(int i = 0; i < index.length; i++){
		 we = wedod.customGetNewTransientWorkEffort(index[i]);
		 we.persist();
		 we.flush();
	    }
		
	    
		expect(formworkeffortquery.getUserId()).andReturn("");
		expect(formworkeffortquery.getStudentFirstName()).andReturn("");
		expect(formworkeffortquery.getStudentMiddleName()).andReturn("");
		expect(formworkeffortquery.getStudentLastName()).andReturn("");
		expect(formworkeffortquery.getEmployerName()).andReturn("");
		expect(formworkeffortquery.getEmployerLocation()).andReturn("");
		expect(formworkeffortquery.isValidationSelected()).andReturn(false);
		expect(formworkeffortquery.isVerificationSelected()).andReturn(false);
		expect(formworkeffortquery.isVerificationTypeSelected()).andReturn(false);
		expect(formworkeffortquery.getStartDate()).andReturn(null);
		expect(formworkeffortquery.getEndDate()).andReturn(null);
		expect(formworkeffortquery.getSortOptions()).andReturn(QuerySortOptions.Student);

		replay(formworkeffortquery);
		List<WorkEffort> list = instance.queryWorkEfforts(formworkeffortquery);
		
		String name1,name2;
		
		
		//Assert.assertEquals("List should have ten items", expectedSize, actual.size());
		for (int i = 0 ; i < list.size() ; i++){
		//make sure that list is sorted according to students names
			name1 = list.get(i).getStudent().getLastName();
		    name2 = list.get(i+1).getStudent().getLastName();
		    assertTrue(name1.compareTo(name2) <= 0);
		    name1 = list.get(i).getStudent().getMiddleName();
		    name2 = list.get(i+1).getStudent().getMiddleName();
		    assertTrue(name1.compareTo(name2) <= 0);
		    name1 = list.get(i).getStudent().getFirstName();
		    name2 = list.get(i+1).getStudent().getFirstName();
		    assertTrue(name1.compareTo(name2) <= 0);
	}
	}
	private void createPersistentWorkeffort(Student student ){
		
		
		 
		
		
		
		
		
	}
	

	
	
	
	
	

}
