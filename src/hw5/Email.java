/**
 * Tasfiya Mubasshira
 * 114870281
 * sec 7
 * CSE 214
 */
package hw5;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Comparator;

/**
 * 
 * @author tasfiya mubasshira 114870281
 *
 *The <code>Email</code> class creates an email object using the parameters
 *  to, cc, bcc, subject, body, and the time sent.
 */
public class Email implements Serializable{

    private String to=null;                      // The String literal which stores the “to” field.
    private String cc=null;                      // The String literal which stores the “cc” field.
    private String bcc=null;                     // The String literal which stores the “bcc” field.
    private String subject=null;                 // The String literal which stores the “subject” field.
    private String body=null;                    // The String literal which stores the email’s body.
    private GregorianCalendar timeStamp =new GregorianCalendar(); // used to set th etime the email is created
    
    /**
     * Default Constructor
     */
    public Email() {
    	
    }
    
    /**
     * A constructor that created the Email Objects and sets the attributes of it
     * @param t This is the "to" of the email being set
     * @param c This is the "cc" of the email being set
     * @param b This is the "bcc" of the email being set
     * @param sub This is the subject of the email being set
     * @param bod This is the body of the email being set
     * @param tstamp This is the "time being set when the email is created
     */
    public Email (String t, String c, String b, String sub, String bod, GregorianCalendar tstamp) {
    	setTo(t);
    	setCc(c);
    	setBcc(b);
    	setSubject(sub);
    	setBody(bod);
    	setTimeStamp(tstamp);
    	
    }
    
    /**
     * Accessor Method to get the "to" of the email 
     * @return to (String)
     */
	public String getTo() {
		return to;
	}
	
	/**
	 * Mutator method for the "to" of the email, being set according to the string in the parameter
	 * @param to This is the "to" recipient being set to the email
	 */
	public void setTo(String to) {
		this.to = to;
	}
	/**
	 * Accessor Method to get the "Cc" of the email 
	 * @return cc (string)
	 */
	public String getCc() {
		return cc;
	}
	
	/**
	 *Mutator method for the "Cc" of the email, being set according to the string in the parameter
	 * @param cc This is the "Cc" recipient being set to the email
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}
	/**
	 * Accessor Method to get the "Bcc" of the email 
	 * @return bcc (string)
	 */
	public String getBcc() {
		return bcc;
	}
	
	/**
	 * Mutator method for the "Bcc" of the email, being set according to the string in the parameter
	 * @param bcc This is the "Bcc" recipient being set to the email
	 */
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	/**
	 * Accessor Method to get the subject of the email 
	 * @return subject (string)
	 */
	public String getSubject() {
		return subject;
	}
	
	/**
	 * Mutator method for the subject of the email, being set according to the string in the parameter
	 * @param subject This is the subject being set to the email
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * Accessor Method to get the body of the email 
	 * @return body (string)
	 */
	public String getBody() {
		return body;
	}
	
	/**
	 * Mutator method for the body of the email, being set according to string in the parameter
	 * @param body This is the body being set to th email
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * Accessor Method to get the time at which the email is created
	 * @return timeStamp
	 */
	public GregorianCalendar getTimeStamp() {
		return timeStamp;
	}


	/**
	 * Mustator method to set the time at which email is created according to the parameter
	 * @param timeStamp This is the time being set
	 */
	public void setTimeStamp(GregorianCalendar timeStamp) {
		this.timeStamp = timeStamp;
	}
	
    
	/**
	 * This method neatly prints out  all of the attributes of an Email Object
	 */
	public String toString() {
        return "\n"
        		+ "To: " + to + 
        		"\n" 
        		+ "CC: = " + cc 
        		+ "\n" +
                "BCC: " + bcc 
                + "\n" +
                "Subject: " + subject 
                + "\n" +
                "" + body 
                + " \n";
    }
	
	
    
    
}