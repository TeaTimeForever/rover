package rover.utils;

import java.util.Date;

/**
 * Created by eq on 11/03/16.
 */
public class RequestsQueue {

    private final Date[] queue;
    private final int capacity;
    private int head, last;
    private boolean filled;

    public RequestsQueue(int capacity){
        super();
        this.capacity = capacity;
        queue = new Date[capacity];
        head = last = 0;
        filled = false;
    }

    public RequestsQueue push(Date e){
        queue[head] = e;
        if(filled){
            head = increasePointer(head);
            last = increasePointer(last);
        } else {
            head++;
            filled = !(capacity - head > 1);
        }
        return this;
    }

    private int increasePointer(int pointer){
        return (pointer < capacity - 1)? pointer+1 : 0;
    }
}
