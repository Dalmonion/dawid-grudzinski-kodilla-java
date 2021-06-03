package com.kodilla.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StoredProcTestSuite {

    private static DbManager dbManager;
    private static Statement statement;

    @BeforeAll
    static void init() throws SQLException{
        dbManager = DbManager.getInstance();
        statement = dbManager.getConnection().createStatement();
    }

    @AfterAll
    static void end() throws SQLException{
        statement.close();
    }

    @Test
    void testUpdateVipLevels() throws SQLException {
        // Given
        String sqlUpdate = "UPDATE READERS SET VIP_LEVEL=\"Not set\"";
        statement.executeUpdate(sqlUpdate);

        // When
        String sqlProcedureCall = "CALL UpdateVipLevels()";
        statement.execute(sqlProcedureCall);

        // Then
        String sqlCheckTable = "SELECT COUNT(*) AS HOW_MANY FROM READERS WHERE VIP_LEVEL=\"Not set\"";
        ResultSet rs = statement.executeQuery(sqlCheckTable);
        int howMany = -1;
        if (rs.next()) {
            howMany = rs.getInt("HOW_MANY");
        }
        assertEquals(0, howMany);
        rs.close();
    }

    @Test
    void testUpdateBestsellers() throws SQLException{
        // Given
        String booksUpdate = "UPDATE BOOKS SET BESTSELLER = -1";
        statement.executeUpdate(booksUpdate);

        // When
        String sqlUpdateBestsellersCall = "CALL UpdateBestsellers()";
        statement.execute(sqlUpdateBestsellersCall);
        //Then
        String sqlCheck = "SELECT COUNT(*) AS HOW_MANY FROM BOOKS WHERE BESTSELLER = -1";
        ResultSet rs = statement.executeQuery(sqlCheck);
        int howMany = -1;
        if (rs.next()) {
            howMany = rs.getInt("HOW_MANY");
        }
        assertEquals(0, howMany);
        rs.close();
    }

}
