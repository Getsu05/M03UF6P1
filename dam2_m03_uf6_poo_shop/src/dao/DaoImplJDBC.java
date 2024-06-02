
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Employee;

public class DaoImplJDBC implements Dao {

	private Connection connection;
	
	
	public DaoImplJDBC() {
		
	}

	@Override
	public void connect() throws SQLException {

		String url = "jdbc:mysql://localhost:3306/shop";
		String user = "root";
		String pass = "";
		this.connection = DriverManager.getConnection(url, user, pass);

	}

	@Override
	public void disconnect() throws SQLException {

		if (connection != null) {
            try {
                connection.close();
                System.out.println("Disconnected from the database.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}

	@Override
	public Employee getEmployee(int employeeId, String password) {

		 if (connection == null) {
	            System.out.println("Not connected to the database.");
	            return null;
	        }
		 
		 try {
	            // Prepara la consulta SQL
	            String query = "SELECT * FROM employees WHERE employeeId = ? AND password = ?";
	            PreparedStatement preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, employeeId);
	            preparedStatement.setString(2, password);

	            // Ejecuta la consulta
	            ResultSet resultSet = preparedStatement.executeQuery();

	            // Si hay resultados, crea y devuelve el empleado
	            if (resultSet.next()) {
	                String name = resultSet.getString("name");
	                Employee employee = new Employee(name);
	                employee.setEmployeeId(employeeId);
	                return employee;
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	}
	

}
