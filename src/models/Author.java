package models;

import java.util.Locale;

public class Author {

    private String firstName;
    private String lastName;
    private int yearOfBirth;


    public Author(String firstName, String lastName, int yearOfBirth) {
        this.firstName = firstName.toLowerCase();
        this.lastName = lastName.toLowerCase();
        this.yearOfBirth = yearOfBirth;
    }

    public String getFirstNameFormatted() {
        return ((char) (this.firstName.codePointAt(0) - 32)  + this.firstName.substring(1));
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLastNameFormatted() {
        return ((char) (this.lastName.codePointAt(0) - 32)  + this.lastName.substring(1));
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getAuthorFullName(){
        return String.format("%s %s", this.getFirstNameFormatted(), this.getLastNameFormatted());
    }



    @Override
    public String toString(){
        return String.format("AUTHOR: %s %s, born in %d", this.firstName, this.lastName, this.yearOfBirth);
    }

}
