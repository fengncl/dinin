package com.ttttt.core.util;

import lombok.Data;
import lombok.experimental.Accessors;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;

@Data
@Schema(description = "Fixed response format")
@Accessors(chain = true)
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * Code: 0 indicates success, other values indicate failure
     */
    @Schema(description = "Code: 200 indicates success, other values indicate failure")
    private int code = 200;
    /**
     * Message content
     */
    @Schema(description = "Message content")
    private String msg = "Request successful";
    /**
     * Response data
     */
    @Schema(description = "Response data")
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

    // Build with specified status code
    public static <T> R<T> get(int code, String msg, T data) {
        return new R<>(code, msg, data);
    }
}
