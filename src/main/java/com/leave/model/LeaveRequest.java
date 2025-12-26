package com.leave.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="leave_request")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveRequest {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer leaveId;

    @ManyToOne //one employee had many leave request
    @JoinColumn(name="emp_id", nullable=false)
    private Employee employee;

    @ManyToOne  
    @JoinColumn(name="leave_type_id", nullable=false)
    private LeaveType leaveType;

    @Column(nullable=false)
    private LocalDate startDate;

    @Column(nullable=false)
    private LocalDate endDate;

    private String reason;

    private String status = "PENDING";

    private LocalDateTime appliedOn = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name="approved_by")
    private Employee approvedBy;

    private LocalDateTime approvedOn;
}
