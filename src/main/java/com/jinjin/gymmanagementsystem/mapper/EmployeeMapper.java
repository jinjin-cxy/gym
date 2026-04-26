package com.jinjin.gymmanagementsystem.mapper;

import com.jinjin.gymmanagementsystem.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author shenjin
 * @date 2026/4/25
 */
@Mapper
public interface EmployeeMapper {
    Integer selectEmployeeCount();

    Employee selectEmployeeByEmployeeAccount(Integer EmployeeAccount);

    Boolean insertEmployee(Employee employee);

    List<Employee> findAll();

    Boolean updateEmployeeByEmployeeAccount(Employee employee);

    Boolean deleteEmployeeByEmployeeAccount(Integer employeeAccount);
}
