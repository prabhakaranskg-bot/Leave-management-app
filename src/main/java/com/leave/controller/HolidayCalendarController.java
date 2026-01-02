package com.leave.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leave.model.HolidayCalendar;
import com.leave.service.HolidayCalendarService;

@RestController
@RequestMapping("/api/holidays")
public class HolidayCalendarController {
	@Autowired
	private HolidayCalendarService holidayService;

	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public HolidayCalendar addHoliday(@RequestBody HolidayCalendar holiday) {
		return holidayService.saveHoliday(holiday);
	}

	@GetMapping
	@PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','EMPLOYEE')")
	public List<HolidayCalendar> getAllHolidays() {
		return holidayService.getAllHolidays();
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public HolidayCalendar updateHoliday(@PathVariable Integer id, @RequestBody HolidayCalendar holiday) {
		return holidayService.updateHoliday(id, holiday);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteHoliday(@PathVariable Integer id) {
		holidayService.deleteHoliday(id);
	}
}
