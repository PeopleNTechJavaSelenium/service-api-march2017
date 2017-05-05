package resources.databases;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by mrahman on 5/2/17.
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeProfile {

    public String _id;
    public String empEmail;
    public String password;
    public String empName;
    public String salary;
    public String department;

    public EmployeeProfile(){}

    public EmployeeProfile(String empEmail, String password){
        this.empEmail = empEmail;
        this.password = password;
    }

    public EmployeeProfile(String _id,String empEmail,String empName, String salary, String department) {
        this._id = _id;
        this.empEmail = empEmail;
        this.empName = empName;
        this.salary = salary;
        this.department = department;
    }
    public EmployeeProfile(String _id, String empEmail, String password, String empName, String salary, String department) {
        this._id = _id;
        this.empEmail = empEmail;
        this.password = password;
        this.empName = empName;
        this.salary = salary;
        this.department = department;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
