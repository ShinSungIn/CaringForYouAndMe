package com.example.administrator.caringforyouandme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;

public class PhotoViewActivity extends AppCompatActivity {

	ImageView imageView;
	PhotoView photoView;
	PhotoViewAttacher mAttacher;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photoview);
/*
		imageView = (ImageView) findViewById(R.id.photo_imageview);
		// helper에 있는 instance로 이미지 표시하기
		imageView.setImageBitmap(BitmapHelper.getInstance().getBitmap());

		mAttacher = new PhotoViewAttacher(imageView);
		// 3.화면에 꽉차는 옵션 (선택사항)
		//mAttacher.setScaleType(ImageView.ScaleType.FIT_XY);
		mAttacher.setZoomable(true);
		mAttacher.update();
*/
		photoView = findViewById(R.id.photo_imageview);
		photoView.setImageBitmap(BitmapHelper.getInstance().getBitmap());
		mAttacher = new PhotoViewAttacher(photoView);
		mAttacher.setZoomable(true);
		mAttacher.update();
	}
}
