package ru.job4j.accident.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accident.Main;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class AccidentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccidentService accidentService;

    @Test
    @WithMockUser
    public void whenCreatedAccidentView() throws Exception {
        this.mockMvc.perform(get("/formCreateAccident"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("createAccident"));
    }

    @Test
    @WithMockUser
    public void whenAddAccident() throws Exception {
        mockMvc.perform(post("/createAccident")
                        .param("name", "ДТП")
                        .param("text", "Превышение скорости")
                        .param("address", "г.Казань")
                        .param("type.id", "1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Accident> argumentCaptor = ArgumentCaptor.forClass(Accident.class);
        verify(accidentService).create(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue().getName(), is("ДТП"));
        assertThat(argumentCaptor.getValue().getText(), is("Превышение скорости"));
        assertThat(argumentCaptor.getValue().getAddress(), is("г.Казань"));

    }

    @Test
    @WithMockUser
    public void whenUpdateAccident() throws Exception {
        String[] ids = {"1", "2"};
        mockMvc.perform(post("/updateAccident")
                        .param("id", "2")
                        .param("name", "ДТП")
                        .param("text", "Превышение скорости")
                        .param("address", "г.Казань")
                        .param("type.id", "1")
                        .param("rIds", ids))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Accident> argumentCaptor = ArgumentCaptor.forClass(Accident.class);
        verify(accidentService).update(eq(2), argumentCaptor.capture());
        assertThat(argumentCaptor.getValue().getName(), is("ДТП"));
        assertThat(argumentCaptor.getValue().getText(), is("Превышение скорости"));
        assertThat(argumentCaptor.getValue().getAddress(), is("г.Казань"));

    }

}