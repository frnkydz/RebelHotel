package edu.unlv.cs.rebelhotel.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.stereotype.Service;

import edu.unlv.cs.rebelhotel.domain.Student;
import edu.unlv.cs.rebelhotel.domain.Employer;
import edu.unlv.cs.rebelhotel.domain.WorkEffort;
import edu.unlv.cs.rebelhotel.form.FormWorkEffortQuery;
@Service
public class WorkEffortQueryService {
	
	public List<WorkEffort> queryWorkEfforts(FormWorkEffortQuery fweq){
		DetachedCriteria search = DetachedCriteria.forClass(WorkEffort.class);
		
		if(fweq.isUserIdSelected()){
			search.createCriteria("student")
			.add(Restrictions.eq("userId",fweq.getUserId()));
		}
		if(fweq.isCompanyNameSelected()){
			search.add(Restrictions.eq("company.name", fweq.getCompanyName()));
		}
		if(fweq.isCompanyLocationSelected()){
			search.add(Restrictions.eq("company.location", fweq.getCompanyLocation()));
		}
		
		if(fweq.isValidationSelected()){
			search.createCriteria("validation")
			.add(Example.create(fweq.getValidation()));
		}
		
		
	
	
	
	List workefforts;
	
	DetachedCriteria rootQuery = DetachedCriteria.forClass(WorkEffort.class);
	search.setProjection(Projections.distinct(Projections.projectionList().add(Projections.alias(Projections.property("id"), "id"))));
	rootQuery.add(Subqueries.propertyIn("id", search));
	Session session = (Session) Student.entityManager().unwrap(Session.class);
	session.beginTransaction();
	workefforts = rootQuery.getExecutableCriteria(session).list();
	session.close();
	
	return workefforts;
	
	
	
	
	
	}
	
	
	
}
