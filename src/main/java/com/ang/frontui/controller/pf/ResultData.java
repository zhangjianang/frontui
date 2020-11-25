package com.ang.frontui.controller.pf;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;


@Data
public class ResultData<T> {

    protected String msg;
    protected T data;
    protected int code;

    public ResultData() {
    }

    /**
     * 状态解析
     *
     * @param code
     * @param msg
     */
    public ResultData(int code, String msg) {
        setCode(code);
        setMsg(msg);
    }


    /**
     * 状态解析
     *
     * @param code
     * @param msg
     */
    public ResultData(int code, String msg, T data) {
        setCode(code);
        setMsg(msg);
        setData(data);
    }

    public static ResultData success(Object data) {
       ResultData ret = new ResultData(0, "操作成功", data);
        return ret;
    }


    public static ResultData fail(Integer code, String message) {
       ResultData ret = new ResultData(code, message);
        return ret;
    }
    public static ResultData success() {
        return ResultData.success(null);
    }

    private Map<String, Object> beanToMap(Object obj) {
        Map<String, Object> params = new HashMap<String, Object>(0);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));// 格式化时间(timestmap)
        params = objectMapper.convertValue(obj, new TypeReference<Map<String, Object>>() {
        });
        return params;
    }

}
