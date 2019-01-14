package com.raktim.picsecret;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Imgencript extends Activity{

	Button encript_browse_btn,enc_savecode_btn,enc_sendimg_btn;
	EditText enc_txtcode_edt;
	TextView enc_img_name;
	ImageView enc_brows_image;
	Bitmap bmpImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imgencript);
		encript_browse_btn=(Button)findViewById(R.id.encrypt_browse_btn);
		enc_savecode_btn=(Button)findViewById(R.id.enc_savecode_btn);
		enc_sendimg_btn=(Button)findViewById(R.id.enc_sending_btn);
		enc_txtcode_edt=(EditText)findViewById(R.id.enc_txtcode_edt);
		enc_img_name=(TextView)findViewById(R.id.enc_img_name);
		enc_brows_image=(ImageView)findViewById(R.id.enc_brows_image);
		
		enc_txtcode_edt.setText(text_encript.encrypted_string);
		
		encript_browse_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
				photoPickerIntent.setType("image/*");
				startActivityForResult(photoPickerIntent, 1337);
			}
		});
		enc_savecode_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {


				if(bmpImage == null) {
					Toast.makeText(getBaseContext(), "No Image Selected, Cannot Encode",
							Toast.LENGTH_SHORT).show();
					return;
				}
				if(enc_txtcode_edt.getText().length() < 1) {
					Toast.makeText(getBaseContext(), "No Secret Message, Cannot Encode",
							Toast.LENGTH_SHORT).show();
					return;
				}
				
				bmpImage = Steganography.encode(bmpImage, enc_txtcode_edt.getText().toString());
				OutputStream fOut = null;
						
				
				
				File file = new File(Environment.getExternalStorageDirectory(), "a"+enc_img_name.getText().toString());
				try{
				
					
					fOut = new FileOutputStream(file);
			
					bmpImage.compress(Bitmap.CompressFormat.PNG, 100, fOut);
					fOut.flush();
					fOut.close();
					
					enc_txtcode_edt.setText("");
					Toast.makeText(getBaseContext(), "Secret encoded successfully and image saved",
							Toast.LENGTH_SHORT).show();
			
				}catch(Exception ex){
					System.out.println(ex.getMessage());
				}
			}
		});
		enc_sendimg_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//Do Sending File Here............
				Intent intent = new Intent();  
                intent.setAction(Intent.ACTION_SEND);  
                intent.setType("image/*");


                String uri = "/mnt/sdcard/test.jpg";
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "a"+enc_img_name.getText().toString())));
                startActivity(intent);
			}
		});
	}
	// image picker intent
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	if(requestCode == 1337) {
    		if(resultCode == Activity.RESULT_OK){
    			Uri selectedImage = data.getData();
    			enc_brows_image.setImageURI(selectedImage);

    			String sFilePath = getRealPathFromURI(selectedImage);
    			
    			if(sFilePath == null) {
    				Toast.makeText(getBaseContext(), "Image Not Found",
    						Toast.LENGTH_SHORT).show();
    				return;
    			} else {
        			String saPathParts[] = sFilePath.split("/");
        			String sFileName = saPathParts[saPathParts.length-1];
        			
        			enc_img_name.setText(sFileName);
        			
    				bmpImage = BitmapFactory.decodeFile(sFilePath);
    				if(bmpImage == null) {
    					Toast.makeText(getBaseContext(), "Image invalid", 
    							Toast.LENGTH_SHORT).show();
    					return;
    				}
    			}
    		}
    	}
    }
    
    // gets the actual file path from a Uri object
    private String getRealPathFromURI(Uri contentURI) {
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null); 
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA); 
        return cursor.getString(idx); 
    }
	
}
