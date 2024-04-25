
import java.util.ArrayList;
import java.util.List;

// superclass
class Employee {
    private String name;
    private int id;
    private boolean present;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
        this.present = false;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public boolean isPresent() {
        return present;
    }

    // Metode menandai kehadiran
    public void markAttendance() {
        present = true;
    }

    // Metode mereset kehadiran
    public void resetAttendance() {
        present = false;
    }

    // Metode mencetak anggota tim
    public void printTeam() {
        System.out.println("Employee ID: " + id + ", Name: " + name);
    }
}

// Kelas Manager sebagai subclass dari Employee
class Manager extends Employee {
    private List<Employee> teamMembers;

    public Manager(String name, int id) {
        super(name, id);
        teamMembers = new ArrayList<>();
    }

    // Metode menambahkan anggota tim
    public void addTeamMember(Employee employee) {
        teamMembers.add(employee);
    }

    // Metode untuk menghapus anggota tim
    public void removeTeamMember(Employee employee) {
        teamMembers.remove(employee);
    }

    // Override metode untuk mencetak anggota tim
    @Override
    public void printTeam() {
        super.printTeam(); // Panggil metode printTeam() dari superclass
        for (Employee employee : teamMembers) {
            employee.printTeam();
        }
    }
}

// untuk mengelola kehadiran karyawan
class AttendanceManagementSystem {
    private List<Employee> employees;

    public AttendanceManagementSystem() {
        employees = new ArrayList<>();
    }

    // Metode untuk menambahkan karyawan
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    // Metode untuk mencatat kehadiran
    public void markEmployeeAttendance(int employeeId) {
        for (Employee employee : employees) {
            if (employee.getId() == employeeId) {
                employee.markAttendance();
                System.out.println(employee.getName() + "'s attendance marked.");
                return;
            }
        }
        System.out.println("Employee with ID " + employeeId + " not found.");
    }

    // Metode untuk mereset kehadiran
    public void resetEmployeeAttendance(int employeeId) {
        for (Employee employee : employees) {
            if (employee.getId() == employeeId) {
                employee.resetAttendance();
                System.out.println(employee.getName() + "'s attendance reset.");
                return;
            }
        }
        System.out.println("Employee with ID " + employeeId + " not found.");
    }

    // Metode untuk mencetak laporan kehadiran
    public void printAttendanceReport() {
        System.out.println("Attendance Report:");
        for (Employee employee : employees) {
            System.out.println("Employee ID: " + employee.getId() + ", Name: " + employee.getName() + ", Present: " + (employee.isPresent() ? "Yes" : "No"));
        }
    }
}

// Kelas Main sebagai kelas utama
public class Main {
    public static void main(String[] args) {
        // Membuat objek sistem manajemen kehadiran
        AttendanceManagementSystem attendanceSystem = new AttendanceManagementSystem();

        // Menambahkan beberapa karyawan dan manajer
        Employee john = new Employee("John", 1);
        Employee alice = new Employee("Alice", 2);
        Manager bob = new Manager("Bob (Manager)", 3);

        attendanceSystem.addEmployee(john);
        attendanceSystem.addEmployee(alice);
        attendanceSystem.addEmployee(bob);

        // Menambahkan beberapa anggota tim kepada manajer
        bob.addTeamMember(john);
        bob.addTeamMember(alice);

        // Mencatat kehadiran karyawan
        attendanceSystem.markEmployeeAttendance(1);
        attendanceSystem.markEmployeeAttendance(3);

        // Melihat laporan kehadiran
        attendanceSystem.printAttendanceReport();

        // Mereset kehadiran karyawan
        attendanceSystem.resetEmployeeAttendance(1);

        // Melihat laporan kehadiran setelah mereset
        attendanceSystem.printAttendanceReport();

        // Menampilkan anggota tim manajer
        bob.printTeam();
    }
}
