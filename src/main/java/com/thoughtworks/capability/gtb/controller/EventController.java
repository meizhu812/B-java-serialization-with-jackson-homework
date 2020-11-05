package com.thoughtworks.capability.gtb.controller;

import com.thoughtworks.capability.gtb.vo.EventType;
import com.thoughtworks.capability.gtb.vo.EventVo;
import com.thoughtworks.capability.gtb.vo.UserVo;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class EventController {

  @GetMapping("/events/{id}")
  public EventVo getEventById(@PathVariable("id") String id) {
    UserVo user = new UserVo("3", "张三");
    return new EventVo(id, "下载文件", EventType.DOWNLOAD, new Date(), user);
  }

  @PostMapping("/events")
  @ResponseStatus(HttpStatus.CREATED)
  public void createEvent(@RequestBody EventVo event) {
    log.info("create event: {}", event);
  }
}
