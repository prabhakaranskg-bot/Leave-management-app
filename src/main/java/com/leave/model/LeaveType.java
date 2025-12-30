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
	@Column(name = "leave_type_id",nullable=false)
    private Integer leaveTypeId;

    @Column(name = "leave_code",nullable=false, unique=true)
    private String leaveCode;

    @Column(name = "leave_name",nullable=false)
    private String leaveName;

    @Column(name = "annual_allocation",nullable=false)
    private Integer annualAllocation;

    @Builder.Default
    @Column(name = "carry_forward",nullable=false)
    private Boolean carryForward = false;
}
