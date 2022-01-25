package com.nymrok.mareu.ui.MeetingsList;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.nymrok.mareu.R;

public class MeetingsListAdapter extends ListAdapter<MeetingsViewStateItem, MeetingsListAdapter.ViewHolder> {

    private final OnMeetingClickedListener listener;

    public MeetingsListAdapter(OnMeetingClickedListener listener) {
        super(new ListMeetingItemCallback());
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.meeting_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.affectation(getItem(position), listener);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ConstraintLayout mMeetingLine;
        public ImageView mMeetingPicture;
        public TextView mMeetingTitle;
        public TextView mMeetingContent;
        public ImageButton mMeetingDeleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mMeetingLine = itemView.findViewById(R.id.fragment_meeting);
            mMeetingPicture = itemView.findViewById(R.id.fragment_meeting_color);
            mMeetingTitle = itemView.findViewById(R.id.fragment_meeting_topLine);
            mMeetingContent = itemView.findViewById(R.id.fragment_meeting_bottomLine);
            mMeetingDeleteBtn = itemView.findViewById(R.id.fragment_meeting_delete_button);
        }

        public void affectation(MeetingsViewStateItem item, OnMeetingClickedListener listener) {
            String title = item.getName() + " - " + item.getHour() + " - " + item.getRoom();
            mMeetingTitle.setText(title);

            String content = TextUtils.join(", ", new String[]{item.getMembers().toString()});
            content = content.replace("[","");
            content = content.replace("]","");
            mMeetingContent.setText(content);

            mMeetingDeleteBtn.setOnClickListener(v -> listener.onDeleteMeetingClicked(item.getId()));

            String color = item.getColor();
            switch(color) {
                case "Beige":
                    mMeetingPicture.setBackgroundResource(R.drawable.ic_circle_beige);
                    break;
                case "Green":
                    mMeetingPicture.setBackgroundResource(R.drawable.ic_circle_green);
                    break;
                case "Blue":
                    mMeetingPicture.setBackgroundResource(R.drawable.ic_circle_blue);
                    break;
                case "Yellow":
                    mMeetingPicture.setBackgroundResource(R.drawable.ic_circle_yellow);
                    break;
                default:
                    mMeetingPicture.setBackgroundResource(R.drawable.ic_circle_grey);
                    break;
            }
        }
    }

    private static class ListMeetingItemCallback extends DiffUtil.ItemCallback<MeetingsViewStateItem> {
        @Override
        public boolean areItemsTheSame(@NonNull MeetingsViewStateItem oldItem, @NonNull MeetingsViewStateItem newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull MeetingsViewStateItem oldItem, @NonNull MeetingsViewStateItem newItem) {
            return oldItem.equals(newItem);
        }
    }
}