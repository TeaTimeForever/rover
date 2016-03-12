package rover.utils;

import java.util.Date;

/**
 * Created by eq on 11/03/16.
 */
public class RequestsQueue {

    private final Date[] queue;
    private final int capacity;
    private final long period;
    private int head, last;
    private boolean filled;

    public RequestsQueue(int capacity, long period){
        super();
        this.capacity = capacity;
        this.period = period;
        queue = new Date[capacity];
        head = -1;
        last = 0;
        filled = false;
    }

    /**
     * @return true if next req is allowed, and false if not;
     * p.s. i know it looks questionably, but it is best place for this return;
     */
    public boolean push(Date e){
        if(filled){
            head = increasePointer(head);
            last = increasePointer(last);
            queue[head] = e;
            return getDiffBtwLastAndCurrentReq() > period;
        } else {
            head++;
            filled = !(capacity - head > 1);
            queue[head] = e;
            return true;
        }
    }

    public Date getLastDate(){
        return queue[last];
    }

    private int increasePointer(int pointer){
        return (pointer < capacity - 1)? pointer+1 : 0;
    }

    //FIXME: refactor this later; use only 1 variable [head or last] than "filled" also will disappear
    private long getDiffBtwLastAndCurrentReq() {
        if(queue[last] == null) {
            return 0;
        }
        return queue[head].getTime() - queue[last].getTime();
    }
}
