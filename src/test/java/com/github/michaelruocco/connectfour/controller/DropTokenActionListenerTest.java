package com.github.michaelruocco.connectfour.controller;

import com.github.michaelruocco.connectfour.model.ConnectFour;
import com.github.michaelruocco.connectfour.view.DropTokenButton;
import org.junit.Test;
import org.mockito.internal.matchers.Any;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class DropTokenActionListenerTest {

    private final ActionEvent event = mock(ActionEvent.class);
    private final ConnectFour connectFour = mock(ConnectFour.class);

    private final DropTokenActionListener listener = new DropTokenActionListener(connectFour);

    @Test
    public void shouldDoNothingIfActionNotTriggeredByDropTokenButton() {
        given(event.getSource()).willReturn(new JButton());

        listener.actionPerformed(event);

        verify(connectFour, never()).dropToken(any(Integer.class));
        verify(connectFour, never()).switchCurrentPlayer();
    }

    @Test
    public void shouldDropTokenInCorrectColumn() {
        int column = 1;
        given(event.getSource()).willReturn(new DropTokenButton(column));

        listener.actionPerformed(event);

        verify(connectFour).dropToken(column);
    }

    @Test
    public void shouldSwitchPlayerAfterTokenDropped() {
        int column = 1;
        given(event.getSource()).willReturn(new DropTokenButton(column));

        listener.actionPerformed(event);

        verify(connectFour).switchCurrentPlayer();
    }

}
