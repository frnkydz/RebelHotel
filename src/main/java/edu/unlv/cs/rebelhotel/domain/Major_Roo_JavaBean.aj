// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package edu.unlv.cs.rebelhotel.domain;

import edu.unlv.cs.rebelhotel.domain.Term;
import java.lang.String;

privileged aspect Major_Roo_JavaBean {
    
    public boolean Major.isReachedMilestone() {
        return this.reachedMilestone;
    }
    
    public void Major.setReachedMilestone(boolean reachedMilestone) {
        this.reachedMilestone = reachedMilestone;
    }
    
    public String Major.getDegreeCode() {
        return this.degreeCode;
    }
    
    public void Major.setDegreeCode(String degreeCode) {
        this.degreeCode = degreeCode;
    }
    
    public Term Major.getCatalogTerm() {
        return this.catalogTerm;
    }
    
    public void Major.setCatalogTerm(Term catalogTerm) {
        this.catalogTerm = catalogTerm;
    }
    
    public boolean Major.isCompleted_work_requirements() {
        return this.completed_work_requirements;
    }
    
    public void Major.setCompleted_work_requirements(boolean completed_work_requirements) {
        this.completed_work_requirements = completed_work_requirements;
    }
    
}
