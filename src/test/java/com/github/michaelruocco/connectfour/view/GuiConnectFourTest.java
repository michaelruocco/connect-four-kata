package com.github.michaelruocco.connectfour.view;

import com.github.michaelruocco.connectfour.model.ConnectFour;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

public class GuiConnectFourTest {

    private FrameFixture fixture;

    @Before
    public void setUp() {
        GuiConnectFour guiConnectFour = new GuiConnectFour(new ConnectFour());
        guiConnectFour.play();
        fixture = new FrameFixture(guiConnectFour);
    }

    @After
    public void tearDown() {
        fixture.cleanUp();
    }

    @Test
    public void shouldDisableButtonWhenColumnFull() {
        fixture.button("DropTokenButton3").click();
        fixture.button("DropTokenButton3").click();
        fixture.button("DropTokenButton3").click();
        fixture.button("DropTokenButton3").click();
        fixture.button("DropTokenButton3").click();
        fixture.button("DropTokenButton3").click();

        fixture.button("DropTokenButton3").requireDisabled();
    }

}
