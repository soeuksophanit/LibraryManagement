import java.util.Locale;
import java.util.Scanner;

public class Functionalities {
    public void display(Book book){
        String sign_available = book.isAvailable() ? "Available" : "Unavailable";
        System.out.println(book.getId()+" "+ book.getTitle() + " "+
                book.getPublish_year()+ " "+
                sign_available + " "
                + book.author.getAuthor_name()
        + " " + book.author.getActive_year()
        );
    }

    public void showErrorValidate(){
        System.out.println("\n---------------------------------------");
        System.out.println("Please enter again the correct input form!!!");
        System.out.println("Accept only character and number (A-Z,0-9) !!");
        System.out.println("\n---------------------------------------");
    }

    public void showMsg(String msg){
        System.out.println("\n---------------------------------------");
        System.out.println(msg);
        System.out.println("---------------------------------------\n");

    }

    public void inputInformation(Book book,int id){
        Library lib = new Library();
        String title,publish_year,author_name,active_year;
        System.out.println("=> Book ID : "+(id));
        Scanner sc = new Scanner(System.in);
        System.out.print("=> Enter title : ");
        title = sc.nextLine();
        System.out.print("=> Enter author name : ");
        author_name = sc.nextLine();
        System.out.print("=> Enter Author year Active : ");
        active_year = sc.nextLine();
        System.out.print("=> Enter publish year : ");
        publish_year = sc.nextLine();
        if(!lib.library_validate(title,author_name) || !lib.library_validate(active_year,publish_year)){
            showErrorValidate();
            inputInformation(book,id);
        }
        setInformation(book, id,title,publish_year,author_name,active_year);
        showMsg("Book added successfully");
    }

    public void showOptions(Library library){
        System.out.println("\n========== "+library.getLibrary_name().toUpperCase(Locale.ROOT)+" , "+library.getLibrary_address().toUpperCase(Locale.ROOT)+" ==========");
        System.out.println("1 - Add Book.");
        System.out.println("1 - Show All Books.");
        System.out.println("3 - Show Available Books.");
        System.out.println("4 - Borrow Book.");
        System.out.println("5 - Return Book.");
        System.out.println("6 - Exit.");
        System.out.println("------------------------------------------------");
    }

    public void setInformation(Book book,int id ,String title,String publish_year,String author_name,String active_year){
        book.setId(id);
        book.setTitle(title);
        book.setPublish_year(publish_year);
        book.author.setAuthor_name(author_name);
        book.author.setActive_year(active_year);
        book.setAvailable(true);
    }

    public boolean exit_library(){
        System.out.print("=> Do you wnt to continue or exit ? [Y/N] : ");
        String answer = new Scanner(System.in).nextLine();
        return answer.equalsIgnoreCase("Y") ? false : true;
    }

    public void showAvailableBook(Book[] books){
        int count = 0;
        for (Book book :books){
            if (book == null){
                count++;
            }else {
                display(book);
            }
        }

        if (count==books.length){
            showMsg("----- No Books Available right now -----");
        }

    }

    public void borrow_book(Book[] books,int allID){
        System.out.print("=> Enter book's ID to borrow : ");
        String book_id = new Scanner(System.in).nextLine();
        if (!(new Library().regex_validate(book_id,"[0-9]+"))){
            showMsg("---- Invalid Book's ID.Try Again!! ----");
            borrow_book(books,allID);
        }else {
            for (Book book:books){
                if (Integer.parseInt(book_id)<=allID){
                    if (book.getId()==Integer.parseInt(book_id)){
                        book.display(book);
                        System.out.print("=> Do you want to borrow this book ? [Y/N] : ");
                        String answer = new Scanner(System.in).nextLine();
                        if (answer.equalsIgnoreCase("Y")){
                            book.setAvailable(false);
                            showMsg("--- Book ID "+ book_id+ " was borrow successfully ---");
                        }
                        break;
                    }
                }else {
                    showMsg("----- Book ID "+ book_id + " was not found!! ----");
                    break;
                }

            }
        }
    }
}
