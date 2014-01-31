package com.example.assignment1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
//We use this activity to view the history of the statistics of the counter
public class StatView extends Activity {
	private static final String FILENAME = "file6.sav";
	private TextView testViewTitle;
	private ListView listViewDate;
	private ArrayAdapter<String> adapter;
	private int checkNum=0;
	private ArrayList<DataObject> tweetss;
	private ArrayList<String> outPut;
	private ArrayList<Calendar> finishDate;
	private String ttile;
	private String counterName;
	private Gson gson = new Gson();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stat_view);
		Intent inten =getIntent(); 
		ttile=inten.getStringExtra("name");
		checkNum=inten.getIntExtra("Num", checkNum);
		counterName=inten.getStringExtra("nameCounter");
		testViewTitle=(TextView)findViewById(R.id.textViewTitle);
		listViewDate=(ListView)findViewById(R.id.listViewDate);
		testViewTitle.setText(ttile);
		loadFromFile();
		int ccc=0;
		finishDate = new ArrayList<Calendar>();
		for(int i=0;i<tweetss.size();i++){
			if(tweetss.get(i).clikName().equals(counterName)){
				//useLists.add(tweetss.get(i));
				Calendar cal = Calendar.getInstance();
				cal.setTime(tweetss.get(i).date2());
				finishDate.add(cal);
				ccc++;
			}
		}
		//counter by hour
		if(checkNum==1){
			outPut = new ArrayList<String>();
			int countH=0;
			int checc = ccc;
			int checkk =ccc;
			String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Novr", "Dec"};
			String[] hourNames = {"AM","PM"};
			while(checc>0){
				int indexx=0;
				int countNumm=0;
				int mounth=0;
				int day=0;
				int hour=0;
				String AfMo;
				Calendar dd=finishDate.get(countH);
				if (dd.get(Calendar.HOUR_OF_DAY)<12){
					AfMo=hourNames[0];
				}else{
					AfMo=hourNames[1];
				}
				while(indexx<checkk){
					if(finishDate.get(indexx).get(Calendar.YEAR)==dd.get(Calendar.YEAR)){
						if(finishDate.get(indexx).get(Calendar.MONTH) == dd.get(Calendar.MONTH)){
							if(finishDate.get(indexx).get(Calendar.DAY_OF_MONTH) == dd.get(Calendar.DAY_OF_MONTH)){
								if(finishDate.get(indexx).get(Calendar.HOUR_OF_DAY)==dd.get(Calendar.HOUR_OF_DAY)){
									countNumm++;
									mounth = finishDate.get(indexx).get(Calendar.MONTH);
									day = finishDate.get(indexx).get(Calendar.DAY_OF_MONTH);
									hour = finishDate.get(indexx).get(Calendar.HOUR);
									finishDate.remove(finishDate.get(indexx));
									checc--;
									checkk--;
								}else{
									indexx++;
								}
							}else{
								indexx++;
							}
								
						}else{
							indexx++;
						}
							
						
					}else{
						indexx++;
					}
				}
				outPut.add("The"+" "+monthNames[mounth]+" "+day +" "+hour+":00 "+AfMo+ "--"+countNumm);
			}
			
			
			adapter = new ArrayAdapter<String>(this,
					R.layout.list_item, outPut);
			listViewDate.setAdapter(adapter);
		}
		
		//count by day
		if(checkNum==2){
			outPut = new ArrayList<String>();
			int countH=0;
			int checc = ccc;
			int checkk =ccc;
			String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Novr", "Dec"};
			while(checc>0){
				int indexx=0;
				int countNumm=0;
				int mounth=0;
				int day=0;
				Calendar dd=finishDate.get(countH);
				while(indexx<checkk){
					if(finishDate.get(indexx).get(Calendar.YEAR)==dd.get(Calendar.YEAR)){
						if(finishDate.get(indexx).get(Calendar.MONTH) == dd.get(Calendar.MONTH)){
							if(finishDate.get(indexx).get(Calendar.DAY_OF_MONTH) == dd.get(Calendar.DAY_OF_MONTH)){
								
									countNumm++;
									mounth = finishDate.get(indexx).get(Calendar.MONTH);
									day = finishDate.get(indexx).get(Calendar.DAY_OF_MONTH);
									finishDate.remove(finishDate.get(indexx));
									checc--;
									checkk--;
							}else{
								indexx++;
							}
								
						}else{
							indexx++;
						}
							
						
					}else{
						indexx++;
					}
				}
				outPut.add("The"+" "+monthNames[mounth]+" "+day + "--"+countNumm);
			}
			
			
			adapter = new ArrayAdapter<String>(this,
					R.layout.list_item, outPut);
			listViewDate.setAdapter(adapter);
		}
		
		
		
		//count by week
		
		if(checkNum==3){
			outPut = new ArrayList<String>();
			int countH=0;
			int checc = ccc;
			int checkk =ccc;
			String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Novr", "Dec"};
			while(checc>0){
				int indexx=0;
				int countNumm=0;
				int mounth=0;
				int day=0;
				Calendar dd=finishDate.get(countH);
				while(indexx<checkk){
					if(finishDate.get(indexx).get(Calendar.YEAR)==dd.get(Calendar.YEAR)){
						if(finishDate.get(indexx).get(Calendar.WEEK_OF_YEAR) == dd.get(Calendar.WEEK_OF_YEAR)){
								
									countNumm++;
									mounth = finishDate.get(indexx).get(Calendar.MONTH);
									day = finishDate.get(indexx).get(Calendar.DAY_OF_MONTH);
									finishDate.remove(finishDate.get(indexx));
									checc--;
									checkk--;
					
								
						}else{
							indexx++;
						}
							
						
					}else{
						indexx++;
					}
				}
				outPut.add("Week of"+" "+monthNames[mounth]+" "+day + "--"+countNumm);
			}
			
			
			adapter = new ArrayAdapter<String>(this,
					R.layout.list_item, outPut);
			listViewDate.setAdapter(adapter);
		}
		//count by month
		
		if(checkNum==4){
			outPut = new ArrayList<String>();
			int countH=0;
			int checc = ccc;
			int checkk =ccc;
			String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Novr", "Dec"};
			while(checc>0){
				int indexx=0;
				int countNumm=0;
				int mounth=0;
				Calendar dd=finishDate.get(countH);
				while(indexx<checkk){
					if(finishDate.get(indexx).get(Calendar.YEAR)==dd.get(Calendar.YEAR)){
						if(finishDate.get(indexx).get(Calendar.MONTH) == dd.get(Calendar.MONTH)){
								
									countNumm++;
									mounth = finishDate.get(indexx).get(Calendar.MONTH);

									finishDate.remove(finishDate.get(indexx));
									checc--;
									checkk--;
								
						}else{
							indexx++;
						}
							
						
					}else{
						indexx++;
					}
				}
				outPut.add("Month of"+" "+monthNames[mounth]+ "--"+countNumm);
			}
			
			
			adapter = new ArrayAdapter<String>(this,
					R.layout.list_item, outPut);
			listViewDate.setAdapter(adapter);
		}
	}

	
	//each time when we get into this activity, we load file first.	
	
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stat_view, menu);
		return true;
	}

}
