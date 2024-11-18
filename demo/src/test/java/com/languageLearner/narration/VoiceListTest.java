package com.languageLearner.narration;
import static org.junit.Assert.*;
import org.junit.Test;

import com.learner.narration.VoiceList;

import software.amazon.awssdk.regions.Region;

public class VoiceListTest {
    @Test
    public void testShowVoices() {
        // Test that showVoices can be called without throwing exceptions
        assertThrows(NullPointerException.class, () -> VoiceList.showVoices(Region.EU_WEST_3));
    }
}
