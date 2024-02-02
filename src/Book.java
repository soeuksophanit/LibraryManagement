import java.util.Locale;

public class Book extends Functionalities {
    private int id ;
    private String title;
    private String publish_year;
    private boolean isAvailable;
    Author author = new Author();
    Book(){}
    public Book(int id,String title,String publish_year,
         boolean isAvailable,String author_name,
         String active_year){
        this.id = id;
        this.title = title;
        this.publish_year = publish_year;
        this.isAvailable = isAvailable;
        this.author.setAuthor_name(author_name);
        this.author.setActive_year(active_year);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublish_year() {
        return publish_year;
    }

    public void setPublish_year(String publish_year) {
        this.publish_year = publish_year;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }


}
