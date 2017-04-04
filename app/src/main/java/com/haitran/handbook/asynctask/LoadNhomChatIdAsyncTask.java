package com.haitran.handbook.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.haitran.handbook.manager.DatabaseManager;
import com.haitran.handbook.model.ThucMonModel;

import java.util.ArrayList;

/**
 * Created by Hai Tran on 3/20/2017.
 */

public class LoadNhomChatIdAsyncTask extends AsyncTask<Void, Void, ArrayList<ThucMonModel>> {
    private OnGetAllMonAn onGetAllMonAn;
    private Context context;
    private String[] listId;

    public LoadNhomChatIdAsyncTask(OnGetAllMonAn onGetAllMonAn, String[] listId, Context context) {
        this.onGetAllMonAn = onGetAllMonAn;
        this.listId = listId;
        this.context = context;
    }

    @Override
    protected ArrayList<ThucMonModel> doInBackground(Void... voids) {
        ArrayList<ThucMonModel> arrayList = new ArrayList<>();
        arrayList.addAll(DatabaseManager.getInstance(context).getAllNhomChatWithId(listId));
        return arrayList;
    }

    @Override
    protected void onPostExecute(ArrayList<ThucMonModel> thucMonModels) {
        super.onPostExecute(thucMonModels);
        if (onGetAllMonAn != null) {
            onGetAllMonAn.onComplete(thucMonModels);
        }
    }

    public interface OnGetAllMonAn {
        void onComplete(ArrayList<ThucMonModel> thucMonModels);
    }

}
