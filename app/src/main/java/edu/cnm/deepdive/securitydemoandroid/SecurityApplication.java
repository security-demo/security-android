package edu.cnm.deepdive.securitydemoandroid;

import android.app.Application;
import edu.cnm.deepdive.securitydemoandroid.service.GoogleSignInService;

public class SecurityApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    GoogleSignInService.setContext(this);
  }
}
