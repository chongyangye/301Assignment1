package com.example.assignment1;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateName extends MainActivity {
	private EditText title;
	private TextView factorOne;
	private	 Button buttonSave;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createname);
		title =(EditText)findViewById(R.id.title);
		factorOne = (TextView)findViewById(R.id.factorOne);
		buttonSave = (Button)findViewById(R.id.buttonSave);
		
		
		factorOne.setText("Create Name");
		buttonSave.setText("Save");
		buttonSave.setOnClickListener(new buttonSaveListener());
		
	}
	class buttonSaveListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String titleStr = title.getText().toString();
			if (titleStr.equals("")){
				Toast.makeText(CreateName.this,
                        "can't be empty!", Toast.LENGTH_SHORT)
                        .show();
			}else{
				Intent intent3 = getIntent();
				int Num = intent3.getIntExtra("num", 0);
				intent3.putExtra("one", titleStr);
				intent3.putExtra("two", Num);
				intent3.setClass(CreateName.this, CreateButton.class);
				CreateName.this.startActivity(intent3);
			}
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

}
