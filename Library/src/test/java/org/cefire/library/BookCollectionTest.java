package org.cefire.library;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BookCollectionTest {

    @Test
    public void shouldFindABookByISBN() {
        final String isbnToLocate = "un-isbn-2";

        BookCollection books = new BookCollection(new Book[]{
                new Book("un-isbn-1", "un titulo 1", "un autor 1"),
                new Book(isbnToLocate, "un titulo 2", "un autor 2"),
                new Book(isbnToLocate, "un titulo 2", "un autor 2"),
                new Book("un-isbn-3", "un titulo 3", "un autor 3"),
        });

        List<Book> foundBooks = books.find(isbnToLocate);

        assertThat(foundBooks.isEmpty(), is(false));

        foundBooks.forEach((Book book) -> assertThat(isbnToLocate, is(equalTo(book.getISBN()))));
    }

    @Test
    public void shouldGetAnEmptyListIfNoMatchesAreFound() {
        final String isbnToLocate = "un-isnb-que-no-existe";
        BookCollection books = new BookCollection(new Book[]{
                new Book("un-isbn-1", "un titulo 1", "un autor 1"),
                new Book("un-isbn-2", "un titulo 2", "un autor 2"),
                new Book("un-isbn-3", "un titulo 3", "un autor 3"),
        });

        assertThat(books.find(isbnToLocate).isEmpty(), is(true));
    }

    @Test
    public void shouldFindCopiesOfABook() {
        final Book bookToBeFound = new Book("libro-que-debe-encontrase", "un titulo 1", "un autor 1");
        BookCollection books = new BookCollection(new Book[]{
                new Book("libro-que-debe-encontrase", "un titulo 1", "un autor 1"),
                new Book("libro-que-debe-encontrase", "un titulo 1", "un autor 1"),
                new Book("un-isbn-2", "un titulo 2", "un autor 2"),
                new Book("un-isbn-3", "un titulo 3", "un autor 3"),
        });

        List<Book> foundBooks = books.findCopies(bookToBeFound);

        assertThat(foundBooks.isEmpty(), is(false));
        foundBooks.forEach((Book book) -> assertThat(book, is(equalTo(bookToBeFound))));
    }

    @Test
    public void shouldGetExceptionWhenUsingFindOrFailWithANonExistentEntry() {
        final String isbnToLocate = "un-texto-que-no-existe-como-isbn-o-author";
        BookCollection books = new BookCollection(new Book[]{
                new Book("un-isbn-1", "un titulo 1", "un autor 1"),
                new Book("un-isbn-2", "un titulo 2", "un autor 2"),
                new Book("un-isbn-3", "un titulo 3", "un autor 3"),
        });

        Assertions.assertThrows(BookCollection.ExpectedToFindAtLeastABook.class, () -> books.findOrFail(isbnToLocate));
    }
}
