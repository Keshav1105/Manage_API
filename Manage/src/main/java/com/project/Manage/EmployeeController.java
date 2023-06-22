package com.project.Manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
public class EmployeeController {

    @Autowired
    private EmployeeRepositiory employeeRepositiory;

    @GetMapping("/employee")
    public List<EmployeeEntity> getAll(){
        return employeeRepositiory.findAll();
    }
    @PostMapping("/employee")
    public EmployeeEntity createEmployee(@RequestParam String firstname, @RequestParam String lastname ,@RequestParam String email ,@RequestParam int id) {

        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(id);
        employee.setFirstName(firstname);
        employee.setLastName(lastname);
        employee.setEmailId(email);

        return employeeRepositiory.save(employee);

    }
    @GetMapping("/employee/{id}")
    @ResponseBody
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable long id) {
        EmployeeEntity employee = employeeRepositiory.findById(Long.valueOf(id)).orElseThrow(() -> new Exception("Employee not exist with id :" + id));

        return ResponseEntity.ok(employee);
    }

    @PutMapping("/employee/update/{id}")
    public ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable long id,@RequestParam String firstname, @RequestParam String lastname ,@RequestParam String email ){

        EmployeeEntity employee = employeeRepositiory.findById((long) id).orElseThrow(() -> new Exception("Employee not exist with id :" + id));

        employee.setFirstName(firstname);
        employee.setLastName(lastname);
        employee.setEmailId(email);

        EmployeeEntity updatedEmployee = employeeRepositiory.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }


    @DeleteMapping("/employee/delete/{id}")
    public String deleteEmployee(@PathVariable long id){
        EmployeeEntity employee = employeeRepositiory.findById((long) id).orElseThrow(() -> new Exception("Employee not exist with id :" + id));


        employeeRepositiory.delete(employee);

        return "Deleted";

    }



}
