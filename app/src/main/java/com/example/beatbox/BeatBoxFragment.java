package com.example.beatbox;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.beatbox.databinding.FragmentBeatBoxBinding;
import com.example.beatbox.databinding.ListItemSoundBinding;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

public class BeatBoxFragment extends Fragment {

    Logger mLogger=Logger.getLogger(getClass().getName());

    @Inject
    BeatBox mBeatBox;

    SeekBar mSeekBar;

    public static BeatBoxFragment newInstance(){
        return new BeatBoxFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       FragmentBeatBoxBinding binding= DataBindingUtil.inflate(inflater,R.layout.fragment_beat_box,container,false);
       binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
       binding.recyclerView.setAdapter(new SoundAdapter(mBeatBox.getSounds()));

       return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);//onDestroy is not called
        ((GlobalVariables)getActivity().getApplicationContext()).getApplicationComponent().inject(this);
    }

    class SoundHolder extends RecyclerView.ViewHolder {

        ListItemSoundBinding mListItemSoundBinding;

        @Inject
        SoundViewModel mSoundViewModel;

        public SoundHolder(ListItemSoundBinding binding) {
            super(binding.getRoot());
            ((GlobalVariables)getActivity().getApplicationContext()).getApplicationComponent().inject(this);
            mListItemSoundBinding=binding;
            mListItemSoundBinding.setViewModel(mSoundViewModel);
        }

        void setSound(Sound sound){
            mListItemSoundBinding.getViewModel().setSound(sound);
//            mSoundViewModel.setSound(sound);
            mListItemSoundBinding.executePendingBindings();
        }

//        @Override
//        public void onClick(View v) {
//
//            mListItemSoundBinding.getViewModel().getBeatBox().play(mSoundViewModel.getSound());
////            mSoundViewModel.onButtonClicked();
//        }
    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder>{

        private List<Sound> mSounds;

        public SoundAdapter(List<Sound> sounds) {
            mSounds = sounds;
        }

        @NonNull
        @Override
        public SoundHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater=LayoutInflater.from(getActivity());
            ListItemSoundBinding binding=DataBindingUtil.inflate(inflater,R.layout.list_item_sound,parent,false);
            return new SoundHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull SoundHolder holder, int position) {
            holder.setSound(mSounds.get(position));

        }

        @Override
        public int getItemCount() {
            return  mSounds.size();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       mLogger.info("onDestroy is called!!");
        mBeatBox.release();
    }
}
