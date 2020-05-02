package com.example.cmusic.view.Home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.chttp.CHttp;
import com.example.chttp.CallBack;
import com.example.chttp.Request;
import com.example.chttp.RequestBody;
import com.example.cjson.CJson;
import com.example.cmusic.R;
import com.example.cmusic.view.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import bean.Album;
import bean.Albums;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private String TAG = "HomeFragment";
    private RecyclerView recyclerView;
    private Albums albums;
    private HomeRecommendAlbumAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: ");

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri,null);
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
        void onFragmentInteraction(Uri uri,Album album);
    }

   private void initView(View view ){
        recyclerView = view.findViewById(R.id.home_recommend_rv);
        getAlbums();
       GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
       recyclerView.setLayoutManager(layoutManager);
       adapter = new HomeRecommendAlbumAdapter(getContext(),albums,mListener);
       recyclerView.setAdapter(adapter);
       adapter.notifyDataSetChanged();
   }
   private void getAlbums() {
       CHttp http = CHttp.getChHttp();
       Request request = new Request(MainActivity.GET_RECOMMEND_ALBUM);
       http.newCall(request, new CallBack() {
           @Override
           public void onSuccess(String str) {
               List<Class> clazz = new ArrayList<>();
               clazz.add(Album.class);
               clazz.add(String.class);
               CJson json = new CJson(clazz);
               albums = json.formJson(str,Albums.class);
           }

           @Override
           public void onFailed(Exception e) {
//               Toast.makeText(getActivity(),"读取数据失败",Toast.LENGTH_SHORT).show();
               Log.d(TAG, "onFailed: "+e);
           }
       });
       http.execute();
   }
}
