package research.data;

import research.business.User;
import java.util.List;
import java.util.Random;
import research.data.PasswordUtility;

import java.security.SecureRandom;
import java.sql.*;

import java.util.ArrayList;
import research.data.ConnectionPool;

public class UserDB {
    
    private List<User> users;
    
    public User getUser(String sEmailId, String password){
       
    	ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        User user = new User();
        
       // System.out.println(PasswordUtility.md5(password));
        
        try
        {
        	statement = connection.prepareStatement("SELECT * FROM user WHERE Username = ? AND Password =?");
        	statement.setString(1, sEmailId);
        	statement.setString(2, password);
        			
        	ResultSet result = statement.executeQuery();
        	
        	
        	if(result!=null)
        	{
        		while(result.next())
        		{
        			user.setEmail(sEmailId);
              		user.setType(result.getString(3));
        			user.setNumPostedStudies(result.getInt(4));
        			user.setNumParticipation(result.getInt(5));
        			user.setNumCoins(result.getInt(6));
        			user.setName(result.getString(7));
        			return user;
        		}
        	}
        	else
        	{
        		return null;
        	}	
        	statement.close();
        	result.close();
        	
        } catch (SQLException e) {
            e.printStackTrace();
        } catch(Exception e){
        	e.getStackTrace();
        }
        finally{
        	pool.freeConnection(connection);
        } 
       return null;
    }
    
    public User addUser(String email, String password, String name){
    	ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        User user = new User();
        
        
        
        try{
        	
        	statement = connection.prepareStatement("INSERT INTO user(UserName, Password, Type, Studies, Participation, Coins, Name)"
        											+" VALUES(?,?,?,?,?,?,?) ");
        	statement.setString(1, email);
        	statement.setString(2, PasswordUtility.md5(password));
        	statement.setString(3,"participant");
        	statement.setInt(4, 0);
        	statement.setInt(5, 0);
        	statement.setInt(6, 0);
        	statement.setString(7, name);
        	statement.executeUpdate();
        	statement.close();
        	
        	user.setEmail(email);
        	user.setName(name);
        	user.setType("participant");
        	
        }catch (SQLException e) {
            e.printStackTrace();
        } catch(Exception e){
        	e.getStackTrace();
        }
        finally{
        	pool.freeConnection(connection);
        } 
        return user;
    }

    public int getParticipants(String user)
    {
    	ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        int participant = 0;
        
        try{
        	
        	statement = connection.prepareStatement("SELECT sum(S.ActParticipants) FROM study S"
        											+" WHERE S.Username = ?");
        	statement.setString(1, user);
        	ResultSet result = statement.executeQuery();
        	
        	if(result!=null)
        	{
        		while(result.next())
        		{
        			participant = result.getInt(1);
        		}
        	}
        	
        	statement.close();
        	
        }catch (SQLException e) {
            e.printStackTrace();
        } catch(Exception e){
        	e.getStackTrace();
        }
        finally{
        	pool.freeConnection(connection);
        } 
        
        return participant;
    }
    
    public int getParticipation(String user)
    {
    	ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        int participation = 0;
        
        try{
        	
        	statement = connection.prepareStatement("SELECT Participation FROM user"
        											+" WHERE Username = ?");
        	statement.setString(1, user);
        	ResultSet result = statement.executeQuery();
        	
        	if(result!=null)
        	{
        		while(result.next())
        		{
        			participation = result.getInt(1);
        		}
        	}
        	
        	statement.close();
        	
        }catch (SQLException e) {
            e.printStackTrace();
        } catch(Exception e){
        	e.getStackTrace();
        }
        finally{
        	pool.freeConnection(connection);
        } 
        
        return participation;
    }
	
	
	
   
}