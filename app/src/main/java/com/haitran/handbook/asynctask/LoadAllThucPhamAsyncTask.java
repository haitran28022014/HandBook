package com.haitran.handbook.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.haitran.handbook.manager.DatabaseManager;
import com.haitran.handbook.model.ThucMonModel;

import java.util.ArrayList;

/**
 * Created by Hai Tran on 3/20/2017.
 */

public class LoadAllThucPhamAsyncTask extends AsyncTask<Void, Void, ArrayList<ThucMonModel>> {
    private OnGetAllMonAn onGetAllMonAn;
    private Context context;

    public LoadAllThucPhamAsyncTask(OnGetAllMonAn onGetAllMonAn, Context context) {
        this.onGetAllMonAn = onGetAllMonAn;
        this.context = context;
    }

    @Override
    protected ArrayList<ThucMonModel> doInBackground(Void... voids) {
        ArrayList<ThucMonModel> arrayList = new ArrayList<>();
        arrayList.addAll(DatabaseManager.getInstance(context).getAllThucPham());
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
