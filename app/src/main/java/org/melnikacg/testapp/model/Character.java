package org.melnikacg.testapp.model;

import io.realm.RealmObject;

/**
 * Character model for DB
 */
public class Character extends RealmObject {

    public static String HOUSE_ID_FIELD = "mHouse.mId";
    public static String NAME_FIELD = "mName";

    // All fields are by default persisted.
    private int mId;
    private String mName;
    private String mBorn;
    private String mDied;
    private String mTitles;
    private String mAliases;
    private String mFather;
    private String mMother;

    private House mHouse;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getBorn() {
        return mBorn;
    }

    public void setBorn(String born) {
        this.mBorn = born;
    }

    public String getDied() {
        return mDied;
    }

    public void setDied(String died) {
        this.mDied = died;
    }

    public String getTitles() {
        return mTitles;
    }

    public void setTitles(String titles) {
        this.mTitles = titles;
    }

    public String getAliases() {
        return mAliases;
    }

    public void setAliases(String aliases) {
        this.mAliases = aliases;
    }

    public String getFather() {
        return mFather;
    }

    public void setFather(String father) {
        this.mFather = father;
    }

    public String getMother() {
        return mMother;
    }

    public void setMother(String mother) {
        this.mMother = mother;
    }

    public House getHouse() {
        return mHouse;
    }

    public void setHouse(House house) {
        mHouse = house;
    }
}
