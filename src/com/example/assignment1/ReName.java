package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ReName extends CreateButton {
	private EditText title1;
	private TextView factorOne1;
	private	 Button buttonSave1;
	int clicke =0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rename);
		title1 =(EditText)findViewById(R.id.title1);
		factorOne1 = (TextView)findViewById(R.id.factorOne1);
		buttonSave1 = (Button)findViewById(R.id.buttonSave1);
		//receive original name of the counter and data
		Intent intent6 = getIntent();
		clicke =intent6.getIntExtra("four", clicked);
		//original name stored in titleStr1
		String titleStr2 = intent6.getStringExtra("three");
		factorOne1.setText("Rename");
		buttonSave1.setText("Save");
		buttonSave1.setOnClickListener(new buttonSave1Listener());
	}
	class buttonSave1Listener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String titleStr1 = title1.getText().toString();
			Intent intent4 = new Intent();
			intent4.putExtra("one", titleStr1);
			intent4.putExtra("two", clicke);
			intent4.setClass(ReName.this, CreateButton.class);
			ReName.this.startActivity(intent4);
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

}
