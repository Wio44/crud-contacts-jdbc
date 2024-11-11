package sk.wio;

import sk.wio.db.DBContactService;
import sk.wio.db.HikariCPDataSource;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        DBContactService service = new DBContactService();
        service.readAll().forEach(System.out::println);
    }
}
