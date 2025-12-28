package com.leave.dto;

import lombok.Data;

@Data
public class LeaveRequestDTO {
	private Integer employeeId;
    private Integer leaveTypeId;
    private String startDate;
    private String endDate;
    private String reason;
}
