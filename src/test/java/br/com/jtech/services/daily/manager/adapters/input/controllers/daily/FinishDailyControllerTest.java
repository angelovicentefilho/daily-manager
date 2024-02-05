package br.com.jtech.services.daily.manager.adapters.input.controllers.daily;

import br.com.jtech.services.daily.manager.adapters.input.protocols.daily.DailyFinishRequest;
import br.com.jtech.services.daily.manager.application.core.domains.daily.Daily;
import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import br.com.jtech.services.daily.manager.application.ports.input.daily.FinishDailyInputGateway;
import br.com.jtech.services.daily.manager.config.infra.mongodb.MongoTestConfig;
import br.com.jtech.services.daily.manager.config.infra.utils.GenId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({MongoTestConfig.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(FinishDailyController.class)
public class FinishDailyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FinishDailyInputGateway finishDailyInputGateway;

    @Test
    public void testFinishWhenDailyExistsThenReturnOk() throws Exception {
        // Arrange
        final var dailyId = GenId.newUuid().toString();
        final var finishAt = LocalDateTime.now();
        final var validPayload = "{\"id\":\"" + dailyId + "\",\"finishedAt\":\"" + finishAt + "\",\"authorEmail\":\"filipe@test.com\",\"summary\":\"Daily 1 description\"}";
        DailyFinishRequest request = DailyFinishRequest.builder().finishedAt(finishAt).authorEmail("filipe@test.com").summary("Daily 1 description").id(dailyId).build();
        final var daily = Daily.fromFinishRequest(request);
        daily.setSquad(Squad.builder().id(GenId.newUuid()).build());
        when(finishDailyInputGateway.finish(any())).thenReturn(Optional.of(daily));
        mockMvc.perform(put("/api/v1/dailies/finish").
                    contentType(MediaType.APPLICATION_JSON).
                    content(validPayload)).
                andExpect(status().isOk());
        verify(finishDailyInputGateway, times(1)).finish(any());
    }
}