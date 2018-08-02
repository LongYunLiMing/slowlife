package com.slowlife.bean;

import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.Table;

@Table(name = "employee")
public class Employee extends Model {

    private int merchentNumber;//商家id
    private int employeeNumber;//员工id，自增

    private String employeePhone;
    private String employeeRealName;
    private String employeeAuthority;
    private String employeeSex;


    public int getMerchentNumber() {
        return merchentNumber;
    }

    public void setMerchentNumber(int merchentNumber) {
        this.merchentNumber = merchentNumber;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getEmployeeRealName() {
        return employeeRealName;
    }

    public void setEmployeeRealName(String employeeRealName) {
        this.employeeRealName = employeeRealName;
    }

    public String getEmployeeAuthority() {
        return employeeAuthority;
    }

    public void setEmployeeAuthority(String employeeAuthority) {
        this.employeeAuthority = employeeAuthority;
    }

    public String getEmployeeSex() {
        return employeeSex;
    }

    public void setEmployeeSex(String employeeSex) {
        this.employeeSex = employeeSex;
    }
}
