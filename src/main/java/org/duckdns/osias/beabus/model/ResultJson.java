package org.duckdns.osias.beabus.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class ResultJson {

    public static Object getResult(String message, int code){
        ResultObject result = new ResultObject();
        result.message = message;
        result.code = code;
        return result;
    }
    public static Object getResult(String message, int code, String data){
        ResultObject result = new ResultObject();
        result.message = message;
        result.code = code;
        result.data = data;
        return result;
    }
}
@Getter
@Setter
class ResultObject {
    public String message;
    public int code;
    public String data;
}