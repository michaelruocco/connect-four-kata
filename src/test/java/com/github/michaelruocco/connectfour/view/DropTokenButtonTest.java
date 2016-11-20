package com.github.michaelruocco.connectfour.view;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class DropTokenButtonTest {

    @Test
    public void shouldSetTextToColumnIndex() {
        int column = 1;
        DropTokenButton button = new DropTokenButton(1);

        assertThat(button.getText()).isEqualTo(Integer.toString(column));
    }

    @Test
    public void shouldReturnColumnIndex() {
        int column = 1;
        DropTokenButton button = new DropTokenButton(1);

        assertThat(button.getColumnIndex()).isEqualTo(column);
    }

}
