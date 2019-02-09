package com.example.administrator.caringforyouandme;

import android.graphics.Bitmap;

/**
 * 이미지 instance로 넘겨주기
 */
public class BitmapHelper {
	private Bitmap bitmap = null;
	private static final BitmapHelper instance = new BitmapHelper();

	public BitmapHelper() {

	}

	public static BitmapHelper getInstance() {
		return instance;
	}

	public  Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
}
