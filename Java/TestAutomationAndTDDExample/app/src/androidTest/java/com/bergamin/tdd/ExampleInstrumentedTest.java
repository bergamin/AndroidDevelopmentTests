package com.bergamin.tdd;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.bergamin.tdd.model.Auction;
import com.bergamin.tdd.ui.recyclerview.adapter.AuctionsListAdapter;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        AuctionsListAdapter adapter = new AuctionsListAdapter(appContext);

        adapter.update(new ArrayList<>(Arrays.asList(
                new Auction("Console"),
                new Auction("Computer")
        )));
        int amountAuctions = adapter.getItemCount();

        assertThat(amountAuctions, is(2));
        assertEquals("com.bergamin.tdd", appContext.getPackageName());
    }
}
