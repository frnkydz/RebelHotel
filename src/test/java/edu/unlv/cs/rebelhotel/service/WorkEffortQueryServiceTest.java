package edu.unlv.cs.rebelhotel.service;
import static org.junit.Assert.*;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.unlv.cs.rebelhotel.domain.Student;
import edu.unlv.cs.rebelhotel.domain.UserAccount;
import edu.unlv.cs.rebelhotel.domain.WorkEffort;
import edu.unlv.cs.rebelhotel.form.FormStudentQuery;
import edu.unlv.cs.rebelhotel.form.FormWorkEffortQuery;
import edu.unlv.cs.rebelhotel.domain.WorkEffortDataOnDemand;
//import edu.unlv.cs.rebelhotel.util.ServiceTest;
import static org.easymock.EasyMock.*;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml")



public class WorkEffortQueryServiceTest {
	
	WorkEffortQueryService instance;
	private FormWorkEffortQuery formworkeffortquery;
	WorkEffortDataOnDemand wedod;
	@Before
	public void setUp() throws Exception {
		instance = new WorkEffortQueryService();
		formworkeffortquery = createMock(FormWorkEffortQuery.class);
	}
	
	
	@Test
	public void testQueryStudentsWithExistentUserId() {
		
		expect(formworkeffortquery.isUserIdSelected()).andReturn(false);
		expect(formworkeffortquery.isCompanyLocationSelected()).andReturn(false);
		expect(formworkeffortquery.isCompanyNameSelected()).andReturn(true);
		expect(formworkeffortquery.isValidationSelected()).andReturn(false);
		expect(formworkeffortquery.getCompanyName()).andReturn("New Orleans");


		
		replay(formworkeffortquery);
		List<WorkEffort> actual = instance.queryWorkEfforts(formworkeffortquery);
		int expectedSize = 1;
		assertEquals("This should have one value", expectedSize,actual.size());
	}
	
	private void createValidForm(String employerName, String employerLocation, String userId){
		
		
		

		
		
		
		
		
	}
	

	
	
	
	
	

}
