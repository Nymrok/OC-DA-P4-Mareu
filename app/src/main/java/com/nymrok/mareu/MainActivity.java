package com.nymrok.mareu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nymrok.mareu.ui.AddMeeting.AddMeetingActivity;
import com.nymrok.mareu.fragments.MeetingsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButtons();
        initPlaceHolder();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_bar, menu);
        return true;
    }

    private void initButtons() {
        FloatingActionButton mAddButton = findViewById(R.id.create_event_button);
        mAddButton.setOnClickListener(v -> {
            startActivity(AddMeetingActivity.navigate(this));
        });
    }

    private void initPlaceHolder() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.recyclerview_placeholder, new MeetingsFragment());
        ft.commit();
    }
}