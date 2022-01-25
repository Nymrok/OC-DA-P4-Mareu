package com.nymrok.mareu.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.nymrok.mareu.models.Meeting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MeetingRepository {

    // Création d'une liste vide de réunions en mode LiveData
    private final MutableLiveData<List<Meeting>> meetingsLiveData = new MutableLiveData<>(new ArrayList<>());

    private long maxId = 0;

    public MeetingRepository() {
        generateFakeMeetings();
    }

    // Fonction de création de Meeting, avec attributs du modèle en paramètres
    public void addMeeting(
        String color,
        String name,
        String hour,
        String room,
        List<String> members
    ) {
        // Récupération du contenu de la liste des réunions existantes
        List<Meeting> meetings = meetingsLiveData.getValue();

        // Pour éviter une alerte @Lint sur un éventuel faux-positif "Null Pointer Exception"
        if (meetings == null) return;

        meetings.add(
            new Meeting(
                maxId++,
                color,
                name,
                hour,
                room,
                members
            )
        );

        meetingsLiveData.setValue(meetings);
    }

    // Fonction de suppression de réunion, identification par l'id
    public void deleteMeeting(long meetingId) {
        // Récupération du contenu de la liste des réunions existantes
        List<Meeting> meetings = meetingsLiveData.getValue();

        // Si il n'y a aucune réunion existante, abandon de la tentative de suppression d'une réunion
        if (meetings == null) return;

        // Récupération de la liste des réunions et vérification des IDs jusqu'à trouver la réunion à supprimer
        for (Iterator<Meeting> iterator = meetings.iterator(); iterator.hasNext();) {

            // Récupération de la prochaine réunion existante depuis la liste des réunions existantes
            Meeting meeting = iterator.next();

            // Si l'id de la dernière réunion reçue correspond à l'id de la réunion à supprimer
            if (meeting.getId() == meetingId) {
                // Alors on retire cette réunion de la liste des réunions existantes
                iterator.remove();
                break;
            }
        }

        // Et on renvoie la liste des réunions mise-à-jour
        meetingsLiveData.setValue(meetings);
    }

    // Envoi de la liste des réunions
    public LiveData<List<Meeting>> getMeetingsLiveData() {
        return meetingsLiveData;
    }

    // Envoi d'une réunion en particulier
    public LiveData<Meeting> getMeetingLiveData(long meetingId) {
        // Transformation de la liste des réunions en réunion unique, identifiée par son id
        return Transformations.map(meetingsLiveData, meetings -> {
            for (Meeting meeting : meetings) {
                if (meeting.getId() == meetingId) {
                    return meeting;
                }
            }
            // Si aucune réunion trouvée avec l'id spécifiée, retour vide
            return null;
        });
    }

    private void generateFakeMeetings() {

        List<String> fakeRandomMembers = new ArrayList<>();
        fakeRandomMembers.add("maxime@lamzone.com");
        fakeRandomMembers.add("alex@lamzone.com");
        fakeRandomMembers.add("paul@lamzone.com");
        fakeRandomMembers.add("viviane@lamzone.com");
        fakeRandomMembers.add("amandine@lamzone.com");
        fakeRandomMembers.add("luc@lamzone.com");

        addMeeting(
                "Beige",
                "Réunion A",
                "07h00",
                "Peach",
                fakeRandomMembers
        );

        addMeeting(
                "Green",
                "Réunion B",
                "08h00",
                "Mario",
                fakeRandomMembers
        );

        addMeeting(
                "Blue",
                "Réunion C",
                "09h00",
                "Luigi",
                fakeRandomMembers
        );

        addMeeting(
                "Yellow",
                "Réunion D",
                "10h00",
                "Yoshi",
                fakeRandomMembers
        );

        addMeeting(
                "Grey",
                "Réunion E",
                "11h00",
                "Zelda",
                fakeRandomMembers
        );

        addMeeting(
                "Yellow",
                "Réunion F",
                "12h00",
                "Link",
                fakeRandomMembers
        );

        addMeeting(
                "Blue",
                "Réunion G",
                "13h00",
                "Toad",
                fakeRandomMembers
        );

        addMeeting(
                "Green",
                "Réunion H",
                "14h00",
                "Falcon",
                fakeRandomMembers
        );

        addMeeting(
                "Beige",
                "Réunion I",
                "15h00",
                "Donkey Kong",
                fakeRandomMembers
        );

        addMeeting(
                "Beige",
                "Réunion J",
                "16h00",
                "Samus",
                fakeRandomMembers
        );

        addMeeting(
                "Yellow",
                "Réunion K",
                "17h00",
                "Daisy",
                fakeRandomMembers
        );

        addMeeting(
                "Green",
                "Réunion L",
                "18h00",
                "Kirby",
                fakeRandomMembers
        );

        addMeeting(
                "Grey",
                "Réunion M",
                "19h00",
                "Pikachu",
                fakeRandomMembers
        );

        addMeeting(
                "Blue",
                "Réunion N",
                "20h00",
                "Fox",
                fakeRandomMembers
        );
    }
}