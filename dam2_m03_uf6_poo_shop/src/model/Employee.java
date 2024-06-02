package model;

import main.Logeable;
import dao.Dao;
import dao.DaoImplJDBC;

public class Employee extends Person implements Logeable {

    private int employeeId;
    private String password;
    private Dao dao;

    public Employee(String name) {
        super(name);
        this.dao = new DaoImplJDBC(); // ii. Asignar un objeto del constructor DaoImplJDBC
    }

    public boolean check_login(int user, String password) {
        // i. Comentar lógica de validación credenciales usando constantes
        /*
        return user == this.employeeId && password.equals(this.password);
        */

        // ii. Invocar metodo dao.connect para conectarse con la base de datos
        dao.connect();

        // iii. Invocar metodo dao.getEmployee
        Employee employee = dao.getEmployee(user, password);

        // iv. Invocar metodo dao.disconnect
        dao.disconnect();

        // v. Devolver true si Employee esta informado o false si null
        return employee != null;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
