package com.zxj.myandroidtest;

import android.app.Application;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import java.util.Arrays;
import java.util.List;

public class MainApplication extends Application implements ReactApplication {
  private static MainApplication application;
  private ReactInstanceManager mReactInstanceManager;

  private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
    @Override
    public boolean getUseDeveloperSupport() {
      return BuildConfig.DEBUG;
    }

    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
              new MainReactPackage(),
              new CustomPackage()); // <-- 添加这一行，类名替换成你的Package类的名字.
    }

    @Override
    protected String getJSMainModuleName() {
      return "index";
    }
  };

  @Override
  public ReactNativeHost getReactNativeHost(){
    return mReactNativeHost;
  }
  public static MainApplication getInstance(){
    return application;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    application = this;
    mReactInstanceManager = mReactNativeHost.getReactInstanceManager();
    //JsBundle是在createReactContextInBackground()中加载的，所以优先先在这里加载，会大大加快RN的启动速度
    mReactInstanceManager.createReactContextInBackground();
    SoLoader.init(this, /* native exopackage */ false);
  }

  public ReactInstanceManager getReactInstanceManager(){
    return mReactInstanceManager;
  }
}