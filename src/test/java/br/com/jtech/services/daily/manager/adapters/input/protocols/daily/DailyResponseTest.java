package br.com.jtech.services.daily.manager.adapters.input.protocols.daily;

import br.com.jtech.services.daily.manager.application.core.domains.daily.Daily;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class DailyResponseTest {

    private final UUID ID = UUID.randomUUID();
    private final UUID ID2 = UUID.randomUUID();

    @Test
    public void testFromDomain() {
        Daily daily = new Daily();
        daily.setId(ID);
        daily.setSummary("Daily 1");
        daily.setAuthor(Employee.builder().username("johndoe").build());
        daily.setSquad(Squad.builder().id(ID).name("Squad 1").build());

        DailyResponse response = DailyResponse.fromDomain(daily);

        assertEquals(daily.getId().toString(), response.getId());
        assertEquals(daily.getSummary(), response.getSummary());
        assertEquals(daily.getAuthor().getUsername(), response.getAuthor().getUsername());
        assertEquals(daily.getSquad().getName(), response.getSquad().getName());
        assertEquals(daily.getSquad().getId().toString(), response.getSquad().getId().toString());
    }

    @Test
    public void testFromDomains() {
        List<Daily> dailies = new ArrayList<>();
        Daily daily1 = new Daily();
        daily1.setId(ID);
        daily1.setSummary("Daily 1");
        daily1.setAuthor(Employee.builder().username("johndoe").build());
        daily1.setSquad(Squad.builder().id(ID).name("Squad 1").build());

        Daily daily2 = new Daily();
        daily2.setId(ID2);
        daily2.setSummary("Daily 2");
        daily2.setAuthor(Employee.builder().username("johndoe").build());
        daily2.setSquad(Squad.builder().id(ID).name("Squad 1").build());

        dailies.add(daily1);
        dailies.add(daily2);

        DailyResponse response = DailyResponse.fromDomains(dailies);

        assertNull(response.getId());
        assertNull(response.getSummary());
        assertNull(response.getAuthor());
        assertNull(response.getSquad());
        assertNotNull(response.getDailies());
        assertEquals(2, response.getDailies().size());
        assertEquals(daily1.getId().toString(), response.getDailies().get(0).getId());
        assertEquals(daily1.getSummary(), response.getDailies().get(0).getSummary());
        assertEquals(daily1.getAuthor().getUsername(), response.getDailies().get(0).getAuthor().getUsername());
        assertEquals(daily1.getSquad().getName(), response.getDailies().get(0).getSquad().getName());
        assertEquals(daily1.getSquad().getId().toString(), response.getDailies().get(0).getSquad().getId().toString());
        assertEquals(daily2.getId().toString(), response.getDailies().get(1).getId());
        assertEquals(daily2.getSummary(), response.getDailies().get(1).getSummary());
        assertEquals(daily2.getAuthor().getUsername(), response.getDailies().get(1).getAuthor().getUsername());
        assertEquals(daily2.getSquad().getName(), response.getDailies().get(1).getSquad().getName());
        assertEquals(daily2.getSquad().getId().toString(), response.getDailies().get(1).getSquad().getId().toString());
    }
}