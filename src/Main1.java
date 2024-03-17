import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main1 {
    public static void main(String[] args) {
        int x = 1;
        Scanner scanner = new Scanner(System.in);
        BookList bookList = new BookList();

        System.out.println("Welcome to the book program!\n");

        while (true) {
           if (x == 1) {
               System.out.print("Would you like to create a book object? (yes/no): ");
           }
            String createBook = scanner.next().toLowerCase();


            if (createBook.equals("yes")) {
                Book book = createBook(scanner);
                if (book != null) {
                    bookList.addBook(book);
                }
            } else if (createBook.equals("no")) {
                System.out.println("Sure!");
                System.out.println("\nHere are all your books...\n");
                bookList.displayBooks();
                break;
            } else {
                System.out.print("I’m sorry, but that isn’t a valid answer. Please enter either yes or no: ");
                x = 0;
            }
            x = 1;
        }

        System.out.println("Take care now!");

    }



    public static Book createBook(Scanner scanner) {

        System.out.print("Please enter the author, title and the isbn of the book separated by /: ");
        String[] bookInfo = scanner.next().split("/");

        while (true) {
            if (bookInfo.length != 3) {
                System.out.print("Invalid input. Please enter the author, title, and isbn separated by /: ");
                bookInfo = scanner.next().split("/");
            } else {
                break;
            }
        }
        String author = bookInfo[0];
        String title = bookInfo[1];
        String isbn = bookInfo[2];

        System.out.print("Now, tell me if it is a bookstore book or a library book (enter BB for bookstore book or LB for library book): ");
        String bookType = scanner.next().toLowerCase();
while(true) {
    if (!bookType.equals("bb") && !bookType.equals("lb")) {

        System.out.print("Invalid input, Tell me if it is a bookstore book or a library book (enter BB for bookstore book or LB for library book): ");
        bookType = scanner.next().toLowerCase();

    } else {
        break;
    }
}

        if (bookType.equals("bb")) {

            double price = 0;
            boolean onSale = false;
            int reductionPercentage = 0;
            int y = 1;
            while (true) {
                try {
                    if(y == 1){
                    System.out.print("Please enter the list price of " + title + " by " + author + ": ");}
                    price = scanner.nextDouble();
                    break;
                }
                catch(java.util.InputMismatchException e){
                    System.out.print("I’m sorry, but that isn’t a valid answer. Please try again: ");
                    scanner.nextLine();
                    y = 0;
                }
            }
            System.out.print("Is it on sale? (y/n): ");
            String saleStatus = scanner.next().toLowerCase();
            while(!saleStatus.equals("y") && !saleStatus.equals("n")){
                System.out.print("I’m sorry, but that isn’t a valid answer. Please enter either y or n: ");
                saleStatus = scanner.next().toLowerCase();
            }
            if (saleStatus.equals("y") ) {
                y = 1;
                while (true) {
                    try {
                        if (y == 1) {
                            System.out.print("Deduction percentage: ");
                        }
                        reductionPercentage = scanner.nextInt();
                        onSale = true;
                        break;
                    }
                    catch (java.util.InputMismatchException e){
                        System.out.print("I’m sorry, but that isn’t a valid answer. Please try again: ");
                        scanner.nextLine();
                        y = 0;
                        onSale = true;
                    }
                }
            }

            return new BookstoreBook(author, title, isbn, price, onSale, reductionPercentage);
        } else if (bookType.equals("lb")) {
            return new LibraryBook(author, title, isbn);
        } else {
            return null;
        }

    }
}
abstract class Book {
    protected String author;
    protected String title;
    protected String isbn;

    public Book(String author, String title, String isbn) {
        this.author = author;
        this.title = title;
        this.isbn = isbn;
    }

    public abstract String toString();
}

class BookstoreBook extends
        Book {
    private double price;
    private boolean onSale;
    private int reductionPercentage;

    public BookstoreBook(String author, String title, String isbn, double price, boolean onSale, int reductionPercentage) {
        super(author, title, isbn);
        this.price = price;
        this.onSale = onSale;
        this.reductionPercentage = reductionPercentage;
    }

    public BookstoreBook(String author, String title, String isbn) {
        this(author, title, isbn, 0, false, 0);
    }

    public BookstoreBook() {
        this("", "", "", 0, false, 0);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }

    public void setReductionPercentage(int reductionPercentage) {
        this.reductionPercentage = reductionPercentage;
    }

    public double getPrice() {
        return price;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public int getReductionPercentage() {
        return reductionPercentage;
    }

    @Override
    public String toString() {
        if (onSale) {
            double discountedPrice = price * (1 - (reductionPercentage / 100.0));
            return String.format("[%s-%s by %s, $%.2f listed for $%.2f]", isbn, title, author, price, discountedPrice);
        } else {
            return String.format("[%s-%s by %s, $%.2f]", isbn, title, author, price);
        }
    }
}

class LibraryBook extends Book {
    private String callNumber;

    public LibraryBook(String author, String title, String isbn) {
        super(author, title, isbn);
        this.callNumber = generateCallNumber(author, isbn);
    }

    private String generateCallNumber(String author, String isbn) {
        Random rand = new Random();
        int floorNumber = rand.nextInt(99) + 1;
        String authorInitials = author.length() >= 3 ? author.substring(0, 3) : author;
        char lastIsbnChar = isbn.charAt(isbn.length() - 1);

        return String.format("%02d.%s.%c", floorNumber, authorInitials, lastIsbnChar);
    }

    @Override
    public String toString() {
        return String.format("[%s-%s by %s-%s]", isbn, title, author, callNumber);
    }
}

class BookList {
    private Book[] list;
    private int bookCount;

    public BookList() {
        list = new Book[100];
        bookCount = 0;
    }

    public void addBook(Book book) {
        if (bookCount < list.length) {
            list[bookCount] = book;
            bookCount++;
        } else {
            System.out.println("Book list is full, cannot add more books.");
        }
    }

    public void displayBooks() {
        System.out.println("Library Books (" + countLibraryBooks() + ")\n");
        for (Book book : list) {
            if (book instanceof LibraryBook) {
                System.out.println(book);
            }
        }

        System.out.println("_ _ _ _\n");
        System.out.println("Bookstore Books (" + countBookstoreBooks() + ")\n");
        for (Book book : list) {
            if (book instanceof BookstoreBook) {
                System.out.println(book);
            }
        }

        System.out.println("_ _ _ _\n");
    }

    public int countLibraryBooks() {
        int count = 0;
        for (Book book : list) {
            if (book instanceof LibraryBook) {
                count++;
            }
        }
        return count;
    }

    public int countBookstoreBooks() {
        int count = 0;
        for (Book book : list) {
            if (book instanceof BookstoreBook) {
                count++;
            }
        }
        return count;
    }
}
