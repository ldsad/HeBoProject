package com.hebo.heboproject.activitys;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.hebo.heboproject.R;

public class CameraActivity extends PublicActivity {

	private Button cameraBtn, selectImgBtn;

	private static String photoPath = "/sdcard/hebo/";
	private static String photoName = photoPath + "test.jpg";
	Uri imageUri;
	private final int REQUEST_CAMERA = 1;
	private final int REQUEST_GALLERY = 2;
	private ImageView imageView;

	private byte [] mContent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		cameraBtn = (Button) findViewById(R.id.cameraBtn);
		selectImgBtn = (Button) findViewById(R.id.selectImgBtn);
		imageView = (ImageView) findViewById(R.id.img_photo);

		cameraBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
				File file = new File(photoPath);
				if (!file.exists()) { // 检查图片存放的文件夹是否存在
					file.mkdir(); // 不存在的话 创建文件夹
				}
				File photo = new File(photoName);
				imageUri = Uri.fromFile(photo);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri); // 这样就将文件的存储方式和uri指定到了Camera应用中
				startActivityForResult(intent, REQUEST_CAMERA);
			}
		});
		
		selectImgBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_PICK, null);
				intent.setDataAndType(
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
				startActivityForResult(intent, REQUEST_GALLERY);
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		String sdStatus = Environment.getExternalStorageState();
		if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
			Log.i("内存卡错误", "请检查您的内存卡");
			return;
		}
		BitmapFactory.Options op = new BitmapFactory.Options();
		Bitmap pic = null;
		op.inSampleSize = 8; // 这个数字越大,图片大小越小.
		switch (requestCode) {
		case REQUEST_CAMERA:
			pic = BitmapFactory.decodeFile(photoName, op);
			imageView.setImageBitmap(pic);
			FileOutputStream b = null;
			;
			try {
				b = new FileOutputStream(photoName);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			if (pic != null) {
				pic.compress(Bitmap.CompressFormat.JPEG, 100, b);
			}
			break;
		case REQUEST_GALLERY:
			Uri uri = data.getData();
			Cursor cursor = getContentResolver().query(uri, null, null, null, null);
			cursor.moveToFirst();
			String imgPath = cursor.getString(1);
			Toast.makeText(CameraActivity.this, imgPath, 3).show();
			pic = BitmapFactory.decodeFile(imgPath, op);
			imageView.setImageBitmap(pic);
			break;
		default:
			return;
		}
	}

//	@Override
//	public boolean onKeyUp(int keyCode, KeyEvent event) {
//		if (keyCode == KeyEvent.KEYCODE_BACK) {
//			if (!(MainActivity.tabHost.getCurrentTabTag().equals("heboInfo"))) {
//				MainActivity.tabHost.setCurrentTabByTag("heboInfo");
//			} else {
//				Toast.makeText(this, "exit exit exit", 3).show();
//			}
//			return true;
//		}
//		return super.onKeyDown(keyCode, event);
//	}
	
	  public static byte[] readStream(InputStream inStream) throws Exception {
	        byte[] buffer = new byte[1024];
	        int len = -1;
	        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	        while ((len = inStream.read(buffer)) != -1) {
	                 outStream.write(buffer, 0, len);
	        }
	        byte[] data = outStream.toByteArray();
	        outStream.close();
	        inStream.close();
	        return data;

	   }

	@Override
	public void initView() {
		
	}

}
