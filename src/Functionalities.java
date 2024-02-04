import java.util.Locale;
import java.util.Scanner;
import org.nocrala.tools.texttablefmt.Table;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.CellStyle.HorizontalAlign;

public class Functionalities {

    public void header(int numberColumns,String[] header_title){
        CellStyle numberStyle = new CellStyle(HorizontalAlign.center);

        Table t = new Table(numberColumns, BorderStyle.UNICODE_ROUND_BOX,
                ShownBorders.SURROUND_HEADER_FOOTER_AND_COLUMNS);

        for (int i =0;i<numberColumns;i++)
            t.setColumnWidth(i, 30, 70);

        for (String each_header:header_title)
            t.addCell(each_header,numberStyle);

        System.out.println(t.render());
    }
    public void display(Book book) {
        setTable(5,book);
    }

    void setTable(int numberColumns,Book book){

        String signAvailable = book.isAvailable() ? ReuseThings.greenColorCode+"Available"+ReuseThings.resetColorGreen : ReuseThings.redColorCode+"Unavailable"+ReuseThings.resetColorCode;

        CellStyle numberStyle = new CellStyle(HorizontalAlign.center);

        Table t = new Table(numberColumns, BorderStyle.UNICODE_ROUND_BOX,
                ShownBorders.SURROUND_HEADER_FOOTER_AND_COLUMNS);

        for (int i =0;i<numberColumns;i++)
            t.setColumnWidth(i, 30, 70);

        t.addCell(String.valueOf(book.getId()),numberStyle);
        t.addCell(book.getTitle(), numberStyle);
        t.addCell(book.author.getAuthor_name()+" ( "+ book.author.getActive_year()+" )", numberStyle);
        t.addCell(book.getPublish_year(),numberStyle);
        t.addCell(signAvailable, numberStyle);

        System.out.println(t.render());

    }

    public void showErrorValidate(){
        showMsg(ReuseThings.redColorCode+"Please enter again the correct input form!!!\nAccept only character and number (A-Z,0-9) !!"+ReuseThings.resetColorCode);
    }

    public void showMsg(String msg){
        header(1,new String[]{msg});
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
        if(lib.library_validate(title, author_name) || lib.library_validate(active_year, publish_year)){
            showErrorValidate();
            inputInformation(book,id);
        }
        setInformation(book, id,title,publish_year,author_name,active_year);
        showMsg("Book added successfully");
    }

    public void showOptions(Library library){
        showMsg(ReuseThings.greenColorCode+library.getLibrary_name().toUpperCase(Locale.ROOT)+" , "+library.getLibrary_address().toUpperCase(Locale.ROOT)+ReuseThings.resetColorGreen);
        header(1,new String[]{"1 - Add Book.","2 - Show All Books.","3 - Show Available Books.","4 - Borrow Book.","5 - Return Book.","6 - Exit."});
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
        return answer.equalsIgnoreCase("Y");
    }

    public void showAvailableBook(Book[] books){
        int count = 0;
        for (Book book : books){
            if (book != null){
                if (book.isAvailable()){
                    display(book);
                    count++;
                }
            }
        }
        if (count==0 ){
            showMsg("No Books Available right now");
        }
    }

    public void borrow_book(Book[] books,int allID){
        System.out.print("=> Enter book's ID to borrow : ");
        String book_id = new Scanner(System.in).nextLine();
        if (!(new Library().regex_validate(book_id,"[0-9]+"))){
            showMsg("Invalid Book's ID.Try Again!!");
            borrow_book(books,allID);
        }else {
            for (Book book:books){
                if (Integer.parseInt(book_id)<=allID){
                    if (book.getId()==Integer.parseInt(book_id)){
                        header(5,Library.heaader_title);
                        book.display(book);
                        if (!book.isAvailable()){
                            showMsg("This Book is Currently Unavailable");
                            break;
                        }
                        System.out.print("=> Do you want to borrow this book ? [Y/N] : ");
                        String answer = new Scanner(System.in).nextLine();
                        if (answer.equalsIgnoreCase("Y")){
                            book.setAvailable(false);
                            showMsg(ReuseThings.greenColorCode+"Book ID "+ book_id+ " was borrow successfully"+ReuseThings.resetColorGreen);
                        }
                        break;
                    }
                }else {
                    showMsg(ReuseThings.redColorCode+"Book ID "+ book_id + " was not found!!"+ReuseThings.resetColorCode);
                    break;
                }

            }
        }
    }
    public void return_book(Book[] books,int allID){
        System.out.print("=> Enter book's ID to return : ");
        String book_id = new Scanner(System.in).nextLine();
        if (!(new Library().regex_validate(book_id,"[0-9]+"))){
            showMsg("Invalid Book's ID.Try Again!!");
            borrow_book(books,allID);
        }else {
            for (Book book:books){
                if (Integer.parseInt(book_id)<=allID){
                    if (book.getId()==Integer.parseInt(book_id)){
                        header(5,Library.heaader_title);
                        book.display(book);
                        if (book.isAvailable()){
                            showMsg("This Book is Already Available");
                            break;
                        }
                        System.out.print("=> Do you want to return this book ? [Y/N] : ");
                        String answer = new Scanner(System.in).nextLine();
                        if (answer.equalsIgnoreCase("Y")){
                            book.setAvailable(true);
                            showMsg("Book ID "+ book_id+ " was return successfully");
                        }
                        break;
                    }
                }else {
                    showMsg(ReuseThings.redColorCode+"Book ID "+ book_id + " was not found!!"+ReuseThings.resetColorCode);
                    break;
                }

            }
        }
    }
}
