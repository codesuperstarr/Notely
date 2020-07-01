package com.example.notely.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notely.Models.Note;
import com.example.notely.R;

import java.util.ArrayList;

public class NotesRecyclerAdapter extends RecyclerView.Adapter<NotesRecyclerAdapter.ViewHolder>{

    private ArrayList<Note> mNOtes = new ArrayList<>();
    private OnNoteListener mOnNoteListener;

    public NotesRecyclerAdapter(ArrayList<Note> notes,OnNoteListener onNoteListener) {
        this.mNOtes = notes;
        this.mOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_note_list_item, parent, false);
        return new ViewHolder(view,mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.timeStamp.setText(mNOtes.get(position).getContent());
        holder.title.setText(mNOtes.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return mNOtes.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title, timeStamp;
        OnNoteListener onNoteListener;

        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            title = itemView.findViewById(R.id.note_title);
            timeStamp = itemView.findViewById(R.id.note_timeStamp);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());

        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);


    }

}
