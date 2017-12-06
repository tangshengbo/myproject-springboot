package com.tangshengbo.util;

import java.util.Date;

/**
 * Created by TangShengBo on 2017-12-06.
 * 代表 [start, end] 日期区间
 */
public class DateRange {

    public DateRange() {
    }

    public DateRange(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public boolean isValid() {
        return (start == null && end == null)
                || (start != null && end != null);
    }

    private Date start;

    private Date end;

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
