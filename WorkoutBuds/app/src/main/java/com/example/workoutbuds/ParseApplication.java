package com.example.workoutbuds;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("IunUIyFeeArZ9Z789brd4M103z0bcWNEwNjyZhlB")
                .clientKey("gEPLsVEuoJRNqCCzdXBdB29bAak16JEbPKCqsqhE")
                .server("https://parseapi.back4app.com")
                .build()
        );

    }
}
