package com.study.java8.datetime;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

/**
 * @Author: H13995 魏喆
 * @Description: 
 * @Date: Created in 上午9:39 2018/7/20
 * @Modified: by 
 */
public class DifferenceBetweenLocalDateTime {


    static final int MINUTES_PER_HOUR = 60;
    static final int SECONDS_PER_MINUTE = 60;
    static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;

    public static void main(String[] args) {
//        LocalDateTime toDateTime = LocalDateTime.of(2014, 9, 9, 19, 46, 45);
//        LocalDateTime fromDateTime = LocalDateTime.of(1984, 12, 16, 7, 45, 55);

        LocalDateTime toDateTime = LocalDateTime.of(2014, 9, 10, 6, 40, 45);
        LocalDateTime fromDateTime = LocalDateTime.of(1984, 12, 16, 7, 45, 55);

        Period period = getPeriod(fromDateTime, toDateTime);
        long time[] = getTime(fromDateTime, toDateTime);

        System.out.println(period.getYears() + " years " +
                period.getMonths() + " months " +
                period.getDays() + " days " +
                time[0] + " hours " +
                time[1] + " minutes " +
                time[2] + " seconds.");



//        LocalDateTime fromDateTime = LocalDateTime.of(1984, 12, 16, 7, 45, 55);
//        LocalDateTime toDateTime = LocalDateTime.of(2014, 9, 10, 6, 40, 45);

        LocalDateTime tempDateTime = LocalDateTime.from( fromDateTime );

        long years = tempDateTime.until( toDateTime, ChronoUnit.YEARS);
        tempDateTime = tempDateTime.plusYears( years );

        long months = tempDateTime.until( toDateTime, ChronoUnit.MONTHS);
        tempDateTime = tempDateTime.plusMonths( months );

        long days = tempDateTime.until( toDateTime, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays( days );


        long hours = tempDateTime.until( toDateTime, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours( hours );

        long minutes = tempDateTime.until( toDateTime, ChronoUnit.MINUTES);
        tempDateTime = tempDateTime.plusMinutes( minutes );

        long seconds = tempDateTime.until( toDateTime, ChronoUnit.SECONDS);

        System.out.println( years + " years " +
                months + " months " +
                days + " days " +
                hours + " hours " +
                minutes + " minutes " +
                seconds + " seconds.");



        minutes = ChronoUnit.MINUTES.between(fromDateTime, toDateTime);
        hours = ChronoUnit.HOURS.between(fromDateTime, toDateTime);
        seconds = ChronoUnit.SECONDS.between(fromDateTime, toDateTime);

        System.out.println( years + " years " +
                months + " months " +
                days + " days " +
                hours + " hours " +
                minutes + " minutes " +
                seconds + " seconds.");

        Duration dur = Duration.between(fromDateTime, toDateTime);
        long millis = dur.toMillis();

        System.out.println(String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis) -
                        TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(millis)),
                TimeUnit.MILLISECONDS.toMinutes(millis) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))));


        Period timePeriod = Period.between(fromDateTime.toLocalDate(), toDateTime.toLocalDate());

        System.out.println( timePeriod.getYears() + " years " +
                timePeriod.getMonths() + " months " +
                timePeriod.getDays() + " days " +
                hours + " hours " +
                minutes + " minutes " +
                seconds + " seconds.");


    }

    private static Period getPeriod(LocalDateTime dob, LocalDateTime now) {
        return Period.between(dob.toLocalDate(), now.toLocalDate());
    }

    private static long[] getTime(LocalDateTime dob, LocalDateTime now) {
        LocalDateTime today = LocalDateTime.of(now.getYear(),
                now.getMonthValue(), now.getDayOfMonth(), dob.getHour(), dob.getMinute(), dob.getSecond());
        Duration duration = Duration.between(today, now);

        long seconds = duration.getSeconds();

        long hours = seconds / SECONDS_PER_HOUR;
        long minutes = ((seconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE);
        long secs = (seconds % SECONDS_PER_MINUTE);

        return new long[]{hours, minutes, secs};
    }
}
