package org.melnikacg.testapp;

import android.app.Application;

import org.melnikacg.testapp.model.House;

import java.util.Map;
import java.util.TreeMap;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

    private static final TreeMap<String, String> houses = new TreeMap<>();
    static {
        houses.put("Stark", "House Stark of Winterfell");
        houses.put("Lannister", "House Lannister of Casterly Rock");
        houses.put("Targaryen", "House Targaryen of King's Landing");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder().build();
        Realm.deleteRealm(realmConfig);
        Realm.setDefaultConfiguration(realmConfig);
        createHousesInitData();
    }

    // Create test data
    private void createHousesInitData() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (Map.Entry<String, String> entry : houses.entrySet()) {
                    House house = realm.createObject(House.class);
                    house.setName(entry.getKey());
                    house.setFullName(entry.getValue());
                    house.setId(System.nanoTime());
                    int resId = getResources().getIdentifier(entry.getKey().toLowerCase() + "_icon",
                            "drawable", getPackageName());
                    house.setCutOfArmsImeageResource(resId);
                    resId = getResources().getIdentifier(entry.getKey().toLowerCase(),
                            "drawable", getPackageName());
                    house.setDetailImeageResource(resId);
                }
            }
        });
        realm.close();
    }

}
