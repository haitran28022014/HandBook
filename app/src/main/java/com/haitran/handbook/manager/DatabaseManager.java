package com.haitran.handbook.manager;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.haitran.handbook.model.ThaiKyModel;
import com.haitran.handbook.model.ThucMonModel;
import com.haitran.handbook.util.BaseModel;
import com.haitran.handbook.util.EncryptUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DatabaseManager {
    public static final String ID = "Id";
    public static final String COLUMN_THAI_NHI = "thai_nhi";
    public static final String COLUMN_CHAT = "chat";
    public static final String COLUMN_MON_AN = "mon_an";
    public static final String COLUMN_THUC_PHAM = "thuc_pham";
    public static final String COLUMN_TEN = "ten";
    public static final String COLUMN_THAIKY_ID = "thaiky_id";
    public static final String COLUMN_THAM_KHAO = "tham_khao";
    public static final String COLUMN_TAM_CA_NGUYET = "tam_ca_nguyet";
    public static final String COLUMN_BA_BAU = "ba_bau";
    public static final String COLUMN_HINH_ANH = "hinh_anh";
    private String DATABASE_NAME = "camnang.db";
    private String DATABASE_PATH = Environment.getDataDirectory().getAbsolutePath() +
            "/data/com.haitran.handbook/";
    private SQLiteDatabase sqLiteDatabase;
    public static DatabaseManager instance;
    public Context context;
    private ArrayList<BaseModel> arrayList;


    public static DatabaseManager getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseManager(context);
        }
        return instance;
    }

    public DatabaseManager(Context context) {
        this.context = context;
        copyDatabases();
    }

    private void copyDatabases() {
        AssetManager asset = context.getAssets();
        try {
            File file = new File(DATABASE_PATH + DATABASE_NAME);
            if (file.exists()) {
                return;
            }
            DataInputStream in = new DataInputStream(asset.open(DATABASE_NAME));
            DataOutputStream out = new DataOutputStream(new FileOutputStream(DATABASE_PATH + DATABASE_NAME));
            byte[] b = new byte[1024];
            int length;
            while ((length = in.read(b)) != -1) {
                out.write(b, 0, length);
            }
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openDatabases() {
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
            sqLiteDatabase = SQLiteDatabase.openDatabase(DATABASE_PATH + DATABASE_NAME, null, SQLiteDatabase.OPEN_READONLY);
        }
    }

    private void closeDatabases() {
        if (sqLiteDatabase != null || sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
    }

    public void insert(String tableName, ContentValues values) {
        openDatabases();
        sqLiteDatabase.insert(tableName, null, values);
        closeDatabases();
    }

    public void delete(String tableName, String whereClause, String[] whereArgs) {
        openDatabases();
        sqLiteDatabase.delete(tableName, whereClause, whereArgs);
        closeDatabases();
    }

    public void update(String tableName, ContentValues values, String whereClause, String[] whereArgs) {
        openDatabases();
        sqLiteDatabase.update(tableName, values, whereClause, whereArgs);
        closeDatabases();
    }


    public ThaiKyModel getOneThaiKy(String idThaiKy) {
        openDatabases();
        ThaiKyModel thaiKyModel = new ThaiKyModel();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * From  ThaiKyGson Where thaiky_id=" + "'" + idThaiKy + "'", null);
        if (cursor == null) {
            return null;
        }
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(ID));
            String a = EncryptUtils.Decrypt(cursor.getString(cursor.getColumnIndex(COLUMN_THAI_NHI)));
            String b = cursor.getString(cursor.getColumnIndex(COLUMN_CHAT));
            String c = cursor.getString(cursor.getColumnIndex(COLUMN_MON_AN));
            String d = cursor.getString(cursor.getColumnIndex(COLUMN_THUC_PHAM));
            String e = cursor.getString(cursor.getColumnIndex(COLUMN_TEN));
            String f = cursor.getString(cursor.getColumnIndex(COLUMN_THAIKY_ID));
            String g = cursor.getString(cursor.getColumnIndex(COLUMN_THAM_KHAO));
            String h = cursor.getString(cursor.getColumnIndex(COLUMN_TAM_CA_NGUYET));
            String i = EncryptUtils.Decrypt(cursor.getString(cursor.getColumnIndex(COLUMN_BA_BAU)));
            String j = cursor.getString(cursor.getColumnIndex(COLUMN_HINH_ANH));
            thaiKyModel = new ThaiKyModel(id, a, b, c, d, e, f, g, h, i, j);
        }
        cursor.close();
        closeDatabases();
        return thaiKyModel;
    }


    public ArrayList<BaseModel> getAllThaiKy() {
        String result = SharePreManager.getInstance(context).getDatePeriod();
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        arrayList = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            Calendar c;
            if (i == 0) {
                c = get7Date(result, 0);
            } else {
                c = get7Date(result, 7);
            }
            String a = sdf1.format(c.getTime());
            result = a;
            int n = (i + 1);
            if (i < 9) {
                arrayList.add(new BaseModel("W0" + n, "week_" + n, "Tuần " + n, a));
                if (i > 0) {
                    arrayList.get(i - 1).setDetail(arrayList.get(i - 1).getDetail() + "-" + a);
                }
            } else if (i >= 9) {
                if (i == 39) {
                    Calendar m = get7Date(result, 7);
                    String y = sdf1.format(m.getTime());
                    arrayList.add(new BaseModel("W" + n, "week_" + n, "Tuần " + n, a + "_" + y));
                } else {
                    arrayList.add(new BaseModel("W" + n, "week_" + n, "Tuần " + n, a));
                }
                arrayList.get(i - 1).setDetail(arrayList.get(i - 1).getDetail() + "-" + a);

            }
        }
        return arrayList;
    }

    public ArrayList<BaseModel> getArrayList() {
        return arrayList;
    }

    public Calendar get7Date(String date, int number) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, number);
        return c;
    }


    public ArrayList<ThucMonModel> getAllThucPhamWithId(String[] strings) {
        ArrayList<ThucMonModel> arrayList = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            arrayList.add(getOneThucPham(strings[i]));
        }
        return arrayList;
    }

    public ArrayList<ThucMonModel> getAllMonAn() {
        ArrayList<ThucMonModel> arrayList = new ArrayList<>();
        ThucMonModel monAnModel;
        openDatabases();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT *FROM  MonAnGson", null);
        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast() == false) {
                int id = cursor.getInt(0);
                String a = cursor.getString(10);
                String b = cursor.getString(9);
                String c = cursor.getString(8);
                String d = cursor.getString(7);
                String e = cursor.getString(6);
                String f = "hai";
                if (cursor.getString(5) != null) {
                    f = EncryptUtils.Decrypt(cursor.getString(5));
                }
                String g = cursor.getString(4);
                String h = cursor.getString(3);
                String i = cursor.getString(2);
                String j = cursor.getString(1);
                String k = "";
                String l = "";
                String m = "";
                monAnModel = new ThucMonModel(id, a, b, c, d, e, f, g, h, i, j, k, l, m);
                arrayList.add(monAnModel);
                cursor.moveToNext();
            }
        }
        cursor.close();
        closeDatabases();
        return arrayList;
    }

    public ArrayList<ThucMonModel> getAllThucPham() {
        ArrayList<ThucMonModel> arrayList = new ArrayList<>();
        ThucMonModel monAnModel;
        openDatabases();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT *FROM  ThucPhamGson", null);
        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast() == false) {
                int id = cursor.getInt(0);
                String a = cursor.getString(8);
                String b = cursor.getString(7);
                String c = cursor.getString(9);
                String d = cursor.getString(11);
                String e = "";
                String f = EncryptUtils.Decrypt(cursor.getString(1));
                String g = "";
                String h = "";
                String i = cursor.getString(6);
                String j = "";
                String k = cursor.getString(2);
                String l = cursor.getString(4);
                String m = cursor.getString(3);
                monAnModel = new ThucMonModel(id, a, b, c, d, e, f, g, h, i, j, k, l, m);
                arrayList.add(monAnModel);
                cursor.moveToNext();
            }
        }
        cursor.close();
        closeDatabases();
        return arrayList;
    }


    public ArrayList<ThucMonModel> getAllMonAnWithId(String[] strings) {
        ArrayList<ThucMonModel> arrayList = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            arrayList.add(getOneMonAn(strings[i]));
        }
        return arrayList;
    }

    public ThucMonModel getOneMonAn(String idMonAn) {
        openDatabases();
        ThucMonModel monAnModel = new ThucMonModel();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from  MonAnGson where monan_id=" + "'" + idMonAn + "'", null);
        if (cursor == null) {
            return null;
        }
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String a = cursor.getString(10);
            String b = cursor.getString(9);
            String c = cursor.getString(8);
            String d = cursor.getString(7);
            String e = cursor.getString(6);
            String f = EncryptUtils.Decrypt(cursor.getString(5));
            String g = cursor.getString(4);
            String h = cursor.getString(3);
            String i = cursor.getString(2);
            String j = cursor.getString(1);
            String k = "";
            String l = "";
            String m = "";
            monAnModel = new ThucMonModel(id, a, b, c, d, e, f, g, h, i, j, k, l, m);
        }
        cursor.close();
        closeDatabases();
        return monAnModel;
    }

    public ThucMonModel getOneThucPham(String idThucPham) {
        openDatabases();
        ThucMonModel monAnModel = new ThucMonModel();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from  ThucPhamGson where thucpham_id=" + "'" + idThucPham + "'", null);
        if (cursor == null) {
            return null;
        }
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String a = cursor.getString(8);
            String b = cursor.getString(7);
            String c = cursor.getString(9);
            String d = cursor.getString(11);
            String e = "";
            String f = EncryptUtils.Decrypt(cursor.getString(1));
            String g = "";
            String h = "";
            String i = cursor.getString(6);
            String j = "";
            String k = cursor.getString(2);
            String l = cursor.getString(4);
            String m = cursor.getString(3);
            monAnModel = new ThucMonModel(id, a, b, c, d, e, f, g, h, i, j, k, l, m);
        }
        cursor.close();
        closeDatabases();
        return monAnModel;
    }

    public ArrayList<ThucMonModel> getAllNhomChatWithId(String[] strings) {
        ArrayList<ThucMonModel> arrayList = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            arrayList.add(getOneNhomChat(strings[i]));
        }
        return arrayList;
    }

    public ArrayList<ThucMonModel> getAllNhomChat() {
        ArrayList<ThucMonModel> arrayList = new ArrayList<>();
        ThucMonModel monAnModel;
        openDatabases();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT *FROM  NhomChatGson", null);
        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast() == false) {
                int id = cursor.getInt(0);
                String a = cursor.getString(3);
                String b = cursor.getString(4);
                String c = cursor.getString(6);
                String d = cursor.getString(1);
                String e = cursor.getString(2);
                String f = EncryptUtils.Decrypt(cursor.getString(12));
                String g = cursor.getString(11);
                String h = cursor.getString(7);
                String i = cursor.getString(9);
                String j = cursor.getString(5);
                String k = "";
                String l = "";
                String m = "";
                monAnModel = new ThucMonModel(id, a, b, c, d, e, f, k, l, i, j, g, h, m);
                arrayList.add(monAnModel);
                cursor.moveToNext();
            }
        }
        cursor.close();
        closeDatabases();
        return arrayList;
    }

    public ThucMonModel getOneNhomChat(String idNhomChat) {
        openDatabases();
        ThucMonModel monAnModel = new ThucMonModel();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from  NhomChatGson where nhomchat_id=" + "'" + idNhomChat + "'", null);
        if (cursor == null) {
            return null;
        }
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String a = cursor.getString(3);
            String b = cursor.getString(4);
            String c = cursor.getString(6);
            String d = cursor.getString(1);
            String e = cursor.getString(2);
            String f = EncryptUtils.Decrypt(cursor.getString(12));
            String g = cursor.getString(11);
            String h = cursor.getString(7);
            String i = cursor.getString(9);
            String j = cursor.getString(5);
            String k = "";
            String l = "";
            String m = "";
            monAnModel = new ThucMonModel(id, a, b, c, d, e, f, k, l, i, j, g, h, m);
        }
        cursor.close();
        closeDatabases();
        return monAnModel;
    }


}
