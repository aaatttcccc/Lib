package com.lib.library.event;

/**
 * 这是MyApplicationDiary接收的Event
 * Created by zhongjh on 2017/5/29.
 */
public class MyApplicationEvent {

    public Throwable getThrowable() {
        return throwable;
    }

    private Throwable throwable;


    public MyApplicationEvent(Throwable throwable) {
        super();
        this.throwable = throwable;
    }


}
