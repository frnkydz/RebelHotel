// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package edu.unlv.cs.rebelhotel.form;

import java.lang.String;

privileged aspect FormWorkEffortQuery_Roo_ToString {
    
    public String FormWorkEffortQuery.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserId: ").append(getUserId()).append(", ");
        sb.append("CompanyName: ").append(getCompanyName()).append(", ");
        sb.append("CompanyLocation: ").append(getCompanyLocation()).append(", ");
        sb.append("Validation: ").append(getValidation()).append(", ");
        sb.append("StartDate: ").append(getStartDate()).append(", ");
        sb.append("EndDate: ").append(getEndDate());
        return sb.toString();
    }
    
}
