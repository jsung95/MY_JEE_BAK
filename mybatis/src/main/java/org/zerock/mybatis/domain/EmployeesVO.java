package org.zerock.mybatis.domain;

import java.sql.Timestamp;

import lombok.Value;

@Value
public class EmployeesVO {
	private Integer EMPLOYEE_ID;
	private String FIRST_NAME;
	private String LAST_NAME;
	private String EMAIL;
	private String PHONE_NUMBER;
	private Timestamp HIRE_DATE;
	private String JOB_ID;
	private Double SALARY;
	private Double COMMISSION_PCT;
	private Integer MANAGER_ID;
	private Integer DEPARTMENT_ID;	
}
