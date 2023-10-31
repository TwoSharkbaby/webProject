package org.web.project.controller;

import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

@Log4j
public class JDBCTests {
    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testConnection() {
        try(Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:oracle",
                "c##test",
                "1234")){
            log.info(String.valueOf(con));
        }catch(Exception e) {
            e.getMessage();
        }
    }
}
