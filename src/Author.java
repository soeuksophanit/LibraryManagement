public class Author {
    private String author_name;
    private String active_year;

    public Author(){}

    public Author(String author_name, String active_year) {
        this.author_name = author_name;
        this.active_year = active_year;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getActive_year() {
        return active_year;
    }

    public void setActive_year(String active_year) {
        this.active_year = active_year;
    }
}
