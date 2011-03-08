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
import edu.unlv.cs.rebelhotel.form.FormStudentQuery;
import edu.unlv.cs.rebelhotel.util.ServiceTest;
import static org.easymock.EasyMock.*;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml")

public class StudentQueryServiceTest {

	StudentQueryService instance;
	private FormStudentQuery formStudentQuery;

	@Before
	public void setUp() throws Exception {
		instance = new StudentQueryService();
		formStudentQuery = createMock(FormStudentQuery.class);
	}

	

	@Test
	public void testQueryStudentsWithNonExistentUserId() {
		String nonExistentUserId = "I do not exist"; 
		expect(formStudentQuery.getUseUserId()).andReturn(true);
		expect(formStudentQuery.getUserId()).andReturn(nonExistentUserId );
		
		replay(formStudentQuery);
		
		List<Student> actual = instance.queryStudents(formStudentQuery);
		int expectedSize = 0;
		assertEquals("This should be an empty list", expectedSize,actual.size());
	}

	@Test
	public void testQueryStudentsWithExistingUserId() {
		
		String existingUserId = "I exist now asdf";
		Student existingStudent = createValidStudent(existingUserId);
		expect(formStudentQuery.getUseUserId()).andReturn(true);
		expect(formStudentQuery.getUserId()).andReturn(existingStudent.getUserId());
		
		replay(formStudentQuery);
		
		List<Student> actual = instance.queryStudents(formStudentQuery);
		int expectedSize = 1;
		assertEquals("This should only be one!",expectedSize,actual.size());
	}
	
	private Student createValidStudent(String userId){
		UserAccount account = new UserAccount();
		account.setUserId(userId);
		account.setPassword("Never use a password like this");
		account.persist();
		Student student = new Student();
		student.setUserAccount(account);
		student.setEmail("email");
		student.setFirstName("firstName");
		student.setUserId(userId );
		student.persist();
		student.flush();
		return student;
	}
}
