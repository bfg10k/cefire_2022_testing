package org.cefire.library;

import org.junit.jupiter.api.Test;

public class BookCollectionTest {

    @Test
    public void shouldFindABookByISBN() {
        BookCollection books = new BookCollection(new Book[]{
                new Book("un-isbn-1", "un titulo 1", "un autor 1"),
                new Book("un-isbn-2", "un titulo 2", "un autor 2"),
                new Book("un-isbn-2", "un titulo 2", "un autor 2"),
                new Book("un-isbn-3", "un titulo 3", "un autor 3"),
        });

        if (!books.find("un-isbn-2").isEmpty()) {
            boolean allConcidencesAreCorrect = true;

            for (Book book : books.find("un-isbn-2")) {
                if (!book.getISBN().equals("un-isbn-2")) {
                    allConcidencesAreCorrect = false;
                    break;
                }
            }

            if (allConcidencesAreCorrect) {
                System.out.println("✔ Should find a book by ISBN");

                return;
            }

            System.out.println("✘ Should find a book by ISBN");
        }
    }
}
