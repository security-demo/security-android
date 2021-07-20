package edu.cnm.deepdive.securitydemoandroid.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import edu.cnm.deepdive.securitydemoandroid.model.entity.User;
import edu.cnm.deepdive.securitydemoandroid.service.UserRepository;
import org.jetbrains.annotations.NotNull;

public class MainViewModel extends AndroidViewModel {

  private final UserRepository userRepository;
  private final MutableLiveData<GoogleSignInAccount> account;
  private final MutableLiveData<User> user;
  private final MutableLiveData<Throwable> throwable;

  public MainViewModel(
      @NonNull @NotNull Application application) {
    super(application);
    userRepository = new UserRepository(application);
    account = new MutableLiveData<>();
    user = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    userFromServer();
  }

  public LiveData<User> getUser() {
    return user;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  @SuppressLint("CheckResult")
  private void userFromServer() {
    //noinspection ResultOfMethodCallIgnored
    userRepository.getUserProfile()
        .subscribe(
            user::postValue,
            throwable::postValue
        );
  }
}
