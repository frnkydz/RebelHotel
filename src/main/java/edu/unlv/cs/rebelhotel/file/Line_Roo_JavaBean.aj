// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package edu.unlv.cs.rebelhotel.file;

import edu.unlv.cs.rebelhotel.domain.Major;
import edu.unlv.cs.rebelhotel.domain.Term;
import java.lang.String;
import java.util.Set;

privileged aspect Line_Roo_JavaBean {
    
    public int Line.getLineNumber() {
        return this.lineNumber;
    }
    
    public void Line.setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
    
    public String Line.getStudentId() {
        return this.studentId;
    }
    
    public void Line.setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public String Line.getLastName() {
        return this.lastName;
    }
    
    public void Line.setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String Line.getFirstName() {
        return this.firstName;
    }
    
    public void Line.setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String Line.getMiddleName() {
        return this.middleName;
    }
    
    public void Line.setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    
    public String Line.getEmail() {
        return this.email;
    }
    
    public void Line.setEmail(String email) {
        this.email = email;
    }
    
    public Set<Major> Line.getMajors() {
        return this.majors;
    }
    
    public void Line.setMajors(Set<Major> majors) {
        this.majors = majors;
    }
    
    public Term Line.getAdmitTerm() {
        return this.admitTerm;
    }
    
    public void Line.setAdmitTerm(Term admitTerm) {
        this.admitTerm = admitTerm;
    }
    
    public Term Line.getGradTerm() {
        return this.gradTerm;
    }
    
    public void Line.setGradTerm(Term gradTerm) {
        this.gradTerm = gradTerm;
    }
    
}
