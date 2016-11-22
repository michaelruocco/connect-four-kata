package com.github.michaelruocco.connectfour.view;

import org.fest.swing.core.GenericTypeMatcher;

import java.awt.*;

public class DialogTitleMatcher extends GenericTypeMatcher<Dialog> {

    private final String title;

    public DialogTitleMatcher(String title) {
        super(Dialog.class);
        this.title = title;
    }

    @Override
    protected boolean isMatching(Dialog dialog) {
        return dialog.getTitle().equals(title) && dialog.isVisible();
    }

}
