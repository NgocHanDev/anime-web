package vn.group04.animeweb.response;

import lombok.Data;

import java.util.Date;
@Data
public class MyResponse {
    private int status;
    private Object data;
    private String message;
    private Date date;
    public MyResponse(String message, int status, Object data){
        this.data = data;
        this.status = status;
        this.message = message;
        this.date = new Date(System.currentTimeMillis());
    }
    public MyResponse(){
        this.date = new Date(System.currentTimeMillis());
    }
}
