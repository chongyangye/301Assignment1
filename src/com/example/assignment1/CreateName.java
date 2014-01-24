package com.example.assignment1;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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
	private static final String FILENAME = "file6.sav";
	private EditText title;
	private TextView factorOne;
	private Button buttonSave;
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
	private boolean loadFromFile(String titleStr) {
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			String line = in.readLine();
				
			while (line != null) {
				String[] breakDownMsg = line.split(" | ");
				
				
                if(breakDownMsg[0].toString().equals(titleStr)){
                	return false;
                }
                line = in.readLine();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
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
				boolean cor=loadFromFile(titleStr);
				if(cor ==true){
					Intent intent3 = getIntent();
					int Num = intent3.getIntExtra("num", 0);
					intent3.putExtra("one", titleStr);
					intent3.putExtra("two", Num);
					intent3.setClass(CreateName.this, CreateButton.class);
					CreateName.this.startActivity(intent3);
				}else{
					Toast.makeText(CreateName.this,
	                        "can't be the same!", Toast.LENGTH_SHORT)
	                        .show();
				}
			}
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

}
