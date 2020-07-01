package com.example.notely;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.notely.Adapters.NotesRecyclerAdapter;
import com.example.notely.Models.Note;
import com.example.notely.Util.VerticalSpacingItemDecorator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;

public class NoteListActivity extends AppCompatActivity implements
        NotesRecyclerAdapter.OnNoteListener,
        View.OnClickListener {

    private static final String TAG = "Some message";
    //ui components
    private RecyclerView mRecyclerView;


    //vars
    private ArrayList<Note> mNotes = new ArrayList<>();
    private NotesRecyclerAdapter mNoteRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        mRecyclerView = findViewById(R.id.recyclerView);

        (findViewById(R.id.fab)).setOnClickListener(this);

        initRecyclerView();
        insertFakeNotes();
        setSupportActionBar((Toolbar) findViewById(R.id.note_toolbar));
        setTitle("Notes");
    }

    private void insertFakeNotes(){
        for(int i = 0; i < 1000; i++){
            Note note = new Note();
            note.setTitle("title #" + i);
            note.setContent("content #" + i);
            note.setTimeStamp("jan 2020 #" + i);
            mNotes.add(note);
        }
        mNoteRecyclerAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mNoteRecyclerAdapter = new NotesRecyclerAdapter(mNotes,this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(itemDecorator);
        mRecyclerView.setAdapter(mNoteRecyclerAdapter);

    }

    @Override
    public void onNoteClick(int position) {
        Log.d(TAG, "onNoteClick: clicked" + position);
        Intent intent = new Intent(this,NoteActivity.class);
        intent.putExtra("selected_notes", mNotes.get(position));
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,NoteActivity.class);
        startActivity(intent);

    }

    private void deleteNote(Note note){
        mNotes.remove(note);
        mNoteRecyclerAdapter.notifyDataSetChanged();

    }

    private ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
            new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN,
                    ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            deleteNote(mNotes.get(viewHolder.getAdapterPosition()));
        }
    };
}