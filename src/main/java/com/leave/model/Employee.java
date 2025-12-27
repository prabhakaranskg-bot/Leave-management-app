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
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer empId;

	    @Column(nullable=false)
	    private String firstName;

	    private String lastName;

	    @Column(nullable=false, unique=true)
	    private String email;

	    private String phone;

	    @ManyToOne
	    @JoinColumn(name="dept_id")
	    private Department department;

	    @Column(nullable=false)
	    private LocalDate joinDate;

	    @Builder.Default
	    @Column(name="status")
	    private String status = "ACTIVE";

	    @Builder.Default
	    @Column(name="created_at")
	    private LocalDateTime createdAt = LocalDateTime.now();
}
