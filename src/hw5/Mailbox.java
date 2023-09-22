/**
 * Tasfiya Mubasshira
 * 114870281
 * sec 7
 * CSE 214
 */
package hw5;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Date;

import java.util.Scanner;

/**
 * 
 * @author tasfiya mubasshira 114870281
 * 
 * The <code>Mailbox</code> represents an email box and contains all the folder objects that contains emails in them
 * The mailBox always contains the Inbox and Trash Folders
 * This method can add/delete Folders, compose Emails, clear the Tras Folder, move emails to other folders, and delete emails from a folder
 */
public class Mailbox {
	
	private Folder inbox = new Folder("Inbox"); 	// There is always an Inbox Folder in the MailBOx
	private Folder trash = new Folder("Trash");		// There is always a Trash Folder in the MailBOx
	private ArrayList<Folder> folders = new ArrayList<Folder>();	// This is the arraylist that holds all the folders in the Mailbox
	Scanner sc = new Scanner(System.in);

	/**
	 * Constructor for a Mailbox, with the Inbox and Trash folders included
	 */
	public Mailbox() {
		
		folders.add(inbox);
		folders.add(trash);
		
	}

	/**
	 * Accessor Method to get the Inbox Folder
	 * @return Inbox Folder
	 */
	public Folder getInbox() {
		return inbox;
	}

	/**
	 * Mutator method to set the Folder in the Parameter as the inbox folder
	 * @param inbox This is the Folder being set as the Inbox
	 */
	public void setInbox(Folder inbox) {
		this.inbox = inbox;
	}


	/**
	 * Accessor Method to get the Trash Folder
	 * @return Trash Folder
	 */
	public Folder getTrash() {
		return trash;
	}

	/** 
	 * Mutator method to set the Folder in the Parameter as the trash folder
	 * @param trash This is the Folder being set as the Trash
	 */
	public void setTrash(Folder trash) {
		this.trash = trash;
	}


	/**
	 * Accesor Method to get the Arraylist of folder in a mailbox
     * @return Arraylist folders
	 */
	public ArrayList<Folder> getFolders() {
		return folders;
	}


	/**
	 * Mutator Method to set the Arraylist of folder in a mailbox 
	 * @param folders This is being set as the arraylist to hold all the folder in a folder
	 */
	public void setFolders(ArrayList<Folder> folders) {
		this.folders = folders;
	}
	
	/**
	 * This method adds the Folder in the parameter to the Mailbox unless that folder already exists
	 * @param folder This is the folder being added
	 * @throws InboxTrashException This exception is thrown if and Inbox or Trash folder is trying to be added as there is already a Inbox and Trash Folder in the Mailbox
	 * @throws DuplicateException This exception is thrown if a Folder with the same name  as one already in the mailbox is trying to be added as there can only be one of each folder
	 */
	public void addFolder(Folder folder) throws InboxTrashException, DuplicateException {
		try {
		if (folder.getName().equalsIgnoreCase("Inbox") || folder.getName().equalsIgnoreCase("Trash"))
            throw new InboxTrashException();
		
		else {
			for(int i=0; i<folders.size(); i++) {
				if(folders.get(i).getName().equalsIgnoreCase(folder.getName())){
					throw new DuplicateException(); 
				}
				
			}
			
		}
		
		folders.add(folder);
        System.out.println("Folder Added.");

		}
		catch (InboxTrashException ie) {
			System.out.print("\nThe Inbox and Trash Folders cannot be deleted. \n");
		}
		catch (DuplicateException ie) {
			System.out.print("\nThis folder already exists \n");
		}
	}
	
	
	/**
	 * This method takes the name of the Folder to be deleted and deletes the folder from the mailbox unless the Folder with that name is not found or it is the Inbox/Trash Folder
	 * @param name This is the name of the Folder to be deleted from the mailbox
	 * @throws InboxTrashException This exception is thrown if the Folder to be deleted is the Inbox or Trash as they can never be deleted
	 * @throws FolderNotFoundException This exception is thrwon when a Folder with the name in the paramter does not exist and therefore cannot be deleted
	 */
	public void deleteFolder(String name) throws InboxTrashException, FolderNotFoundException {
		try {
		if (name.equalsIgnoreCase("Inbox") || name.equalsIgnoreCase("Trash"))
            throw new InboxTrashException();
		
		boolean found = false;
		for(int i = 0; i < folders.size(); i++) {
			if(folders.get(i).getName().equalsIgnoreCase(name)){
				folders.remove(i);
				found= true;
		        System.out.println("Folder Deleted.");
				return;
			}
		}
		if (found==false) {
			throw new FolderNotFoundException();
		}
		}
		catch (InboxTrashException ie) {
			System.out.print("\nThe Inbox and Trash Folders cannot be deleted. \n");
		}
		catch (FolderNotFoundException ie) {
			System.out.print("\nFolder not found \n");
		}
	}
	
