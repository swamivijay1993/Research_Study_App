package research.business;

import java.io.Serializable;

public class User implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
    private String name;
    private String password;
    private enum Type{
    	participant("participant"),
    	admin("admin");
    	private final String name;
    private Type (String s)
    {
    		name = s;
    }
    public String toString() {
    	 return this.name;
    	 }
    };
    Type type;
    private int numCoins;
    private int numPostedStudies;
    private int numParticipation;

    public User() {
        email = "";
        name = "";
        type = User.Type.participant;
        numCoins = 0;
        numPostedStudies = 0;
        numParticipation = 0;
    }

    public User(String name, String password, Type utype, String email, int numCoins, int numPostedStudies, int numParticipation) {
        this.name = name;
        this.password = password;
        this.type = utype;
        this.email = email;
        this.numCoins = numCoins;
        this.numPostedStudies = numPostedStudies;
        this.numParticipation = numParticipation;
        
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type.toString();
    }

    public void setType(String type) {
        if(type.equalsIgnoreCase("participant"))
        {
        	this.type = Type.participant;
        }
        else if(type.equalsIgnoreCase("admin"))
        {
        	this.type = Type.admin;
        }
    	
    }
    
    public int getNumCoins() {
        return numCoins;
    }

    public void setNumCoins(int numCoins) {
        this.numCoins = numCoins;
    }
    
    public int getNumPostedStudies() {
        return numPostedStudies;
    }

    public void setNumPostedStudies(int numPostedStudies) {
        this.numPostedStudies = numPostedStudies;
    }
        
    public int getNumParticipation() {
        return numParticipation;
    }

    public void setNumParticipation(int numParticipation) {
        this.numParticipation = numParticipation;
    }

	
}