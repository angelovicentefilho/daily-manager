package br.com.jtech.services.daily.manager.config.infra.utils;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GenIdTest {

    @Test
    void testNewId() {
        String id = GenId.newId();
        assertNotNull(id);
        assertFalse(id.isEmpty());
    }

    @Test
    void testNewIdWithExistingId() {
        String existingId = "123e4567-e89b-12d3-a456-556642440000";
        String id = GenId.newId(existingId);
        assertEquals(existingId, id);
    }

    @Test
    void testNewUuid() {
        UUID uuid = GenId.newUuid();
        assertNotNull(uuid);
    }

    @Test
    void testNewUuidWithExistingId() {
        String existingId = "123e4567-e89b-12d3-a456-556642440000";
        UUID uuid = GenId.newUuid(existingId);
        assertEquals(UUID.fromString(existingId), uuid);
    }
}