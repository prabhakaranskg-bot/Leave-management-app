package com.leave.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leave.model.HolidayCalendar;
import com.leave.repository.HolidayCalendarRepository;

@Service
public class HolidayCalendarService {
	@Autowired
    private HolidayCalendarRepository holidayRepository;

    public HolidayCalendar saveHoliday(HolidayCalendar holiday) {
        return holidayRepository.save(holiday);
    }

    public List<HolidayCalendar> getAllHolidays() {
        return holidayRepository.findAll();
    }

    public void deleteHoliday(Integer id) {
        holidayRepository.deleteById(id);
    }
}
