package com.leave.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leave.model.HolidayCalendar;

public interface HolidayCalendarRepository extends JpaRepository<HolidayCalendar, Integer> {}

