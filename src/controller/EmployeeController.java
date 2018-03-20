/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Employee;

/**
 *
 * @author User
 */
public class EmployeeController {

    Connection connection = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public void saveEmployee(Employee employee) {
        connection = DBConnect.connectDB();
        if (connection != null) {
            String sql = "Insert into employee(name,employeeid,designation,age,joiningdate,address,status,email) "
                    + "values (?,?,?,?,?,?,?,?)";
            try {
                pst = connection.prepareStatement(sql);
                pst.setString(1, employee.getName());
                pst.setString(2, employee.getEmployeeId());
                pst.setString(3, employee.getDesignation());
                pst.setInt(4, employee.getAge());
                pst.setString(5, employee.getJoiningDate());
                pst.setString(6, employee.getAddress());
                pst.setString(7, String.valueOf(employee.isStatus()));
                pst.setString(8, employee.getEmail());
                pst.execute();
                JOptionPane.showMessageDialog(null, "Saved");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                try {
                    connection.close();
                    pst.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
    }

    public void updateEmployee(Employee employee) {
        connection = DBConnect.connectDB();
        if (connection != null) {
            int status = (employee.isStatus()) ? 1 : 0;
            String sql = "update employee set name = '" + employee.getName()
                    + "',designation = '" + employee.getDesignation()
                    + "',age = '" + employee.getAge()
                    + "',joiningdate = '" + employee.getJoiningDate()
                    + "',address = '" + employee.getAddress()
                    + "',status = '" + status
                    + "',email = '" + employee.getEmail() + "' where employeeid = '" + employee.getEmployeeId() + "'";
            try {
                pst = connection.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Updated");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                try {
                    connection.close();
                    pst.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
    }

    public Employee getEmployeeById(String id) {
        Employee employee = new Employee();
        connection = DBConnect.connectDB();
        if (connection != null) {
            String sql = "Select name,employeeid,designation,age,joiningdate,address,status,email from employee where employeeid = '" + id + "'";
            try {
                pst = connection.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    employee.setName(rs.getString("name"));
                    employee.setEmployeeId(rs.getString("employeeid"));
                    employee.setDesignation(rs.getString("designation"));
                    employee.setAge(rs.getInt("age"));
                    employee.setJoiningDate(rs.getString("joiningdate"));
                    employee.setAddress(rs.getString("address"));
                    employee.setStatus(rs.getBoolean("status"));
                    employee.setEmail(rs.getString("email"));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                try {
                    connection.close();
                    pst.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
        return employee;
    }

    public ArrayList<Employee> getAllEmployee() {
        connection = DBConnect.connectDB();
        ArrayList<Employee> employees = new ArrayList<>();
        if (connection != null) {
            String sql = "Select name,employeeid,designation,age,joiningdate,address,status,email from employee";
            try {
                pst = connection.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    Employee e = new Employee();
                    e.setName(rs.getString("name"));
                    e.setEmployeeId(rs.getString("employeeid"));
                    e.setDesignation(rs.getString("designation"));
                    e.setAge(rs.getInt("age"));
                    e.setJoiningDate(rs.getString("joiningdate"));
                    e.setAddress(rs.getString("address"));
                    e.setStatus(rs.getBoolean("status"));
                    e.setEmail(rs.getString("email"));
                    employees.add(e);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                try {
                    connection.close();
                    pst.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
        return employees;
    }

    public void deleteEmployee(String id) {
        connection = DBConnect.connectDB();
        if (connection != null) {
            String sql = "delete from employee where employeeid = '" + id + "'";
            try {
                pst = connection.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Deleted");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                try {
                    connection.close();
                    pst.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
    }
}
