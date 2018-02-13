package com.github.michaelruocco.connectfour.view;

import com.github.michaelruocco.connectfour.model.ConnectFour;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JButtonFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
        int columnIndex = 3;
        fillColumn(columnIndex);

        JButtonFixture button = getDropTokenButton(columnIndex);
        assertThat(button.requireDisabled()).isNotNull();
    }

    @Test
    public void canResizeWindow() {
        fixture.resizeWidthTo(800);

        playGame();

        DialogFixture dialog = getDialogByTitle("Winner!");
        JButtonFixture button = dialog.button(new DialogButtonTextMatcher("Reset")).click();
        assertThat(button).isNotNull();
    }

    @Test
    public void canPlayGame() {
        playGame();

        DialogFixture dialog = getDialogByTitle("Winner!");
        dialog.requireVisible();
        dialog.button(new DialogButtonTextMatcher("Reset")).click();

        playGame();

        dialog = getDialogByTitle("Winner!");
        assertThat(dialog.requireVisible()).isNotNull();
    }

    private void playGame() {
        dropToken(3);
        dropToken(4);
        dropToken(3);
        dropToken(4);
        dropToken(3);
        dropToken(4);
        dropToken(3);
    }

    private DialogFixture getDialogByTitle(String title) {
        return fixture.dialog(new DialogTitleMatcher(title));
    }

    private void fillColumn(int columnIndex) {
        for (int i = 0; i < 6; i++)
            dropToken(columnIndex);
    }

    private void dropToken(int columnIndex) {
        String id = toDropTokenButtonId(columnIndex);
        clickButton(id);
    }

    private void clickButton(String id) {
        JButtonFixture button = getButton(id);
        button.click();
    }

    private String toDropTokenButtonId(int columnIndex) {
        return "DropTokenButton" + columnIndex;
    }

    private JButtonFixture getDropTokenButton(int columnIndex) {
        return getButton(toDropTokenButtonId(columnIndex));
    }

    private JButtonFixture getButton(String id) {
        return fixture.button(id);
    }

}
