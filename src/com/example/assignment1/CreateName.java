package com.example.assignment1;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.google.gson.Gson;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
//create a new counter and if the counter is exists, can not do it
public class CreateName extends MainActivity {
	private static final String FILENAME = "file6.sav";
	private EditText title;
	private TextView factorOne;
	private Button buttonSave;
	private ArrayList<DataObject> tweetss;
	private Gson gson = new Gson();
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
	//load file and save it to tweetss arrayList
	private void loadFromFile() {
		tweetss = new ArrayList<DataObject>();
		try {
			FileInputStream fis = openFileInput(FILENAME);
			 
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			String line = in.readLine();
			
			while (line != null) {
				System.out.println(line);
				DataObject json=gson.fromJson(line, DataObject.class);
				tweetss.add(json);
		
				line = in.readLine();
			}
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return tweets.toArray(new String[tweets.size()]);
	}
	//once we click save, check the duplicate and jump to next activity
	class buttonSaveListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String titleStr = title.getText().toString();
			loadFromFile();
			int checkFile=0;
			for(int i=0;i<tweetss.size();i++){
				if(tweetss.get(i).clikName().equals(titleStr)){
					checkFile=1;
				}
			}
			if(checkFile==1){
				Toast.makeText(CreateName.this,
                        "can't be the same!", Toast.LENGTH_SHORT)
                        .show();
			}
			else if (titleStr.equals("")){
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
