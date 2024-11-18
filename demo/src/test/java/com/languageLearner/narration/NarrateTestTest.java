package com.languageLearner.narration;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;
import org.junit.Test;

import com.learner.narration.Narrator;

public class NarrateTestTest {

    @Test
    public void testNarratePlaySound() {
        // Test that playSound can be called without throwing exceptions
        try {
            Narrator.playSound("Kumusta ka na?");
        } catch (NullPointerException e) {
            fail("Unexpected exception was thrown: " + e.getMessage());
        }
    }

    @Test
    public void testNarratePlaySoundWithNull() {
        // Test that playSound with null throws NullPointerException
        try {
            Narrator.playSound(null);
            fail("Expected NullPointerException to be thrown");
        } catch (NullPointerException e) {
            // Expected exception, test passes
        }
    }

    @Test
    public void testNarratePlaySoundWithEmptyString() {
        // Test that playSound with an empty string does not throw exceptions
        try {
            Narrator.playSound("");
        } catch (NullPointerException e) {
            fail("Unexpected exception was thrown: " + e.getMessage());
        }
    }
}
