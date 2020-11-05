package com.thoughtworks.capability.gtb.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonVo {

  private String id;
  private Integer age = 0;
  private String name;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String hobby;

  @JsonProperty("age")
  public Integer getAgeAlways() {
    return age == null ? 0 : age;
  }
}
