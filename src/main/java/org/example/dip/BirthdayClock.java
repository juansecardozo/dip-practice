package org.example.dip;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public class BirthdayClock extends Clock {
    @Override
    public ZoneId getZone() {
        return null;
    }

    @Override
    public Clock withZone(ZoneId zone) {
        return null;
    }

    @Override
    public Instant instant() {
        return null;
    }
}
