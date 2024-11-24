package com.astrolightz.pocketbox;

import android.graphics.drawable.Drawable;

public class ToolButton
{
    private String title;
    private Drawable icon;

    public ToolButton(String title, Drawable icon)
    {
        this.title = title;
        this.icon = icon;
    }

    public String getTitle()
    {
        return title;
    }

    public Drawable getIcon()
    {
        return icon;
    }
}
