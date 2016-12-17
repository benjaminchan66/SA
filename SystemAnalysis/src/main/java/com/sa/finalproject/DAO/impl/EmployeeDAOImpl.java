package com.sa.finalproject.DAO.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.DataSource;
import com.sa.finalproject.entity.Employee;

import org.springframework.beans.factory.annotation.Autowired;

import com.sa.finalproject.entity.BillOfPurchase;

public class EmployeeDAOImpl {
	private DataSource dataSource;
	private Connection conn = null;
	private ResultSet rs = null;
	private PreparedStatement smt = null;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Employee> getList() {
		String sql = "SELECT * FROM Employee";
		return getList(sql);
	}

	public List<Employee> getList(String sql) {
		List<Employee> Employee = new ArrayList<Employee>();
		// will need this part later

		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			rs = smt.executeQuery();
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setId(rs.getString("employee_id"));
				employee.setName(rs.getString("employee_name"));
				employee.setPhone(rs.getString("employee_phone"));
				employee.setAddress(rs.getString("employee_address"));
				employee.setLevel(rs.getString("employee_level"));
				employee.setDep(rs.getString("employee_dep"));
				Employee.add(employee);

			}
			rs.close();
			smt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return Employee;
	}

	public Employee getAEmployee(long employeeID) {
		Employee employee = new Employee();
		String sql = "SELECT * FROM Employee WHERE employee_id = ?";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setLong(1, employeeID);
			rs = smt.executeQuery();

			if (rs.next()) {
				employee.setName(rs.getString("employee_name"));
				employee.setPhone(rs.getString("employee_phone"));
				employee.setAddress(rs.getString("employee_address"));
				employee.setLevel(rs.getString("employee_level"));
				employee.setDep(rs.getString("employee_dep"));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return employee;
	}

}
