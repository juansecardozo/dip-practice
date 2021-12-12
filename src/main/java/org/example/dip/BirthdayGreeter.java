package org.example.dip;

import java.time.Clock;
import java.time.MonthDay;

public class BirthdayGreeter {
    private final EmployeeRepository employeeRepository;
    private final Notifiable notifiable;
    private final Clock clock;

    public BirthdayGreeter(EmployeeRepository employeeRepository, Notifiable notifiable, Clock clock) {
        this.employeeRepository = employeeRepository;
        this.notifiable = notifiable;
        this.clock = clock;
    }

    public void sendGreetings() {
        MonthDay date = MonthDay.now(clock);
        employeeRepository.findEmployeesBornOn(date)
                .forEach(notifiable::send);
    }

}
