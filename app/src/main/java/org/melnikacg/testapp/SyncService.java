package org.melnikacg.testapp;

import android.app.IntentService;
import android.content.Intent;
import android.text.TextUtils;

import org.melnikacg.testapp.model.Character;
import org.melnikacg.testapp.model.House;
import org.melnikacg.testapp.retrofit.CharacterRetrofit;
import org.melnikacg.testapp.retrofit.HouseRetrofit;
import org.melnikacg.testapp.retrofit.ThronesApi;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit.RestAdapter;

public class SyncService extends IntentService {

    private ThronesApi api;

    public SyncService() {
        super("");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        api = createThronesApi();
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Realm realm = Realm.getDefaultInstance();
        RealmResults<House> houses = realm.where(House.class).findAll();
        for (final House realmHouse : houses) {
            List<HouseRetrofit> housesRetrofit = api.houseByName(realmHouse.getFullName());

            if (!housesRetrofit.isEmpty()) {
                final HouseRetrofit houseRetrofit = housesRetrofit.get(0);
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realmHouse.setWords(houseRetrofit.getWords());
                    }
                });


                List<String> characterList = houseRetrofit.getSwornMembers();
                for (String characterUrl : characterList) {
                    final String id = characterUrl.substring(characterUrl.lastIndexOf("/") + 1,
                            characterUrl.length());
                    final CharacterRetrofit characterRetrofit = api.characterById(id);

                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            Character character = realm.createObject(Character.class);
                            character.setId(Integer.parseInt(id));
                            character.setName(characterRetrofit.getName());
                            character.setBorn(characterRetrofit.getBorn());
                            character.setDied(characterRetrofit.getDied());
                            character.setTitles(
                                    TextUtils.join(", ", characterRetrofit.getTitles()));
                            character.setAliases(
                                    TextUtils.join(", ", characterRetrofit.getAliases()));
                            character.setFather(characterRetrofit.getFather());
                            character.setMother(characterRetrofit.getMother());
                            character.setHouse(realmHouse);
                        }
                    });

                }
            }
        }
        realm.close();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private ThronesApi createThronesApi() {

        RestAdapter.Builder builder = new RestAdapter
                .Builder()
                .setEndpoint("http://www.anapioficeandfire.com/api");
        return builder.build().create(ThronesApi.class);
    }
}