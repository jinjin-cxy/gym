package com.jinjin.gymmanagementsystem.controller;

import com.jinjin.gymmanagementsystem.pojo.Employee;
import com.jinjin.gymmanagementsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author shenjin
 * @date 2026/4/25
 */
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/selEmployeeList")
    public ResponseEntity<Map<String, Object>> getEmployeeList() {
        List<Employee> employees = employeeService.selectAllEmployee();
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("employeeList", employees);
        return ResponseEntity.ok(response);
    }

    @RequestMapping("/queryEmployee/{employeeAccount}")
    public ResponseEntity<Map<String, Object>> getEmployeeByEmployeeAccount(@PathVariable Integer employeeAccount) {
        Employee employee = employeeService.selectEmployeeByEmployeeAccount(employeeAccount);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("employee", employee);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<Map<String, Object>> addEmployee(@RequestBody Employee employee) {
        //工号随机生成
        Random random = new Random();
        String account1 = "1010";
        for (int i = 0; i < 5; i++) {
            account1 += random.nextInt(10);
        }
        Integer account = Integer.parseInt(account1);

        //获取当前日期
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowDay = simpleDateFormat.format(date);
        employee.setEmployeeAccount(account);
        employee.setEntryTime(nowDay);
        employeeService.insertEmployee(employee);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("employee", employee);
        response.put("message", "Employee added successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteEmployee/{employeeAccount}")
    public ResponseEntity<Map<String, Object>> deleteEmployee(@PathVariable Integer employeeAccount) {
        employeeService.deleteEmployeeByEmployeeAccount(employeeAccount);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Employee deleted successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<Map<String, Object>> updateEmployee(@RequestBody Employee employee) {
        employeeService.updateEmployeeByEmployeeAccount(employee);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("employee", employee);
        response.put("message", "Employee updated successfully");
        return ResponseEntity.ok(response);
    }
}
