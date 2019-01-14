package com.raktim.picsecret;



import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Imgdecript extends Activity{
Button decript_browse_btn,dec_getcode_btn,dec_gettext_btn;
EditText dec_txtcode_edt;
TextView dec_img_name;
ImageView dec_brows_image;
Bitmap bmpImage;
public static String text_code_string;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		decript_browse_btn=(Button)findViewById(R.id.decript_browse_btn);
		dec_getcode_btn=(Button)findViewById(R.id.dec_getcode_btn);
		dec_gettext_btn=(Button)findViewById(R.id.dec_gettext_btn);
		dec_txtcode_edt=(EditText)findViewById(R.id.dec_txtcode_edt);
		dec_img_name=(TextView)findViewById(R.id.dec_img_name);
		dec_brows_image=(ImageView)findViewById(R.id.dec_brows_image);
		
		decript_browse_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
				photoPickerIntent.setType("image/*");
				startActivityForResult(photoPickerIntent, 1337);
			}
		});
		dec_getcode_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(bmpImage == null) {
					Toast.makeText(getBaseContext(), "No Image Selected, Cannot Decode",
							Toast.LENGTH_SHORT).show();
					return;
				}
				
				String decodedMsg = Steganography.decode(bmpImage);
				text_code_string=decodedMsg;
				if(decodedMsg == null || decodedMsg.length() < 1) {
					Toast.makeText(getBaseContext(), "There Was NO Message Encoded in the Image",
							Toast.LENGTH_SHORT).show();
					return;
				} else {
					dec_txtcode_edt.setText(decodedMsg);
					Toast.makeText(getBaseContext(), "Secret Message Decrypted From Image!",
							Toast.LENGTH_SHORT).show();
				}
				
			}
		});

		dec_gettext_btn.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		
		//Text decryption page here............
		Intent text_dec_intent=new Intent(getApplicationContext(),text_decript.class);
		startActivity(text_dec_intent);
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
    			dec_brows_image.setImageURI(selectedImage);

    			String sFilePath = getRealPathFromURI(selectedImage);
    			
    			if(sFilePath == null) {
    				Toast.makeText(getBaseContext(), "Image Not Found",
    						Toast.LENGTH_SHORT).show();
    				return;
    			} else {
        			String saPathParts[] = sFilePath.split("/");
        			String sFileName = saPathParts[saPathParts.length-1];
        			
        			dec_img_name.setText(sFileName);
        			
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
