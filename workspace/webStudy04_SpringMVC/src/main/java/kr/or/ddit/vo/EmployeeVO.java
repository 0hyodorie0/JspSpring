package kr.or.ddit.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of= "employeeId")
public class EmployeeVO {
	@NotNull
	private Integer employeeId;
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	private String email;
	private String phoneNumber;
	@NotNull
	private String hireDate;
	@NotBlank
	private String jobId;
	private Integer salary;
	private Integer commissionPct;
	private Integer managerId;
	private Integer departmentId;
	private String empName;
	
	private int chlidCnt;
}
