package org.example.dip;

public class EmailSender implements Notifiable {
    @Override
    public void send(Employee employee) {
        final String text = String
                .format("To:%s, Subject: Happy birthday!, Message: Happy birthday, dear %s!",
                        employee.getEmail(),
                        employee.getFirstName());

        System.out.print(text);
    }
}
