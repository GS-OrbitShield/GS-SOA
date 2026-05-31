package com.gs.orbitshield.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HashUtilTest {

    @Test
    void sha256_ShouldReturnCorrectHash() {
        String input = "OrbitShield_testkey_alpha";
        String expected = "93e08d75d40523d42bac5777cd4bab24147f0d12287fffc22bf4cbad4b7034be";
        assertEquals(expected, HashUtil.sha256(input));
    }

    @Test
    void sha256_ShouldHandleEmptyString() {
        String input = "";
        String hash = HashUtil.sha256(input);
        assertNotNull(hash);
        assertEquals(64, hash.length());
    }
}
