package com.thoughtworks.capability.gtb.controller;

import com.thoughtworks.capability.gtb.vo.PersonVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class PersonController {

  @GetMapping("/persons/{id}")
  public PersonVo getPerson(@PathVariable("id") String id) {
    Map<String, PersonVo> persons = Stream.of(
            new PersonVo("3", null, "张三", null),
            new PersonVo("4", 25, "李四", "Everything"))
            .collect(Collectors.toMap(PersonVo::getId, Function.identity()));
    return persons.getOrDefault(id, null);
  }
}
