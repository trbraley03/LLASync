package com.languageLearner.narration;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;
import org.junit.Test;

import com.learner.narration.Narrator;

public class NarrateTestTest {

    @Test
    void testNarratePlaySound() {
        // Test that playSound can be called without throwing exceptions
        try {
            Narrator.playSound("Kumusta ka na?");
        } catch (Exception e) {
            fail("Method threw an exception: " + e.getMessage());
        }
    }

    @Test
    void testNarratePlaySoundWithNull() {
        // Test that playSound with null throws NullPointerException
        assertThrows(NullPointerException.class, () -> Narrator.playSound(null));
    }

    @Test
    void testNarratePlaySoundWithEmptyString() {
        // Test that playSound with an empty string does not throw exceptions
        try {
            Narrator.playSound("");
        } catch (Exception e) {
            fail("Method threw an exception: " + e.getMessage());
        }
    }
}
