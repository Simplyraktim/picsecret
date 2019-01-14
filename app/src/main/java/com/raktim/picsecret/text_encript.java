package com.raktim.picsecret;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class text_encript extends Activity{
	EditText enc_text_key_edt,enc_text_edt;
	Button enc_text_btn;
	public static String encrypted_string;
	SimpleCrypto2 tool =new SimpleCrypto2();
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_encript);
		enc_text_key_edt=(EditText)findViewById(R.id.enc_text_key_edt);
		enc_text_edt=(EditText)findViewById(R.id.enc_text_edt);
		enc_text_btn=(Button)findViewById(R.id.enc_text_btn);

		enc_text_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {

					//text.setText(SimpleCrypto.encrypt("raktim", "Hi how r u?"));
					String key = enc_text_key_edt.getText().toString(),temp;
					//key = "Bar12345Bar12345";
					int sz = key.length(),gap=0;
					if(sz<32)
					{
						if(sz<16)
						{
							gap = 16-sz;
						}
						else
						{
							gap = 32-sz;
						}
						tool.setExtra(gap);
					}

					String addIt = "";
					for(int i=0;i<gap;i++) addIt+="*";
					key = addIt+key;

					Log.d("rak1",Integer.toString(SimpleCrypto2.getExtra()));
					Log.d("rak1",key);
					encrypted_string=tool.encrypt(key,"RandomInitVector",enc_text_edt.getText().toString());
					//text.setText(encrypted_string);
					Log.d("rak1","encrypt: "+encrypted_string);
					temp=tool.decrypt(key,"RandomInitVector",encrypted_string);
					Log.d("rak1","decrypt:	"+temp);
					Toast.makeText(getApplicationContext(),encrypted_string , 100).show();
					enc_text_key_edt.setText("");
					enc_text_edt.setText("");

					Intent encriptImg_intent=new Intent(getApplicationContext(),Imgencript.class);
					startActivity(encriptImg_intent);
				} catch (Exception e) {

					e.printStackTrace();
				}

			}
		});

	}
}
