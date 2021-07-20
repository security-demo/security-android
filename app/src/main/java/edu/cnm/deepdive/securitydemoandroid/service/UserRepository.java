package edu.cnm.deepdive.securitydemoandroid.service;

import android.content.Context;
import edu.cnm.deepdive.securitydemoandroid.model.entity.User;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class UserRepository {

  private final Context context;
  private final WebServiceProxy webService;
  private final GoogleSignInService signInService;

  public UserRepository(Context context) {
    this.context = context;
    signInService = GoogleSignInService.getInstance();
    webService = WebServiceProxy.getInstance();
  }

  public Single<User> getUserProfile() {
    return signInService.refresh()
        .observeOn(Schedulers.io())
        .flatMap((account) -> webService.getProfile(getBearerToken(account.getIdToken())))
        .subscribeOn(Schedulers.io());
  }

  private String getBearerToken(String idToken) {
    return String.format("Bearer %s", idToken);
  }
}
