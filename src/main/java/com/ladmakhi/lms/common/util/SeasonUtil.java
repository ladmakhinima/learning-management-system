package com.ladmakhi.lms.common.util;

import com.ladmakhi.lms.models.SeasonType;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class SeasonUtil {
    public SeasonType getSeasonBasedOnDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        if (month >= 1 && month <= 3) {
            //بهار
            return SeasonType.SPRING;
        } else if (month >= 4 && month <= 6) {
            //تابستان
            return SeasonType.SUMMER;
        } else if (month >= 7 && month <= 9) {
            // پاییز
            return SeasonType.FALL;
        } else if (month >= 10 && month <= 12) {
            // زمستان
            return SeasonType.WINTER;
        }
        return null;
    }
}
