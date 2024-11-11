package sk.wio;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String connectionUrl = "jdbc:mysql://localhost:3306/contacts";
        String select = "SELECT * FROM contact";

        try {
            Connection connection = DriverManager.getConnection(connectionUrl, "root", "password");
            PreparedStatement statement = connection.prepareStatement(select);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");

                System.out.println("id: " + id + ", name: " + name + ", email: " + email + ", phone: " + phone);

            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while connecting to database");
        }
    }
}
