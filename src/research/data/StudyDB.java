/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package research.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import research.business.Answer;
import research.business.Study;


public class StudyDB {
	
	public static int STUDY_CODE = 4;
    
    ArrayList<Study> studylist=null;
    
    Study study=null;

    
    public ArrayList<Study> getStudies()
    {
    	
    	ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        studylist = new ArrayList<Study>();
    	
         try{
        	 statement = connection.prepareStatement("SELECT S.StudyID, S.StudyName, S.ImageURL, Q.QuestionID, Q.Question, S.SStatus, S.ReqParticipants, S.ActParticipants, S.Username, Q.QuestionID "+
        			 								 "FROM study AS S, question as Q "+
        			 								 "WHERE S.StudyID = Q.StudyID");
        	 
        	 ResultSet result = statement.executeQuery();
        	 
        	 if(result!=null)
        	 {
        		 while(result.next()){
        			 study = new Study();
        			 study.setStudyCode(result.getString(1));
        			 study.setStudyName(result.getString(2));
        			 study.setImageURL(result.getString(3));
        			 study.setQuestion(result.getString(5));
        			 study.setStatus(result.getString(6));
        			 study.setRequestedParticipants(result.getInt(7));
        			 study.setNumofParticipants(result.getInt(8));
        			 study.setEmail(result.getString(9));
        			 study.setQuestionCode(result.getString(10));
        				 studylist.add(study);
        			 
             	 }
        	 }
        	 
         }catch(SQLException e){
        	 e.printStackTrace();
         }catch(Exception e){
        	 e.printStackTrace();
         }finally{
         	pool.freeConnection(connection);
         } 
      return studylist;
    }
      
    public Study getStudy(String code)
    {
      if(code!=null)  
      {
    	  ConnectionPool pool = ConnectionPool.getInstance();
          Connection connection = pool.getConnection();
          PreparedStatement statement = null;
          study = new Study();
          ArrayList<String> answerList = new ArrayList<String>();
          
          try{
        	  
        	  statement = connection.prepareStatement("SELECT S.StudyID, Q.Question, S.ImageURL, Q.Option1, Q.Option2, Q.Option3, Q.Option4, Q.Option5, Q.QuestionID, S.ReqParticipants, S.StudyName, S.Description "
        			  									+"FROM study S, question Q "
        			  									+"WHERE Q.StudyID = S.StudyID AND S.StudyID = ?");
        	  statement.setString(1, code);
        	  ResultSet result = statement.executeQuery();
        	  
        	  if(result!=null)
        	  {
        		  while(result.next())
        		  {
        			  study.setStudyCode(result.getString(1));
        			  study.setQuestion(result.getString(2));
        			  study.setImageURL(result.getString(3));
        			  answerList.add(result.getString(4));
        			  answerList.add(result.getString(5));
        			  answerList.add(result.getString(6));
        			  if(result.getString(7)!=null)
        				  answerList.add(result.getString(7));
        			  if(result.getString(8)!=null)
        				  answerList.add(result.getString(8));
        			  study.setAnswers(answerList);
        			  study.setQuestionCode(result.getString(9));
        			  study.setNumofParticipants(result.getInt(10));
        			  study.setStudyName(result.getString(11));
        			  study.setDescription(result.getString(12));
        		  }
        	  }
        	  
          }catch(SQLException e){
         	 e.printStackTrace();
          }catch(Exception e){
         	 e.printStackTrace();
          }finally{
          	pool.freeConnection(connection);
          } 
          
      }
          return study;
    }
    
    public int getNumOfParticipants(String email)
    {
    	int numOfParticipants = 0;
    	
    	ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        studylist = new ArrayList<Study>();
    	
         try{
        	 statement = connection.prepareStatement("SELECT SUM(S.ActParticipants)"+
        			 								 "FROM study AS S"+
        			 								 " WHERE S.Username = ?");
        	 statement.setString(1, email);
        	 ResultSet result = statement.executeQuery();
        	 
        	 if(result!=null)
        	 {
        		 while(result.next()){
        			 numOfParticipants = result.getInt(1);
        			 }
             	 }
        	 }
        	 
         	catch(SQLException e){
        	 e.printStackTrace();
         }catch(Exception e){
        	 e.printStackTrace();
         }finally{
         	pool.freeConnection(connection);
         } 
    	
    	 return numOfParticipants;
    }
    
