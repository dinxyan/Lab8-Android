package com.example.lab8;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class LabDatabase extends SQLiteOpenHelper {
    //Define Database
    private static final String DB_NAME="labdb.db";
    private static final int DB_VERSION=1;
    private static final String Table_NAME="notes";
    private static final String ClM_ID="_id";
    private static final String CLM_CONTENT="content";
    public LabDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
 /*create table user
 ( _id integer primary key autoincrement,
 content text
 ) */
        String sql="create table " + Table_NAME +
                "(" +
                ClM_ID +" integer primary key autoincrement," +
                CLM_CONTENT+" text" +
                ")";
        sqLiteDatabase.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int
            newVersion) {
        sqLiteDatabase.execSQL("drop table if exists " + Table_NAME);
        onCreate(sqLiteDatabase);
    }
    public void createNote(String content)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues myData=new ContentValues();
        myData.put(CLM_CONTENT,content);
        String nullcolumnhack=null;
        sqLiteDatabase.insert(Table_NAME, nullcolumnhack, myData);
        sqLiteDatabase.close();
    }
    public Cursor getAllNotes()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("select * from " + Table_NAME,null);
        return c;
    }
    public int countRecord()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("select count(*) from " + Table_NAME,null);
        c.moveToFirst();
        int x;
        do
        {
            x=Integer.parseInt(c.getString(0));
        }while(c.moveToNext());
        return x;
    }
}
