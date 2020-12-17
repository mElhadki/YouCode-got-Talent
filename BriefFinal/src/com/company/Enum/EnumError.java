package com.company.Enum;

public enum EnumError {

	IDUSERSHORT("the id is too short\n"), 
	FIRSTNAMESHORT("The first name is too short\n"),
	LASTNAMESHORT("The Last name is too short\n"),
	EMAILSHORT("the email is too short\n"),
	EMAILFORMAT("email format invalid\n"),
	PHONESHORT("Phone Number too short\n"),
	PHONEFORMAT("phone format invalid\n"), 
	ADDEDUSER("You are added"), 
	IDUSERCHECK("the id already exists\n"),
	IDUSEREXIST("The id doesn't exist"), 
	UPDATEUSER("You are updated"), 
	ADDPARTICIPATION("Your participation added");

	private EnumError(String message) {
		this.message = message;
	}

	public final String message;

	public String getMessage() {
		return message;
	}

	public String toString() {
		return message;
	}

}
