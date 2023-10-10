package org.community.common;

import java.io.Serializable;

public class Result<T> implements Serializable {

    /**
     * 状态码
     */
    private String status;
    /**
     * 状态信息,错误描述.
     */
    private String message;
    /**
     * 数据.
     */
    private T data;

    private Result(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    private Result(String status, String message) {
        this.status = status;
        this.message = message;
    }

    private Result(String message) {
        this.message = message;
    }

    /**
     * 创建一个带有状态、消息和数据的结果对象.
     *
     * @param status  状态
     * @param message 消息内容
     * @param data    数据
     * @return 结构数据
     */
    public static <T> Result<T> buildResult(Constants.Status status, String message, T data) {
        return new Result<T>(status.getCode(), message, data);
    }

    /**
     * 创建一个带有状态、消息和数据的结果对象.
     *
     * @param status  状态
     * @param message 消息内容
     * @return 结构数据
     */
    public static <T> Result<T> buildResult(Constants.Status status, String message) {
        return new Result<T>(status.getCode(), message);
    }

    /**
     * 创建一个带有状态和数据的结果对象.
     *
     * @param status 状态
     * @param data   数据
     * @return 结构数据
     */
    public static <T> Result<T> buildResult(Constants.Status status, T data) {
        return new Result<T>(status.getCode(), status.getReason(), data);
    }

    /**
     * 创建一个带有状态的结果对象.
     *
     * @param status 状态
     * @return 结构数据
     */
    public static <T> Result<T> buildResult(Constants.Status status) {
        return new Result<T>(status.getCode(), status.getReason());
    }

    /**
     * 获取状态。
     *
     * @return 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 获取消息内容。
     *
     * @return 消息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 获取数据内容。
     *
     * @return 数据
     */
    public T getData() {
        return data;
    }


}
