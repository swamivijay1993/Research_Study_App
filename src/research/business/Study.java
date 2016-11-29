package research.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Study implements Serializable {

    private String studyName;
    private String studyCode;
    private String questionCode;
    private String dateCreated;
    private String email;
    private String question;
    private String imageURL;
    private int requestedParticipants;
    private int numofParticipants;
    private String description;
    private String status;
    
    private enum AnswerType{
    	Text("String"),Numeric("Integer");
    	private final String names;
    	private AnswerType(String s)
    	{	
    	names =s;
    	}
    	 public String toString() {
        	 return this.names;
        	 }
    };

    private AnswerType answerType;
    private ArrayList<String> answers;
    private ArrayList<Answer> answerList;

    public Study() {
        studyName = "";
        studyCode = "";
        dateCreated = "";
        email = "";
        question = "";
        requestedParticipants=0;
        numofParticipants=0;
        description="";
        status = "";
        imageURL="";
        answerType = Study.AnswerType.Text;
        answers = new ArrayList<String>();
        answerList = new ArrayList<Answer>();
    }

    public Study(String studyName, String studyCode, String dateCreated, String email, String question, 
            int requestedParticipants, int numofParticipants,String imageURL, String description, String status, AnswerType answerType, ArrayList<String> answers, ArrayList<Answer> answerList)
    {
        this.studyName = studyName;
        this.dateCreated = dateCreated;
        this.studyCode = studyCode;
        this.email = email;
        this.question = question;
        this.requestedParticipants = requestedParticipants;
        this.numofParticipants = numofParticipants;
        this.imageURL=imageURL;
        this.description = description;
        this.status = status;
        this.answerType = answerType;
        this.answers = answers;
        this.answerList = answerList;
    }
    
    public String getStudyName() {
        return studyName;
    }

    public void setStudyName(String studyName) {
        this.studyName = studyName;
    }
    
    public String getStudyCode() {
        return studyCode;
    }

    public void setStudyCode(String studyCode) {
        this.studyCode = studyCode;
    }
    
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    
    public String getImageURL(){
        return imageURL;
    }
 
    public int getRequestedParticipants() {
        return requestedParticipants;
    }

    public void setRequestedParticipants(int requestedParticipants) {
        this.requestedParticipants = requestedParticipants;
    }
    
    public int getNumofParticipants() {
        return numofParticipants;
    }

    public void setNumofParticipants(int numofParticipants) {
        this.numofParticipants = numofParticipants;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public AnswerType getAnswerType() {
        return answerType;
    }
    
    public ArrayList<String> getAnswers() {    	
    	return this.answers;
    }
    
    public void setAnswers(ArrayList<String> list){
    	this.answers = list;
    }
    
    public void setAnswerList(Answer answer){
    	this.answerList.add(answer);
    }
    
    public ArrayList<Answer> getAnswerList(){
    	return answerList;
    }

    public void setAnswerType(String answerType) {
    	if(answerType.equalsIgnoreCase("numeric"))
    		this.answerType = AnswerType.Numeric;
    	else if(answerType.equalsIgnoreCase("text"))
    		this.answerType = AnswerType.Text;
    		
    }
    
   public int getAverage(){
       ArrayList<Integer> intAnswers = new ArrayList<Integer>();
       int sum = 0;
	   for(String i : this.answers)
       {
    	   intAnswers.add(Integer.parseInt(i));
       }
	   for (int i: intAnswers)
	   {
		   sum += i;
	   }
	   return(sum/intAnswers.size());
    }
    
    public int getMinimum(){
    	ArrayList<Integer> intAnswers = new ArrayList<Integer>();
 	   for(String i : this.answers)
        {
     	   intAnswers.add(Integer.parseInt(i));
        }
 	   Collections.sort(intAnswers);
    	return intAnswers.get(0);
    }
    
    public int getMaximum(){
    	ArrayList<Integer> intAnswers = new ArrayList<Integer>();
  	   for(String i : this.answers)
         {
      	   intAnswers.add(Integer.parseInt(i));
         }
  	   Collections.sort(intAnswers);
     	return intAnswers.get(intAnswers.size()-1);
    }
    
    public int getSD(){
    	ArrayList<Integer> intAnswers = new ArrayList<Integer>();
        int average, sum = 0;
        double sd = 0;
 	   for(String i : this.answers)
        {
     	   intAnswers.add(Integer.parseInt(i));
        }
 	   for (int i: intAnswers)
 	   {
 		   sum += i;
 	   }
 	   average = sum/intAnswers.size();
 	   
 	   for(int i: intAnswers)
 	   {
 		   sd = sd + ((i - average)*(i - average));
 	   }
    	
        return (int) (Math.sqrt(sd/(intAnswers.size()-1)));
    }

	public String getQuestionCode() {
		return questionCode;
	}

	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}
    
    
    
}

