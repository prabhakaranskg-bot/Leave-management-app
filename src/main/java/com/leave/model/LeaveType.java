package com.leave.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="leave_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveType {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer leaveTypeId;

    @Column(nullable=false, unique=true)
    private String leaveCode;

    @Column(nullable=false)
    private String leaveName;

    @Column(nullable=false)
    private Integer annualAllocation;

    private Boolean carryForward = false;
}
