package com.github.auth.spring.boot.util;

import org.springframework.lang.NonNull;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.Serializable;
import java.util.Set;
import java.util.function.Function;

/**
 * @author czk
 * @date 2019-11-25
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -7115432878946262424L;

    /**
     * 状态码
     */
    private int code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T body;

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private static final int SUCCESS = 2000;

    private static final int ERROR = 4000;

    private static final int PARAMETER_VALIDATOR_ERROR = 4001;

    /**
     * 初始化一个统一返回对象,并执行参数验证,如果验证参数有问题,将不执行action
     *
     * @param request 请求参数
     * @param action  处理请求
     * @param <V>     参数类型
     * @param <T>     返回类型
     * @return Response对象
     */
    public static <V, T> Result<T> execute(@NonNull V request, Function<V, T> action) {
        Set<ConstraintViolation<V>> set = validator.validate(request);
        if (!set.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (ConstraintViolation<V> cv : set) {
                builder.append(cv.getPropertyPath().toString())
                    .append(cv.getMessage()).append("\n")
                    .append(",");
            }
            builder.deleteCharAt(builder.length() - 1);
            return Result.failed(PARAMETER_VALIDATOR_ERROR, builder.toString());
        }
        return Result.success(action.apply(request));
    }

    public static <T> Result<T> success() {
        return new Result<>(SUCCESS, "success", null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(SUCCESS, "success", data);
    }

    public static <T> Result<T> error() {
        return new Result<>(ERROR, "操作失败", null);
    }

    public static <T> Result<T> error(String msg, T data) {
        return new Result<>(ERROR, msg, data);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>(ERROR, msg, null);
    }

    public static <T> Result<T> failed(int code, String msg) {
        return new Result<>(code, msg, null);
    }

    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.body = data;
    }

    private Result() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
