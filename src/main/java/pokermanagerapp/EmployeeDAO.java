package pokermanagerapp;

import model.Address;
import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDAO {
  private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/pokermanager?useSSL=false" + "&" + "currentSchema=pokermanager";
  private static final String DATABASE_USERNAME = "root";
  private static final String DATABASE_PASSWORD = "admin";
  private static final String INSERT_QUERY = "INSERT INTO player (name, lastname, nick, telnumber, email, address, " +
          "city, postalcode, telpush, emailpush) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
  private static final String UPDATE_QUERY = "UPDATE player " +
          "SET name = ?, lastname = ?, nick = ?, telnumber = ?, email = ?, address = ?, " +
          "city = ?, postalcode = ?, telpush = ?, emailpush = ? " +
          "WHERE id = ?;";
  public static Employee getEmployeeById(int id)
  {
    Employee employee = null;
    String query = "SELECT name, lastname, telnumber, email, address, position FROM employee WHERE id=?";

    try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        String name = resultSet.getString("name");
        String lastName = resultSet.getString("lastname");
        String telNumber = resultSet.getString("telnumber");
        String email = resultSet.getString("email");
        String address = resultSet.getString("address");
        String city = resultSet.getString("city");
        String postalCode = resultSet.getString("postalcode");
        String position = resultSet.getString("position");
        Address employeeAddress = new Address(address, city, postalCode);
        employee = new Employee(id, name, lastName, telNumber, email, employeeAddress, position);
      }
    } catch (SQLException e) {
      printSQLException(e);
    }
    return employee;
  }

  public static List<Employee> getAllEmployees() {
    List<Employee> employeeList = new ArrayList<>();
    String query = "SELECT id, name, lastname, telnumber, email, address, position FROM employee";

    try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String lastName = resultSet.getString("lastname");
        String telNumber = resultSet.getString("telnumber");
        String email = resultSet.getString("email");
        String address = resultSet.getString("address");
        String city = resultSet.getString("city");
        String postalCode = resultSet.getString("postalcode");
        String position = resultSet.getString("position");
        Address employeeAddress = new Address(address, city, postalCode);
        Employee employee = new Employee(id, name, lastName, telNumber, email, employeeAddress, position);
        employeeList.add(employee);
      }
    } catch (SQLException e) {
      printSQLException(e);
    }
    return employeeList;
  }

  public static void printSQLException(SQLException ex) {
    for (Throwable e: ex) {
      if (e instanceof SQLException) {
        e.printStackTrace(System.err);
        System.err.println("SQLState: " + ((SQLException) e).getSQLState());
        System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
        System.err.println("Message: " + e.getMessage());
        Throwable t = ex.getCause();
        while (t != null) {
          System.out.println("Cause: " + t);
          t = t.getCause();
        }
      }
    }
  }
}
