package com.ucas.memory;

import android.app.Activity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery.LayoutParams;
import android.widget.ViewSwitcher.ViewFactory;

/**
 * Created by w on 2014/12/30.
 */
public class ShowMemoryInfoActivity extends Activity implements OnItemSelectedListener,
        ViewFactory {


    private ImageSwitcher mSwitcher;
    //大图片对应的缩略图源数组
    private Integer[] mThumbIds = { R.drawable.pic1,
            R.drawable.pic1, R.drawable.pic1,
            R.drawable.pic1, R.drawable.pic1,
            R.drawable.pic1, R.drawable.pic1,
            R.drawable.pic1 };
    //大图片源数组
    private Integer[] mImageIds = { R.drawable.pic1, R.drawable.pic1,
            R.drawable.pic1, R.drawable.pic1, R.drawable.pic1,
            R.drawable.pic1, R.drawable.pic1, R.drawable.pic1 };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置窗口无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.home_detail_head);
        mSwitcher = (ImageSwitcher) findViewById(R.id.switcher);
        //注意在使用一个ImageSwitcher之前，
        //一定要调用setFactory方法，要不setImageResource这个方法会报空指针异常。
        mSwitcher.setFactory(this);
        //设置动画效果
        mSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in));
        mSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out));

        Gallery g = (Gallery) findViewById(R.id.gallery);

        //添加OnItemSelectedListener监听器
        g.setAdapter(new ImageAdapter(this));
        g.setOnItemSelectedListener(this);

    }
    //创建内部类ImageAdapter
    public class ImageAdapter extends BaseAdapter {
        public ImageAdapter(Context c) {
            mContext = c;
        }
        public int getCount() {
            return mThumbIds.length;
        }
        public Object getItem(int position) {
            return position;
        }
        public long getItemId(int position) {
            return position;
        }
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView i = new ImageView(mContext);

            i.setImageResource(mThumbIds[position]);
            //设置边界对齐
            i.setAdjustViewBounds(true);
            //设置布局参数
            i.setLayoutParams(new Gallery.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            //设置背景资源
         //   i.setBackgroundResource(R.drawable.picture_frame);
            return i;
        }
        private Context mContext;
    }

    @Override
    //实现onItemSelected()方法，更换图片
    public void onItemSelected(AdapterView<?> adapter, View v, int position,
                               long id) {
        //设置图片资源
        mSwitcher.setImageResource(mImageIds[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

    @Override
    //实现makeView()方法，为ImageView设置布局格式
    public View makeView() {
        ImageView i = new ImageView(this);
        //设置背景颜色
        i.setBackgroundColor(0xFF000000);
        //设置比例类型
        i.setScaleType(ImageView.ScaleType.FIT_CENTER);
        //设置布局参数
        i.setLayoutParams(new ImageSwitcher.LayoutParams(
                LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        return i;
    }

}


