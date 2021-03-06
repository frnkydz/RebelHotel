package edu.unlv.cs.rebelhotel.form;

import java.util.Date;

import edu.unlv.cs.rebelhotel.domain.Term;

public class FormStudentQuery {
	private boolean useUserId;
	private String userId;
	
	private boolean useModified;
	private Date lastModifiedStart;
	private Date lastModifiedEnd;
	
	private boolean useCatalogTerm;
	private Term catalogTerm;
	
	private boolean useGradTerm;
	private Term gradTerm;
	
	private boolean useMilestone;
	private boolean hasMilestone;
	
	private boolean useMajor;
	private String degreeCode;
	
	private boolean showUserId;
	private boolean showEmail;
	private boolean showFirstName;
	private boolean showMiddleName;
	private boolean showLastName;
	private boolean showAdmitTerm;
	private boolean showGradTerm;
	private boolean showCodeOfConductSigned;
	private boolean showLastModified;
	private boolean showUserAccount;
	
	private boolean outputCsv;
	
	private boolean useFirstName;
	private String firstName;
	
	private boolean useMiddleName;
	private String middleName;
	
	private boolean useLastName;
	private String lastName;
			
	public boolean getUseUserId() {
		return useUserId;
	}
	
	public void setUseUserId(boolean useUserId) {
		this.useUserId = useUserId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public boolean getUseModified() {
		return useModified;
	}
	
	public void setUseModified(boolean useModified) {
		this.useModified = useModified;
	}
	
	public Date getLastModifiedStart() {
		return lastModifiedStart;
	}
	
	public void setLastModifiedStart(Date lastModifiedStart) {
		this.lastModifiedStart = lastModifiedStart;
	}
	
	public Date getLastModifiedEnd() {
		return lastModifiedEnd;
	}
	
	public void setLastModifiedEnd(Date lastModifiedEnd) {
		this.lastModifiedEnd = lastModifiedEnd;
	}
	
	public boolean getUseCatalogTerm() {
		return useCatalogTerm;
	}
	
	public void setUseCatalogTerm(boolean useCatalogTerm) {
		this.useCatalogTerm = useCatalogTerm;
	}
	
	public Term getCatalogTerm() {
		return catalogTerm;
	}
	
	public void setCatalogTerm(Term admitTerm) {
		this.catalogTerm = admitTerm;
	}
	
	public boolean getUseGradTerm() {
		return useGradTerm;
	}
	
	public void setUseGradTerm(boolean useGradTerm) {
		this.useGradTerm = useGradTerm;
	}
	
	public Term getGradTerm() {
		return gradTerm;
	}
	
	public void setGradTerm(Term gradTerm) {
		this.gradTerm = gradTerm;
	}
	
	public boolean isStartDateAfterEndDate() {
    	return (lastModifiedStart.compareTo(lastModifiedEnd) > 0);
    }
	
	public boolean getHasMilestone() {
		return hasMilestone;
	}
	
	public void setHasMilestone(boolean hasMilestone) {
		this.hasMilestone = hasMilestone;
	}
	
	public boolean getUseMilestone() {
		return useMilestone;
	}
	
	public void setUseMilestone(boolean useMilestone) {
		this.useMilestone = useMilestone;
	}
	
	public boolean getUseMajor() {
		return useMajor;
	}
	
	public void setUseMajor(boolean useMajor) {
		this.useMajor = useMajor;
	}
	
	public String getDegreeCode() {
		return degreeCode;
	}
	
	public void setDegree(String degreeCode) {
		this.degreeCode = degreeCode;
	}
	
	public boolean getShowUserId() {
		return showUserId;
	}
	
	public void setShowUserId(boolean showUserId) {
		this.showUserId = showUserId;
	}
	
	public boolean getShowEmail() {
		return showEmail;
	}
	
	public void setShowEmail(boolean showEmail) {
		this.showEmail = showEmail;
	}
	
	public boolean getShowFirstName() {
		return showFirstName;
	}
	
	public void setShowFirstName(boolean showFirstName) {
		this.showFirstName = showFirstName;
	}
	
	public boolean getShowMiddleName() {
		return showMiddleName;
	}
	
	public void setShowMiddleName(boolean showMiddleName) {
		this.showMiddleName = showMiddleName;
	}
	
	public boolean getShowLastName() {
		return showLastName;
	}
	
	public void setShowLastName(boolean showLastName) {
		this.showLastName = showLastName;
	}
	
	public boolean getShowAdmitTerm() {
		return showAdmitTerm;
	}
	
	public void setShowAdmitTerm(boolean showAdmitTerm) {
		this.showAdmitTerm = showAdmitTerm;
	}
	
	public boolean getShowGradTerm() {
		return showGradTerm;
	}
	
	public void setShowGradTerm(boolean showGradTerm) {
		this.showGradTerm = showGradTerm;
	}
	
	public boolean getShowCodeOfConductSigned() {
		return showCodeOfConductSigned;
	}
	
	public void setShowCodeOfConductSigned(boolean showCodeOfConductSigned) {
		this.showCodeOfConductSigned = showCodeOfConductSigned;
	}
	
	public boolean getShowLastModified() {
		return showLastModified;
	}
	
	public void setShowLastModified(boolean showLastModified) {
		this.showLastModified = showLastModified;
	}
	
	public boolean getShowUserAccount() {
		return showUserAccount;
	}
	
	public void setShowUserAccount(boolean showUserAccount) {
		this.showUserAccount = showUserAccount;
	}
	
	public boolean getOutputCsv() {
		return outputCsv;
	}
	
	public void setOutputCsv(boolean outputCsv) {
		this.outputCsv = outputCsv;
	}
	
	public boolean getUseFirstName() {
		return useFirstName;
	}
	
	public void setUseFirstName(boolean useFirstName) {
		this.useFirstName = useFirstName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public boolean getUseMiddleName() {
		return useMiddleName;
	}
	
	public void setUseMiddleName(boolean useMiddleName) {
		this.useMiddleName = useMiddleName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public boolean getUseLastName() {
		return useLastName;
	}
	
	public void setUseLastName(boolean useLastName) {
		this.useLastName = useLastName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}