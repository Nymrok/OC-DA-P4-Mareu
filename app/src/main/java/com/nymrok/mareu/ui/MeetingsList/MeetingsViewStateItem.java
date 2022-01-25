package com.nymrok.mareu.ui.MeetingsList;

import java.util.List;

public class MeetingsViewStateItem {

    private final long id;
    private final String color;
    private final String name;
    private final String hour;
    private final String room;
    private final List<String> members;

    public MeetingsViewStateItem(long id, String color, String name, String hour, String room, List<String> members) {
        this.id = id;
        this.color = color;
        this.name = name;
        this.hour = hour;
        this.room = room;
        this.members = members;
    }

    public long getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public String getHour() {
        return hour;
    }

    public String getRoom() {
        return room;
    }

    public List<String> getMembers() {
        return members;
    }
}
