package com.example.springdatajpatips.response;

public class IdNameDTO {

  private Long id;

  private String name;

  public String getName() {
    return name;
  }

  public IdNameDTO() {
  }
  
  public IdNameDTO(Long id, String name) {
    this.name = name;
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

}
