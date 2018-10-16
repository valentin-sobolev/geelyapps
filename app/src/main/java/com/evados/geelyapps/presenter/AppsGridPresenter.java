package com.evados.geelyapps.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.evados.geelyapps.BuildConfig;
import com.evados.geelyapps.model.AppModel;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class AppsGridPresenter {

    private PackageManager packageManager;
    private Context context;

    public AppsGridPresenter(Context context) {
        this.packageManager = context.getPackageManager();
        this.context = context;
    }

    public void runApp(AppModel appModel) {
        Intent launchIntent = packageManager.getLaunchIntentForPackage(appModel.getPackageName());
        if (launchIntent != null) {
            try {
                context.startActivity(launchIntent);//null pointer check in case package name was not found
            } catch (Exception e) {
                Timber.e(e);
            }
        }
    }

    public List<AppModel> getAppsList() {
        List<AppModel> result = new ArrayList<>();
        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
        for (PackageInfo packageInfo : packageInfoList) {
            if (!isSystemPackage(packageInfo)) {
                String name = packageInfo.applicationInfo.loadLabel(packageManager).toString();
                Drawable icon = packageInfo.applicationInfo.loadIcon(packageManager);
                String packageName = packageInfo.applicationInfo.packageName;
                if (!packageName.equals(BuildConfig.APPLICATION_ID)) {
                    AppModel app = new AppModel(name, packageName, icon);
                    result.add(app);
                }
            }
        }
        return result;
    }

    private boolean isSystemPackage(PackageInfo packageInfo) {
        return (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
    }
}
