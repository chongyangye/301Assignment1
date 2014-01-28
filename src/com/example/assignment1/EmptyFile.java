package com.example.assignment1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class EmptyFile extends Activity {
	private TextView testEmpty;
	private Button ButEmpty;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_empty_file);
		testEmpty=(TextView)findViewById(R.id.textViewEmpty);
		ButEmpty=(Button)findViewById(R.id.buttonEmpty);
		testEmpty.setText("File is Empty, you have to create counter first!");
		ButEmpty.setText("Back to Main Meau");
		ButEmpty.setOnClickListener(new ButEmptyListener());
	}
	class ButEmptyListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			intent.setClass(EmptyFile.this, MainActivity.class);
			EmptyFile.this.startActivity(intent);
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.empty_file, menu);
		return true;
	}

}
