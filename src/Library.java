import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Library {

    private String library_name;
    private String library_address;

    Library(){}

    public Library(String library_name, String library_address) {
        this.library_name = library_name;
        this.library_address = library_address;
    }

    public String getLibrary_name() {
        return library_name;
    }

    public void setLibrary_name(String library_name) {
        this.library_name = library_name;
    }

    public String getLibrary_address() {
        return library_address;
    }

    public void setLibrary_address(String library_address) {
        this.library_address = library_address;
    }

    public void inputLibraryInfo(){
        String lib_name,lib_address;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n========== SET UP LIBRARY ==========");
        System.out.print("=> Enter Library's Name : ");
        lib_name = sc.nextLine();
        System.out.print("=> Enter Library's Address : ");
        lib_address = sc.nextLine();
        if (library_validate(lib_name, lib_address)){
            System.out.println("\n---------------------------------------");
            System.out.println("Please enter again the correct input form!!!");
            System.out.println("Accept only character and number (A-Z,0-9) !!");
            System.out.println("---------------------------------------\n");
            inputLibraryInfo();

        }
        this.library_address = lib_address;
        this.library_name = lib_name;

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String date_and_time = currentDateTime.format(formatter);

        System.out.println("\""+this.getLibrary_name().toUpperCase(Locale.ROOT)+"\" Library is Already Created in" +
                " \""+this.getLibrary_address().toUpperCase(Locale.ROOT)+"\" address successfully on "+ date_and_time);
    }
    public boolean library_validate(String library_name,String library_address){
        if (Pattern.matches("[a-zA-Z0-9- ]+",library_name) && Pattern.matches("[a-zA-Z0-9- ]+",library_address))
            return false;
        return true;
    }

    public boolean regex_validate(String anyString,String regex){
        return Pattern.matches(regex,anyString);
    }
}
