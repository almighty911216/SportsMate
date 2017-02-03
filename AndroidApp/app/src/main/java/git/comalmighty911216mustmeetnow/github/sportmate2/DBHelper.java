package git.comalmighty911216mustmeetnow.github.sportmate2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Xnote on 2017-02-03.
 */

public class DBHelper extends SQLiteOpenHelper{

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PICTURE (Ex_name TEXT PRIMARY KEY);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void write_pic(String url){
        SQLiteDatabase db = getWritableDatabase();
        int cnt = read_pic_num();
        if(cnt == 0){
            String sql = "INSERT INTO PICTURE VALUES('"+url+"'"+")";
            db.execSQL(sql);
        }

    }
    public int read_pic_num(){
        SQLiteDatabase db = getReadableDatabase();
        int i =0;
        Cursor cursor = db.rawQuery("SELECT * FROM PICTURE",null);
        while(cursor.moveToNext()){
            i++;
        }
        return i;
    }
    public String read_pic(){
        SQLiteDatabase db = getReadableDatabase();
        String temp = null;
        Cursor cursor = db.rawQuery("SELECT * FROM PICTURE",null);
        while(cursor.moveToNext()){
            temp = cursor.getString(0);
        }
        return temp;
    }
}
