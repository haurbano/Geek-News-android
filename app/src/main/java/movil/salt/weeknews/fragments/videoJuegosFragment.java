package movil.salt.weeknews.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import movil.salt.weeknews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class videoJuegosFragment extends TitleFragment {

    private final String title = "Video Juegos";

    public videoJuegosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_juegos, container, false);
    }


    @Override
    public String getTtitle() {
        return title;
    }
}
