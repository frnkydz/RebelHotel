package edu.unlv.cs.rebelhotel.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import edu.unlv.cs.rebelhotel.domain.Student;
import edu.unlv.cs.rebelhotel.domain.Employer;
import edu.unlv.cs.rebelhotel.domain.WorkEffort;
import edu.unlv.cs.rebelhotel.form.FormStudentQuery;
import edu.unlv.cs.rebelhotel.form.FormWorkEffortQuery;
import edu.unlv.cs.rebelhotel.form.QuerySortOptions;

@Service
public class WorkEffortQueryService {

	public List<WorkEffort> queryWorkEfforts(FormWorkEffortQuery fweq) {

		DetachedCriteria search = DetachedCriteria.forClass(WorkEffort.class);
		search.createAlias("student", "student");
		// look for non-empty fields here

		
		if (fweq.getUserId() != "") {
			search.add(Restrictions.eq("student.userId", fweq.getUserId()));
		}
		if (fweq.getStudentFirstName() != "") {

			search.add(Restrictions.eq("student.firstName",
					fweq.getStudentFirstName()));
		}
		if (fweq.getStudentMiddleName() != "") {
			search.add(Restrictions.eq("student.middleName",
					fweq.getStudentMiddleName()));

		}
		if (fweq.getStudentLastName() != "") {
			search.add(Restrictions.eq("student.lastName",
					fweq.getStudentLastName()));

		}
		if (fweq.getEmployerName() != "") {
			search.add(Restrictions.eq("employer.name", fweq.getEmployerName()));
		}
		if (fweq.getEmployerLocation() != "") {
			search.add(Restrictions.eq("employer.location",
					fweq.getEmployerLocation()));
		}

		if (fweq.isValidationSelected()) {
			search.add(Restrictions.eq("validation", fweq.getValidation()));
		}
		
		if (fweq.isVerificationSelected()) {
			search.add(Restrictions.eq("verification", fweq.getVerification()));
		}
		if (fweq.isVerificationTypeSelected()) {
			search.add(Restrictions.eq("verifcationType", fweq.getVerificationType()));
		}
		
		if (fweq.getStartDate() != null) {
			search.add(Restrictions.ge("duration.endDate", fweq.getStartDate()));
		}
		if (fweq.getEndDate() != null) {
			search.add(Restrictions.le("duration.startDate", fweq.getEndDate()));
		}
	
		search.setProjection(Projections.distinct(Projections.projectionList()
				.add(Projections.alias(Projections.property("id"), "id"))));
		

		DetachedCriteria rootQuery = DetachedCriteria.forClass(WorkEffort.class);
		
		rootQuery.add(Subqueries.propertyIn("id", search));
		rootQuery.createAlias("student", "student");
		
		if(fweq.getSortOptions().compareTo(QuerySortOptions.Student) == 0){
			rootQuery.addOrder(Order.asc("student.firstName"));
			rootQuery.addOrder(Order.asc("student.middleName"));
			rootQuery.addOrder(Order.asc("student.lastName"));	
		}
		
		else if(fweq.getSortOptions().compareTo(QuerySortOptions.Date) == 0){
			rootQuery.addOrder(Order.asc("duration.startDate"));
		}
		else if(fweq.getSortOptions().compareTo(QuerySortOptions.Employer)== 0){
			rootQuery.addOrder(Order.asc("employer.name"));
		}
		
	
		Session session = (Session) Student.entityManager().unwrap(Session.class);
		session.beginTransaction();
		
		List workefforts = rootQuery.getExecutableCriteria(session).list();
		session.close();
		return workefforts;

	}


}
