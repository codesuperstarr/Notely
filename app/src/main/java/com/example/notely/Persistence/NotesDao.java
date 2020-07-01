package com.example.notely.Persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notely.Models.Note;

import java.util.List;

@Dao
public interface NotesDao {

    @Insert
    long[] insertNotes(Note... notes);

    @Query("SELECT *FROM notes")
    LiveData<List<Note>> getNotes();


    @Update
    int update(Note... notes);

    @Delete
    int delete(Note... notes);
}
