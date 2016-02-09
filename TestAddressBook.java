package Homework7;
//Purpose: This application will save a person to an address book. You will
//be able to add a person, delete a person, modify an entry, search for a person and list all of
//the persons in your address book. You can choose to enter either a Personal Friend, or a Business Associate. You can 
//download the file to disk and upload it back into a fresh run of the program. You can also print your address book to a 
//text document. 
//Powell Hamner and Raymond C. Beauchene
//CS 142
//TESTADDRESSBOOK CLASS

import java.util.*;
import java.io.*;
import java.text.*;
public class TestAddressBook {
	static Scanner sc= new Scanner(System.in);
	static String firstname, lastname, address, city, state, zip, phone, in, cell, 
	email, zodiac, jobType, faxNumber, compName, contactType, text, test;
	static String junk = "\n\n";
	static char input, ConType;
	static Date date, birthdate;
	static int friendcount, businesscount, year, month, day, age;
	static SimpleDateFormat sdf = new SimpleDateFormat("MMM. dd, yyyy");
	static Person p;
	static ArrayList<Person> AddressBook = new ArrayList<Person>();
	public static void main(String[] args) {

		do{
			Menu();
			//decides what to do based on user selection
			switch (input){
			case 'A':addPerson();break;
			case 'R':deletePerson();break;
			case 'M':modifyPerson();break;
			case 'S':search();break;	
			case 'L':listAll();break;
			case 'D':try {download();} catch (Exception e) {e.printStackTrace();}break;
			case 'U':try {upload();} catch (Exception e) {e.printStackTrace();}break;
			case 'P': try {printAll();} catch (Exception e) {e.printStackTrace();} break;
			}
		}while(input!='Q');
		System.out.println("Application Closed");
		System.exit(0);
	}
	//user menu
	public static void Menu(){
		System.out.println("Address Book Menu");
		System.out.println("\tEnter A to (A)dd a Person");
		System.out.println("\tEnter R to (R)emove a Person");
		System.out.println("\tEnter M to (M)odify a Person");
		System.out.println("\tEnter S to (S)earch Address Book");
		System.out.println("\tEnter L to (L)ist All (sorted)");
		System.out.println("\tEnter D to (D)ownload to Disk");
		System.out.println("\tEnter U to (U)pload from Disk");
		System.out.println("\tEnter P to (P)rint Address Book");
		System.out.println("\tEnter Q to (Q)uit");
		System.out.println("Please enter your choice: ");
		input=sc.nextLine().toUpperCase().charAt(0);
		while(input!='A' && input !='R' && input !='M' && input !='S' && input !='L' &&  input!='D' && 
				input != 'U' && input != 'P' && input !='Q'){
			System.out.println("ERROR: Invalid Input \nPlease select either (A)dd, (R)emove, (M)odify, "
					+ "(S)earch, (L)ist All, (D)ownload, (U)pload, (P)rint, or (Q)uit");
			in=sc.nextLine();
			input=in.toUpperCase().charAt(0);
		}
	}

