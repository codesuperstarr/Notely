package com.example.notely.Persistence;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.notely.Models.Note;

@Database(entities = {Note.class},version = 1)
public abstract class NoteDatabase extends RoomDatabase {
}
