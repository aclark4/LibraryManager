public class Reader {
    private String fullName;
    private int yearOfBirth;
    private int zipCode;

    public void setFullName(String fullName) {
        if(fullName.length() <= 20 && fullName.length() >= 3) { //Makes sure the length of name requirement is met
            this.fullName = fullName;
        }
    }
    public void setYearOfBirth(int yearOfBirth) { 
        if(yearOfBirth >= 1923 && yearOfBirth <= 2013) { // Makes sure the yearOfBirth is in the right range
            this.yearOfBirth = yearOfBirth;
        }
    }
    public void setZipCode(int zipCode) {
        if(zipCode >= 10000 && zipCode <= 99999) { //Makes sure the zipCode given is 5 digits
            this.zipCode = zipCode;
        }
    }

    public String getFullName() {
        return this.fullName;
    }
    public int getYearOfBirth() {
        return this.yearOfBirth;
    }
    public int getZipCode() {
        return this.zipCode;
    }

    public Reader (String fullName, int yearOfBirth, int zipCode) { //Constructs a Reader using the proper arguments
        setFullName(fullName);
        setYearOfBirth(yearOfBirth);
        setZipCode(zipCode);

    }

}
