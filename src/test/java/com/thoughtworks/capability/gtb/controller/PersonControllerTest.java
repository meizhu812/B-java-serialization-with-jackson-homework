package com.thoughtworks.capability.gtb.controller;

import com.thoughtworks.capability.gtb.vo.PersonVo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PersonController.class)
class PersonControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldSendCompleteJson() throws Exception {
    PersonVo personComplete = new PersonVo("4", 25, "李四", "Everything");
    mockMvc.perform(get("/persons/4"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(personComplete.getId()))
            .andExpect(jsonPath("$.age").value(personComplete.getAge()))
            .andExpect(jsonPath("$.name").value(personComplete.getName()))
            .andExpect(jsonPath("$.hobby").value(personComplete.getHobby()));
  }

  @Test
  void shouldSendIncompleteJson() throws Exception {
    PersonVo personIncomplete = new PersonVo("3", null, "张三", null);
    mockMvc.perform(get("/persons/3"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(personIncomplete.getId()))
            .andExpect(jsonPath("$.age").value(0))
            .andExpect(jsonPath("$.name").value(personIncomplete.getName()))
            .andExpect(jsonPath("$.hobby").doesNotExist());
  }
}