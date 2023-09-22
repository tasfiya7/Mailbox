/**
 * Tasfiya Mubasshira
 * 114870281
 * sec 7
 * CSE 214
 */
package hw5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.io.Serializable;
import java.util.GregorianCalendar;


/**
 * 
 * @author tasfiya Mubasshira 114870281
 * 
 * The <code>Folder</code> creates an email folder Object that can contain email objects in it
 * This class is responsible for adding and removing emails from the folders as well as sorting them inside the folder
 *
 */
public class Folder implements Serializable{

	private ArrayList<Email> emails= new ArrayList<>();            // Stores emails in this arrayList.
    private String name=null;                        				// Stores the name of the folder.
    private String currentSortingMethod="DD";       				 //Stores the current sorting method of the folder (Default is Date Descending Order)
	
    /**
     * Default Constructor
     */
    public Folder() {
    	
    }
    /**
     * Creates a Folder and set the name of the Folder according to the parameter
     * @param name This is the name being set to the Folder
     */
    public Folder(String name){
		
		setName(name);
		
	}
    
    /**
     * Accesor Method to get the Arraylist of emails in a folder
     * @return Arraylist emails
     */
    public ArrayList<Email> getEmails() {
		return emails;
	}
    
    /**
     * Mutator metod to set the ArrayList of emails for a folder
     * @param emails This is being set as the arraylist to hold all the emails in a folder
     */
	public void setEmails(ArrayList<Email> emails) {
		this.emails = emails;
	}
	
	/**
	 * Accessor method to obtain the name of the folder
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Mustator method to set the name of the folder according to the parameter
	 * @param name This is the name beig set to the folder
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Accessor method to obtain the current sorting method to organize the folder
	 * @return name of the current sorting Mthod abbreviated as either SA, SD, DA, or DD
	 */
	public String getCurrentSortingMethod() {
		return currentSortingMethod;
	}
	
	/**
	 * Mutator method to set the current sorting method of the folder according to the parameter
	 * @param currentSortingMethod This is the method being set to sort the folder
	 */
	public void setCurrentSortingMethod(String currentSortingMethod) {
		this.currentSortingMethod = currentSortingMethod;
	}

	/**
	 * This method adds an email (taken from the parameter) to a Folder and sorts its position according to the currentSortingMethod
	 * @param email This is the email being added to the folder
	 */
	public void addEmail(Email email) {
		
        emails.add(email);

		if (currentSortingMethod == null) {
			currentSortingMethod = "DD";
            sortByDateDescending();

		}
		else if (currentSortingMethod == "DD") 
			
            sortByDateDescending();
		else if  (currentSortingMethod.equalsIgnoreCase("SA"))
            sortBySubjectAscending();

        else if (currentSortingMethod.equalsIgnoreCase("SD"))
            sortBySubjectDescending();

        else if (currentSortingMethod.equalsIgnoreCase("SA"))
            sortByDateAscending();
    }
		
	
	
	/**
	 * This method sorts the emails in the Folder in the Subject Ascending Order using the help of a comparator
	 */
	public void sortBySubjectAscending()  {
		
       
		
		Collections.sort(emails,subjectAscendingComparator);
		
	}
	
	/**
	 * This method sorts the emails in the Folder in the Subject Descending Order using the help of a comparator
	 */
	public void sortBySubjectDescending()  {
		
		
		Collections.sort(emails,subjectDescendingComparator);
		
	}
	
	/**
	 * This method sorts the emails in the Folder in the Date Ascending Order using the help of a comparator
	 */
	public void sortByDateAscending() {
		
		
		Collections.sort(emails,dateAscendingComparator);
		
	}

	/**
	 * This method sorts the emails in the Folder in the Date Descending Order using the help of a comparator
	 */
	public void sortByDateDescending()  {
			
		Collections.sort(emails,dateDescendingComparator); 
		
	}
	
	/**
	 * Comparator method to sort the email objects in date ascending order
	 */
	public static Comparator<Email> dateAscendingComparator=new Comparator<Email>(){
		public int compare(Email e1,Email e2){
			Email email1 = (Email)e1 ;
			Email email2 = (Email) e2;
			return (email1.getTimeStamp().compareTo(email2.getTimeStamp()));
			
		}
	};
	
	/**
	 * Comparator method to sort the email objects in date descending order
	 */
	public static Comparator<Email> dateDescendingComparator=new Comparator<Email>()
	{
		public int compare(Email e1,Email e2){
			Email email1 = (Email)e1;
			Email email2 = (Email)e2;
			return -(email1.getTimeStamp().compareTo(email2.getTimeStamp()));
		}
		
	}; 
	
	/**
	 * Comparator method to sort the email objects in subject ascending order
	 */
	public static Comparator<Email> subjectAscendingComparator=new Comparator<Email>()
	{
		public int compare(Email e1,Email e2){
			Email email1 = (Email)e1;
			Email email2 = (Email)e2;
			return (email1.getSubject().compareTo(email2.getSubject()));
		}		
	};
	
	/**
	 * Comparator method to sort the email objects in subject descending order
	 */
	public static Comparator<Email> subjectDescendingComparator=new Comparator<Email>()
	{
		public int compare(Email e1,Email e2){
			Email email1 = (Email)e1;
			Email email2 = (Email)e2;
			return -(email1.getSubject().compareTo(email2.getSubject()));
		}
	};
	
	/**
	 * This method removes an email (at the index in the paramter) of a Folder and returns the Email object removed
	 * @param index This is the index of the Email being removed from the Folder
	 * @return The Email Object removed
	 */
	public Email removeEmail(int index)  {
		
		return emails.remove(index);
		
	}
	
	/**
	 * This method neatly formats the content (emails) inside a folder in a table 
	 */
	public void printFolder(){
			
		if(emails.isEmpty())
			System.out.println( "This Folder is empty.");

		System.out.printf("\n%-6s|%-8s%-22s| %-7s\n", "Index", "", "Time", "Subject");
		System.out.print("-----------------------------------------------------------------------------------------\n");
		
	
			
			for(int i=0; i<emails.size(); i++){
				System.out.printf("\n  %-4s|  %-17s| %-7s", i+1, emails.get(i).getTimeStamp().getTime(), emails.get(i).getSubject());
			}
	}
	
	
}
