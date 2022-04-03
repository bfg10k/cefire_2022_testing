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
                new Book(isbnToLocate, "un titulo 5", "un autor 4"),
                new Book(isbnToLocate, "un titulo 6", "un autor 5"),
                new Book(isbnToLocate, "un titulo 2", "un autor 2"),
                new Book("un-isbn-3", "un titulo 3", "un autor 3"),
        });

        List<Book> foundBooks = books.find(isbnToLocate);

        Assertions.assertEquals(3, foundBooks.size());
        foundBooks.forEach((Book book)-> Assertions.assertEquals(isbnToLocate, book.getISBN()));
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

    @Test
    public void shouldFindBooksByPartialTitle(){
        final String partialTitleToLocate = "titulo-a-encontrar";

        BookCollection books = new BookCollection(new Book[]{
           new Book("un-isbn-cualquiera", partialTitleToLocate, "un-autor-cualquiera"),
           new Book("un-isbn-cualquiera-2", partialTitleToLocate.concat("-en-la-colección"), "un-autor-cualquiera"),
           new Book("un-isbn-cualquiera-44543", "un-titulo-no-coincidente", "un-autor-cualquiera"),
           new Book("un-isbn-cualquiera-423423", "otro-titulo-no-coincidente", "un-autor-cualquiera"),
        });

        List<Book> foundBooks = books.find(partialTitleToLocate);
        Assertions.assertEquals(2, foundBooks.size());
        foundBooks.forEach((Book book) -> Assertions.assertTrue(book.getTitle().contains(partialTitleToLocate)));
    }

    @Test
    public void shoudlNotFindCoincidencesIfCollectionIsEmpty(){
        final String isbnOrTitleThatShouldNotBeFound = "";
        BookCollection books = new BookCollection(new Book[]{});

        List<Book> foundBooks = books.find(isbnOrTitleThatShouldNotBeFound);

        Assertions.assertTrue(foundBooks::isEmpty);
    }
}
