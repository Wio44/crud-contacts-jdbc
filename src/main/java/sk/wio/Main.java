package sk.wio;

import sk.wio.db.DBContactService;
import sk.wio.db.HikariCPDataSource;
import sk.wio.service.CRUDManager;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        new CRUDManager().printOptions();
    }
}
