package com.raktim.picsecret;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class entry extends Activity{

    TextView tpwd;
    Button btn_reg,chk;
    String user,password;
    EditText us,pw;
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);
        
        tpwd=(TextView)findViewById(R.id.textView5);
        btn_reg=(Button)findViewById(R.id.button1);
        chk=(Button)findViewById(R.id.button2);
        us=(EditText)findViewById(R.id.editText1);
        pw=(EditText)findViewById(R.id.editText2);
        
        tpwd.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View arg0) {
				Intent int1=new Intent(getApplicationContext(),pass_set.class);
				startActivity(int1);
				
				
			}
		});
        btn_reg.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View arg0) {
			Intent int2=new Intent(getApplicationContext(),register.class);
			startActivity(int2);
				
			}
		});
        
        chk.setOnClickListener(new OnClickListener() {
        	
			
			@Override
			public void onClick(View arg0) {
			
							user=us.getText().toString();
							password=pw.getText().toString();
				 SharedPreferences log=getSharedPreferences("regdet", MODE_WORLD_READABLE);
				 String shruser=log.getString("username","not found");
				 String shrpas=log.getString("Newpassword","not found");
				 
				 if(user.equals(shruser)&&(password.equals(shrpas)))
				 {
				 
				 
				 Intent chk=new Intent(getApplicationContext(),dec_enc_option.class);
					startActivity(chk);
					 
				 }
				 else
				 {
				   Toast.makeText(getApplicationContext(), "Invalid username/password",Toast.LENGTH_LONG).show();
				 }
				
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
