package com.github.michaelruocco.connectfour.view;

import org.fest.swing.core.GenericTypeMatcher;

import javax.swing.*;

public class DialogButtonTextMatcher extends GenericTypeMatcher<JButton> {

    private final String text;

    public DialogButtonTextMatcher(String text) {
        super(JButton.class);
        this.text = text;
    }

    @Override
    protected boolean isMatching(JButton button) {
        return button.getText().equals(text);
    }

}
