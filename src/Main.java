import models.*;
import org.w3c.dom.ls.LSOutput;

import java.lang.module.FindException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class Main {


    private static final String RESET = "\u001B[0m";
    private static final String BLUE = "\u001B[34m";
    public static final String RED = "\033[0;31m";

    public static void printBooksByDate(List<Book> books){

        books.stream()
                .sorted((book1, book2) -> (book1.getReleaseYear() > book2.getReleaseYear())? 1: -1 )
                .forEach(book -> System.out.println(String.format("[%d] %s",book.getReleaseYear(), book.toString())));

    }


    public static void printAllBooksByAuthorSurnameLetter(String input, List<Book> books){

        boolean []flag = {false};
        books.stream()
                .filter(book -> book.getAuthor().getLastName().startsWith(input) || book.getAuthor().getLastName().startsWith(input.toLowerCase()))
                .forEach(book -> {
                    System.out.println(book.toString());
                    flag[0] = true;
                });

        if(flag[0]==false){
            System.out.println("AUTHOR NOT FOUND\n");
        }


    }

    public static void printAuthorsWithThreeBooksAndMore(List<Book> books){

        Map<Object, Long> authors =
                books.stream().collect(
                                Collectors.groupingBy(book -> book.getAuthor().getAuthorFullName() , Collectors.counting()));

        System.out.println("AUTHORS WITH 3+ BOOKS:");
         authors.keySet().forEach(author -> {
             if(authors.get(author) > 3)
                 System.out.println("\t" +author);
         });

    }


    public static void printNewestAndOldestBook(List<Book> books){

        books.stream().max((o1, o2) -> o1.getReleaseYear() > o2.getReleaseYear()? 1: -1).ifPresentOrElse(book -> System.out.println( "NEWEST BOOK:\tRELEASED: " + book.getReleaseYear() + "\t\tTITLE: "+ book.getTitle() ), ()-> System.out.println("NEWEST BOOK NOT FOUND"));

        books.stream().max((o1, o2) -> o1.getReleaseYear() < o2.getReleaseYear()? 1: -1).ifPresentOrElse(book -> System.out.println( "OLDEST BOOK: \tRELEASED: " + book.getReleaseYear() +"\t\tTITLE: " + book.getTitle()) ,() -> System.out.println("OLDEST BOOK NOT FOUND"));
    }


    public static List<Book> generateListOfBooks(){

        List<Book> books = new LinkedList<>();
        books.add(new Ebook("Eragon", 1231231231, 2002, new Author("Christopher",
                "Paolini", 1983), 20.1f, BookFormat.EPUB));
        books.add( new Ebook("Lord of The Rings - The Fellowship of the Ring", 197897869, 1954, new Author("J.R.R.",
                "Tolkien", 1892), 10.4f, BookFormat.HTML5));
        books.add( new Ebook("Lord of The Rings - The Two Towers", 197897812, 1954, new Author("J.R.R.",
                "Tolkien", 1892), 10.9f, BookFormat.HTML5));
        books.add( new Ebook("Lord of The Rings - The Return of the King", 197897871, 1955, new Author("J.R.R.",
                "Tolkien", 1892), 11.4f, BookFormat.HTML5));
        books.add( new Ebook("The Hobbit", 197897123, 1937, new Author("J.R.R.",
                "Tolkien", 1892), 12.4f, BookFormat.HTML5));
        books.add(new Ebook("Harry Potter and the Philosopher Stone", 123456, 1997, new Author("J.K.",
                "Rowling", 1965), 15.4f, BookFormat.PDF));
        books.add( new PrintCopy("A Game of Thrones", 89475643,1996 ,new Author("George", "R.R. Martin", 1948),
                192, 1.3f));
        books.add(new PrintCopy("A Clash of Kings", 89475123,1998 ,new Author("George", "R.R. Martin", 1948),
                210, 1.5f));
        books.add(new PrintCopy("A Dance with the Dragons", 894751234,2011 ,new Author("George", "R.R. Martin", 1948),
                356, 1.8f));
        books.add( new PrintCopy("A Storm of Winds", 894751235,2000 ,new Author("George", "R.R. Martin", 1948),
                281, 1.65f));

        books.add(new Ebook("Harry Potter and the Chambers of Secrets", 123123567, 1998,new Author("J.K.",
                "Rowling", 1965),15.1f, BookFormat.EPUB));
        books.add(new Ebook("Harry Potter and the Prisoner of Azkaban", 123123567, 1999,new Author("J.K.",
                "Rowling", 1965),14.1f, BookFormat.EPUB));
        books.add(new Ebook("Harry Potter and the Goblet of Fire", 123123568, 2000,new Author("J.K.",
                "Rowling", 1965),14.5f, BookFormat.EPUB));
        books.add(new Ebook("Harry Potter and the Order of the Phoenix", 123123569, 2003,new Author("J.K.",
                "Rowling", 1965),17.1f, BookFormat.EPUB));
        books.add(new Ebook("Harry Potter and the Half Blood Prince", 123123510, 2005,new Author("J.K.",
                "Rowling", 1965),18.1f, BookFormat.EPUB));

        books.add(new Ebook("Programming Notes", 1231243561, 2009, new Author("Stefan",
                "Apostolovski", 1997), 20.1f, BookFormat.EPUB));
        books.add(new Ebook("Autobiography of Malcolm X", 123122354, 1965, new Author("Alex ",
                "Haley", 1921), 10.1f, BookFormat.HTML5));


        return books;

    }


    public static boolean matchingDecade(Book book, Integer decade){
        if(book.getReleaseYear()/10 == decade || (book.getReleaseYear()%10 == 0 && (book.getReleaseYear()/10-1 == decade)))
            return true;
        return false;
    }

    public static void printBooksByDecade(List<Book> books){

        List<Integer> years = books.stream()
                .map(book -> book.getAuthor().getYearOfBirth()/10)
                .distinct()
                .sorted()
                .collect(toList());

        System.out.println("BOOKS WRITTEN IN");

        IntStream.range(0,years.size())
                .forEach(index ->{
                    int decade = years.get(index);
                    System.out.println(String.format("===========================================\nDecade: %d - %d", decade*10, (decade+1)*10 ));
                    books.stream().filter(book -> matchingDecade(book, decade)).forEach(System.out::println);
        });

    }


    public static void main(String[] args) {


        List<Book> books = generateListOfBooks();

        System.out.println(BLUE + "JAVA GRADUATES TEST APPLICATION DEVELOPED BY STEFAN APOSTOLOVSKI\n" + RESET);

        while(true){

            Scanner scanner = new Scanner(System.in);

            // print all options
            System.out.println("\nENTER THE NUMBER OF THE INTENDED OPERATION: \n\t1. Print all books chronologically (starting with the oldest book)\n" +
                    "\t2. Print all books from author with surname containing letter\n\t3. Print all books written in a decade when an author is born\n" +
                    "\t4. Print all authors who have written more than 3 books\n\t5. Print the oldest and the newest books\n\t0. Exit");

            if(scanner.hasNextInt()){

                int input = scanner.nextInt();

                if(input == 1){
                    printBooksByDate(books);
                    continue;
                }

                if(input == 2){
                    while(true){
                        System.out.println("ENTER SURNAME LETTER");
                        String s = scanner.next();
                        printAllBooksByAuthorSurnameLetter(s,books);

                        System.out.println("DO YOU WISH TO ENTER NEW NAME?\n1. Yes\n2. No");
                        if(scanner.hasNextInt() && scanner.nextInt() == 2)
                            break;
                    }
                    continue;
                }
                if(input == 3){
                    printBooksByDecade(books);
                    continue;
                }
                if(input == 4){
                    printAuthorsWithThreeBooksAndMore(books);
                    continue;
                }
                if(input == 5){
                    printNewestAndOldestBook(books);
                    continue;
                }

                if(input == 0){
                    System.out.println(RED + "GOODBYE!" + RESET);
                    break;
                }
                else{
                    System.out.println(RED + "WRONG INPUT - ENTER ONE OF GIVEN NUMBERS\n" + RESET);
                    continue;
                }

            }else{
                System.out.println(RED + "WRONG INPUT - ENTER ONE OF GIVEN NUMBERS\n" + RESET);
                continue;
            }

        }


    }
}
