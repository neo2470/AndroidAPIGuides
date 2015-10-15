package com.alex.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alex on 15-10-15.
 */
public class PeopleParcelable implements Parcelable {

    public PeopleParcelable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    public static final Parcelable.Creator<PeopleParcelable> CREATOR = new Creator<PeopleParcelable>() {

        @Override
        public PeopleParcelable createFromParcel(Parcel source) {
            PeopleParcelable people = new PeopleParcelable(source.readString());
            return people;
        }

        @Override
        public PeopleParcelable[] newArray(int size) {
            return new PeopleParcelable[size];
        }
    };

    private String name;
}
