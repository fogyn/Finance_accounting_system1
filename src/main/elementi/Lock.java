package main.elementi;

import java.io.Serializable;

public class Lock{
    private Object locker;

    public Lock(){
        locker = new Object();
    }

    public Object getLocker() {
        return locker;
    }

    public void setLocker(Object locker) {
        this.locker = locker;
    }
}
