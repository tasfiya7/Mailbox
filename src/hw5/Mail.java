/**
 * Tasfiya Mubasshira
 * 114870281
 * sec 7
 * CSE 214
 */
package hw5;

import java.io.*;
import java.util.Scanner;
import java.io.Serializable;


/**
 * 
 * @author tasfiya mubasshira 114970281
 * 
 * This class contain the main method runs a menu that asks the user for input to execute an opperation. 
 *
 */
public class Mail implements Serializable{

	public static Mailbox mailbox;
	
	
	
	
	public static void main(String[] args) throws Exception {
		Scanner sc= new Scanner (System.in); //  takes in user input
		String input="";

		// from hw notes
		try {
			FileInputStream file = new FileInputStream("mailbox.obj");
			ObjectInputStream fin = new ObjectInputStream(file);
			mailbox = (Mailbox) fin.readObject();
			file.close();
			System.out.println("Previous save found, starting with previous mailbox.\n");
		}
		catch (IOException ie) {
			mailbox = new Mailbox();
			System.out.println("Previous save not found, starting with an empty mailbox.\n");
		}
		catch (ClassNotFoundException ie) {
			mailbox = new Mailbox();
			System.out.println("Previous save not found, starting with an empty mailbox.\n");
		}
	
		do {
			// main Menu
			System.out.println();
			System.out.printf("Mailbox:\n----------\n");
			System.out.print(mailbox.toString());
			System.out.println();
            System.out.printf("A – Add folder\n");
            System.out.printf("R – Remove folder\n");
            System.out.printf("C – Compose email\n");
            System.out.printf("F – Open folder\n");
            System.out.printf("I – Open Inbox\n");
            System.out.printf("T – Open Trash\n");
            System.out.printf("E – Empty Trash\n");
            System.out.printf("Q - Quit");
			System.out.println();

			
			System.out.println("Select a menu option: ");
			
			System.out.println();

			
			input=sc.nextLine().toUpperCase(); // disregard case in user's inut
				
			
			switch(input) {
			
			case "A": //adds folder
				
				System.out.print("Enter the folder name: ");
				String name = sc.nextLine().trim();
				Folder folder = new Folder(name);
                mailbox.addFolder(folder);
				
				
                break;
                
			case "R": //removes folder
				
				System.out.print("Enter the folder to be removed: ");
                String folderName = sc.nextLine().trim();
                mailbox.deleteFolder(folderName);
				
				break;
			case "C": 
			
					mailbox.composeEmail();
				
	        	break;
	        case "F": 
	       
	        	System.out.println("Enter folder name: ");
				name = sc.nextLine().trim();
				Folder folder1= mailbox.getFolder(name);
	        	folder1.sortByDateDescending();
	        	subMenu(folder1);
	        	
	            break;
	        case "I": 
	        	
	        	subMenu(mailbox.getFolder("Inbox"));
	        
	        	
	        	break;
	        case "T": 
	        	
		        	subMenu(mailbox.getFolder("Trash"));
		        
	            break;
	        case "E": 
	     
					mailbox.clearTrash();

	        	break;
	        case "Q": // stops program
	            System.out.println("Program terminated.");
	            System.exit(0);
	            break;
	        default: // incorrect input
	            System.out.println("Not an Option. Try Again");
			
                
			}
			
			
			
		} while(input !="Q"); // While "Q" is not inputed the menu will repeat
		
		
		try{ // from hw notes
			FileOutputStream file = new FileOutputStream("mailbox.obj");
			ObjectOutputStream fout = new ObjectOutputStream(file);
			fout.writeObject(mailbox);
			fout.close();
			System.out.println("Program succesfully exited and mailbox saved.");
		}
		catch(IOException a){
			System.out.println("Cannot save file.");
		}
		System.exit(0);
		
	}
	
	public static void subMenu(Folder folder) throws Exception{
		Scanner sc= new Scanner (System.in); //  takes in user input
		String input="";
		folder.sortByDateDescending();
		
		do {
			//submenu
			System.out.println();

			System.out.println(folder.getName());
			folder.printFolder();
			System.out.println();
			System.out.println();
			System.out.println("M - Move email");
			System.out.println("D - Delete email");
			System.out.println("V - View email contents");
			System.out.println("SA - Sort by subject line in ascending order");
			System.out.println("SD - Sort by subject line in descending order");
			System.out.println("DA - Sort by date in ascending order");
			System.out.println("DD - Sort by date in descending order");
			System.out.println("R - Return to mailbox\n");
			System.out.print("Enter a user option: ");
			System.out.println();

			input=sc.nextLine().toUpperCase(); // disregard case in user's inut
			
			
			switch(input) {
			
			case "M": // moves email to a folder
				
				try {
					
				System.out.println("Enter the index number of the email: ");
                int index = sc.nextInt();
                if (index<1 || index>folder.getEmails().size()) {
                	throw new EmailNotFoundException("Invalid index");
                }
                sc.nextLine();
                System.out.println("Enter the name of the target folder the email will be moved to: ");
                String name = sc.nextLine();
                Folder targetFolder = new Folder();
                targetFolder.setName(name);
                
                mailbox.moveEmail(folder.removeEmail(index-1), targetFolder);
					
				}
				catch(EmailNotFoundException ie) {
					System.out.println("Email does not exist at that index");
							
				}
				
				 break;
	        case "D":  // deletes email
	        	
	        	try {
					
	        	System.out.println("Enter the index number of the email: ");
	            int index = sc.nextInt();
	              	if (index<1 || index>folder.getEmails().size()) {
	                	throw new EmailNotFoundException("Invalid index");
	                }
	            mailbox.deleteEmail(folder.removeEmail(index-1));
	              	
				}
				catch(EmailNotFoundException ie) {
					System.out.println("Email does not exist at that index");
					
				}
				

	        	break;
	        case "V": 
	        	
	        	try { // printds out email content
	        		
	        	System.out.println("Enter the index number of the email: ");
		        int index = sc.nextInt();
		             if (index<1 || index>folder.getEmails().size()) {
		                throw new EmailNotFoundException("Invalid index");
		             }
		        System.out.println(folder.getEmails().get(index-1).toString());
					
				}
				catch(EmailNotFoundException ie) {
					System.out.println("Email does not exist at that index");
					
				}
				
				
	            break;
	        case "SA": 
	        		
	        		folder.sortBySubjectAscending();
				
	        	  break;
	        case "SD": 

                    folder.sortBySubjectDescending();
                
	        	break;
	        case "DA": 
	        	
                    folder.sortByDateAscending();

	            break;
	        case "DD": 
	        		
                    folder.sortByDateDescending();
	        	
	        	break;
	        case "R": 
	        	System.out.println("Exiting subMenu.");
	            
	            break;
	        default: // incorrect input
	            System.out.println("Not an Option. Try Again");
			
			}
			
			
		} while (!input.equals("R")); //While "R" is not inputed the menu will repeat
		
		
	}
	
	
}

/**
 * 
 * @author tasfiyamubasshira 114970281
 * This class was created for the exception thrown when and Email is not found based on the index
 */
class EmailNotFoundException extends Exception{
	EmailNotFoundException(){
		
	}
	EmailNotFoundException(String s){
		super(s);
		System.out.println(s);
	}
}
