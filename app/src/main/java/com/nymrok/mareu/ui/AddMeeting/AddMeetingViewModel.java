package com.nymrok.mareu.ui.AddMeeting;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nymrok.mareu.repositories.MeetingRepository;
import com.nymrok.mareu.utils.SingleLiveEvent;

import java.util.List;

public class AddMeetingViewModel extends ViewModel {

    private final MeetingRepository mMeetingRepository;

    // Liaison du ViewModel avec le Repository
    public AddMeetingViewModel(MeetingRepository meetingRepository) {
        this.mMeetingRepository = meetingRepository;
    }

    // Récupération des attributs enregistrés dans l'activité "Add" pour création d'une nouvelle entrée dans le Repository
    public void SaveBtnClicked (String color, String name, String hour, String room, List<String> members) {
        mMeetingRepository.addMeeting(color, name, hour, room, members);
        closeActivitySingleLiveEvent.call();
    }

    // Le bouton "Save" est désactivé par défaut. MutableLiveData pour pouvoir utiliser "setValue()"
    private final MutableLiveData<Boolean> isSaveButtonEnabledMutableLiveData = new MutableLiveData<>(false);

    // Renvoi l'état actuel du bouton "Save" : Activé ou Désactivé (true or false)
    public LiveData<Boolean> getIsSaveButtonEnabledLiveData() {
        return isSaveButtonEnabledMutableLiveData;
    }

    // La surveillance de contenu du champ "Name" active ou désactive le bouton "Save"
    public void onNameChanged(String name) {
        isSaveButtonEnabledMutableLiveData.setValue(!name.isEmpty());
    }

    private final SingleLiveEvent<Void> closeActivitySingleLiveEvent = new SingleLiveEvent<>();

    public SingleLiveEvent<Void> getCloseActivitySingleLiveEvent() {
        return closeActivitySingleLiveEvent;
    }
}