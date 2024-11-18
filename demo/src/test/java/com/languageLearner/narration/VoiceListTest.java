package com.languageLearner.narration;
import static org.junit.Assert.fail;
import org.junit.Test;

import com.learner.narration.VoiceList;

import software.amazon.awssdk.regions.Region;

public class VoiceListTest {
    @Test
    void testShowVoices() {
        // Test that showVoices can be called without throwing exceptions
        try {
            VoiceList.showVoices(Region.EU_WEST_3);
        } catch (Exception e) {
            fail("showVoices should not throw an exception when called with Region.EU_WEST_3. Exception: " + e.getMessage());
        }
        
    }
}
