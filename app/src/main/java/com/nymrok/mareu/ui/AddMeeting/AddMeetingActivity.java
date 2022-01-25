package com.nymrok.mareu.ui.AddMeeting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.nymrok.mareu.R;
import com.nymrok.mareu.injections.ViewModelFactory;
import com.nymrok.mareu.models.Meeting;
import com.nymrok.mareu.ui.MeetingsList.MeetingsListViewModel;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AddMeetingActivity extends AppCompatActivity {

    private String mThisMeetingColor;
    private String mThisMeetingName;
    private String mThisMeetingHour;
    private String mThisMeetingRoom;
    private List<String> mThisMeetingMembers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        AddMeetingViewModel viewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(AddMeetingViewModel.class);

        Button mBeigeColorBtn = findViewById(R.id.form_colorPicker_beige);
        Button mGreenColorBtn = findViewById(R.id.form_colorPicker_green);
        Button mBlueColorBtn = findViewById(R.id.form_colorPicker_blue);
        Button mYellowColorBtn = findViewById(R.id.form_colorPicker_yellow);
        Button mGreyColorBtn = findViewById(R.id.form_colorPicker_grey);
        TextInputEditText mNameInput = findViewById(R.id.form_name_input);
        TextInputEditText mHourInput = findViewById(R.id.form_hour_input);
        TextInputEditText mRoomInput = findViewById(R.id.form_room_input);
        TextInputEditText mMembersInput = findViewById(R.id.form_members_input);
        Button mSaveBtn = findViewById(R.id.save_button);

        mThisMeetingColor = "Grey";
        mBeigeColorBtn.setOnClickListener(v -> mThisMeetingColor = "Beige");
        mGreenColorBtn.setOnClickListener(v -> mThisMeetingColor = "Green");
        mBlueColorBtn.setOnClickListener(v -> mThisMeetingColor = "Blue");
        mYellowColorBtn.setOnClickListener(v -> mThisMeetingColor = "Yellow");
        mGreyColorBtn.setOnClickListener(v -> mThisMeetingColor = "Grey");

        mSaveBtn.setOnClickListener(v -> {

            mThisMeetingName = String.valueOf(mNameInput.getText());
            mThisMeetingHour = Objects.requireNonNull(mHourInput.getText()).toString();
            mThisMeetingRoom = Objects.requireNonNull(mRoomInput.getText()).toString();
            mThisMeetingMembers = Arrays.asList(Objects.requireNonNull(mMembersInput.getText()).toString().split("\\s|,"));

            viewModel.SaveBtnClicked(mThisMeetingColor, mThisMeetingName, mThisMeetingHour, mThisMeetingRoom, mThisMeetingMembers);
            finish();
        });

        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    mSaveBtn.setEnabled(true);
                    mSaveBtn.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(206, 10, 36)));
                } else {
                    mSaveBtn.setEnabled(false);
                    mSaveBtn.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(180, 180, 180)));
                }
            }
        });
    }

    public static Intent navigate(Context context) {
        return new Intent(context, AddMeetingActivity.class);
    }
}