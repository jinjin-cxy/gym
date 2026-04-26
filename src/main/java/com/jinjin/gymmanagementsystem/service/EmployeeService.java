package com.jinjin.gymmanagementsystem.service;

import com.jinjin.gymmanagementsystem.pojo.Admin;
import com.jinjin.gymmanagementsystem.pojo.Employee;

import java.util.List;

/**
 * @author shenjin
 * @date 2026/4/24
 */
public interface EmployeeService {
    Integer selectTotalCount();

    Employee selectEmployeeByEmployeeAccount(Integer employeeAccount);

    List<Employee> selectAllEmployee();

    Boolean insertEmployee(Employee employee);

    Boolean deleteEmployeeByEmployeeAccount(Integer employeeAccount);

    Boolean updateEmployeeByEmployeeAccount(Employee employee);
}
