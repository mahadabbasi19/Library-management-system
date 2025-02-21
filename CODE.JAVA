import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class Book {
    private int id;
    private String title;
    private String author;
    private int publicationYear;

    public Book(int id, String title, String author, int publicationYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    @Override
    public String toString() {
        return "Book ID: " + id + ", Title: " + title + ", Author: " + author + ", Publication Year: " + publicationYear;
    }
}

class Borrower {
    private int id;
    private String name;
    private String address;
    private String contact;

    public Borrower(int id, String name, String address, String contact) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }

    @Override
    public String toString() {
        return "Borrower ID: " + id + ", Name: " + name + ", Address: " + address + ", Contact: " + contact;
    }
}

class Transaction {
    private Book book;
    private Borrower borrower;
    private String dueDate;

    public Transaction(Book book, Borrower borrower, String dueDate) {
        this.book = book;
        this.borrower = borrower;
        this.dueDate = dueDate;
    }

    public Book getBook() {
        return book;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public String getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "Book: " + book.getTitle() + ", Borrower: " + borrower.getName() + ", Due Date: " + dueDate;
    }
}

class Library {
    private List<Book> books;
    private List<Borrower> borrowers;
    private List<Transaction> transactions;

    public Library() {
        books = new ArrayList<>();
        borrowers = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully.");
    }

    public void removeBook(int bookId) {
        boolean found = false;
        for (Book book : books) {
            if (book.getId() == bookId) {
                books.remove(book);
                found = true;
                break;
            }
        }
        if (found) {
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void addBorrower(Borrower borrower) {
        borrowers.add(borrower);
        System.out.println("Borrower added successfully.");
    }

    public void removeBorrower(int borrowerId) {
        boolean found = false;
        for (Borrower borrower : borrowers) {
            if (borrower.getId() == borrowerId) {
                borrowers.remove(borrower);
                found = true;
                break;
            }
        }
        if (found) {
            System.out.println("Borrower removed successfully.");
        } else {
            System.out.println("Borrower not found.");
        }
    }

    public void borrowBook(int bookId, int borrowerId, String dueDate) {
        Book book = getBookById(bookId);
        Borrower borrower = getBorrowerById(borrowerId);
        if (book != null && borrower != null) {
            Transaction transaction = new Transaction(book, borrower, dueDate);
            transactions.add(transaction);
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Book or borrower not found.");
        }
    }

    public void returnBook(int bookId, int borrowerId) {
        boolean found = false;
        for (Transaction transaction : transactions) {
            if (transaction.getBook().getId() == bookId && transaction.getBorrower().getId() == borrowerId) {
                transactions.remove(transaction);
                found = true;
                break;
            }
        }
        if (found) {
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Transaction not found.");
        }
    }

    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            System.out.println("Books:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public void displayAllBorrowers() {
        if (borrowers.isEmpty()) {
            System.out.println("No borrowers available.");
        } else {
            System.out.println("Borrowers:");
            for (Borrower borrower : borrowers) {
                System.out.println(borrower);
            }
        }
    }

    public void displayAllTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
        } else {
            System.out.println("Transactions:");
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }

    private Book getBookById(int bookId) {
        for (Book book : books) {
            if (book.getId() == bookId) {
                return book;
            }
        }
        return null;
    }

    private Borrower getBorrowerById(int borrowerId) {
        for (Borrower borrower : borrowers) {
            if (borrower.getId() == borrowerId) {
                return borrower;
            }
        }
        return null;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Add Borrower");
            System.out.println("4. Remove Borrower");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Display All Books");
            System.out.println("8. Display All Borrowers");
            System.out.println("9. Display All Transactions");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice. Please enter a number.");
                scanner.nextLine(); // Consume invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int bookId;
                    try {
                        bookId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Book ID should be an integer.");
                        scanner.nextLine(); // Consume invalid input
                        continue;
                    }
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Publication Year: ");
                    int publicationYear;
                    try {
                        publicationYear = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Publication Year should be an integer.");
                        scanner.nextLine(); // Consume invalid input
                        continue;
                    }
                    Book newBook = new Book(bookId, title, author, publicationYear);
                    library.addBook(newBook);
                    break;
                case 2:
                    System.out.print("Enter Book ID: ");
                    try {
                        bookId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Book ID should be an integer.");
                        scanner.nextLine(); // Consume invalid input
                        continue;
                    }
                    library.removeBook(bookId);
                    break;
                case 3:
                    System.out.print("Enter Borrower ID: ");
                    int borrowerId;
                    try {
                        borrowerId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Borrower ID should be an integer.");
                        scanner.nextLine(); // Consume invalid input
                        continue;
                    }
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter Contact: ");
                    String contact = scanner.nextLine();
                    Borrower newBorrower = new Borrower(borrowerId, name, address, contact);
                    library.addBorrower(newBorrower);
                    break;
                case 4:
                    System.out.print("Enter Borrower ID: ");
                    try {
                        borrowerId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Borrower ID should be an integer.");
                        scanner.nextLine(); // Consume invalid input
                        continue;
                    }
                    library.removeBorrower(borrowerId);
                    break;
                case 5:
                    System.out.print("Enter Book ID: ");
                    try {
                        bookId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Book ID should be an integer.");
                        scanner.nextLine(); // Consume invalid input
                        continue;
                    }
                    System.out.print("Enter Borrower ID: ");
                    try {
                        borrowerId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Borrower ID should be an integer.");
                        scanner.nextLine(); // Consume invalid input
                        continue;
                    }
                    System.out.print("Enter Due Date: ");
                    String dueDate = scanner.nextLine();
                    library.borrowBook(bookId, borrowerId, dueDate);
                    break;
                case 6:
                    System.out.print("Enter Book ID: ");
                    try {
                        bookId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Book ID should be an integer.");
                        scanner.nextLine(); // Consume invalid input
                        continue;
                    }
                    System.out.print("Enter Borrower ID: ");
                    try {
                        borrowerId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Borrower ID should be an integer.");
                        scanner.nextLine(); // Consume invalid input
                        continue;
                    }
                    library.returnBook(bookId, borrowerId);
                    break;
                case 7:
                    library.displayAllBooks();
                    break;
                case 8:
                    library.displayAllBorrowers();
                    break;
                case 9:
                    library.displayAllTransactions();
                    break;
                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number from the menu.");
            }
        }
    }
}