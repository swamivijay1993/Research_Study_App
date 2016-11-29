package research.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import research.business.Report;
import research.business.Study;


public class ReportDB {

	ArrayList<Report> reportList = null;
	Report report = null;
	
	
	public void addReport(String studyCode, String questionCode, String reportedDate, String reporterEmail)
	{
		report = new Report();
		report.setReportedDate(reportedDate);
		report.setQuestionCode(questionCode);
		report.setStudyCode(studyCode);
		report.setStatus("Under Review");
		
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        
        try{
        	statement = connection.prepareStatement("INSERT INTO reported VALUES"+
        											"(?,?,?,(select ReqParticipants from study where StudyID = ?),?,?)");
        	statement.setInt(1, Integer.parseInt(questionCode));
        	statement.setInt(2, Integer.parseInt(studyCode));
        	statement.setString(3, reportedDate);
        	statement.setString(4, studyCode);
        	statement.setString(5, "Under Review");
        	statement.setString(6, reporterEmail);
        	
        	statement.executeUpdate();
        	statement.close();
        	
        }catch(SQLException e){
          	 e.printStackTrace();
           }catch(Exception e){
          	 e.printStackTrace();
           }finally{
           	pool.freeConnection(connection);
           } 
		
	}
	
	public ArrayList<Report> getReports(){
		
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        reportList = new ArrayList<Report>();
		try{
			
			statement = connection.prepareStatement("SELECT R.StudyID, R.QuestionID, R.Date, Q.Question, R.Status, R.ReporterEmail FROM "
													+"reported AS R, question AS Q "
													+"WHERE R.QuestionID = Q.QuestionID ");
			ResultSet result = statement.executeQuery();
			
			if(result!=null)
			{
				while(result.next())
				{
					report = new Report();
					report.setQuestionCode(result.getString(2));
					report.setStudyCode(result.getString(1));
					report.setReportedDate(result.getString(3));
					report.setReportQuestion(result.getString(4));
					report.setStatus(result.getString(5));
					report.setReporterEmail(result.getString(6));
					reportList.add(report);
				}
			}
			
		}catch(SQLException e){
         	 e.printStackTrace();
          }catch(Exception e){
         	 e.printStackTrace();
          }finally{
          	pool.freeConnection(connection);
          } 
		return reportList;
	}
	
	public ArrayList<Report> setStatus(String studyCode, String status){
		
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        reportList = new ArrayList<Report>();
        try{
			
			statement = connection.prepareStatement("UPDATE reported SET Status = ?"
													+"WHERE StudyID = ? ");
			statement.setString(1, status);
			statement.setString(2, studyCode);
			statement.executeUpdate();
			statement.close();
			
		}catch(SQLException e){
         	 e.printStackTrace();
          }catch(Exception e){
         	 e.printStackTrace();
          }finally{
          	pool.freeConnection(connection);
          } 
	
	ArrayList<Report> reports = getReports();
	return reports;
	}
}
