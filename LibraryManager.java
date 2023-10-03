public class LibraryManager {
    //Test your code
    public static void main(String[] args) {
        { //Creating Scope for Test 1
            Library myLibrary = new Library("La Pleiade", "1234, Abc Street, Fairfax");
            Book book1 = new Book("Harry Potter", "J.K. Rowling", 1997, 1000000000);
            System.out.println(myLibrary);
            myLibrary.addBook(book1);
            System.out.println(myLibrary);
            System.out.println(myLibrary.displayBooks());
        }

        {
            Book book1 = new Book("Harry Potter", "J. Rowling", 1997, 1000000000);
            book1.setAuthor("J.K. Rowling");
            System.out.println(book1.toString());
        }
    }
}