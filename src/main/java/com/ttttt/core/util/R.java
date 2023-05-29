package com.ttttt.core.util;

import lombok.Data;
import lombok.experimental.Accessors;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;

@Data
@Schema(description = "固定响应格式")
@Accessors(chain = true)
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 编码：0表示成功，其他值表示失败
     */
    @Schema(description = "编码：200，表示成功，其他值表示失败")
    private int code = 200;
    /**
     * 消息内容
     */
    @Schema(description = "消息内容")
    private String msg = "请求成功";
    /**
     * 响应数据
     */
    @Schema(description = "响应数据")
    private T data;

    public R() {

    }

    public R(int code, String msg, T data) {
        this.setCode(code);
        this.setMsg(msg);
        this.setData(data);
    }

    public static <T> R<T> ok(T data) {
        R<T> result = new R<>();
        result.setData(data);
        return result;
    }

    public static <T> R<T> ok() {
        return new R<>();
    }

    public static <T> R<T> error() {
        R<T> result = new R<>();
        result.code = 500;
        return result;
    }

    public static <T> R<T> error(int code) {
        R<T> result = new R<>();
        result.code = code;
        return result;
    }

    public static <T> R<T> error(String msg) {
        R<T> result = new R<>();
        result.code = 500;
        result.msg = msg;
        return result;
    }

    public R<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    // 构建指定状态码
    public static <T> R<T> get(int code, String msg, T data) {
        return new R<>(code, msg, data);
    }
}
