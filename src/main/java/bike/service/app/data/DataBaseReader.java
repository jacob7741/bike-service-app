package bike.service.app.data;

import java.sql.*;

public class DataBaseReader {
    public static void main(String[] args) {
        //płączenie z bazą danych
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:/Users/jacobsunny/BServiceProject/src/main/resources/bikeServices",
                    "sa","password" );
            Statement statement = connection.createStatement();
            //tutaj pobieram info z BikeService o serwisach i ich id
            ResultSet resultSet = statement.executeQuery("SELECT * FROM BikeService");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id"));
                System.out.println(resultSet.getString("name"));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
