package com.nymrok.mareu.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nymrok.mareu.R;
import com.nymrok.mareu.injections.ViewModelFactory;
import com.nymrok.mareu.ui.MeetingsList.MeetingsListAdapter;
import com.nymrok.mareu.ui.MeetingsList.MeetingsListViewModel;
import com.nymrok.mareu.ui.MeetingsList.OnMeetingClickedListener;

public class MeetingsFragment extends Fragment {

    private RecyclerView mRecyclerView;

    public MeetingsFragment() {}

    public static Fragment newInstance() {
        MeetingsFragment fragment = new MeetingsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meetings, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        MeetingsListViewModel viewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(MeetingsListViewModel.class);

        MeetingsListAdapter adapter = new MeetingsListAdapter(viewModel::onDeleteMeetingClicked);

        mRecyclerView.setAdapter(adapter);

        viewModel.getMeetingViewStateItemsLiveData().observe(getViewLifecycleOwner(), adapter::submitList);
    }
}