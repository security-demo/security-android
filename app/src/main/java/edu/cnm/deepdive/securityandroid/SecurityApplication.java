package edu.cnm.deepdive.securityandroid;

import android.app.Application;
import edu.cnm.deepdive.securityandroid.service.GoogleSignInService;

public class SecurityApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    GoogleSignInService.setContext(this);
  }
}
