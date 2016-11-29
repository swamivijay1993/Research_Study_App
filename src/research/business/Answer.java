
package research.business;


import java.io.Serializable;

public class Answer implements Serializable {

    private String email;
    private String choice;
    private String submissionDate;

    public Answer() {
        email = "";
        choice = "";
        submissionDate = "";
    }

    public Answer(String choice, String submissionDate, String email) {
        this.choice = choice;
        this.email = email;
        this.submissionDate = submissionDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getChoice() {
        return choice;
    }

    public void setChoice(String Choice) {
        this.choice = Choice;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }
    
}
