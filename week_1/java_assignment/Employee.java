// Q 6,7 Answers
import java.util.*;
import java.sql.*;

class Employee {
    private int id;
    private String name;
    private String department;

    public Employee(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public String getDepartment() { return department; }

    public void setName(String name) { this.name = name; }

    public void setDepartment(String department) { this.department = department; }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Dept: " + department;
    }
}

// 6) CRUD using ArrayList
class EmployeeCRUD {
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee emp) {
        employees.add(emp);
        System.out.println("Employee added.");
    }

    public void viewEmployees() {
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    public void updateEmployee(int id, String newName, String newDept) {
        for (Employee e : employees) {
            if (e.getId() == id) {
                e.setName(newName);
                e.setDepartment(newDept);
                System.out.println("Employee updated.");
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    public void deleteEmployee(int id) {
        Iterator<Employee> it = employees.iterator();
        while (it.hasNext()) {
            if (it.next().getId() == id) {
                it.remove();
                System.out.println("Employee deleted.");
                return;
            }
        }
        System.out.println("Employee not found.");
    }
}

// 7) CRUD using JDBC
class EmployeeJDBC {
    private static final String URL = "jdbc:mysql://localhost:3306/your_db";
    private static final String USER = "your_username";
    private static final String PASS = "your_password";

    public void insertEmployee(Employee e) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "INSERT INTO employees (id, name, department) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, e.getId());
            ps.setString(2, e.getName());
            ps.setString(3, e.getDepartment());
            ps.executeUpdate();
            System.out.println("Employee inserted into DB.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void viewEmployees() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM employees")) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("name") +
                        ", Dept: " + rs.getString("department"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateEmployee(int id, String name, String dept) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "UPDATE employees SET name=?, department=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, dept);
            ps.setInt(3, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Employee updated." : "Employee not found.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteEmployee(int id) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "DELETE FROM employees WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Employee deleted." : "Employee not found.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
