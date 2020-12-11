package com.kodilla.testing.library;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookDirectoryTestSuite {

    private List<Book> generateListOfNBooks(int booksQuantity) {
        List<Book> resultList = new ArrayList<>();
        for (int i = 1; i <= booksQuantity; i++) {
            Book theBook = new Book("Title" + i, "Author" + i, 1970 + i);
            resultList.add(theBook);
        }
        return resultList;
    }

    @Mock
    private LibraryDatabase libraryDatabaseMock;

    @Test
    void testListBooksWithConditionsReturnList() {
        //Given
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
        List<Book> resultListOfBooks = new ArrayList<>();
        Book book1 = new Book("Secrets of Alamo", "John Smith", 2008);
        Book book2 = new Book("Secretaries and Directors", "Dilbert Michigan", 2012);
        Book book3 = new Book("Secret life of programmers", "Steve Wolkowitz", 2016);
        Book book4 = new Book("Secrets of Java", "Ian Tenewitch", 2010);
        resultListOfBooks.add(book1);
        resultListOfBooks.add(book2);
        resultListOfBooks.add(book3);
        resultListOfBooks.add(book4);
        when(libraryDatabaseMock.listBooksWithCondition("Secret")).thenReturn(resultListOfBooks);

        //When
        List<Book> theListOfBooks = bookLibrary.listBooksWithCondition("Secret");

        //Then
        assertEquals(4, theListOfBooks.size());
    }

    @Test
    void testListBooksWithConditionMoreThan20() {
        //Given
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
        List<Book> resultListOf0Books = new ArrayList<>();
        List<Book> resultListOf15Books = generateListOfNBooks(15);
        List<Book> resultListOf40Books = generateListOfNBooks(40);
        when(libraryDatabaseMock.listBooksWithCondition(anyString())).thenReturn(resultListOf15Books);
        when(libraryDatabaseMock.listBooksWithCondition("ZeroBooks")).thenReturn(resultListOf0Books);
        when(libraryDatabaseMock.listBooksWithCondition("FortyBooks")).thenReturn(resultListOf40Books);

        //When
        List<Book> theListOfBooks0 = bookLibrary.listBooksWithCondition("ZeroBooks");
        List<Book> theListOfBooks15 = bookLibrary.listBooksWithCondition("Any title");
        List<Book> theListOfBooks40 = bookLibrary.listBooksWithCondition("FortyBooks");

        //Then
        assertEquals(0, theListOfBooks0.size());
        assertEquals(15, theListOfBooks15.size());
        assertEquals(0, theListOfBooks40.size());
    }

    @Test
    void  testListBooksWithConditionFragmentShorterThan3() {
        //Given
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
//        List<Book> resultListOf10Books = generateListOfNBooks(10);
//        when(libraryDatabaseMock.listBooksWithCondition(anyString())).thenReturn(resultListOf10Books);

        //When
        List<Book> theListOfBooks10 = bookLibrary.listBooksWithCondition("An");

        //Then
        assertEquals(0, theListOfBooks10.size());
        verify(libraryDatabaseMock, times(0)).listBooksWithCondition(anyString());
    }

    @Nested
    @DisplayName("Tests for amount of books held by LibraryUser")
    public class UserRentedBooksTestSuite {

        @Test
        void testUserHasNotRentedBooksList() {
            //Given
            BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
            List<Book> resultListOf0Books = new ArrayList<>();
            LibraryUser libraryUser = new LibraryUser("John", "Smith", "92011206979");
            when(libraryDatabaseMock.listBooksInHandsOf(libraryUser)).thenReturn(resultListOf0Books);

            //When
            List<Book> rentedBooks0 = bookLibrary.listBooksInHandsOf(libraryUser);

            //Then
            assertEquals(0, rentedBooks0.size());
        }

        @Test
        void testUserHasOneRentedBook() {
            //Given
            BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
            LibraryUser libraryUser = new LibraryUser("John", "Smith", "92011206979");
            List<Book> resultListOf1Book = new ArrayList<>();
            Book book1 = new Book("book1", "bookAuthor1", 2000);
            resultListOf1Book.add(book1);
            when(libraryDatabaseMock.listBooksInHandsOf(libraryUser)).thenReturn(resultListOf1Book);

            //When
            List<Book> rentedBooks1 = bookLibrary.listBooksInHandsOf(libraryUser);

            //Then
            assertEquals(1, rentedBooks1.size());
        }

        @Test
        void testUserHasFiveRentedBooks() {
            //Given
            BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
            List<Book> resultListOf5Books = new ArrayList<>();
            Book book1 = new Book("book1", "bookAuthor1", 2000);
            Book book2 = new Book("book2", "bookAuthor2", 2002);
            Book book3 = new Book("book3", "bookAuthor3", 2003);
            Book book4 = new Book("book4", "bookAuthor4", 2004);
            Book book5 = new Book("book5", "bookAuthor5", 2005);

            resultListOf5Books.add(book1);
            resultListOf5Books.add(book2);
            resultListOf5Books.add(book3);
            resultListOf5Books.add(book4);
            resultListOf5Books.add(book5);

            LibraryUser libraryUser = new LibraryUser("John", "Smith", "92011206979");
            when(libraryDatabaseMock.listBooksInHandsOf(libraryUser)).thenReturn(resultListOf5Books);

            //When
            List<Book> rentedBooks5 = bookLibrary.listBooksInHandsOf(libraryUser);

            //Then
            assertEquals(5, rentedBooks5.size());
        }
        private List<Book> generateListOfNBooks(int booksQuantity) {
            List<Book> resultList = new ArrayList<>();
            for (int i = 1; i <= booksQuantity; i++) {
                Book theBook = new Book("Title" + i, "Author" + i, 1970 + i);
                resultList.add(theBook);
            }
            return resultList;
        }
    }
}
