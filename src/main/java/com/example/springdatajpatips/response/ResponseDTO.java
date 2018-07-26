package com.example.springdatajpatips.response;

public class ResponseDTO<T> {

  private T data;

  private String message = "success";

  private int status = 200;

  private int code;

  public ResponseDTO(T data) {
    this.data = data;
  }

  public ResponseDTO() {}


  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

}
