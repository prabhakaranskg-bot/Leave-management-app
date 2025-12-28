package com.leave.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.leave.model.HolidayCalendar;
import com.leave.repository.HolidayCalendarRepository;

@Service
public class HolidayCalendarService {
	@Autowired
    private HolidayCalendarRepository holidayRepository;

	@Autowired
	 private JdbcTemplate jdbcTemplate;
	
    public HolidayCalendar saveHoliday(HolidayCalendar holiday) {
        return holidayRepository.save(holiday);
    }

    public List<HolidayCalendar> getAllHolidays() {
        return holidayRepository.findAll();
    }

    public void deleteHoliday(Integer id) {
        holidayRepository.deleteById(id);
        resetSequenceIfEmpty();
    }

    public HolidayCalendar updateHoliday(Integer id, HolidayCalendar holiday) {
        holiday.setHolidayId(id);  // ensure update, not insert
        return holidayRepository.save(holiday);
    }
    
    /**
     * Resets AUTO_INCREMENT to 1 if table is empty
     */
    private void resetSequenceIfEmpty() {
        Long count = holidayRepository.count();
        if (count == 0) {
            jdbcTemplate.execute("ALTER TABLE holiday_calendar AUTO_INCREMENT = 1");
        }
    }
}
