package com.example.beatbox;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import com.example.beatbox.databinding.FragmentBeatBoxBinding;

public class BeatBoxFragment extends Fragment {

    public static BeatBoxFragment newInstance(){
        return new BeatBoxFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentBeatBoxBinding binding= DataBindingUtil.inflate(
                inflater,R.layout.fragment_beat_box,container,false);

        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));

                return binding.getRoot();
    }
}
