package models;

public abstract class Book {

    private String title;
    private long ISBN;
    private int releaseYear;
    private Author author;

    public Book(String title, long ISBN, int releaseYear, Author author) {
        this.title = title;
        this.ISBN = ISBN;
        this.releaseYear = releaseYear;
        this.author = author;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public abstract String toString();
}
