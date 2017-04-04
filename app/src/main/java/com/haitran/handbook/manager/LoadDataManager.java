package com.haitran.handbook.manager;

import android.content.Context;

import com.haitran.handbook.model.NoteModel;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by diepnt on 2/16/2017.
 */

public class LoadDataManager {
    public static LoadDataManager instance;
    Realm realm;

    public LoadDataManager(Context context) {
        Realm.init(context);
        realm = Realm.getDefaultInstance();
    }


    public static LoadDataManager getInstance(Context context) {
        if (instance == null) {
            instance = new LoadDataManager(context);
        }
        return instance;
    }


    public RealmResults<NoteModel> getNoteModel() {
        RealmResults<NoteModel> results = realm.where(NoteModel.class).findAll();
        return results;
    }

    public void deleteAllTimerModel() {
        final RealmResults<NoteModel> results = realm.where(NoteModel.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                results.deleteAllFromRealm();
            }
        });
    }

    public NoteModel oneTimerModel(long idModel) {
        return realm.where(NoteModel.class).equalTo("id", idModel).findFirst();
    }

    public void deleteOneTimerModel(long idModel) {
        final NoteModel timerModel = realm.where(NoteModel.class).equalTo("id", idModel).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                timerModel.deleteFromRealm();
            }
        });
    }

    public void updateTimerModel(NoteModel timer) {
        NoteModel noteModel = oneTimerModel(timer.getId());
        realm.beginTransaction();
        noteModel.setId(timer.getId());
        noteModel.setDays(timer.getDays());
        noteModel.setName(timer.getName());
        noteModel.setContent(timer.getContent());
        noteModel.setTimer(timer.getTimer());
        noteModel.setNoteOrAlarm(timer.isNoteOrAlarm());
        realm.commitTransaction();
    }

    public void updateTimerModel(long id, boolean value) {
        NoteModel timerModel = oneTimerModel(id);
        realm.beginTransaction();
        timerModel.setNoteOrAlarm(value);
        realm.commitTransaction();
    }


    public void addTimerModel(NoteModel timer) {
        realm.beginTransaction();
        NoteModel noteModel = realm.createObject(NoteModel.class);
        noteModel.setId(timer.getId());
        noteModel.setDays(timer.getDays());
        noteModel.setName(timer.getName());
        noteModel.setTimer(timer.getTimer());
        noteModel.setContent(timer.getContent());
        noteModel.setNoteOrAlarm(timer.isNoteOrAlarm());
        realm.commitTransaction();
    }

    public Realm getRealm() {
        return realm;
    }
}
