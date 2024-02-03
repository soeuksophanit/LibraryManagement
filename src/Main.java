import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int id = 0;
        String options_case;
        Book[] books = new Book[40];
        Functionalities fc = new Functionalities();

        Library lib = new Library();
        lib.inputLibraryInfo();

        Scanner sc = new Scanner(System.in);

        while (true){
            fc.showOptions(lib);
            System.out.print("=> Choose Option [1-6] : ");
            options_case = sc.nextLine();
            if (!lib.regex_validate(options_case,"[1-9]")){
                fc.showMsg("Please enter the correct form!");
                continue;
            }

            switch (options_case){
                case "1":
                    System.out.println("Case1");
                    books[id] = new Book();
                    books[id].inputInformation(books[id],(id+1));
                    id++;
                    break;
                case "2":
                    System.out.println("\n========== ALL BOOKS INFORMATION ==========");
                    int count = 0;
                    for (Book book : books) {
                        if (book == null){
                            count++;
                            if (count==books.length){
                                fc.showMsg("No Books Available");
                                break;
                            }
                            continue;
                        }
                        book.display(book);
                    }
                    break;
                case "3":
                    fc.showMsg("-- AVAILABLE BOOKS INFORMATION --");
                    fc.showAvailableBook(books);
                    break;
                case "4":
                    fc.showMsg("------- BORROW BOOK INFORMATION -------");
                    fc.borrow_book(books,id);
                    break;
                case "5":
                    System.out.println("Case5");
                    break;
                case "6":
                    boolean isExit = fc.exit_library();
                    if (!isExit){
                        fc.showMsg("----- See you again. Goodbye!! -----");
                        return;
                    }
                default:
                    fc.showMsg("Invalid Option. Please Try Again!!");

            }


        }




    }
}