package com.languageLearner.narration;
import static org.junit.Assert.fail;
import org.junit.Test;

import com.learner.narration.Narrator;

public class NarratorTest {

    @Test
    void testPlaySound() {
        String testText = "Kumusta ka na?";
        
        // Test that playSound can be called without throwing exceptions
        try {
            Narrator.playSound(testText);
        } catch (Exception e) {
            fail("playSound should not throw an exception. Exception message: " + e.getMessage());
        }
        
    }

    @Test
    void testPlaySoundWithEmptyString() {
        String emptyText = "";
        
        // Test that playSound does not throw an exception with an empty string
        try {
            Narrator.playSound(emptyText);
        } catch (Exception e) {
            fail("playSound should not throw an exception with an empty string. Exception: " + e.getMessage());
        }
        
    }

    @Test
    void testPlaySoundWithNull() {
        String nullText = null;
        
        // Test that playSound throws a NullPointerException with null input
        try {
            Narrator.playSound(nullText);
            fail("playSound should throw NullPointerException with null input.");
        } catch (NullPointerException e) {
            // Test passes if exception is caught
        } catch (Exception e) {
            fail("Expected NullPointerException, but got " + e.getClass().getSimpleName());
        }        
    }
}
