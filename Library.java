public class Library {
    private String name;
    private String address;
    private Book[] books;

    public void setName(String name) {
        if(name.length() >= 9 && name.length() <= 20) { //Makes sure the name fits validation
            this.name = name;
        }
    }
    public void setAddress(String address) {
        if(address.length() >= 10 && address.length() <= 25) { //Makes sure the name is a valid size
            this.address = address;
        }
    }

    public String getName(){
        return this.name;
    }
    public String getAddress(){
        return this.address;
    }

    public Library(String name, String address) {
        setName(name);
        setAddress(address);
    }

    public Book[] getAvailableBooks(){
        return this.books;
    }

    public boolean addBook(Book book) {

        if(book.getStatus().equals("Available")) { //Checks if the book given is available
            if(this.books == null) { //If this is the first time someone is adding a book
                this.books = new Book[0]; //Set the length to zero so that 1 can be added to it.
            }
            for (Book bookCheck : books) { //A loop to check to see if the book is already in the array first
                if(bookCheck.equals(book)) { 
                    //This checks if the object in the existing array matches the given object
                    return false;
                }
            }
            Book[] booksTemp = new Book[this.books.length + 1]; //create a temp array to add the book to first.
            for (int i = 0; i < this.books.length;i++) { //A loop that goes through every element of the old books array-
                booksTemp[i] = books[i]; // (Cont) but duplicates it with 1 more spot at the end for the new book.
            } 
            booksTemp[this.books.length] = book; //Make the book given the last slot in the array
            this.books = booksTemp; //Swap the temp array in for the old books
            return true;
        }
        
        return false;
    }

    public boolean removeBook(Book book) {
        if(books == null) { //If there are no books, return false.
            return false;
        }
        if(book.getStatus().equals("Archived")) { //Checks to make sure the book given is archvied
            Book[] booksTempRemove = new Book[this.books.length - 1]; //create a temp array to add the book to first.
            int indexOfNewArray = 0;
            for (int i = 0; i < this.books.length;i++) { //A loop that goes through every element of the old books array-
                if(books[i].equals(book)) { // (Cont) but duplicates it with 1 less spot, allowing for removal of a book.
                    continue;//This will skip over the following step that allows for the array to be copied, essentially removing this item.
                } 
                booksTempRemove[indexOfNewArray] = books[i]; //This will add objects that are not the one that should be removed back to the array.
                indexOfNewArray++;
            } 
            this.books = booksTempRemove;
            return true;
        }

        return false;
    }

    public boolean returnBook(Book book, int rating) {//NEED TO DO STILL
        if(book.getStatus().equals("Rented")) { //If the book is rented,
            book.addRating(book.getLastReader(), rating); //Add the rating to the book.
            book.setStatus("Available"); //Set the book back to Available
            return true;
        }
        return false;
    }

    public String toString() { //Done
        if(this.getAvailableBooks() == null) {
            return "Library " + this.getName() + " has 0 book available"; //if getavailablebooks is null, then there are 0 books!
        }
        if(this.getAvailableBooks().length == 1) {
            return "Library " + this.getName() + " has " + this.getAvailableBooks().length + " book available"; //The amount of books is 1, singular
        } 
        return "Library " + this.getName() + " has " + this.getAvailableBooks().length + " books available"; //If it isnt 1 book, then it's plural books
    }

    public String displayBooks(){ //Done
        String value = "List of books:\n"; //Starting sequence before loop of books

        for (Book bookString : books) { //Loop through all of the books in array books
            value += "\t" + bookString.toString() + "\n"; //In each loop, write each book as a string, with added tab and new line.
        }
        return value;
    }

    public boolean rentBook(int i, Reader reader) {
        if (reader == null) { //Reader must not be null.
            return false; 
        }
        i--; //This sets the input i int to correlate with the index of the array of books that we have.
        return this.books[i].rent(reader); //find the book at i, then use the rent method on that book with the reader given.
    }
}