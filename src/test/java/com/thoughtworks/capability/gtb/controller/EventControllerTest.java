package com.thoughtworks.capability.gtb.controller;

import com.thoughtworks.capability.gtb.vo.EventType;
import com.thoughtworks.capability.gtb.vo.EventVo;
import com.thoughtworks.capability.gtb.vo.UserVo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EventController.class)
class EventControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldReceiveJson() throws Exception {
    String postJson = "{\n" +
            "    \"id\": \"1\",\n" +
            "    \"name\": \"下载文件\",\n" +
            "    \"type\": \"D\",\n" +
            "    \"time\": 1590050488701,\n" +
            "    \"userId\": \"3\",\n" +
            "    \"userName\": \"张三\"\n" +
            "}";
    mockMvc.perform(post("/events")
            .contentType(MediaType.APPLICATION_JSON)
            .content(postJson))
            .andExpect(status().isCreated());
  }

  @Test
  void shouldSendJson() throws Exception {
    UserVo user = new UserVo("3", "张三");
    EventVo event = new EventVo("5", "下载文件", EventType.DOWNLOAD, new Date(), user);

    mockMvc.perform(get("/events/5"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(event.getId()))
            .andExpect(jsonPath("$.name").value(event.getName()))
            .andExpect(jsonPath("$.type").value(event.getType().toString()))
            .andExpect(jsonPath("$.time").isNumber())  // new Date() 在方法和测试中产生的时间总是不同的
            .andExpect(jsonPath("$.userId").value(event.getUser().getId()))
            .andExpect(jsonPath("$.userName").value(event.getUser().getName()));
  }
}