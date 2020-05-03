package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * Created by dr-chene on @date 2020/5/3
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    public MySQLiteOpenHelper(Context context,int version) {
        super(context, "cmusic.db", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table song(_id integer primary key autoincrement,ids integer,flag integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public static long readSongId(Context context) {
        MySQLiteOpenHelper sqLiteOpenHelper = new MySQLiteOpenHelper(context,1);
        SQLiteDatabase sqLiteDatabase = sqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from song where flag = ?",new String[]{"0"});
       long id = 0;
        while (cursor.moveToNext()) {
            id = cursor.getLong(cursor.getColumnIndex("ids"));
        }
        return id;
    }
    public static void changeId(Context context,long id) {
        MySQLiteOpenHelper sqLiteOpenHelper = new MySQLiteOpenHelper(context,1);
        SQLiteDatabase sqLiteDatabase = sqLiteOpenHelper.getReadableDatabase();
        if (searchId(sqLiteDatabase)) {
            ContentValues values = new ContentValues();
            values.put("ids", id);
            values.put("flag",0);
            sqLiteDatabase.update("song", values, "flag = ? ", new String[]{"0"});
            sqLiteDatabase.close();
        } else {
            ContentValues values = new ContentValues();
            values.put("ids", id);
            values.put("flag",0);
            sqLiteDatabase.insert("song",null,values);
            sqLiteDatabase.close();
        }
    }

    private static Boolean searchId(SQLiteDatabase sqLiteDatabase) {
        Cursor cursor = sqLiteDatabase.rawQuery("select * from song where flag = ?",new String[]{"0"});
        while (cursor.moveToNext()) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }
}
