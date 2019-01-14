package com.raktim.picsecret;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class dec_enc_option extends Activity
{

	Button encript_optn_btn,decript_optn_btn;
	@Override
protected void onCreate(Bundle savedInstanceState) 
{
	
	super.onCreate(savedInstanceState);
	setContentView(R.layout.dec_enc_option);
	encript_optn_btn=(Button)findViewById(R.id.encript_optn_btn);
	decript_optn_btn=(Button)findViewById(R.id.decript_optn_btn);
	
	encript_optn_btn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent in=new Intent(getApplicationContext(),text_encript.class);
			startActivity(in);
			
		}
	});
	decript_optn_btn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent in=new Intent(getApplicationContext(),Imgdecript.class);
			startActivity(in);
			
		}
	});
	
}
	
}
