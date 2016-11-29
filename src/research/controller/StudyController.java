package research.controller;

import research.business.Report;
import research.business.Study;
import research.data.StudyDB;
import research.data.UserDB;
import research.data.GMailSender;
import research.data.ReportDB;
import research.business.User;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JFileChooser;

@WebServlet(name = "StudyController", urlPatterns={"/study"})
public class StudyController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = null;

        String url = null;
        String msg = null;
        StudyDB studyDB;
        ReportDB reportDB;
        Study study;
        
        // get current action
        String action = request.getParameter("action");
        
        
        
        if (action == null) {
            if(request.getSession(false).getAttribute("theUser")!=null)
            {
            	url="/main.jsp";
            }
            else
            {
            	if(request.getSession(false).getAttribute("theAdmin")!=null)
            	{
            		url="/admin.jsp";
            	}
            	else
            	{
            		url="/home.jsp";
            	}
            }
        }
        
        
        // participate in a study
        if (action.equals("participate")) {   
        	
        	String studyCode = request.getParameter("studycode");
        	
        	
        	if(request.getSession(false).getAttribute("theUser")!=null)
        	{
        		UserDB userDB = new UserDB();
        		String email = ((User)request.getSession(false).getAttribute("theUser")).getEmail();
    			
        		if(studyCode == null)
        		{
        			ArrayList<Study> studies = new ArrayList<Study>();
        			studyDB = new StudyDB();
        			studies = studyDB.getStudies();
        			
        			request.setAttribute("studies", studies);
        			request.setAttribute("user", ((User)request.getSession(false).getAttribute("theUser")).getEmail());
        			
        			request.getSession(false).setAttribute("coins", userDB.getParticipation(email));
           			request.getSession(false).setAttribute("numParticipation", userDB.getParticipation(email));
           			
        			
        			url="/participate.jsp";
        		}
        		
        		else{
        		
        			studyDB = new StudyDB();
        			study = new Study();
        			study = studyDB.getStudy(studyCode);
        			request.setAttribute("study", study);
        			
        			request.getSession(false).setAttribute("coins", userDB.getParticipation(email));
           			request.getSession(false).setAttribute("numParticipation", userDB.getParticipation(email));
           			
        			
        			url = "/question.jsp";
        		}
        		
        	}
        	else
        	{
        		url="/login.jsp";
        	}
        	
             
        } 
        else if (action.equals("edit")) {
        	
        	if(request.getSession(false).getAttribute("theUser")!=null)
        	{
        		String studyCode = request.getParameter("studycode");
        		study = new Study();
        		studyDB = new StudyDB();
        		study = studyDB.getStudy(studyCode);
        		request.setAttribute("study", study);
        		url = "/editstudy.jsp";
        		
        	}
        	else
        	{
        		url="/login.jsp";
        	}
        	

        }
        else if (action.equals("report")) {
        	
        	String studyCode = request.getParameter("studycode");
        	String questionCode = request.getParameter("questioncode");
        	
        	if(request.getSession(false).getAttribute("theUser")!=null)
        	{
        		if(studyCode!=null)
        		{
        			String email = ((User)request.getSession(false).getAttribute("theUser")).getEmail();
        			
        			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        	    	Date date = new Date();
        	    	String dateString = df.format(date);
        	    	
        	    	reportDB = new ReportDB();
        	    	ArrayList<Report> reportList = new ArrayList<Report>();
        	    	
        	    	reportDB.addReport(studyCode, questionCode, dateString, email);
        	    	url = "/confirmrep.jsp";
        		}
        		
        		
        		
        		else
        		{
        			String email = ((User)request.getSession(false).getAttribute("theUser")).getEmail();
        			ArrayList<Report> reportList = new ArrayList<Report>();
        			reportDB = new ReportDB();
        			reportList = reportDB.getReports();
        			
        			request.setAttribute("user", email);
        			request.setAttribute("reports", reportList);
        			url = "/reporth.jsp";
        		}
        	}
        	
        	else if (request.getSession(false).getAttribute("theAdmin")!=null)
    		{
    			reportDB = new ReportDB();
    	    	ArrayList<Report> reportList = new ArrayList<Report>();
    	    	
    	    	reportList = reportDB.getReports();
    	    	request.setAttribute("reports", reportList);
    	    	url ="/reportques.jsp";
    			
    		}
        	
        	else
        	{
        		url="/login.jsp";
        	}

        }
        
        else if(action.equals("status")){
        	if(request.getSession(false).getAttribute("theAdmin")!=null){
        		reportDB = new ReportDB();
        		ArrayList<Report> reportList = new ArrayList<Report>();
        		reportList = reportDB.getReports();
        		
        		request.setAttribute("reports", reportList);
        		url = "/statushistory.jsp";
        	}
        	else
        	{
        		url = "/login.jsp";
        	}
        }
        
        else if (action.equals("approve")) {

        	if(request.getSession(false).getAttribute("theAdmin")!=null)
        	{
        		String studyCode = request.getParameter("studycode");
        		reportDB = new ReportDB();
        		ArrayList<Report> reportList = new ArrayList<Report>();
        		reportList = reportDB.setStatus(studyCode, "Approved");
        		
        		request.setAttribute("reports", reportList);
        		url = "/reportques.jsp";
        		
        	}
        	else
        	{
        		url = "/login.jsp";
        	}
        	
        }
        else if (action.equals("disapprove")) {
        	

        	if(request.getSession(false).getAttribute("theAdmin")!=null)
        	{
        		String studyCode = request.getParameter("studycode");
        		reportDB = new ReportDB();
        		ArrayList<Report> reportList = new ArrayList<Report>();
        		reportList = reportDB.setStatus(studyCode, "Disapproved");
        		
        		request.setAttribute("reports", reportList);
        		url = "/reportques.jsp";
        		
        	}
        	else
        	{
        		url = "/login.jsp";
        	}
        	

        }
        else if (action.equals("update")) {
        	
        	if(request.getSession(false).getAttribute("theUser")!=null)
        	{
        		String question = request.getParameter("question_text");
        		String studyName = request.getParameter("study_name");
        		String studyCode = request.getParameter("studycode");
        		String imageURL = request.getParameter("image-url");
        		String numOfParticipants = request.getParameter("participants");
        		String description = request.getParameter("description");
        		String[] answer = request.getParameterValues("DynamicTextBox");
        		String email = ((User)request.getSession(false).getAttribute("theUser")).getEmail();
        		
        		
        		ArrayList<String> answersLists = new ArrayList<String>();
        		Collections.addAll(answersLists, answer);
      		
        		for(int i =0; i<answersLists.size(); i++)
        		{
        			System.out.println(answersLists.get(i));
        		}
        		System.out.println(answer.length);
        		
       		    studyDB = new StudyDB();
        		ArrayList<Study> list = new ArrayList<Study>();
        		list = studyDB.updateStudies(studyCode, studyName, question, imageURL, description, numOfParticipants, answersLists,email);
        		
        		request.setAttribute("studies", list);
        		url = "/studies.jsp";
        		
        	}
        	else
        	{
        		url = "/login.jsp";
        	}

        }
        else if (action.equals("add")) {
        	
        	//System.out.println("inside add");
        	
        	if(request.getSession(false).getAttribute("theUser")!=null)
        	{
        		String email = ((User)request.getSession(false).getAttribute("theUser")).getEmail();
        		String question = request.getParameter("question_text");
        		String studyName = request.getParameter("study_name");
        		String imageURL = request.getParameter("image-url");
        		String numOfParticipants = request.getParameter("participant_text");
        		String description = request.getParameter("description");
        		String[] answers = request.getParameterValues("DynamicTextBox");
        		
        		System.out.println(answers);
        		
        		ArrayList<String> answersList = new ArrayList<String>();
        		Collections.addAll(answersList, answers);
        		
        		
        		/*System.out.println(email+""+question+""+studyName+""+imageURL+""+numOfParticipants+""+description+""+answers[2]);
        		for(String s: answersList){
        			System.out.println(s);
        		}*/
        		
       		    studyDB = new StudyDB();
        		ArrayList<Study> list = new ArrayList<Study>();
        		list = studyDB.addStudies(email, studyName, question, imageURL, description, numOfParticipants, answersList);
        		request.setAttribute("studies", list);
        		url = "/studies.jsp";
        		
           	}
        	else
        	{
        	url = "/login.jsp";	
        	}

        }
        else if (action.equals("start")) {
        	
        	String studyCode = request.getParameter("studycode");
        	if(request.getSession(false).getAttribute("theUser")!=null)
        	{
        		ArrayList<Study> studies = new ArrayList<Study>();
        		String email = ((User)request.getSession(false).getAttribute("theUser")).getEmail();
            	studyDB = new StudyDB();
        		studies = studyDB.updateStudyStatus(studyCode, "start",email);
        		request.setAttribute("studies", studies);
        		url = "/studies.jsp";
        	}
        	else
        	{
        		url = "/login.jsp";
        	}
        	
        }
        else if (action.equals("stop")) {
        	
        	String studyCode = request.getParameter("studycode");
        	if(request.getSession(false).getAttribute("theUser")!=null)
        	{
        		ArrayList<Study> studies = new ArrayList<Study>();
        		String email = ((User)request.getSession(false).getAttribute("theUser")).getEmail();
        		studyDB = new StudyDB();
        		studies = studyDB.updateStudyStatus(studyCode, "stop",email);
        		request.setAttribute("studies", studies);
        		url = "/studies.jsp";
        		
        	}
        	else
        	{
        		url = "/login.jsp";
        	}
        	
        }
        else if (action.equals("answer")) {
        	if(request.getSession(false).getAttribute("theUser")!=null)
        	{
        		String studyCode = request.getParameter("studycode");
        		String questionCode = request.getParameter("questionCode");
        		String answer = request.getParameter("number");
        		String email = ((User)request.getSession(false).getAttribute("theUser")).getEmail();
        		
        		User user = (User) request.getSession(false).getAttribute("theUser");
        		
        		studyDB = new StudyDB();
        		UserDB userDB = new UserDB();
        		studyDB.addAnswer(email,studyCode,answer,questionCode);
        		ArrayList<Study> studies = new ArrayList<Study>();
       			studies = studyDB.getStudies();
    			
       			request.getSession(false).setAttribute("coins", userDB.getParticipation(email));
       			request.getSession(false).setAttribute("numParticipation", userDB.getParticipation(email));
       			
    			request.setAttribute("studies", studies);
    			url="/participate.jsp";
        		
        	}
        	else
        	{
        		url = "/login.jsp";
        	}
        	
        }
        
        else if(action.equals("recommend"))
        {
        	String name = request.getParameter("study_name");
        	String email = request.getParameter("email");
        	String friendEmail = request.getParameter("friend_email");
        	String message = request.getParameter("message");
        	GMailSender.emailRecommendTrigger(name, email, friendEmail, message);
        	url = "/confirmr.jsp";
        }
        
        else if(action.equals("contact"))
        {
        	String name = request.getParameter("study_name");
        	String email = request.getParameter("email");
        	String message = request.getParameter("message");
        	GMailSender.emailContactTrigger(name, email, message);
        	url="/confirmc.jsp";
        }
       
        
        else if (action.equals("studies")) {
        	
        	if(request.getSession(false).getAttribute("theUser")!=null)
        	{
        		String email = ((User)request.getSession(false).getAttribute("theUser")).getEmail();
        		
        		
        		ArrayList<Study> studies = new ArrayList<Study>();
    			studyDB = new StudyDB();
    			studies = studyDB.getStudyByEmail(email);
    			//System.out.println(studies.get(0).getStatus());
    			request.setAttribute("studies", studies);
    			url="/studies.jsp";
        	}
        	else
        	{
        		url = "/login.jsp";
        	}

        }
        getServletContext()
        .getRequestDispatcher(url)
        .forward(request, response);
    }    
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }    
}