import java.util.Locale;
import java.util.Scanner;

public class Functionalities {
    public void display(Book book){
        System.out.println(book.getId()+" "+ book.getTitle() + " "+
                book.getPublish_year()+ " "+
                book.isAvailable() + " "
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
}
