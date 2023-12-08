package br.com.jtech.services.daily.manager.config.infra.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class JsonsTest {

    @Test
    void testToJsonStringWithValidObject() {
        Map<String, Object> object = new HashMap<>();
        object.put("name", "John");
        object.put("age", 30);
        String jsonString = Jsons.toJsonString(object);
        assertEquals("{\"name\":\"John\",\"age\":30}", jsonString);
    }

    @Test
    void testToJsonStringWithNullObject() {
        String jsonString = Jsons.toJsonString(null);
        assertNull(jsonString);
    }

    @Test
    void testParseJsonStringWithValidJsonStringAndClass() {
        String jsonString = "{\"name\":\"John\",\"age\":30}";
        Map<String, Object> object = Jsons.parseJsonString(jsonString, new TypeReference<Map<String, Object>>() {
        });
        assertEquals("John", object.get("name"));
        assertEquals(30, object.get("age"));
    }

    @Test
    void testParseJsonStringWithNullJsonString() {
        Map<String, Object> object = Jsons.parseJsonString(null, new TypeReference<Map<String, Object>>() {
        });
        assertNull(object);
    }
}