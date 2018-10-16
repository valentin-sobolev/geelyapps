package com.evados.geelyapps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.evados.geelyapps.model.AppModel;
import com.evados.geelyapps.presenter.AppsGridPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    AppsGridPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new AppsGridPresenter(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        initGrid(presenter.getAppsList());
    }

    private void initGrid(List<AppModel> appsList) {
        ArrayAdapter<AppModel> adapter = new CustomGridViewAdapter(this, R.layout.app_item, appsList, appModel -> presenter.runApp(appModel));
        GridView gridView = findViewById(R.id.greed_view);
        gridView.setAdapter(adapter);
    }
}
