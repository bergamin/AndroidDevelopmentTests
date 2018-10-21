package com.bergamin.tdd;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
public class ExampleUnitTest {
    @Test
    public void assertionExamples() {
        String nullVar = null;

        assertEquals(4, 2 + 2);
        assertTrue(4 == 2 + 2);
        assertThat(2 + 2, equalTo(4));
        assertNull(nullVar);
    }
}
