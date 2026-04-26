package com.jinjin.gymmanagementsystem.service.ipml;

import com.jinjin.gymmanagementsystem.mapper.EmployeeMapper;
import com.jinjin.gymmanagementsystem.pojo.Employee;
import com.jinjin.gymmanagementsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shenjin
 * @date 2026/4/25
 */
@Service
public class EmployeeServiceIpml implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public Integer selectTotalCount() {
        return employeeMapper.selectEmployeeCount();
    }

    @Override
    public Employee selectEmployeeByEmployeeAccount(Integer employeeAccount) {
        return employeeMapper.selectEmployeeByEmployeeAccount(employeeAccount);
    }

    @Override
    public List<Employee> selectAllEmployee() {
        return employeeMapper.findAll();
    }

    @Override
    public Boolean insertEmployee(Employee employee) {
        return employeeMapper.insertEmployee(employee);
    }

    @Override
    public Boolean deleteEmployeeByEmployeeAccount(Integer employeeAccount) {
        return employeeMapper.deleteEmployeeByEmployeeAccount(employeeAccount);
    }

    @Override
    public Boolean updateEmployeeByEmployeeAccount(Employee employee) {
        return employeeMapper.updateEmployeeByEmployeeAccount(employee);
    }

}
