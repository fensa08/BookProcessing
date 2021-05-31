package models;

public class Ebook extends Book{

    private float size;
    private BookFormat bookFormat;

    public Ebook(String title, long ISBN, int releaseYear, Author author, float size, BookFormat bookFormat) {
        super(title, ISBN, releaseYear, author);
        this.size = size;
        this.bookFormat = bookFormat;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String  getBookFormat() {
        return new String(this.bookFormat.toString());
    }

    public void setBookFormat(BookFormat bookFormat) {
        this.bookFormat = bookFormat;
    }

    @Override
    public String toString(){
        return String.format("Book: %s\nIt is written by %s and is released on %d. It is Ebook in %s format and it is %.2f MB." +
                        " BookISBN: %d\n", this.getTitle().toUpperCase(), this.getAuthor().getAuthorFullName(), this.getReleaseYear(),
                this.getBookFormat(), this.getSize(),  this.getISBN());
    }
}
