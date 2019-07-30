package com.example.androidarchexample;

import android.app.Application;
import android.view.LayoutInflater;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


//Avoid storing application context in the view model or
// Enables app to survive configuration changes
//its is only removed from meteor when the lifecycle of the corresponding activity is over

//Android View Model is a subclass of ViewModel the difference  being that
//The Android View Model passes the application in the constructor

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository respository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);

        respository = new NoteRepository(application);
        allNotes = respository.getAllNotes();
    }

    public void insert(Note note) {
        respository.insert(note);
    }


    public void update(Note note){
        respository.update(note);
    }


    public void deletAllNotes(){
        respository.deleteAllNotes();
    }


    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
