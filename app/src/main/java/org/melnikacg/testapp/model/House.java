package org.melnikacg.testapp.model;

import io.realm.RealmObject;

/**
 * House model for DB
 */
public class House extends RealmObject {

    // All fields are by default persisted.
    private String mName;
    private String mFullName;
    private String mWords;
    private long mId;
    private int mCutOfArmsImeageResource;
    private int mDetailImeageResource;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        this.mFullName = fullName;
    }

    public String getWords() {
        return mWords;
    }

    public void setWords(String words) {
        this.mWords = words;
    }

    public int getCutOfArmsImeageResource() {
        return mCutOfArmsImeageResource;
    }

    public void setCutOfArmsImeageResource(int cutOfArmsImeageResource) {
        this.mCutOfArmsImeageResource = cutOfArmsImeageResource;
    }

    public int getDetailImeageResource() {
        return mDetailImeageResource;
    }

    public void setDetailImeageResource(int detailImeageResource) {
        this.mDetailImeageResource = detailImeageResource;
    }
}
