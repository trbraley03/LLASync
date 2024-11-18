package com.languageLearner.narration;
import static org.junit.Assert.*;
import org.junit.Test;

import com.learner.narration.Narrator;

public class NarratorTest {

    @Test
    public void testPlaySound() {
        String testText = "Kumusta ka na?";
        
        // Test that playSound can be called without throwing exceptions
        assertDoesNotThrow(() -> Narrator.playSound(testText), "playSound should not throw an exception.");
    }

    @Test
    public void testPlaySoundWithEmptyString() {
        String emptyText = "";
        
        // Test that playSound does not throw an exception with an empty string
        assertDoesNotThrow(() -> Narrator.playSound(emptyText), "playSound should not throw an exception with an empty string.");
    }

    @Test
    public void testPlaySoundWithNull() {
        String nullText = null;
        
        // Test that playSound throws a NullPointerException with null input
        assertThrows(NullPointerException.class, () -> Narrator.playSound(nullText), "playSound should throw NullPointerException with null input.");
    }
}
