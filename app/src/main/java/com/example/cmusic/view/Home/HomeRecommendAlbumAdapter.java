package com.example.cmusic.view.Home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmusic.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import bean.Album;
import bean.Albums;

/**
 * Created by dr-chene on @date 2020/5/2
 */
public class HomeRecommendAlbumAdapter extends RecyclerView.Adapter<HomeRecommendAlbumAdapter.ViewHolder> {

    private Context context;
    private Albums albums;
    private HomeFragment.OnFragmentInteractionListener mListener;

    public HomeRecommendAlbumAdapter(Context context, Albums albums,HomeFragment.OnFragmentInteractionListener mListener) {
        this.context = context;
        this.albums = albums;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Album album = albums.getPlaylists().get(position);
//        Bitmap bitmap = getURLimage(album.getCoverImgUrl());
//        holder.homeRecommendCoverImg.setImageBitmap(bitmap);
        holder.homeRecommendCoverImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction(null,album);
            }
        });
        Log.d("TAG", "onBindViewHolder: "+album.getCoverImgUrl());
        holder.homeRecommendName.setText(album.getName());
        holder.homeRecommendCreator.setText(album.getCreator().getNickname());
    }

    @Override
    public int getItemCount() {
        return albums == null?0:albums.getPlaylists().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
         ImageView homeRecommendCoverImg;
         TextView homeRecommendName;
         TextView homeRecommendCreator;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            homeRecommendCoverImg = itemView.findViewById(R.id.home_recommend_rv_item_cover_iv);
            homeRecommendName = itemView.findViewById(R.id.home_recommend_rv_item_name_tv);
            homeRecommendCreator = itemView.findViewById(R.id.home_recommend_rv_item_creator_tv);
        }
    }

    private Bitmap getURLimage(String url) {
        Bitmap bmp = null;
        try {
            URL myurl = new URL(url);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.setConnectTimeout(6000);//设置超时
            conn.setDoInput(true);
            conn.setUseCaches(false);//不缓存
//            conn.connect();
            InputStream is = conn.getInputStream();//获得图片的数据流
            bmp = BitmapFactory.decodeStream(is);//读取图像数据
            //读取文本数据
            //byte[] buffer = new byte[100];
            //inputStream.read(buffer);
            //text = new String(buffer);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }

}
