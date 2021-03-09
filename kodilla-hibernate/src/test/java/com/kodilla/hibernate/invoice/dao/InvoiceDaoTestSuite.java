package com.kodilla.hibernate.invoice.dao;

import com.kodilla.hibernate.invoice.Invoice;
import com.kodilla.hibernate.invoice.Item;
import com.kodilla.hibernate.invoice.Product;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootTest
public class InvoiceDaoTestSuite {

    @Autowired
    private InvoiceDao invoiceDao;

    @Test
    void testInvoiceDaoSave() {
        //Given
        Product mouse = new Product("Mouse");
        Product keyboard = new Product("Keyboard");
        Product headset = new Product("Headset");
        Product monitor = new Product("Monitor");
        Product stand = new Product("Monitor Stand");
        Product laptop = new Product("Laptop");
        Item item1 = new Item(new BigDecimal(199), 35, new BigDecimal(2500));
        Item item2 = new Item(new BigDecimal(299), 25, new BigDecimal(4500));
        Item item3 = new Item(new BigDecimal(159), 15, new BigDecimal(5500));
        Item item4 = new Item(new BigDecimal(999), 10, new BigDecimal(12500));
        Item item5 = new Item(new BigDecimal(99), 90, new BigDecimal(1500));
        Item item6 = new Item(new BigDecimal(2199), 50, new BigDecimal(6500));
        item1.setProduct(mouse);
        item2.setProduct(keyboard);
        item3.setProduct(headset);
        item4.setProduct(monitor);
        item5.setProduct(stand);
        item6.setProduct(laptop);
        Invoice invoice1 = new Invoice("2021090399");
        invoice1.getItems().add(item1);
        invoice1.getItems().add(item2);
        invoice1.getItems().add(item3);
        Invoice invoice2 = new Invoice("2021090212");
        invoice2.getItems().add(item4);
        invoice2.getItems().add(item5);
        invoice2.getItems().add(item6);
        item1.setInvoice(invoice1);
        item2.setInvoice(invoice1);
        item3.setInvoice(invoice1);
        item4.setInvoice(invoice2);
        item5.setInvoice(invoice2);
        item6.setInvoice(invoice2);

        //When
        invoiceDao.save(invoice1);
        invoiceDao.save(invoice2);
        int invoice1Id = invoice1.getId();
        int invoice2Id = invoice2.getId();

        //Then
        assertNotEquals(0, invoice1Id);
        assertNotEquals(0, invoice2Id);

        //CleanUp
        invoiceDao.deleteById(invoice1Id);
        invoiceDao.deleteById(invoice2Id);
    }
}
