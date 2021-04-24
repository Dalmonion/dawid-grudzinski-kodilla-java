package com.kodilla.patterns2.adapter.bookclassifier;

import com.kodilla.patterns2.adapter.company.bookclasifier.MedianAdapter;
import com.kodilla.patterns2.adapter.company.bookclasifier.librarya.Book;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MedianAdapterTestSuite {
    @Test
    void publicationYearMedianTest() {
        //Given
        Book book1 = new Book("Author1", "Title1", 1999, "00001");
        Book book2 = new Book("Author2", "Title2", 2000, "00002");
        Book book3 = new Book("Author3", "Title3", 2001, "00003");
        Book book4 = new Book("Author4", "Title4", 2002, "00004");
        Book book5 = new Book("Author5", "Title5", 2003, "00005");
        Book book6 = new Book("Author6", "Title6", 2004, "00006");
        Book book7 = new Book("Author7", "Title7", 2005, "00005");
        Set<Book> bookSet = Stream.of(book1, book2, book3, book4, book5, book6, book7).collect(Collectors.toSet());
        MedianAdapter adapter = new MedianAdapter();
        //When
        int median = adapter.publicationYearMedian(bookSet);
        //Then
        assertEquals(2002, median);
    }
}
