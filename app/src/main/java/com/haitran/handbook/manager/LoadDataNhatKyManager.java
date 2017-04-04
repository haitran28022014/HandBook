package com.haitran.handbook.manager;

import android.content.Context;

import com.haitran.handbook.model.NhatKyModel;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by diepnt on 2/16/2017.
 */

public class LoadDataNhatKyManager {
    public static LoadDataNhatKyManager instance;
    Realm realm;

    public LoadDataNhatKyManager(Context context) {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .schemaVersion(17)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        realm = Realm.getDefaultInstance();
        realm.setAutoRefresh(true);
    }


    public static LoadDataNhatKyManager getInstance(Context context) {
        if (instance == null) {
            instance = new LoadDataNhatKyManager(context);
        }
        return instance;
    }
    

    public Realm getRealm() {
        return realm;
    }


    public RealmResults<NhatKyModel> getNhatKyModel() {
        RealmResults<NhatKyModel> results = realm.where(NhatKyModel.class).findAll();
        return results;
    }


    public NhatKyModel oneNhatKyModel(long idModel) {
        return realm.where(NhatKyModel.class).equalTo("id", idModel).findFirst();
    }

    public void deleteOneNhatKyModel(long idModel) {
        final NhatKyModel timerModel = realm.where(NhatKyModel.class).equalTo("id", idModel).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                timerModel.deleteFromRealm();
            }
        });
    }

    public void updateNhatKyModel(NhatKyModel timer) {
        NhatKyModel noteModel = oneNhatKyModel(timer.getId());
        realm.beginTransaction();
        noteModel.setId(timer.getId());
        noteModel.setContent(timer.getContent());
        noteModel.setDate(timer.getDate());
        realm.commitTransaction();
    }

    public void addNhatKyModel(NhatKyModel timer) {
        realm.beginTransaction();
        NhatKyModel noteModel = realm.createObject(NhatKyModel.class);
        noteModel.setId(timer.getId());
        noteModel.setContent(timer.getContent());
        noteModel.setDate(timer.getDate());
        realm.commitTransaction();
    }
}
