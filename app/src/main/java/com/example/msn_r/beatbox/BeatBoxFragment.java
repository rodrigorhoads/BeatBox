package com.example.msn_r.beatbox;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.msn_r.beatbox.databinding.FragmentBeatBoxBinding;
import com.example.msn_r.beatbox.databinding.ListItemSoundBinding;

import java.util.ArrayList;
import java.util.List;


public class BeatBoxFragment extends Fragment {


    private  BeatBox beatBox;
    public static BeatBoxFragment newInstance() {
        return new BeatBoxFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        beatBox = new BeatBox(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentBeatBoxBinding binding = DataBindingUtil
                .inflate(inflater,R.layout.fragment_beat_box,container,false);

        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        binding.recyclerView.setAdapter(new SoundAdapter(beatBox.getmSounds()));
        return binding.getRoot();
    }

    public class SoundHolder extends RecyclerView.ViewHolder {
        private ListItemSoundBinding mBinding;

        public SoundHolder(ListItemSoundBinding binding) {
            super(binding.getRoot());
            mBinding=binding;
            mBinding.setViewModel(new SoundsViewModel(beatBox));
        }
        public void Bind(Sounds sound){
            mBinding.getViewModel().setSound(sound);
            mBinding.executePendingBindings();
        }
    }

    public class SoundAdapter extends RecyclerView.Adapter<SoundHolder>{
        private List<Sounds> listSounds = new ArrayList<>();

        public SoundAdapter(List<Sounds> sounds){
            listSounds=sounds;
        }

        @NonNull
        @Override
        public SoundHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            ListItemSoundBinding listItemSoundBinding= DataBindingUtil.inflate(inflater,R.layout.list_item_sound,parent,false);

            return new SoundHolder(listItemSoundBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull SoundHolder holder, int position) {
            Sounds sounds = listSounds.get(position);
            holder.Bind(sounds);
        }

        @Override
        public int getItemCount() {
            return listSounds.size();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        beatBox.release();
    }
}
