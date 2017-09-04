package com.rongchut.shuvo.shasthokothon.Starting.DrugReaction.ViewFeedback;

import com.github.mikephil.charting.data.BarData;

/**
 * Created by ASUS on 07-Jan-17.
 */

public class Capsule {
    private BarData barData;
    private int status;

    public Capsule(BarData barData, int status) {
        this.barData = barData;
        this.status = status;
    }

    public BarData getBarData() {
        return barData;
    }

    public void setBarData(BarData barData) {
        this.barData = barData;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
