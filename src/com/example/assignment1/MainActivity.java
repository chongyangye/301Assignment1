package com.example.assignment1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
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
	class PreButtonListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent inte =new Intent();
			inte.setClass(MainActivity.this, PreBut.class);
			MainActivity.this.startActivity(inte);
		}
	}
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
