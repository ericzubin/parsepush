package com.parsepush.conrads.parsepush;

import com.parse.Parse;
import com.parse.ParseInstallation;

public class Application extends android.app.Application {

  public Application() {
  }

  @Override
  public void onCreate() {
    super.onCreate();


  Parse.initialize(this, "95IB9VOnaYSWQ87Zlms0qskdgpHEAUETwONznigQ", "l58gzWaqPFFVbVdE8o4SFGrayzxs8kCOgTOARZN2");
  ParseInstallation.getCurrentInstallation().saveInBackground();

    ParseInstallation installation = ParseInstallation.getCurrentInstallation();
    installation.put("device_id", "1234567890");
    installation.saveInBackground();    installation.put("device_id", "1234567890");
    installation.saveInBackground();
}

  }
