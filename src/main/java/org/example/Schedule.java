package org.example;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Schedule {
    private String day;
    private String start;
    private String end;
    private String room;

    private static final Map<String, Integer> DAY_MAP = new HashMap<String, Integer>();
    static {
        DAY_MAP.put("MON", 2); DAY_MAP.put("TUE", 3); DAY_MAP.put("WED", 4);
        DAY_MAP.put("THU", 5); DAY_MAP.put("FRI", 6); DAY_MAP.put("SAT", 7);
        DAY_MAP.put("SUN", 1);
    }


    private Date parseDate(String time) {
        Integer dayOfWeek = DAY_MAP.get(this.day);
        if (dayOfWeek == null) return null;

        int dayOffset = dayOfWeek - 2;
        String dateString = "1970-01-" + (5 + dayOffset) + " " + time;

        try {

            return new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateString);
        } catch (ParseException e) {

            return null;
        }
    }

    public Date getStartDate() {
        return parseDate(this.start);
    }

    public Date getEndDate() {
        return parseDate(this.end);
    }

    public String getDay() { return day; }
    public void setDay(String day) { this.day = day; }
    public String getStart() { return start; }
    public void setStart(String start) { this.start = start; }
    public String getEnd() { return end; }
    public void setEnd(String end) { this.end = end; }
    public String getRoom() { return room; }
    public void setRoom(String room) { this.room = room; }
}