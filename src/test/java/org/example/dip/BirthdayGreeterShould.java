package org.example.dip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.ZoneOffset;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BirthdayGreeterShould {
    private static final int CURRENT_MONTH = 7;
    private static final int CURRENT_YEAR = 2021;
    private static final int CURRENT_DAY_OF_MONTH = 9;
    private static final String EMPLOYEE_FIRST_NAME = "John";
    private static final String EMPLOYEE_LAST_NAME = "Doe";
    private static final LocalDate EMPLOYEE_DATE_OF_BIRTH = LocalDate.of(1980, 9, 10);
    private static final String EMPLOYEE_EMAIL = "john.doe@foobar.com";

    private EmployeeRepository employeeRepository;
    private BirthdayGreeter birthdayGreeter;
    private Employee employee;


    private final ByteArrayOutputStream consoleContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        ZoneOffset offset = ZoneOffset.UTC;
        Instant inst = LocalDate
                .of(CURRENT_YEAR, CURRENT_MONTH, CURRENT_DAY_OF_MONTH)
                .atStartOfDay(offset)
                .toInstant();
        Clock clock = Clock.fixed(inst, offset.normalized());
        employeeRepository = mock(EmployeeRepository.class);
        Notifiable notifiable = new EmailSender();
        birthdayGreeter = new BirthdayGreeter(employeeRepository, notifiable, clock);
        employee = new Employee(EMPLOYEE_FIRST_NAME, EMPLOYEE_LAST_NAME, EMPLOYEE_DATE_OF_BIRTH, EMPLOYEE_EMAIL);
    }

    @Test
    public void should_send_greeting_email_to_employee() {
        System.setOut(new PrintStream(consoleContent));
        when(employeeRepository.findEmployeesBornOn(MonthDay.of(CURRENT_MONTH, CURRENT_DAY_OF_MONTH))).thenReturn(Collections.singletonList(employee));

        birthdayGreeter.sendGreetings();

        String actual = consoleContent.toString();
        assertEquals("To:" + employee.getEmail() + ", Subject: Happy birthday!, Message: Happy birthday, dear " + employee.getFirstName()+"!", actual);
    }

}