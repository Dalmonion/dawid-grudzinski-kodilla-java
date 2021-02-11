package com.kodilla.patterns.prototype.library;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.concurrent.CompletionException;
import java.util.stream.IntStream;

public class LibraryTestSuite {

    @Test
    void  testGetBooks() {
        //Given
        Library library = new Library("library");
        //creating books
        IntStream.iterate(1, n -> n + 1)
                .limit(10)
                .forEach(n -> library.getBooks().add(
                        new Book("Title number " + n, "Author number" + n,
                                LocalDate.of(1991 + n, 1 + n, 5  + n))));

        //making a shallow clone of library
        Library clonedLibrary = null;
        try {
            clonedLibrary = library.shallowCopy();
            clonedLibrary.setName("shallowClonedLibrary");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        //making a deep copy of object library
        Library deepClonedLibrary = null;
        try {
            deepClonedLibrary = library.deepCopy();
            deepClonedLibrary.setName("deepClonedLibrary");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        //When
        Book tempBook = new Book("Title number " + 1, "Author number" + 1,
                LocalDate.of(1992, 2, 6));
        library.getBooks().remove(tempBook);


        //Then
        System.out.println("Size of library = " + library.getBooks().size());
        System.out.println(library);
        System.out.println("Size of cloned library = " + clonedLibrary.getBooks().size());
        System.out.println(clonedLibrary);
        System.out.println("Size of deep cloned library = " + deepClonedLibrary.getBooks().size());
        System.out.println(deepClonedLibrary);
        assertEquals(9, library.getBooks().size());
        assertEquals(9, clonedLibrary.getBooks().size());
        assertEquals(10, deepClonedLibrary.getBooks().size());
        assertEquals(clonedLibrary.getBooks(), library.getBooks());
        assertNotEquals(deepClonedLibrary.getBooks(), library.getBooks());
    }
}
