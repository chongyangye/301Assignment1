package com.example.assignment1;

import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
//in this activity, user have to decided the method of statistic:hour,day,week,month
/**
 * @uml.dependency   supplier="com.example.assignment1.MainActivity"
 */
public class StatButton extends MainActivity {
	private Button buttonH;
	private Button buttonD;
	private Button buttonW;
	private Button buttonM;
	private TextView textViewCount;
	private String Name1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stat_button);
		Intent iii =getIntent();
		Name1=iii.getStringExtra("name1");
		textViewCount=(TextView)findViewById(R.id.textViewCount);
		textViewCount.setText("Counter Statistic");
		buttonH=(Button)findViewById(R.id.buttonH);
		buttonH.setText("Count by Hour");
		buttonH.setOnClickListener(new buttonHListener());
		
		buttonD=(Button)findViewById(R.id.buttonD);
		buttonD.setText("Count by Day");
		buttonD.setOnClickListener(new buttonDListener());
		
		buttonW=(Button)findViewById(R.id.buttonW);
		buttonW.setText("Count by Week");
		buttonW.setOnClickListener(new buttonWListener());
		
		buttonM=(Button)findViewById(R.id.buttonM);
		buttonM.setText("Count by Month");
		buttonM.setOnClickListener(new buttonMListener());
		
		
	}
	//month
	class buttonMListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intt3 =getIntent();
			intt3.putExtra("name", "Month");
			intt3.putExtra("Num", 4);
			intt3.putExtra("nameCounter", Name1);
			intt3.setClass(StatButton.this, StatView.class);
			StatButton.this.startActivity(intt3);
		}
	}
	//week
	class buttonWListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intt2 =getIntent();
			intt2.putExtra("name", "Week");
			intt2.putExtra("Num", 3);
			intt2.putExtra("nameCounter", Name1);
			intt2.setClass(StatButton.this, StatView.class);
			StatButton.this.startActivity(intt2);
		}
	}
	//day
	class buttonDListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intt1 =getIntent();
			intt1.putExtra("name", "Day");
			intt1.putExtra("Num", 2);
			intt1.putExtra("nameCounter", Name1);
			intt1.setClass(StatButton.this, StatView.class);
			StatButton.this.startActivity(intt1);
		}
	}
	//hour
	class buttonHListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intt =getIntent();
			intt.putExtra("name", "Hour");
			intt.putExtra("Num", 1);
			intt.putExtra("nameCounter", Name1);
			intt.setClass(StatButton.this, StatView.class);
			StatButton.this.startActivity(intt);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stat_button, menu);
		return true;
	}

}
