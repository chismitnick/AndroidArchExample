package com.example.androidarchexample;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {


    //Creates a Singleton, Singleton
    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;


    }


    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };


    private static class PopulateDataBaseAsyncTask extends AsyncTask<Void, Void, Void> {

        private NoteDao noteDao;

        private PopulateDataBaseAsyncTask(NoteDatabase db) {
            noteDao = db.noteDao();

        }


        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("A travel through the mind of a genius", "Fiction",  1));
            noteDao.insert(new Note("Linear Algebra", "Mathematics", 2));
            noteDao.insert(new Note("Algorithms and Data Structures ", "Computers", 3));
            return null;
        }
    }


}