    public ArrayList<Study> getStudyByEmail(String email)
    {
    	ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        studylist = new ArrayList<Study>();
        
    	
         try{
        	 statement = connection.prepareStatement("SELECT S.StudyID, S.StudyName, S.ReqParticipants, S.ActParticipants, S.SStatus "+
        			 								 "FROM study AS S "+
        			 								 "WHERE S.Username = ?");
        	 statement.setString(1, email); 
        	 ResultSet result = statement.executeQuery();
        	 
        	 if(result!=null)
        	 {
        		 while(result.next()){
        			 study = new Study();
        			 study.setStudyCode(result.getString(1));
        			 study.setStudyName(result.getString(2));
        			 study.setRequestedParticipants(result.getInt(3));
        			 study.setNumofParticipants(result.getInt(4));
        			 study.setStatus(result.getString(5));
        				 studylist.add(study);
             	 }
        	 }
        	 
         }catch(SQLException e){
        	 e.printStackTrace();
         }catch(Exception e){
        	 e.printStackTrace();
         }finally{
         	pool.freeConnection(connection);
         } 
      return studylist;
    }
    
     
    public void updateStudy(String studycode, Study study)
    {
    	 for(Study studyLoop : this.studylist){
    		 
    		 if(studyLoop.getStudyCode().equals(studycode))
    		 {
    			 this.studylist.remove(studyLoop.getStudyCode());
    			 this.studylist.add(study);
    		 }
    	 }
    	
    }
    
    public ArrayList<Study> updateStudyStatus(String studycode, String status, String email)
    {
    	ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        studylist = new ArrayList<Study>();
        
        try{
        	
        	if(status.equals("stop"))
        	{
        		statement = connection.prepareStatement("UPDATE study SET"+
        											" SStatus = 'stop' WHERE StudyID = ?");
        		statement.setString(1, studycode);
        	}
        	else{
        		statement = connection.prepareStatement("UPDATE study SET"+
						" SStatus = 'start' WHERE StudyID = ?");
        		statement.setString(1, studycode);
        	}
        	statement.executeUpdate();
        	
        }catch(SQLException e){
       	 e.printStackTrace();
        }catch(Exception e){
       	 e.printStackTrace();
        }finally{
        	pool.freeConnection(connection);
        } 
        ArrayList<Study>  returnList = getStudyByEmail(email);;
		return returnList;
    }
    
    public ArrayList<Study> updateStudies (String studyCode, String studyName, String question, String imageURL, String description, String numOfParticipants, ArrayList<String> list, String email)
    {
    		ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            PreparedStatement statement = null;
            
            try{
            	
            	statement = connection.prepareStatement("UPDATE study SET StudyName = ?, Description = ?, ImageURL = ?, ReqParticipants = ? "
            											+"WHERE StudyID = ?");
            	statement.setString(1, studyName);
            	statement.setString(2, description);
            	statement.setString(3, imageURL);
            	statement.setString(4, numOfParticipants);
            	statement.setString(5, studyCode);
            	
            	statement.executeUpdate();
            	statement.close();
            	
            	String option1 = null, option2 = null, option3 = null, option4 = null, option5 =null;
        		
        		if(list!=null)
        		{
        			option1 = list.get(0);
        			option2 = list.get(1);
        			option3 = list.get(2);
        			if(list.size() >= 4)
        				option4 = list.get(3);
        			if(list.size() >= 5)
        				option5 = list.get(4);
        		}
            	
            	statement = connection.prepareStatement("UPDATE question SET Question = ?, Option1 = ?, Option2 = ?, Option3 = ?, Option4 = ?, Option5 = ? "
            											+"WHERE StudyID = ?");
            	statement.setString(1, question);
            	statement.setString(2, option1);
            	statement.setString(3, option2);
            	statement.setString(4, option3);
            	statement.setString(5, option4);
            	statement.setString(6, option5);
            	statement.setString(7, studyCode);
            	
            	statement.executeUpdate();
            	statement.close();
            	
            }catch(SQLException e){
           	 e.printStackTrace();
            }catch(Exception e){
           	 e.printStackTrace();
            }finally{
            	pool.freeConnection(connection);
            } 
    		
            ArrayList<Study>  returnList = getStudyByEmail(email);;
    		return returnList;
    }
    
