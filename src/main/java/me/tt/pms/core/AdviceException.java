package me.tt.pms.core;

/**
 * @ClassName: AdviceException
 * @Description: 用于消息通知的异常
 * @author: tongjianjun@itiaoling.com
 * @date 2018/1/10 10:26
 */
public class AdviceException extends RuntimeException{
    public AdviceException() {
        super();
    }
    public AdviceException(String format, Object... args) {
        super(String.format(format, args));
    }
    public AdviceException(Throwable cause) { super(cause); }
    public AdviceException(String format, Throwable cause, Object... args) {
        super(String.format(format, args), cause);
    }
}