package com.example.assignment1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	//This activity is main menu, contains two buttons create button and view history
	private Button myButton = null;
	private Button buttonPre = null;
	int clickedd=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myButton=(Button)findViewById(R.id.myButton);
		myButton.setText("CreateButton");
		buttonPre=(Button)findViewById(R.id.buttonPre);
		buttonPre.setText("Previous Counters");
		myButton.setOnClickListener(new MyButtonListener());
		buttonPre.setOnClickListener(new PreButtonListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	//once we click this button, will check if file is empty or exists and jump to another activity called PreBut 
	class PreButtonListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			String string= "";
			try{
				FileInputStream fis = openFileInput("file6.sav");
				BufferedReader er = new BufferedReader(new InputStreamReader(fis));
				string= er.readLine();
				if(string==null){
					String masEmp = "All counter has been removed, please create a new one";
					Toast.makeText(MainActivity.this, 
							masEmp, Toast.LENGTH_LONG).show();
				}else{
					Intent newc = new Intent();
					newc.setClass(MainActivity.this,PreBut .class);
					MainActivity.this.startActivity(newc);
				}
			}catch (FileNotFoundException e) {
			// TODO: handle exception
			String masErr = "There is No counter in the File";
			Toast.makeText(MainActivity.this, 
					masErr, Toast.LENGTH_LONG).show();
			e.printStackTrace();
			}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			}
			
		}
	}
	//Once we click this button we jump to the create name activity.
	class MyButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			intent.putExtra("num",clickedd);
			intent.setClass(MainActivity.this, CreateName.class);
			MainActivity.this.startActivity(intent);
			}

		
		
	}

}
