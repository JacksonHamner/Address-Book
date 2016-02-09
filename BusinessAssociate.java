package Homework7;

//Powell Hamner
//11/26/2013
//CS 142
//Purpose: This application will save a person to an address book. You will
//be able to add a person, delete a person, modify an entry, search for a person and list all of
//the persons in your address book. You can choose to enter either a Personal Friend, or a Business Associate. You can 
//download the file to disk and upload it back into a fresh run of the program. You can also print your address book to a 
//text document. 
import java.io.Serializable;
import java.util.Date;
public class BusinessAssociate extends Person implements Serializable {
	private String jt;//Job Title
	private String fn;//Fax Number
	private String compName;//Company Name
	//Constructor
	public BusinessAssociate(String firstname, String lastname,String address, String city, String state, String zip, String phone,
			Date date, String cell, String email, String contactType, String jobType, String faxNumber, String compName){
		super(firstname,lastname, address, city, state, zip, phone, date, cell, email, contactType);
		this.jt=jobType;
		this.fn=faxNumber;
		this.compName=compName;
	}
	public BusinessAssociate() { super();}
	//Getters
	public String getJT(){return this.jt;}
	public String getFN(){return this.fn;}
	public String getCompName(){return this.compName;}
	//Setters
	public void setJT(String jobType){this.jt=jobType;}
	public void setFN(String faxNumber){this.fn=faxNumber;}
	public void setCompName(String compName){this.compName=compName;}
	//ToString
	public String toString(){
		return super.toString()+
				"\nJob Type: " + this.jt +
				"\nFax Number: " + this.fn + 
				"\nCompany name: " + this.compName;
	}
}
