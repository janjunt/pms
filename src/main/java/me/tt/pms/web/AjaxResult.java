package me.tt.pms.web;

/**
 * @ClassName: AjaxResult
 * @Description: ajax请求数据
 * @author: tongjianjun@itiaoling.com
 * @date 2018/8/8 17:32
 */
public class AjaxResult {
    /**
     * true:成功；false:失败
     */
    public boolean success;
    /**
     * 消息
     */
    public String message;
    /**
     * 数据
     */
    public Object data;

    private AjaxResult(boolean success, String message, Object data){
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static AjaxResult success(){
        return new AjaxResult(true, "", null);
    }

    public static AjaxResult success(Object data){
        return new AjaxResult(true, "", data);
    }

    public static AjaxResult error(String message){
        return new AjaxResult(false, message, null);
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}