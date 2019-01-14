package com.raktim.picsecret;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class register extends Activity{

	Spinner sqtxt;
	EditText ustxt;
	EditText nptxt, cnptxt, anstxt;
	Button reg;
	String user,paswrd,cnpaswrd,seq,ans;
	String[] qs = {"Your Favorite Pet?","Your Favorite Color?","Your Favorite Teacher?"};

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		reg=(Button)findViewById(R.id.button1);

		ustxt=(EditText)findViewById(R.id.editText1);
		nptxt=(EditText)findViewById(R.id.editText2);
		cnptxt=(EditText)findViewById(R.id.editText3);
		sqtxt = (Spinner) findViewById(R.id.editText4);

		anstxt=(EditText)findViewById(R.id.editText5);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,qs);
		sqtxt.setAdapter(adapter);

		sqtxt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				//seq=sqtxt.getOnItemSelectedListener().toString();


				reg.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {

						user=ustxt.getText().toString();
						paswrd=nptxt.getText().toString();
						cnpaswrd=cnptxt.getText().toString();
						String seq=sqtxt.getSelectedItem().toString();
						ans=anstxt.getText().toString();

						if(paswrd.equals(cnpaswrd))
						{

							SharedPreferences spf=getSharedPreferences("regdet",MODE_WORLD_WRITEABLE);
							Editor regis=spf.edit();
							regis.putString("username", user);
							regis.putString("Newpassword", paswrd);
							regis.putString("Sequrity", seq);
							regis.putString("Answer", ans);

							regis.commit();
							Intent in=new Intent(getApplicationContext(),entry.class);
							startActivity(in);
						}
						else
						{
							Toast.makeText(getApplicationContext(),"password and confirm password did not match" ,Toast.LENGTH_LONG).show();
						}


					}
				});
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Toast.makeText(getApplicationContext(),"You selected nothing:" ,Toast.LENGTH_LONG).show();

			}
		});}}