package bike.service.app.data;

import java.sql.*;

public class DataBaseReader {
    public static void main(String[] args) {
        //płączenie z bazą danych
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:/Users/jacobsunny/BServiceProject/src/main/resources/bikeServices",
                    "sa", "password");
            Statement statement = connection.createStatement();
            //tutaj pobieram info z BikeService o serwisach i ich id
            ResultSet resultSet = statement.executeQuery("SELECT * FROM SERVICES");

            while (resultSet.next()) {
                System.out.print(resultSet.getInt("serviceid") + " - ");
                System.out.println(resultSet.getString("smallservice"));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
