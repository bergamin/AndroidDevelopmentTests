package com.bergamin.tdd.formatter;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CurrencyFormatterTest {

    @Test
    public void should_formatCurrency_whenReceivesDouble() {
        CurrencyFormatter formatter = new CurrencyFormatter();

        assertThat(formatter.format(200.0), is("R$ 200,00"));
    }
}
