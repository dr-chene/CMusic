package com.example.cmusic.view.mycenter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chttp.CHttp;
import com.example.chttp.CallBack;
import com.example.chttp.Request;
import com.example.cjson.CJson;
import com.example.cmusic.R;
import com.example.cmusic.view.main.MainActivity;

import bean.mycenter.MyCenter;
import bean.mycenter.Profile;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyCenterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyCenterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyCenterFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private TextView nickname;
    private TextView city;
    private TextView followers;
    private TextView posts;
    private TextView following;
    private MyCenter myCenter;
    private Handler handler = new Handler();

    public MyCenterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyCenterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyCenterFragment newInstance(String param1, String param2) {
        MyCenterFragment fragment = new MyCenterFragment();
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
        View view = inflater.inflate(R.layout.fragment_my_center, container, false);
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
        nickname = view.findViewById(R.id.my_center_nickname_tv);
        city = view.findViewById(R.id.my_center_city_tv);
        followers = view.findViewById(R.id.my_center_followers_tv);
        following = view.findViewById(R.id.my_center_following_tv);
        posts = view.findViewById(R.id.my_center_posts_tv);

        final CHttp http = CHttp.getChHttp();
        Request request = new Request(MainActivity.GET_USER_DETAIL+"?uid=1351234965");
        http.newCall(request, new CallBack() {
            @Override
            public void onSuccess(String str) {
                CJson json = new CJson(null);
                myCenter = json.formJson(str,MyCenter.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Profile profile = myCenter.getProfile();
                        nickname.setText(profile.getNickname());
                        city.setText(""+profile.getCity());
                        followers.setText(""+profile.getFolloweds());
                        posts.setText(""+profile.getPlaylistCount());
                        following.setText(""+profile.getFollows());
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
