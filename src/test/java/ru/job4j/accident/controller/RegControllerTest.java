package ru.job4j.accident.controller;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accident.Main;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.UserRepository;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class RegControllerTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void whenRegistrationView() throws Exception {
        mockMvc.perform(get("/reg"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("reg"));
    }

    @Test
    @WithMockUser
    public void whenRegistration() throws Exception {
        String password = passwordEncoder.encode("rrr");
        this.mockMvc.perform(post("/reg")
                        .param("username", "Tom")
                        .param("password", password))
                .andDo(print())
                .andExpect(status().isOk());
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(argumentCaptor.capture());
        MatcherAssert.assertThat(argumentCaptor.getValue().getUsername(), is("Tom"));
        MatcherAssert.assertThat(argumentCaptor.getValue().getPassword(), is(password));

    }
}