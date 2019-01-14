package com.raktim.picsecret;

import android.app.Activity;
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

public class pass_set extends Activity{

	Spinner sq;
	Button save,ok;
	EditText oldpsd,newpsd,cnpsd,sqans;
	String old,chng,cnchng,seq,seqans;
	String[] qs = {"Your Favorite Pet?","Your Favorite Color?","Your Favorite Teacher"};

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.pass_set);

		save=(Button)findViewById(R.id.button2);
		ok=(Button)findViewById(R.id.button1);
		oldpsd=(EditText)findViewById(R.id.editText1);
		newpsd=(EditText)findViewById(R.id.editText2);
		cnpsd=(EditText)findViewById(R.id.editText3);
		sq=(Spinner)findViewById(R.id.editText4);
		sqans=(EditText)findViewById(R.id.editText5);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,qs);
		sq.setAdapter(adapter);

		sq.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
										 @Override
										 public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


										 }
										 @Override
										 public void onNothingSelected(AdapterView<?> parent) {
											 Toast.makeText(getApplicationContext(),"You selected nothing:" ,Toast.LENGTH_LONG).show();

										 }
									 }
		);
		save.setOnClickListener(new OnClickListener() {


			public void onClick(View v) {
				old=oldpsd.getText().toString();
				chng=newpsd.getText().toString();
				cnchng=cnpsd.getText().toString();
				if(chng.equals(cnchng))
				{
					SharedPreferences pass=getSharedPreferences("regdet", MODE_WORLD_WRITEABLE);
					Editor psd=pass.edit();
					psd.putString("Newpassword", chng);
					psd.commit();
				}

				else
				{
					Toast.makeText(getApplicationContext(), "Password and Confirm Password donot match", Toast.LENGTH_LONG).show();
				}


			}
		});
		ok.setOnClickListener(new OnClickListener() {


			@Override
			public void onClick(View v) {


				seqans=sqans.getText().toString();
				seq=sq.getSelectedItem().toString();


				SharedPreferences forget=getSharedPreferences("regdet", MODE_WORLD_WRITEABLE);
				String shrseq=forget.getString("Sequrity", "Not found");
				String shrans=forget.getString("Answer", "Not found");

				if(seq.equals(shrseq)&&(seqans.equals(shrans)))
					if (seqans.equals(shrans))
					{
						String shrpas=forget.getString("Newpassword", "Not found");
						Toast.makeText(getApplicationContext(), "Your Password is:"+shrpas, Toast.LENGTH_LONG).show();
					}
					else
					{
						Toast.makeText(getBaseContext(), "Please Enter correct Security Question and Answer", Toast.LENGTH_LONG).show();
					}
			}
		});
	}
}

