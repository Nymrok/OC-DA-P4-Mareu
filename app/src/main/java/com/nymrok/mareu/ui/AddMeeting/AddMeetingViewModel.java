package com.nymrok.mareu.ui.AddMeeting;

import androidx.lifecycle.ViewModel;

import com.nymrok.mareu.repositories.MeetingRepository;

import java.util.List;

public class AddMeetingViewModel extends ViewModel {

    private final MeetingRepository mMeetingRepository;

    public AddMeetingViewModel(MeetingRepository meetingRepository) {
        this.mMeetingRepository = meetingRepository;
    }

    public void SaveBtnClicked (String color, String name, String hour, String room, List<String> members) {
        mMeetingRepository.addMeeting(color, name, hour, room, members);
    }
}