	/**
	 * This method creates an email by asking the user for inputs of all the attributes of an email an adds the email to the inbox folder
	 */
	public void composeEmail() {
		
		System.out.println();
        System.out.println("Enter recipient (To): ");
        String to = sc.nextLine().trim();

		System.out.println();
        System.out.println("Enter carbon copy recipients (CC): ");
        String cc = sc.nextLine().trim();

		System.out.println();
        System.out.println("Enter blind carbon copy recipients (BCC): ");
        String bcc = sc.nextLine().trim();

		System.out.println();
        System.out.println("Enter subject line: ");
        String subject = sc.nextLine().trim();

		System.out.println();
        System.out.println("Enter body: ");
        String body = sc.nextLine().trim();

        GregorianCalendar timestamp = new GregorianCalendar();
        Date date = new Date();
        timestamp.setTime(date);

        Email temp = new Email(to, cc, bcc, subject, body, timestamp);

        inbox.addEmail(temp);
        System.out.println("Email successfully added.");
		
		
	}
	
	/**
	 * This method deletes the email in the paramter from a folder (put it in the trash)
	 * @param email
	 */
	public void deleteEmail(Email email) {
        trash.addEmail(email);
        System.out.println("Email deleted.");

    }
	
	/**
	 * This method empties the trash folder unless it is already empty
	 * @throws Exception If the trash is already empty this exception is thrown
	 */
	public void clearTrash() throws Exception {
		try {
		if (trash.getEmails().isEmpty()) {
			throw new Exception ();
		}
		
        trash.getEmails().clear();
        System.out.println("Trash Emptied.");
		}
        catch (Exception ie) {
        	System.out.println("Trash is already empty");
        }
    }
	
	/**
	 * This method moves an email to a folder as specified in the paramater. If the Folder is not found then the email is moved to the inbox folder
	 * @param email This is the email being moved
	 * @param target This is the Folder to which we want to move the email to
	 */
	public void moveEmail(Email email, Folder target)  {
		boolean found=false;
		if (target==null) {
			inbox.addEmail(email);
			System.out.println("The email has been moved to Inbox");

		}
		for (int i = 0; i < folders.size(); i++) {
            if (folders.get(i).getName().equalsIgnoreCase(target.getName())) {
                folders.get(i).addEmail(email);
                found = true;
                System.out.println("The email has been moved to desired Folder");
            }
       }
		
		 if (found==false) {
			inbox.addEmail(email);
			System.out.println("The email has been moved to Inbox");
		}
		
	}
	
	/**
	 * This method looks for a Folder using the name of the Folder in the paramater and returns the Folder unless it is not found
	 * @param name This is the name of the Folder being obtained
	 * @return The Folder with the name in the paramater
	 * @throws FolderNotFoundException Thsi exception ois thrown if a file with the name in the paramater is not found
	 */
	public Folder getFolder(String name) throws FolderNotFoundException {
		try {
		boolean found = false;
		for(int i=0; i<folders.size(); i++){
			if(folders.get(i).getName().equalsIgnoreCase(name)){
				found=true;
				return folders.get(i);
			}
		}
		 if (found== false) {
			 throw new FolderNotFoundException();
		 }
		}
		catch(FolderNotFoundException ie) {
			System.out.println("Folder not found");
		}
		 
	return null;
		
		
	}
	
	/**
	 * This method Formats the names of the folders in the mailboz each on a seperate line
	 */
	public String toString() {
		String str = "";

		for (Folder item : folders) {
            str+= item.getName() + "\n";
        }
		
		return str;
		
	}
	
	
	

}

/**
 * 
 * @author tasfiya mubasshira 114970281
 * 
 *This class was created for the exception thrown when a Folder is not found
 *
 */
class FolderNotFoundException extends Exception{
	FolderNotFoundException(){
		
	}
	FolderNotFoundException(String s){
		super(s);
		System.out.println(s);
	}
}


/**
 * 
 * @author tasfiya mubasshira 114970281
 *
 *This class was created for the exception thrown when a duplcate folder is trying to be added to the mailbox
 */
class DuplicateException extends Exception{
	DuplicateException(){
		
	}
	DuplicateException(String s){
		super(s);
		System.out.println(s);
	}
}


/**
 * 
 * @author tasfiya mubasshira 114970281
 * This class was created for the exception thrown when the Inbox or Trash Folders are trying to be removed or a duplicate Inbox/Trash Folder is trying to be created
 */
class InboxTrashException extends Exception{
	InboxTrashException(){
		
	}
	InboxTrashException(String s){
		super(s);
		System.out.println(s);
	}
}
