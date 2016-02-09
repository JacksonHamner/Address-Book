package Homework7;

//Powell Hamner
//11/26/2013
//CS 142
//Purpose: This application will save a person to an address book. You will
//be able to add a person, delete a person, modify an entry, search for a person and list all of
//the persons in your address book. You can choose to enter either a Personal Friend, or a Business Associate. You can 
//download the file to disk and upload it back into a fresh run of the program. You can also print your address book to a 
//text document. 
import java.text.*;
import java.util.*;
public class PersonalFriend extends Person implements java.io.Serializable{
	private Date bd;
	private int age;
	private String zodiac;
	static SimpleDateFormat sdf = new SimpleDateFormat("MMM. dd, yyyy");
	
	//CONSTRUCTOR
	public PersonalFriend(String firstname, String lastname, String address, String city, String state, String zip, String phone, 
			Date date, String cell, String email, String contactType, int age, String zodiac, int year, int month, int day){
		super(firstname,lastname, address, city, state, zip, phone, date, cell, email, contactType);
		this.age=age;
		this.zodiac=zodiac;		
		this.setBirthDate(year, month, day);
	}
	public PersonalFriend(){super();}
	//GETTERS
	public String getZodiac(){return this.zodiac;}
	public int getAge(){return this.age;}
	public Date getBirthDate(){return this.bd;}
	//SETTERS
	public void setZodiac(String zodiac){this.zodiac=zodiac;}
	public void setAge(int age){this.age=age;}
	public void setBirthDate(int year, int month, int day) {
	    GregorianCalendar cal = new GregorianCalendar(year, month - 1, day);
		bd = cal.getTime(); 
	}
	
	public String toString(){
		return super.toString()+
				"\nBirthDate: " + sdf.format(getBirthDate()) +
				"\nAge: " + getAge() + 
				"\nZodiac Sign: " + getZodiac();
	}
}

