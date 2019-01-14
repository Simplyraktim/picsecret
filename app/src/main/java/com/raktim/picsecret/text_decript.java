package com.raktim.picsecret;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class text_decript extends Activity{
	EditText dec_text_key_edt;
	Button dec_text_btn;
	TextView dec_text_view;
	int count=0;
    SimpleCrypto2 tool = new SimpleCrypto2();
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_decript);
		dec_text_btn=(Button)findViewById(R.id.dec_text_btn);
		dec_text_key_edt=(EditText)findViewById(R.id.dec_text_key_edt);
		dec_text_view=(TextView)findViewById(R.id.dec_text_view);


		dec_text_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				try {


					//System.out.println("Decrypted ----: "+SimpleCrypto2.decrypt("raktim", SimpleCrypto2.encrypt("raktim", "Hai how r u?")));
					String key = dec_text_key_edt.getText().toString();
                    //key = "Bar12345Bar12345";
					int gap = SimpleCrypto2.getExtra();
					String addIt = "";
					for(int i=0;i<gap;i++) addIt+="*";
					key = addIt+key;

					Log.d("abid1",Integer.toString(SimpleCrypto2.getExtra()));
					String original = tool.decrypt(key,"RandomInitVector", Imgdecript.text_code_string);
					if(original==null)
					{
						original = "Wrong Key!! Press the right key. Thanks";
					}
                    dec_text_view.setText(original);

					//dec_text_view.setText(SimpleCrypto2.decrypt());
					System.out.println("#Debug:"+dec_text_key_edt.getText().toString()+Imgdecript.text_code_string);


				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "Cryptography Exception See log cat....", 100).show();
					count++;
					if(count==3){
						Intent in=new Intent(getApplicationContext(),entry.class);
						startActivity(in);
						finish();
					}
				}

			}
		});
	}

}
