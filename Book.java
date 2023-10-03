public class Book {
    private String title;
    private String author;
    private int publicationYear;
    private float rating;
    private long isbn;
    private String status;
    private Reader[] readersHistory;
    private int[] ratingsHistory;

    public void setTitle(String title) {
        if(title.length() >= 2 && title != null) {
            this.title = title;
        }
    }

    public void setAuthor(String author) {
        if(author.length() >= 5 && author != null) {
            this.author = author;
        }
    }
    
    public void setPublicationYear(int publicationYear) {
        if(publicationYear >= 1700 && publicationYear <= 2023) {
            this.publicationYear = publicationYear;
        }
    }

    public void setIsbn (long isbn) {
        if(this.getPublicationYear() < 2007) { //If the year is less than 2007
            if(isbn >= 1000000000 && isbn <= 9.9e9) { //Check for a 10 digit isbn
                this.isbn = isbn;
            }
        }
        else { //If the year is newer than 2007 or 2007
            if(isbn >= 1.0e12 && isbn <= 9.9e12) { //Check for a 13 digit number
                this.isbn = isbn;
            }
        }
    }

    public void setStatus(String status) {
        if(getStatus() == null) { //This is needed for when the book is first created, as status will be null.
            this.status = status;
        }
        
        if(status.equals("Available")) { //Want to set status to avaiable
            if(getStatus().equals("Rented")) { // Make sure the book is only rented, not archived
                this.status = status; //If the book is rented, make it available.
            }
        }
        if(status.equals("Rented")) { //If you want to set status to rented
            if(getStatus().equals("Available")) { //Check to see if the book is available
                this.status = status; //If the books is available, rent it.
            }
        }
        if(status.equals("Archived")) { //If you want to set status to archived
            if(getStatus().equals("Available")) { //You need to make sure it is available, and not rented
                this.status = status;//If it is available, set it to archvied.
            }
        }

    }


    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getPublicationYear() {
        return this.publicationYear;
    }

    public float getRating() {

        if(this.ratingsHistory == null) {
            return 0.0f; //If there are no ratings in the system, return 0.0, not null
        }
        float total = 0.0f; //A variable used to hold the total of all the ratings
        float count = 0.0f; //A variable to hold the number of ratings
        for(int i = 0; i < this.ratingsHistory.length; i++) {
            if(this.ratingsHistory[i] > 0 && this.ratingsHistory[i] <= 5) { //Checks that the rating is within 1-5
                total += this.ratingsHistory[i];
                count++;
            }
        }

        this.rating = total / count;
        return this.rating;
    }

    public long getIsbn() {
        return this.isbn;
    }

    public String getStatus() {
        return this.status;
    }

    public Book(String title, String author, int publicationYear, long isbn) { //Done
        setTitle(title); //Sets the title of the book as given
        setAuthor(author); //Sets the author of the book as given
        setPublicationYear(publicationYear); //Sets the publication year
        setIsbn(isbn); //Sets the isbn
        setStatus("Available"); //Sets the status to available upon construction
        this.readersHistory = null; //Sets the readers history of this book to null
        this.ratingsHistory = null; //Does the same for ratings.
    }

    public boolean archive() { //Done
        setStatus("Archived"); //Use set method to attempt to set the book to archived
        if(getStatus().equals("Archived")) { //Check to see if it worked
            return true;
        }
        return false;
    }

    public boolean rent(Reader reader) {
        if(this.getStatus().equals("Available")) { //Check to see if book is available
            if(this.readersHistory == null) { //If this is the first time someone is renting this book
                this.readersHistory = new Reader[0]; //Set the length to zero so that 1 can be added to it.
            }
            Reader[] readersHistoryAddTo = new Reader[this.readersHistory.length + 1]; //If so, create a temp array to add the reader to first.
            for (int i = 0; i < this.readersHistory.length;i++) { //A loop that goes through every element of the old readersHistory array-
                readersHistoryAddTo[i] = this.readersHistory[i]; // (Cont) but duplicates it with 1 more spot at the end for the new reader.
            } 
            readersHistoryAddTo[this.readersHistory.length] = reader; //Make the reader given the last slot in the array
            this.readersHistory = readersHistoryAddTo; //Swap the temp array in for the old readersHistory
            this.setStatus("Rented"); //Set the book status to rented
            return true;
        }
        return false;
    }

    public Reader getLastReader() {
        if(this.readersHistory.length > 0) {
            return this.readersHistory[readersHistory.length - 1];
        }
        return null;
    }

    public boolean addRating(Reader reader, int rating) {
        if(this.readersHistory == null) { //If there are no readers (nobody to rate), return false.
            return false;
        }
        for(Reader checker : this.readersHistory) { //Checks all items in readersHistory
            if (checker == reader) {
                //If our reader given matches the checker,they are in the the list; proceed to rating.
                if (this.ratingsHistory == null) {
                    this.ratingsHistory = new int[0];
                }
                int[] tempRatings = new int[this.ratingsHistory.length + 1];
                for(int i = 0; i < this.ratingsHistory.length; i++) {
                    tempRatings[i] = this.ratingsHistory[i]; //Copies the array, leaving one extra slot at the end.
                }
                tempRatings[this.ratingsHistory.length] = rating; //Sets last spot as the rating.
                this.ratingsHistory = tempRatings; //Swaps in the temp for the actual ratings history.
                
                getRating();
                return true;

            }
        }
        return false;
    }

    public String toString() {
        return "\"" + this.getTitle() + "\" by " + this.getAuthor() + " (" + this.getPublicationYear() + ") -- Rating:" + this.getRating() +  " -- " + this.getStatus();
    }




}
