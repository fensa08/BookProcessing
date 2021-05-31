package models;

import java.util.Locale;

public class PrintCopy extends Book {

    private int numberOfPages;
    private float weight;

    public PrintCopy(String title, long ISBN, int releaseYear, Author author, int numberOfPages, float weight) {
        super(title, ISBN, releaseYear, author);
        this.numberOfPages = numberOfPages;
        this.weight = weight;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }


    @Override
    public String toString() {
        return String.format("Book: %s\nIt is written by %s and is released on %d. It is print copy which weights %.2f " +
                "and it has %d pages. BookISBN: %d\n", this.getTitle().toUpperCase(), this.getAuthor().getAuthorFullName(), this.getReleaseYear(),
                this.getWeight(), this.getNumberOfPages(), this.getISBN());
    }
}
