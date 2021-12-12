package org.example.dip;

import java.time.MonthDay;
import java.util.Date;

public interface Schedulable {
    public MonthDay monthDay(Date date);
}
