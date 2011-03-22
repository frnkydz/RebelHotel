package edu.unlv.cs.rebelhotel.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import edu.unlv.cs.rebelhotel.domain.enums.Validation;
import javax.persistence.Enumerated;

@RooJavaBean
@RooToString
public class FormWorkEffortQuery {

	private String userId;
	private boolean userIdSelected;

	private String employerName;
	private boolean employerNameSelected;

	private String employerLocation;
	private boolean employerLocationSelected;

	@Enumerated
	private Validation validation;
	private boolean validationSelected;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date endDate;

	private boolean datesSelected;

	public void setStartDate(String startDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = new java.util.Date();
		try {
			date = sdf.parse(startDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		this.startDate = date;
	}

	public void setEndDate(String endDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = new java.util.Date();
		try {
			date = sdf.parse(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		this.endDate = date;
	}

}
