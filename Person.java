package Homework7;

//Powell Hamner
//11/26/2013
//CS 142
//Purpose: This application will save a person to an address book. You will
//be able to add a person, delete a person, modify an entry, search for a person and list all of
//the persons in your address book. You can choose to enter either a Personal Friend, or a Business Associate. You can 
//download the file to disk and upload it back into a fresh run of the program. You can also print your address book to a 
//text document. 
//PERSON CLASS
import java.util.*;

public abstract class Person implements Comparable<Person>, java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstname;
	private String lastname;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phone;
	private Date date;
	private String cell;
	private String email;
	private String contactType;
	
	protected Person(){}
	
	public Person(String firstname, String lastname, String address,	String city, String state, String zip, String phone, Date date
			,String cell, String email, String contactType) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address=address;
		this.city=city;
		this.state=state;
		this.zip=zip;
		this.phone=phone;
		this.date=date;
		this.cell=cell;
		this.email=email;
		this.contactType = contactType;
	}
	//GETTERS
	public String getFirstName(){return this.firstname;}
	public String getLastName(){return this.lastname;}
	public String getAddress(){return this.address;}
	public String getCity(){return this.city;}
	public String getState(){return this.state;}
	public String getZip(){return this.zip;}
	public String getPhone(){return this.phone;}
	public Date get_date(){return this.date;}
	public String getCell(){return this.cell;}
	public String getEmail(){return this.email;}
	public String getContactType(){return this.contactType;}

	//SETTERS
	public void setFirstName(String firstname){this.firstname=firstname;}
	public void setLastName(String lastname){this.lastname=lastname;}
	public void setAddress(String address){this.address=address;}
	public void setCity(String city){this.city=city;}
	public void setState(String state){this.state=state;}
	public void setZip(String zip){this.zip=zip;}
	public void setPhone(String phone){this.phone=phone;}
	public void setDate(Date date){this.date=date;}
	public void setCell(String cell){this.cell=cell;}
	public void setEmail(String email){this.email=email;}
	public void setContactType(String contactType){this.contactType = contactType;}
	
	//Address Interface
	public interface AddressInterface{
		String getAddress();
		String getCity();
		String getState();
		String getZip();		
	}
	
	public String FullName(String firstname, String lastname){
		String FullName = firstname + " " + lastname;
		return FullName;
		
	}
	
	//ToString
	public String toString(){
		String addressString="\nContact Type:"+ getContactType() + "\nLast Modified: " + get_date() +
				"\nName: "+ FullName(firstname, lastname) + "\nAddress: " + getAddress() + " " + getCity()+ ", " + getState() +  " " + getZip()
				+"\nPhone Number: " + getPhone();
		return addressString;
	}
	//compareTo method
	@Override
	public int compareTo(Person o) {
		int result = -1;
		if(this.firstname.equalsIgnoreCase(o.firstname) && this.lastname.equalsIgnoreCase(o.lastname)){
			result=0;}
		return result;
	}
}
