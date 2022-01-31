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
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.nymrok.mareu.R;
import com.nymrok.mareu.injections.ViewModelFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AddMeetingActivity extends AppCompatActivity {

    private String mColorInput;

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

        mColorInput = "Grey";
        mBeigeColorBtn.setOnClickListener(v -> mColorInput = "Beige");
        mGreenColorBtn.setOnClickListener(v -> mColorInput = "Green");
        mBlueColorBtn.setOnClickListener(v -> mColorInput = "Blue");
        mYellowColorBtn.setOnClickListener(v -> mColorInput = "Yellow");
        mGreyColorBtn.setOnClickListener(v -> mColorInput = "Grey");

        checkName(viewModel, mNameInput);
        addButton(viewModel, mColorInput, mNameInput, mHourInput, mRoomInput, mMembersInput, mSaveBtn);

        viewModel.getCloseActivitySingleLiveEvent().observe(this, aVoid -> finish());
    }

    private void checkName(AddMeetingViewModel viewModel, TextInputEditText mNameInput) {
        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                viewModel.onNameChanged(s.toString());
            }
        });
    }

    private void addButton(AddMeetingViewModel viewModel, String mColorInput, TextInputEditText mNameInput, TextInputEditText mHourInput, TextInputEditText mRoomInput, TextInputEditText mMembersInput, Button mSaveBtn) {
        mSaveBtn.setOnClickListener(v -> viewModel.SaveBtnClicked(
            mColorInput,
            Objects.requireNonNull(mNameInput.getText()).toString(),
            Objects.requireNonNull(mHourInput.getText()).toString(),
            Objects.requireNonNull(mRoomInput.getText()).toString(),
            Arrays.asList(Objects.requireNonNull(mMembersInput.getText()).toString().split("\\s|,"))
        ));
        viewModel.getIsSaveButtonEnabledLiveData().observe(this, mSaveBtn::setEnabled);
    }

    public static Intent navigate(Context context) {
        return new Intent(context, AddMeetingActivity.class);
    }
}