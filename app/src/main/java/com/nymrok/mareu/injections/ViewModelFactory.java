package com.nymrok.mareu.injections;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.nymrok.mareu.repositories.MeetingRepository;
import com.nymrok.mareu.ui.AddMeeting.AddMeetingViewModel;
import com.nymrok.mareu.ui.MeetingsList.MeetingsListViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private static ViewModelFactory factory;

    public static ViewModelFactory getInstance() {
        if (factory == null) {
            synchronized (ViewModelFactory.class) {
                if (factory == null) {
                    factory = new ViewModelFactory(new MeetingRepository());
                }
            }
        }

        return factory;
    }

    // This field inherit the singleton property from the ViewModelFactory : it is scoped to the ViewModelFactory
    // Ask your mentor about DI scopes (Singleton, ViewModelScope, ActivityScope)
    @NonNull
    private final MeetingRepository MeetingRepository;

    private ViewModelFactory(@NonNull MeetingRepository MeetingRepository) {
        this.MeetingRepository = MeetingRepository;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MeetingsListViewModel.class)) {
            return (T) new MeetingsListViewModel(
                    MeetingRepository
            );
        } else if (modelClass.isAssignableFrom(AddMeetingViewModel.class)) {
            return (T) new AddMeetingViewModel(
                    MeetingRepository
            );
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
