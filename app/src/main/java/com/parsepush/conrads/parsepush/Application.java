package com.parsepush.conrads.parsepush;

import com.parse.Parse;
import com.parse.ParseInstallation;

public class Application extends android.app.Application {

  public Application() {
  }

  @Override
  public void onCreate() {
    super.onCreate();


  Parse.initialize(this, "g0p79lQxI51ZETZtwKTCZuqjf1YyxQUZn8Qy1kQb", "tUKgzMsZ09LbLIIdwSZyIH1PVcu24aEcMpxcWH4A");
  ParseInstallation.getCurrentInstallation().saveInBackground();

    ParseInstallation installation = ParseInstallation.getCurrentInstallation();
    installation.put("device_id", "1234567890");
    installation.saveInBackground();

}

  }
