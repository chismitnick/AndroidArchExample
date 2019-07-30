package com.example.androidarchexample;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

// Repository provides an abstraction layer between the database and the other application components

//Create  a class and a NoteDao object
//use Live data and List to display notes that th are in the room database
public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    //Pass the application as an object
    //Use public constructor
    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }


    //The following methods form the API that are exposed to the outside
    //Abstraction layer

    //method to insert notes
    public void insert(Note note) {
        new InsertNoteAsyncTask(noteDao).execute(note);

    }

    //method to update notes
    public void update(Note note) {
        new UpdateNoteAsyncTask(noteDao).execute(note);

    }

    //method to delete notes
    public void delete(Note note) {
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }

    //method to delete all notes
    public void deleteAllNotes() {

        new DeleteAllNotesAsyncTask(noteDao).execute();

    }

    // method to display all nodes
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }


    //Asynctask used because room android prevents database operations from executing on the main thread


    // Insert AysncTask
    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        public InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }


    //Update Async task
    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        public UpdateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }


    //Delete AsysncTask
    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        public DeleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            //Access note at index 0
            noteDao.insert(notes[0]);
            return null;
        }
    }


    // Delete all nodes AsyncTask
    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        public DeleteAllNotesAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.DeleteAllNotes();
            return null;

        }
    }


}