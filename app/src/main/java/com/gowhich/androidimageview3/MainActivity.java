package com.gowhich.androidimageview3;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private int minWidth = 80;
    private ImageView imageView;
    private TextView textView1;
    private TextView textView2;
    private SeekBar seekBar1;
    private SeekBar seekBar2;
    private Matrix matrix = new Matrix();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) this.findViewById(R.id.imageView);
        textView1 = (TextView) this.findViewById(R.id.textView1);
        textView2 = (TextView) this.findViewById(R.id.textView2);
        seekBar1 = (SeekBar) this.findViewById(R.id.seekBar1);
        seekBar2 = (SeekBar) this.findViewById(R.id.seekBar2);

        seekBar1.setOnSeekBarChangeListener(this);
        seekBar2.setOnSeekBarChangeListener(this);

        //
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        seekBar1.setMax(displayMetrics.widthPixels - minWidth);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (seekBar.getId() == R.id.seekBar1) {
            //缩放图片
            int newWidth = i + minWidth;
            int newHeight = (int)(newWidth * 3/4);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(newWidth, newHeight));
            textView1.setText("图像宽度："+newWidth+" 图像高度："+newHeight);
        } else if (seekBar.getId() == R.id.seekBar2) {
            //翻转图片
            Bitmap bitmap = ((BitmapDrawable)(getResources().getDrawable(R.drawable.background))).getBitmap();
            matrix.setRotate(i);//设置翻转角度
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            imageView.setImageBitmap(bitmap);
            textView2.setText("图像翻转角度："+i);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        if (seekBar.getId() == R.id.seekBar1) {

        } else if (seekBar.getId() == R.id.seekBar2) {

        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar.getId() == R.id.seekBar1) {

        } else if (seekBar.getId() == R.id.seekBar2) {

        }
    }
}
