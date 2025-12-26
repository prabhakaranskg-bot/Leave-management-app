package com.leave.model;

import java.time.LocalDate;

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
@Table(name="holiday_calendar")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HolidayCalendar {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer holidayId;

    @Column(nullable=false, unique=true)
    private LocalDate holidayDate;

    @Column(nullable=false)
    private String description;
}
