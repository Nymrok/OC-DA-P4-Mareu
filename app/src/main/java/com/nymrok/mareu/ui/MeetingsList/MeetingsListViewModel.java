package com.nymrok.mareu.ui.MeetingsList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.nymrok.mareu.models.Meeting;
import com.nymrok.mareu.repositories.MeetingRepository;

import java.util.ArrayList;
import java.util.List;

public class MeetingsListViewModel extends ViewModel {

    // REPOSITORIES
    private final MeetingRepository mMeetingRepository;

    public MeetingsListViewModel(MeetingRepository meetingRepository) {
        this.mMeetingRepository = meetingRepository;
    }

    public LiveData<List<MeetingsViewStateItem>> getMeetingViewStateItemsLiveData() {
        return Transformations.map(mMeetingRepository.getMeetingsLiveData(), meetings ->
        {
            List<MeetingsViewStateItem> meetingsViewStateItems = new ArrayList<>();

            for (Meeting meeting : meetings) {
                meetingsViewStateItems.add(new MeetingsViewStateItem(meeting.getId(), meeting.getColor(), meeting.getName(), meeting.getHour(), meeting.getRoom(), meeting.getMembers()));
            }
            return meetingsViewStateItems;
        });
    }

    public void onDeleteMeetingClicked(long meetingId) {
        mMeetingRepository.deleteMeeting(meetingId);
    }
}