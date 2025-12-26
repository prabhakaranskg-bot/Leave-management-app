package com.leave.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leave.model.HolidayCalendar;
import com.leave.service.HolidayCalendarService;

@RestController
@RequestMapping("/api/holidays")
@CrossOrigin(origins = "*")
public class HolidayCalendarController {
	@Autowired
    private HolidayCalendarService holidayService;

    @PostMapping
    public HolidayCalendar addHoliday(@RequestBody HolidayCalendar holiday) {
        return holidayService.saveHoliday(holiday);
    }

    @GetMapping
    public List<HolidayCalendar> getAllHolidays() {
        return holidayService.getAllHolidays();
    }

    @DeleteMapping("/{id}")
    public void deleteHoliday(@PathVariable Integer id) {
        holidayService.deleteHoliday(id);
    }
}
