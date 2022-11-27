package com.example.mobile;

import java.util.ArrayList;

public class Track {
    String id, name;
    int length;
    ArrayList<String> writers;

    public Track(String id, String name, int length, ArrayList<String> writers) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.writers = writers;
    }

    public String getTrackLengthString() {
        int hour = length % 3600;
        int minute = (length - 3600 * hour) % 60;
        int second = length - 3600 * hour - 60 * minute;

        if (hour == 0) {
            return minute + ":" + second;
        } else {
            return hour + ":" + minute + ":" + second;
        }
    }

    public String getWriterString() {
        String writerString = "";
        for (int i = 0; i < writers.size(); i++) {
            writerString += writers.get(i).toString() + "; ";
        }
        return writerString;
    }
}
