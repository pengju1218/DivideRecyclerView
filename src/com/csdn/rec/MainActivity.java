package com.csdn.rec;

import android.app.Activity;
import android.os.Bundle;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 收藏图片与视频
 */
public class MainActivity extends Activity {
    private RecyclerView idsReceyee;
    private List<HomeItem> list=new ArrayList<>();
    private String[] urls={"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1212787870,3689909937&fm=26&gp=0.jpg"
            ,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1212787870,3689909937&fm=26&gp=0.jpg"

            ,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1212787870,3689909937&fm=26&gp=0.jpg"
            ,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1212787870,3689909937&fm=26&gp=0.jpg"
            ,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1212787870,3689909937&fm=26&gp=0.jpg"
            ,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1212787870,3689909937&fm=26&gp=0.jpg"
            ,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1212787870,3689909937&fm=26&gp=0.jpg"
            ,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1212787870,3689909937&fm=26&gp=0.jpg"
            ,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1212787870,3689909937&fm=26&gp=0.jpg"





    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();

        idsReceyee = (RecyclerView) findViewById(R.id.ids_receyee);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this );
//设置布局管理器
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        idsReceyee.setLayoutManager(layoutManager);
        idsReceyee.setAdapter(new HomeAdapter(R.layout.img_item_video,list));
    }

    public void init(){
        list.clear();
        for(int i=0;i<10;i++){
            HomeItem homeItem=new HomeItem();
            homeItem.setTime("2058-01-25");
            homeItem.setImgurls(urls);
            list.add(homeItem);

        }
    }





    public class HomeAdapter extends BaseQuickAdapter<HomeItem, BaseViewHolder> {
        public HomeAdapter(int layoutResId, List data) {
            super(R.layout.img_item_video, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeItem item) {
            //helper.setText(R.id.text, item.getTitle());
            //helper.setImageResource(R.id.icon, item.getImageResource());
            // 加载网络图片
            //Glide.with(mContext).load(item.getUserAvatar()).crossFade().into((ImageView) helper.getView(R.id.iv));

            helper.setText(R.id.ids_time,item.getTime());
            List<String> strings= Arrays.asList(item.getImgurls());
            RecyclerView recyclerView=(RecyclerView)helper.getView(R.id.recyclerview2);

// 设置布局管理器：瀑布流式

            //GridLayoutManager gridLayoutManager=new GridLayoutManager(ImgAndVideoActivity.this,4);

//            //recyclerView.setLayoutManager(gridLayoutManager);


            GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 4);
          /*  gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position == 0) {
                        return 4;
                    } else {
                        return 1;
                    }
                }
            });*/

            recyclerView.addItemDecoration(new SpaceItemDecoration(4));

            //  recyclerView.addItemDecoration(new DividerItemDecoration(ImgAndVideoActivity.this , DividerItemDecoration.VERTICAL));
            //recyclerView.addItemDecoration(new DividerItemDecoration(ImgAndVideoActivity.this , DividerItemDecoration.HORIZONTAL));

//           / /FlowLayoutManager flowLayoutManager=new FlowLayoutManager(ImgAndVideoActivity.this,true);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(new ItemAdatper(R.layout.img_item,strings));
        }
    }

    public class ItemAdatper extends BaseQuickAdapter<String, BaseViewHolder> {
        public ItemAdatper(int layoutResId, List data) {
            super(R.layout.img_item, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String im) {
            helper.setImageResource(R.id.img_bak,R.drawable.head);
            /*if(MediaFile.isImg(im)){
                Glide.with(mContext).load("http://www.pptbz.com/pptpic/UploadFiles_6909/201203/2012031220134655.jpg").into((ImageView) helper.getView(R.id.img_bak));

            }else if(MediaFile.isVideoFileType(im)){
             Bitmap bitmap=getBitmap(im);
             helper.setImageBitmap(R.id.img_bak,bitmap);
            }*/
        }
    }


    public Bitmap getBitmap(String url){

        Bitmap bitmap = null;
        //MediaMetadataRetriever 是android中定义好的一个类，提供了统一
        //的接口，用于从输入的媒体文件中取得帧和元数据；
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据文件路径获取缩略图
            retriever.setDataSource(url, new HashMap<String, String>());
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime(0, MediaMetadataRetriever.OPTION_NEXT_SYNC);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }

        return bitmap;

    }
}
