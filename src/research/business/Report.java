package research.business;

import java.io.Serializable;

public class Report implements Serializable {
	
	private String studyCode;
	private String reporterEmail;
	private String status;
	private String questionCode;
	private String reportedDate;
	private String reportQuestion;
	
	public Report(){
		this.studyCode = "";
		this.reportQuestion = "";
		this.reportedDate = "";
		this.status = "";
		this.questionCode = "";
		this.reporterEmail = "";
	}
	
	public Report(String studyCode, String reportQuestion, String reporterEmail, String status, String reportedDate){
		this.studyCode = studyCode;
		this.reportQuestion = reportQuestion;
		this.reportedDate = reportedDate;
		this.reporterEmail = reporterEmail;
		this.status = status;
	}
	
	public String getStudyCode(){
		return studyCode;
	}
	
	public void setStudyCode(String studyCode){
		this.studyCode = studyCode;
	}
	
	public String getReportQuestion(){
		return reportQuestion;
	}
	
	public void setReportQuestion(String reportQuestion){
		this.reportQuestion = reportQuestion;
	}
	
	public String getStatus(){
		return status;
	}
	
	public void setQuestionCode(String questionCode){
		this.questionCode = questionCode;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	
	public String getReportedDate(){
		return reportedDate;
	}
	
	public void setReportedDate(String reportedDate){
		this.reportedDate = reportedDate;
	}
	
	public String getReporterEmail(){
		return reporterEmail;
	}
	
	public void setReporterEmail(String reporterEmail){
		this.reporterEmail = reporterEmail;
	}

	public String getQuestionCode(String questionCode) {
		return questionCode;
	}
}
