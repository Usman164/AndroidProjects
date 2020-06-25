package com.example.whatsapp;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.whatsapp.Tabbed_Screen.Tabbed_Screen;
import com.google.android.material.tabs.TabLayout;

public class On_Touch_Listener extends GestureDetector.SimpleOnGestureListener {

    private static final int MIN_SWIPE_DISTANCE_X = 100;
    private static final int MIN_SWIPE_DISTANCE_Y = 100;

    private static final int MAX_SWIPE_DISTANCE_X = 100;
    private static final int MAX_SWIPE_DISTANCE_Y = 100;

    private Tabbed_Screen tabbed_screen;

    public Tabbed_Screen getTabbed_screen() {
        return tabbed_screen;

    }

    public void setTabbed_screen(Tabbed_Screen tabbed_screen) {
        this.tabbed_screen = tabbed_screen;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        this.tabbed_screen.displayMSG("Swipe");
        float deltaX = e1.getX() - e2.getX();
        float deltaY = e1.getY() - e2.getY();

        float deltaXabs = Math.abs(deltaX);
        float deltaYabs = Math.abs(deltaY);

        if (deltaXabs >= MIN_SWIPE_DISTANCE_X && deltaXabs <= MAX_SWIPE_DISTANCE_X) {
            if (deltaX>0)
            {
                this.tabbed_screen.displayMSG("Swipe to left");
            }
            else {
                this.tabbed_screen.displayMSG("Swipe to left");
            }
        }
        if (deltaYabs >= MIN_SWIPE_DISTANCE_Y && deltaYabs <= MAX_SWIPE_DISTANCE_Y) {
            if (deltaY>0)
            {
                this.tabbed_screen.displayMSG("Swipe to left");
            }
            else {
                this.tabbed_screen.displayMSG("Swipe to left");

            }
        }
        return true;
    }
}
