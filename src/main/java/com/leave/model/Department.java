package com.leave.model;

import java.time.LocalDateTime;

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
@Table(name = "department")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deptId;

    @Column(nullable = false, unique = true)
    private String deptName;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
