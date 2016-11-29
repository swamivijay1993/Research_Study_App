  package research.controller;

import research.data.UserDB;
import research.data.PasswordUtility;
import research.data.StudyDB;
import research.business.User;
import java.io.*;
import java.net.InetAddress;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "UserController", urlPatterns={"/user"})
public class UserController extends HttpServlet {
    

	@Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
		
        
        HttpSession session = null;

        String url = null;
        String msg ="";
        User user;
        UserDB userDB;
        StudyDB studyDB;
        
        // get current action
        String action = request.getParameter("action");
        
        int portNumber = request.getServerPort();
        //System.out.println(portNumber);
        InetAddress hostAddress = InetAddress.getLocalHost();
        //System.out.println(hostAddress);
        Cookie mycookie = new Cookie("hostName", hostAddress +": "+ portNumber);
        mycookie.setMaxAge(60*60*24);
        response.addCookie(mycookie);
        
        if (action == null) {
            url = "/home.jsp";
        }
        // perform action and set URL to appropriate page
        if (action.equals("login")) {   
        	
        	String email = request.getParameter("email");
        	String password = request.getParameter("password");
        	String type = request.getParameter("type");
        	
        	String hashedPassword = PasswordUtility.md5(password);
        	
        	user = new User();
        	userDB = new UserDB();
        	user = userDB.getUser(email,hashedPassword);
        	
        //	System.out.println(hashedPassword);
        	
        	if(user!=null)
        	{
        		//System.out.println("inside loop");
        		if((user.getType().equalsIgnoreCase("participant")))
        		{
        			//System.out.println("inside loop2");
        			studyDB = new StudyDB();
        			int numofParticipants = studyDB.getNumOfParticipants(email);
        			        			
        			session = request.getSession(false);
        			session.setAttribute("theUser", user);
        			session.setAttribute("coins", user.getNumCoins());
        			session.setAttribute("numParticipation", user.getNumParticipation());
        			session.setAttribute("numParticipants", numofParticipants);
        			url="/main.jsp";
        		}
        		else if(user.getType().equalsIgnoreCase("admin"))
        		{
        			session = request.getSession(false);
        			session.setAttribute("theAdmin", user);
        			url="/admin.jsp";
        		}
        		
        	}
        	else
        	{
        		msg = "invalid userid or password";
        		request.setAttribute("message", msg);
        		url = "/login.jsp";
        	}
        } 
        
        else if (action.equals("create")) {
        	
        	
        	String name = request.getParameter("name");
        	String email = request.getParameter("email");
        	String password = request.getParameter("password");
        	String confirmPassword = request.getParameter("confirm_password");
        	
        	if(confirmPassword.equals(password))
        	{
        		
        		userDB = new UserDB();
        		user = new User();
        		user = userDB.addUser(email, password, name);
        		studyDB = new StudyDB();
        		
        		int numofParticipants = studyDB.getNumOfParticipants(email);
        		
        		session = request.getSession(false);
        		session.setAttribute("theUser", user);
        		session.setAttribute("coins", user.getNumCoins());
    			session.setAttribute("numParticipation", user.getNumParticipation());
    			session.setAttribute("numParticipants", numofParticipants);
        		url="/main.jsp";
        	}
        	else{
        		
        		msg = "invalid data entered";
        		request.setAttribute("message", msg);
        		url = "/signup.jsp";
        		
        	}
        			
        	
        }
        else if (action.equals("how")) {
        	
        	if(request.getSession(false).getAttribute("theUser")!= null)
        	{
        		url="/main.jsp";
        	}
        	else
        	{
        		url="/how.jsp";
        	}
        	
        }
        else if (action.equals("about")) {

        	if(request.getSession(false).getAttribute("theUser")!= null)
        	{
        		String email = ((User)request.getSession(false).getAttribute("theUser")).getEmail();
        		userDB = new UserDB();
        		request.getSession(false).setAttribute("numParticipation", userDB.getParticipation(email));
        		request.getSession(false).setAttribute("coins", userDB.getParticipation(email));
        		request.getSession(false).setAttribute("numParticipants", userDB.getParticipants(email));
        		url="/aboutl.jsp";
        	}
        	else
        	{
        		url="/about.jsp";
        	}
        	
        }
        else if (action.equals("home")) {
        	if(request.getSession(false).getAttribute("theUser")!= null)
        	{
        		url="/main.jsp";
        	}
        	else if(request.getSession(false).getAttribute("theAdmin")!= null)
        	{
        		url="/admin.jsp";
        	}
        	else
        	{
        		url="/home.jsp";
        	}
        	
        }
        else if (action.equals("main")) {
        	
        	if(request.getSession(false).getAttribute("theUser")!= null)
        	{
        		url="/main.jsp";
        	}
        	else if(request.getSession(false).getAttribute("theAdmin")!= null)
        	{
        		url="/admin.jsp";
        	}
        	else
        	{
        		url="/login.jsp";
        	}

        }
        else if (action.equals("logout")) {
        	
        	if(request.getSession(false).getAttribute("theUser")!= null || request.getSession(false).getAttribute("theAdmin")!= null )
        	{
        		request.getSession(false).invalidate();
        		url = "/home.jsp";
        	}
        	else
        	{
        		url = "/home.jsp";
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