    public ArrayList<Study> addStudies (String email, String studyName, String question, String imageURL, String description, String numOfParticipants, ArrayList<String> list)
    {
    	
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = new Date();
    	String dateString = df.format(date);
    	System.out.println(dateString);
    	ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
    	
    	Study studyobj = new Study();
    	studyobj.setDescription(description);
    	studyobj.setDateCreated(dateString);
    	studyobj.setStatus("start");
    	studyobj.setEmail(email);
    	studyobj.setAnswerType("text");
		studyobj.setImageURL(imageURL);
		studyobj.setNumofParticipants(Integer.parseInt(numOfParticipants));
		studyobj.setQuestion(question);
		studyobj.setStudyName(studyName);
		studyobj.setAnswers(list);
		
		String option1 = null, option2 = null, option3 = null, option4 = null, option5 =null;
		
		if(list!=null)
		{
			option1 = list.get(0);
			option2 = list.get(1);
			option3 = list.get(2);
			if(list.size() >= 4)
				option4 = list.get(3);
			if(list.size() >= 5)
				option5 = list.get(4);
		}
		
		   	
         try{
        	 statement = connection.prepareStatement("INSERT INTO study(StudyName, Description, Username, DateCreated, ImageURL, ReqParticipants, SStatus, ActParticipants )"+
        			 								 "VALUES(?,?,?,?,?,?,?,0)");
        	 statement.setString(1, studyName);
        	 statement.setString(2, description);
        	 statement.setString(3, email);
        	 statement.setString(4, dateString);
        	 statement.setString(5, imageURL);
        	 statement.setInt(6,Integer.parseInt(numOfParticipants));
        	 statement.setString(7, "start");
        	 
        	 statement.executeUpdate();
        	 statement.close();
        	 
        	 statement = connection.prepareStatement("INSERT INTO question(StudyID,Question,AnswerType,Option1,Option2,Option3,Option4,Option5) "
        	 		+ "VALUES ((SELECT MAX(StudyID) FROM study),?,'text',?,?,?,?,? )");
        	 statement.setString(1, question);
        	 statement.setString(2,option1);
        	 statement.setString(3,option2);
        	 statement.setString(4,option3);
        	 statement.setString(5, option4);
        	 statement.setString(6, option5);
        	 
        	 statement.executeUpdate();
        	 statement.close(); 
        	 
        	 
        	 statement = connection.prepareStatement("UPDATE user SET Studies = Studies+1 WHERE Username = ?");
        	 statement.setString(1, email);
        	 
        	 statement.executeUpdate();
        	 statement.close();
        	 
         }catch(SQLException e){
        	 e.printStackTrace();
         }catch(Exception e){
        	 e.printStackTrace();
         }finally{
         	pool.freeConnection(connection);
         } 
         ArrayList<Study>  returnList = getStudyByEmail(email);;
		return returnList;
    }

	public void addAnswer(String email, String studyCode, String answer, String questionCode) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = new Date();
    	String dateString = df.format(date);
    	
       	Answer answerObj = new Answer();
    	answerObj.setChoice(answer);
    	answerObj.setEmail(email);
    	answerObj.setSubmissionDate(dateString);
    	
    	ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
    	
        try{
        	
        	statement = connection.prepareStatement("INSERT INTO answer (StudyID, QuestionID, UserName, Choice, DateSubmitted)"
        				+"VALUES(?,?,?,?,?)");
        	statement.setString(1, studyCode);
        	statement.setString(2,questionCode);
        	statement.setString(3, email);
        	statement.setString(4, answer);
        	statement.setString(5, dateString);
        	
        	statement.executeUpdate();
        	statement.close();
        	
        	statement = connection.prepareStatement("UPDATE user "
        											+"SET Participation = Participation + 1, Coins = Coins + 1 "
        											+"WHERE Username = ?");
        	statement.setString(1, email);
        	statement.executeUpdate();
        	statement.close();
        	
        	statement = connection.prepareStatement("UPDATE study "
        											+"SET ActParticipants = ActParticipants + 1 "
        											+"WHERE StudyID = ?");
        	statement.setString(1, studyCode);
        	statement.executeUpdate();
        	
        	statement.close();
        	
        }catch(SQLException e){
       	 e.printStackTrace();
        }catch(Exception e){
       	 e.printStackTrace();
        }finally{
        	pool.freeConnection(connection);
        } 
    	
    	
    	
    	/*for(Study study : this.studylist)
    	{
    		if(study.getStudyCode().equals(studyCode))
    		{
    			study.setAnswerList(answerObj);
    		}
    	}*/
		    	    	
	}
    
}
