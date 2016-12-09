package com.sa.finalproject.DAO;


import java.util.List;
import com.sa.finalproject.entity.Employee;


public interface EmployeeDAO {
	public List<Employee> getList(); //列出員工明細
	public Employee getAEmployee(long employeeID);
	
}
