package org.example;

import java.util.Date;

public interface Schedulable {
    Date getStartTime();
    Date getEndTime();
    boolean overlapsWith(Schedulable other);
}