	public static void addPerson(){
		System.out.println("Please input the your contact's information (one line per field)");

		//First name validation
		System.out.print("\nPlease enter your contact's first name:");firstname=sc.nextLine();
		while (firstname.equals("")) {
			System.out.println("Error, please enter contact's first name");
			System.out.print("\nPlease enter contact's first name:");firstname=sc.nextLine();
		}
		//Last name validation
		System.out.print("\nPlease enter your contact's last name:");lastname=sc.nextLine();
		while (lastname.equals("")) {
			System.out.println("Error, please enter contact's last name");
			System.out.print("\nPlease enter contact's last name:");lastname=sc.nextLine();
		}
		//address validation
		System.out.print("\nPlease enter your contact's street address:");address=sc.nextLine();
		while (address.equals("")) {
			System.out.println("Error, Please enter your contact's street address:");
			System.out.print("\nPlease enter your contact's street address:");address=sc.nextLine();
		}
		//city validation
		System.out.print("\nPlease enter your contact's city:");city=sc.nextLine();
		while (city.equals("")) {
			System.out.println("Error, Please enter you contact's city:");
			System.out.print("\nPlease enter you contact's city:");city=sc.nextLine();
		}

		//State Validation
		System.out.print("\nPlease enter your contact's state:");state=sc.nextLine();
		while(state.equals("") || state.length()!= 2){
			System.out.println("Error, Please enter you contact's State:");
			System.out.print("\nPlease enter your contact's state:");state=sc.nextLine();
		}

		//zip validation
		System.out.print("\nPlease enter your contact's zip code: #####");zip=sc.nextLine();
		while (!zip.matches("\\d{5}")) {
			System.out.println("Error, Please enter you contact's zip code: #####");
			System.out.print("\nPlease enter your contact's zip code: #####");zip=sc.nextLine();
		}
		//Phone Exception
		do{
			try {
				System.out.print("\nPlease enter contact's phone number: ###-###-#### ");phone=sc.nextLine();
				if (!phone.matches("\\d{3}-\\d{3}-\\d{4}")) {
					throw new PhoneException("Error.  Phone number must be  ###-###-#### ");
				}
			} catch (PhoneException pe) {
				System.out.println("Error.  Phone number must be  ###-###-#### ");
			}
		}while (!phone.matches("\\d{3}-\\d{3}-\\d{4}"));
		//Cell Exception
		do{
			try {
				System.out.print("\nPlease enter contact's cell phone number: ###-###-#### ");cell=sc.nextLine();
				if (!cell.matches("\\d{3}-\\d{3}-\\d{4}")) {
					throw new PhoneException("Error.  Cell number must be  ###-###-#### ");
				}
			} catch (PhoneException ce) {
				System.out.println("Error.  Cell number must be  ###-###-#### ");
			}}while (!cell.matches("\\d{3}-\\d{3}-\\d{4}"));
		//Email Exception
		do{
			try {
				System.out.print("\nPlease enter your contact's email: (example@test.com) ");email=sc.nextLine();
				if (!email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,}|com|org|net|gov|edu|)$")) {
					throw new EmailException("Error. Email must be example@test.com");
				}
			} catch (EmailException ee) {
				System.out.println("Error.  Email must be example@test.com");
			}}while (!email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,}|com|org|net|gov|edu|)$"));

		System.out.print("\nIs this person a (F)riend or (B)usiness Associate?");
		input=sc.nextLine().toUpperCase().charAt(0);
		while(input!='F' && input !='B'){
			System.out.println("ERROR: Invalid Input \nPlease enter either F or B");
			input=sc.nextLine().toUpperCase().charAt(0);
		}

		if(input == 'F'){
			contactType = "Personal Friend";
			//Birth Year validation
			System.out.print("\nPlease enter contact's birth year:"); year=Integer.parseInt(sc.nextLine());
			while (year < 1901 || year > 3000) {
				System.out.println("Error, please enter contact's birth year: ");
				System.out.print("\nPlease enter contact's birth year:");year=Integer.parseInt(sc.nextLine());
			}
			//Birth month validation
			System.out.print("\nPlease enter contact's birth month: 1-12");month = Integer.parseInt(sc.nextLine());
			while (month < 1 || month > 12) {
				System.out.println("Error, please enter contact's birth month between 1-31");
				System.out.print("\nPlease enter contact's birth month:");month=Integer.parseInt(sc.nextLine());
			}
			//Birth day validation
			System.out.print("\nPlease enter contact's birth day:");day = Integer.parseInt(sc.nextLine());
			while (day < 1 || day > 31) {
				System.out.println("Error, please enter contact's birth day between 1-31");
				System.out.print("\nPlease enter contact's birth day:");day=Integer.parseInt(sc.nextLine());
			}
			//Age validation
			System.out.print("\nPlease enter contact's age:");age = Integer.parseInt(sc.nextLine());
			while (age < 1 || age > 120) {
				System.out.println("Error, please enter contact's age between 1-150");
				System.out.print("\nPlease enter contact's age:");age=Integer.parseInt(sc.nextLine());
			}
			System.out.print("\nPlease enter contact's Zodiac Sign:");zodiac=sc.nextLine();


			friendcount++;
			date= new Date();
			AddressBook.add(new PersonalFriend(firstname, lastname, address, city, state, zip, phone, date, cell, email, contactType, age, zodiac, year, month, day));
		}
		else{
			contactType="Business Associate";
			//jobType validation ****This Needs Changing On All HW****
			System.out.print("\nPlease enter business associate's job type: ");jobType=sc.nextLine();
			while (jobType.equals("")) {
				System.out.println("Error, Please enter business associate's job type:");
				System.out.print("\nPlease enter business associate's job type:");jobType=sc.nextLine();
			}
			//faxNumber validation ****This Needs Changing On All HW****
			do{
				try {
					System.out.print("\nPlease enter business associate's fax number: ###-###-####");faxNumber=sc.nextLine();
					if (!faxNumber.matches("\\d{3}-\\d{3}-\\d{4}")) {
						throw new PhoneException("Error.  Business associate's fax number must be  ###-###-####");
					}
				} catch (PhoneException ce) {
					System.out.println("Error.  Business associate's fax number must be  ###-###-####");
				}}while (!faxNumber.matches("\\d{3}-\\d{3}-\\d{4}"));
			//compName validation ****This Needs Changing On All HW****
			System.out.print("\nPlease enter business associate's company name: ");compName=sc.nextLine();
			while (compName.equals("")) {
				System.out.println("Error, Please enter business associate's company name:");
				System.out.print("\nPlease enter business associate's company name:");compName=sc.nextLine();
			}
			businesscount++;
			date= new Date();
			AddressBook.add(new BusinessAssociate(firstname,lastname, address, city, state, zip, phone, date, cell, email, contactType, jobType, faxNumber, compName));
		}
		System.out.println(junk);
	}

	public static void deletePerson(){
		System.out.println("Please enter the first name of the Person youd like to delete: ");
		String firstn=sc.nextLine();
		System.out.println("Please enter the last name of the Person youd like to delete: ");
		String ln=sc.nextLine();
		String fn= firstn+" "+ln;
		char option;
		boolean flag=false;
		for(int i=0;i<AddressBook.size(); i++){
			if(AddressBook.get(i).getFirstName().equalsIgnoreCase(firstn)&&AddressBook.get(i).getLastName().equalsIgnoreCase(ln)){
				flag=true;
				System.out.println(AddressBook.get(i).toString());
				System.out.println("Would you really like to delete " + fn + " from your Address Book (Y)es/(N)o" );
				option=sc.nextLine().toUpperCase().charAt(0);
				if(option == 'Y'){
					ConType = AddressBook.get(i).getContactType().toUpperCase().charAt(0);
					if(ConType == 'P'){friendcount--;}
					else{businesscount--;}
					AddressBook.remove(i);
				}
				break;
			}
		}
		if (flag == false){
			System.out.println("No person by the name of "+fn+" was found in your address book");
		}System.out.println(junk);
	}

	public static void modifyPerson(){
		System.out.println("Please enter the first name of the Person youd like to modify: ");
		String firstn=sc.nextLine();
		System.out.println("Please enter the last name of the Person youd like to modify: ");
		String ln=sc.nextLine();
		String fn= firstn+" "+ln;
		boolean flag=false;
		for(int i=0;i<AddressBook.size(); i++){
			if(AddressBook.get(i).getFirstName().equalsIgnoreCase(firstn)&&AddressBook.get(i).getLastName().equalsIgnoreCase(ln)){

				ConType = AddressBook.get(i).getContactType().toUpperCase().charAt(0);
				flag=true;
				System.out.println(AddressBook.get(i).toString());

				//First name validation
				System.out.print("\nPlease enter your contact's NEW first name:");firstname=sc.nextLine();
				while (firstname.equals("")) {
					System.out.println("Error, please enter contact's NEW first name");
					System.out.print("\nPlease enter contact's NEW first name:");firstname=sc.nextLine();
				}
				//Last name validation
				System.out.print("\nPlease enter your contact's NEW last name:");lastname=sc.nextLine();
				while (lastname.equals("")) {
					System.out.println("Error, please enter contact's NEW last name");
					System.out.print("\nPlease enter contact's NEW last name:");lastname=sc.nextLine();
				}
				//address validation
				System.out.print("\nPlease enter your contact's NEW street address:");address=sc.nextLine();
				while (address.equals("")) {
					System.out.println("Error, Please enter your contact's NEW street address:");
					System.out.print("\nPlease enter your contact's NEW street address:");address=sc.nextLine();
				}
				//city validation
				System.out.print("\nPlease enter your contact's NEW city:");city=sc.nextLine();
				while (city.equals("")) {
					System.out.println("Error, Please enter you contact's NEW city:");
					System.out.print("\nPlease enter you contact's NEW city:");city=sc.nextLine();
				}

				//State Validation
				System.out.print("\nPlease enter your contact's NEW state:");state=sc.nextLine();
				while(state.equals("") || state.length()!= 2){
					System.out.println("Error, Please enter you contact's NEW State:");
					System.out.print("\nPlease enter your contact's NEW state:");state=sc.nextLine();
				}

				//zip validation
				System.out.print("\nPlease enter your contact's NEW zip code: #####");zip=sc.nextLine();
				while (!zip.matches("\\d{5}")) {
					System.out.println("Error, Please enter you contact's NEW zip code: #####");
					System.out.print("\nPlease enter your contact's NEW zip code: #####");zip=sc.nextLine();
				}
				//Phone Exception
				do{
					try {
						System.out.print("\nPlease enter contact's NEW phone number: ###-###-#### ");phone=sc.nextLine();
						if (!phone.matches("\\d{3}-\\d{3}-\\d{4}")) {
							throw new PhoneException("Error.  Phone number must be  ###-###-#### ");
						}
					} catch (PhoneException pe) {
						System.out.println("Error.  Phone number must be  ###-###-#### ");
					}
				}while (!phone.matches("\\d{3}-\\d{3}-\\d{4}"));
				//Cell Exception
				do{
					try {
						System.out.print("\nPlease enter contact's NEW cell phone number: ###-###-#### ");cell=sc.nextLine();
						if (!cell.matches("\\d{3}-\\d{3}-\\d{4}")) {
							throw new PhoneException("Error.  Cell number must be  ###-###-#### ");
						}
					} catch (PhoneException ce) {
						System.out.println("Error.  Cell number must be  ###-###-#### ");
					}}while (!cell.matches("\\d{3}-\\d{3}-\\d{4}"));
				//Email Exception
				do{
					try {
						System.out.print("\nPlease enter your contact's NEW email: (example@test.com) ");email=sc.nextLine();
						if (!email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,}|com|org|net|gov|edu|)$")) {
							throw new EmailException("Error. Email must be example@test.com");
						}
					} catch (EmailException ee) {
						System.out.println("Error.  Email must be example@test.com");
					}}while (!email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,}|com|org|net|gov|edu|)$"));


				date= new Date();
				AddressBook.get(i).setFirstName(firstname);
				AddressBook.get(i).setLastName(lastname);
				AddressBook.get(i).setAddress(address);
				AddressBook.get(i).setCity(city);
				AddressBook.get(i).setState(state);
				AddressBook.get(i).setZip(zip);
				AddressBook.get(i).setPhone(phone);
				AddressBook.get(i).setCell(cell);
				AddressBook.get(i).setEmail(email);
				AddressBook.get(i).setDate(date);

				if(ConType == 'P'){
					contactType = "Personal Friend";
					//Birth Year validation
					System.out.print("\nPlease enter contact's NEW birth year:"); year=Integer.parseInt(sc.nextLine());
					while (year < 1901 || year > 3000) {
						System.out.println("Error, please enter contact's NEW birth year: ");
						System.out.print("\nPlease enter contact's NEW birth year:");year=Integer.parseInt(sc.nextLine());
					}
					//Birth month validation
					System.out.print("\nPlease enter contact's NEW birth month: 1-12");month = Integer.parseInt(sc.nextLine());
					while (month < 1 || month > 12) {
						System.out.println("Error, please enter contact's NEW birth month between 1-31");
						System.out.print("\nPlease enter contact's NEW birth month:");month=Integer.parseInt(sc.nextLine());
					}
					//Birth day validation
					System.out.print("\nPlease enter contact's NEW birth day:");day = Integer.parseInt(sc.nextLine());
					while (day < 1 || day > 31) {
						System.out.println("Error, please enter contact's NEW birth day between 1-31");
						System.out.print("\nPlease enter contact's NEW birth day:");day=Integer.parseInt(sc.nextLine());
					}
					//Age validation
					System.out.print("\nPlease enter contact's NEW age:");age = Integer.parseInt(sc.nextLine());
					while (age < 1 || age > 120) {
						System.out.println("Error, please enter contact's NEW age between 1-150");
						System.out.print("\nPlease enter contact's NEW age:");age=Integer.parseInt(sc.nextLine());
					}
					System.out.print("\nPlease enter contact's NEW Zodiac Sign:");zodiac=sc.nextLine();

					((PersonalFriend) AddressBook.get(i)).setBirthDate(year, month, day);
					((PersonalFriend) AddressBook.get(i)).setAge(age);
					((PersonalFriend) AddressBook.get(i)).setZodiac(zodiac);
				}


				else{
					contactType="Business Associate";
					//jobType validation ****This Needs Changing On All HW****
					System.out.print("\nPlease enter business associate's NEW job type: ");jobType=sc.nextLine();
					while (jobType.equals("")) {
						System.out.println("Error, Please enter business associate's NEW job type:");
						System.out.print("\nPlease enter business associate's NEW job type:");jobType=sc.nextLine();
					}
					//faxNumber validation ****This Needs Changing On All HW****
					do{
						try {
							System.out.print("\nPlease enter business associate's NEW fax number: ###-###-####");faxNumber=sc.nextLine();
							if (!faxNumber.matches("\\d{3}-\\d{3}-\\d{4}")) {
								throw new PhoneException("Error.  Business associate's NEW fax number must be  ###-###-####");
							}
						} catch (PhoneException ce) {
							System.out.println("Error.  Business associate's NEW fax number must be  ###-###-####");
						}}while (!faxNumber.matches("\\d{3}-\\d{3}-\\d{4}"));
					//compName validation ****This Needs Changing On All HW****
					System.out.print("\nPlease enter business associate's NEW company name: ");compName=sc.nextLine();
					while (compName.equals("")) {
						System.out.println("Error, Please enter business associate's NEW company name:");
						System.out.print("\nPlease enter business associate's NEW company name:");compName=sc.nextLine();
					}
					((BusinessAssociate) AddressBook.get(i)).setJT(jobType);
					((BusinessAssociate) AddressBook.get(i)).setFN(faxNumber);
					((BusinessAssociate) AddressBook.get(i)).setCompName(compName);
				}
			}
		}
		if (flag == false){System.out.println("No person by the name of "+fn+" was found in your address book");}
		System.out.println(junk);
	}

	public static void search(){
		System.out.println("Please enter the first name of the Person youd like to find: ");
		String firstn=sc.nextLine();
		System.out.println("Please enter the last name of the Person youd like to find: ");
		String ln=sc.nextLine();
		String fn= firstn+" "+ln;
		boolean flag=false;
		for(int i=0;i<AddressBook.size(); i++){
			if(AddressBook.get(i).getFirstName().equalsIgnoreCase(firstn)&&AddressBook.get(i).getLastName().equalsIgnoreCase(ln)){
				flag=true;
				System.out.println(AddressBook.get(i).toString());
				break;
			}
		}
		if (flag == false){System.out.println("No person by the name of "+fn+" was found in your address book");}
		System.out.println(junk);
	}

	public static void listAll(){
		if(AddressBook.size() == 0){System.out.println("Nobody in Saved to Address Book!");}
		else{
			Collections.sort(AddressBook);
			for(int i =0; i<AddressBook.size();i++){System.out.println(AddressBook.get(i).toString());}
			System.out.print("\nNumber of Friends: "+friendcount+"\tNumber of Business Associates: "+businesscount);
			System.out.println("\nTotal Contacts: "+(friendcount+businesscount));
			System.out.println(junk);
		}
	}

	public static void download() throws IOException{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(/*location + "\\" +  text +*/ "People.dat"));
		out.writeObject(AddressBook);
		System.out.println("File saved\n");
		out.close();
	}

	@SuppressWarnings("unchecked")
	public static void upload() throws Exception{
	
		ObjectInputStream fileInput = new ObjectInputStream(new FileInputStream(/*text*/"People.dat"));
		AddressBook = (ArrayList<Person>)fileInput.readObject();
		System.out.println("File Uploaded!");
		fileInput.close();
	}

	public static void printAll() throws IOException{
		PrintWriter out = new PrintWriter("AddressBook.txt");
		for (Person p : AddressBook) {
			out.println(p);
		}	
		System.out.println("Address book successfully printed\n");
		out.close(); 
	}
}
@SuppressWarnings("serial")
class PhoneException extends Exception {
	public PhoneException(String message) {
		super(message);
	}
}

@SuppressWarnings("serial")
class EmailException extends Exception {
	public EmailException(String message) {
		super(message);
	}
}
