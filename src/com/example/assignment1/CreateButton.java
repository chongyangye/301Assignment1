package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CreateButton extends MainActivity{
	int clicked=0;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private TextView titleView1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create);
		titleView1=(TextView)findViewById(R.id.titleView);
		Intent intent2 = getIntent();
		clicked =intent2.getIntExtra("two", clicked);
		String titleStr = intent2.getStringExtra("one");
		titleView1.setText(titleStr);
		
		final TextView text = (TextView)findViewById(R.id.textView1);
		text.setText("\n Not been clicked");
		
		button1 =(Button)findViewById(R.id.button1);
		button1.setText("add");
		button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				clicked++;
				text.setText("\n"+clicked);
			}
		});
		button2 =(Button)findViewById(R.id.button2);
		button2.setText("minus");
		button2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				clicked--;
				if(clicked<0){
					clicked=0;
				}
				text.setText("\n"+clicked);
			}
		});
		button3 =(Button)findViewById(R.id.button3);
		button3.setText("reset");
		button3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				clicked=0;
				text.setText("\n"+clicked);
			}
		});
		button4 =(Button)findViewById(R.id.button4);
		button4.setText(R.string.rename);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}
	
}
