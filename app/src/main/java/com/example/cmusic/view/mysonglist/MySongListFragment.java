package com.example.cmusic.view.mysonglist;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.chttp.CHttp;
import com.example.chttp.CallBack;
import com.example.chttp.Request;
import com.example.cjson.CJson;
import com.example.cmusic.R;
import com.example.cmusic.view.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import bean.Songs;
import bean.Tracks;
import bean.mysonglist.MySongList;
import bean.mysonglist.PlayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MySongListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MySongListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MySongListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerView;
    private MySongList songList;
    private Songs songs;
    private Handler handler = new Handler();

    public MySongListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MySongListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MySongListFragment newInstance(String param1, String param2) {
        MySongListFragment fragment = new MySongListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_song_list, container, false);
        initView(view);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.my_song_list_rv);
        getSongList();
    }

    private void getSongList() {
        final CHttp http = CHttp.getChHttp();
        Request request = new Request(MainActivity.GET_MY_SONG_LIST+"?uid=1351234965");
        http.newCall(request, new CallBack() {
            @Override
            public void onSuccess(String str) {
                List<Class> clazz = new ArrayList<>();
                clazz.add(PlayList.class);
                CJson json = new CJson(clazz);
                songList = json.formJson(str,MySongList.class);
                Log.d("TAGMYSONG", "onSuccess: "+(songList == null));
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Request songRequest = new Request(MainActivity.GET_PLAYLIST_DETAIL+"?id="+songList.getPlaylist().get(0).getId());
                        http.newCall(songRequest, new CallBack() {
                            @Override
                            public void onSuccess(String str) {
                                List<Class> classes = new ArrayList<>();
                                classes.add(Tracks.class);
                                CJson songJson = new CJson(classes);
                                songs = songJson.formJson(str,Songs.class);
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Log.d("TAG2", "run: "+(songs == null));
                                        Log.d("TAG2", "run: "+songs.getPlaylist().getTracks().get(0).getName());
                                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                                        recyclerView.setLayoutManager(layoutManager);
                                        MySongListAdapter adapter = new MySongListAdapter(getContext(),songs);
                                        recyclerView.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                    }
                                });
                            }

                            @Override
                            public void onFailed(Exception e) {

                            }
                        });
                    }
                });
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
        http.execute();
    }

}
