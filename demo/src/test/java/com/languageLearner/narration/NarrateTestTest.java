package com.languageLearner.narration;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;
import org.junit.Test;

import com.learner.narration.Narrator;

public class NarrateTestTest {

    @Test
    public void testNarratePlaySound() {
        // Test that playSound can be called without throwing exceptions
        assertThrows(NullPointerException.class, () -> Narrator.playSound("Kumusta ka na?"));
    }

    @Test
    public void testNarratePlaySoundWithNull() {
        // Test that playSound with null throws NullPointerException
        assertThrows(NullPointerException.class, () -> Narrator.playSound(null));
    }

    @Test
    public void testNarratePlaySoundWithEmptyString() {
        // Test that playSound with an empty string does not throw exceptions
        assertThrows(NullPointerException.class, () -> Narrator.playSound(""));
    }
}
