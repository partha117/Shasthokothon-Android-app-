package com.rongchut.shuvo.shasthokothon.Starting.Others;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by ASUS on 27-Jan-17.
 */

public class Utility {
    private static FirebaseDatabase mDatabase;

    public static FirebaseDatabase getDatabase() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(true);
        }
        return mDatabase;
    }
}
