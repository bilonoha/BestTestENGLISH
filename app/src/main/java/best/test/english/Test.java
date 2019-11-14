package best.test.english;

import java.io.*;
import java.util.ArrayList;

import best.test.english.R;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Test extends Activity implements Data{
//	CountDownTimer tmr = wait();
	private CountDownTimer countDownTimer,countDownTimer1;
	private AdView mAdView;
	long min, sec;
	RadioGroup radioGroup;
//	ImageView image;
	Toast mToast;
	TextView textView1,
			 timer,
			 counter;
	Button nextButton, finishButton;
	RadioButton radioButton1,
				radioButton2,
				radioButton3,
//				radioButton4,
				radioButton;
	String 	question="default",
			answer1="default",
			answer2,
			answer3,
//			answer4,
			img;	
	int set=0, //number of set of questions
		num=0, // number of question
		score=0; //number of correct answers
	Integer	level=0; //level opened by the user
	int selectedId;
	
	CSVReader f;	
	ArrayList<String[]> data;
	
	public enum Options {a,b,c};
	Options correct;
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);

		MobileAds.initialize(this, new OnInitializationCompleteListener() {
			@Override
			public void onInitializationComplete(InitializationStatus initializationStatus) {
			}
		});
		mAdView = findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		mAdView.loadAd(adRequest);

		mToast = Toast.makeText( this  , "" , Toast.LENGTH_LONG );	
	
		   
//		
//		Bundle extras = getIntent().getExtras();
//		set = extras.getInt("set");
//		level = extras.getInt("level");
		
		num=set*finish;
		
		counter = (TextView) findViewById(R.id.counter);
//		image = (ImageView) findViewById(R.id.image);
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
		textView1 = (TextView) findViewById(R.id.textView1);
		nextButton = (Button) findViewById(R.id.nextButton);
		finishButton = (Button) findViewById(R.id.finishButton);
		radioButton1 = (RadioButton) findViewById(R.id.radio01);
		radioButton2 = (RadioButton) findViewById(R.id.radio02);
		radioButton3 = (RadioButton) findViewById(R.id.radio03);
//		radioButton4 = (RadioButton) findViewById(R.id.radio04);
		
		selectedId = radioGroup.getCheckedRadioButtonId();
	    radioButton = (RadioButton) findViewById(selectedId);	
		
	    radioGroup.clearCheck();

		
		counter.setText(""+((num+1)-finish*set)+'/'+finish);
		
		// countdown timer
		timer = (TextView) findViewById(R.id.timer);	
		countDownTimer = new CountDownTimer(time*60*1000, 1000) {
		//long min, sec;	
		     public void onTick(long millisUntilFinished) {
		    	 min = millisUntilFinished/1000/60;
		    	 sec = millisUntilFinished/1000 - min*60;
		    	 if (sec>9)
		    		 timer.setText("" + min + ':' + sec );
		    	 else 
		    		 timer.setText("" + min + ":0" + sec );
		     }

		     public void onFinish() {
		    	// timer.setText("done!");
		    	 radioButton1.setChecked(true);
		    	 clickFinish(null);
		     }
		  }.start();
		
		try {
			f = new CSVReader(getFilesDir().toString());
			data = f.list;

			question = data.get(num)[0];
			correct = Options.valueOf(data.get(num)[1]);
			answer1 = data.get(num)[2];
			answer2 = data.get(num)[3];
			answer3 = data.get(num)[4];
//			answer4 = data.get(num)[5];
//			img = data.get(num)[6];
		} catch (FileNotFoundException e) {
			question = "Error occured while reading file with tests";
			e.printStackTrace();
		} 
		

		
		// Set image
//		if(Integer.valueOf(img)>0) {			
//		Bitmap bitmap = BitmapFactory.decodeFile(getFilesDir().toString()+"/"+img+".gif");
//		image.setImageBitmap(bitmap);
//		}
//		else image.setImageResource(R.drawable.empty);
		
		// Set question and answers		
	//	textView1.setText(question);
		textView1.setText("");
		for (int i=0; i<question.split(";;").length; i++){
  			textView1.append(question.split(";;")[i]+"\n");  			
  		}
		radioButton1.setText(answer1);
		radioButton2.setText(answer2);
		radioButton3.setText(answer3);
//		radioButton4.setText(answer4);		
	}
	
	
	public void clickNext(View view)  {
//		Intent result = new Intent(this, Q1.class);
//		Intent intent2 = new Intent(this, Q1.class);
//	    EditText editText = (EditText) findViewById(R.id.edit_message);
//	    String message = editText.getText().toString();
//	    intent.putExtra(EXTRA_MESSAGE, message);
		
//		radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);	
//
//		if (radioGroup.getCheckedRadioButtonId () == R.id.radio01) {
//			Main.score+=1;
//			Toast.makeText(this, "Yes Toast"+Main.score.toString(), Toast.LENGTH_LONG).show();
//		}
//		startActivity(intent);
		
		// get selected radio button from radioGroup

		
	    if ((num-finish*set)<finish-1){		
		    radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
			selectedId = radioGroup.getCheckedRadioButtonId();
			// find the radio button by returned id
		    radioButton = (RadioButton) findViewById(selectedId);		
		    // check if user selected answer
		    if (radioGroup.getCheckedRadioButtonId()==-1){
		    	mToast.setText("Please select your answer");
		    	mToast.show();
		    	num--;
		    }
		    // check if answer is correct
		    else if (radioButton.getText().toString().substring(0, 1).toLowerCase().matches(correct.toString())){
		    	score++;
//		    	mToast.setText("Yes, that's correct");
//		    	mToast.show();
		    }
//		    else {
//		    	String message="";
//		    	switch (correct){
//			    	case a:
//			    		message="Sorry. Correct answer is: " + radioButton1.getText();
//			    		break;
//			    	case b:
//			    		message="Sorry. Correct answer is: " + radioButton2.getText();
//			    		break;	
//			    	case c:
//			    		message="Sorry. Correct answer is: " + radioButton3.getText();
//			    		break;
//			    	case d:
//			    		message="Sorry. Correct answer is: " + radioButton4.getText();
//			    		break;
//		    		}
//		    	mToast.setText(message);
//		    	mToast.show();
//		    }	
	    	
		    num++;	    
		    
		    counter.setText(""+((num+1)-finish*set)+'/'+finish);
		    
		    radioGroup.clearCheck();
	    	
    		question = data.get(num)[0];
			correct = Options.valueOf(data.get(num)[1]);
			answer1 = data.get(num)[2];
			answer2 = data.get(num)[3];
			answer3 = data.get(num)[4];
//			answer4 = data.get(num)[5];
//			img = data.get(num)[6];
			
			// Set image
//			if(Integer.valueOf(img)>0) {			
//			Bitmap bitmap = BitmapFactory.decodeFile(getFilesDir().toString()+"/"+img+".jpg");
//			image.setImageBitmap(bitmap);
//			}
//			else image.setImageResource(R.drawable.empty);
			
			// Set question and answers
			//textView1.setText(question);
			textView1.setText("");
			for (int i=0; i<question.split(";;").length; i++){
	  			textView1.append(question.split(";;")[i]+"\n");  			
	  		}
			radioButton1.setText(answer1);
			radioButton2.setText(answer2);
			radioButton3.setText(answer3);
//			radioButton4.setText(answer4);
			if((num-finish*set)==finish-1)  {
				nextButton.setVisibility(View.GONE);
			}
		}
	    else {
	    	clickFinish(view);
//	    	Toast.makeText(this, "Congratulations! Your Final Score is " + score, Toast.LENGTH_LONG).show();
	    }

		

	        
	}	
	
	public void clickFinish(View view)   {
	    Intent intent = new Intent(this, Results.class);
//	    EditText editText = (EditText) findViewById(R.id.edit_message);
//	    String message = editText.getText().toString();
//	    intent.putExtra(EXTRA_MESSAGE, message);
	    radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
		selectedId = radioGroup.getCheckedRadioButtonId();
		// find the radio button by returned id
	    radioButton = (RadioButton) findViewById(selectedId);
	    if (radioGroup.getCheckedRadioButtonId()==-1){
	    	mToast.setText("Please select your answer");
	    	mToast.show();
	    	//	    	num--;
	    }
	    else {	
	    		countDownTimer.cancel();
		    	if (radioButton.getText().toString().substring(0, 1).toLowerCase().matches(correct.toString())){    
		    	score++;
		    	mToast.setText("Yes, that's correct");
		    	mToast.show();
		    	}
		    
//		    	score = score*100/finish;
//		    	if ((score>=required)&&(set==level)){
//			    	level++;    	
//			    	Writer wr;
//							try {
//								wr = new FileWriter(getFilesDir().toString()+"/l");
//								wr.write(level.toString());
//								wr.close();
//							} catch (IOException e) {
//								Toast.makeText(this, "Sorry, application file is corrupted. Please reinstall", Toast.LENGTH_LONG).show();
//								e.printStackTrace();
//							}
//				    	
//			    	}

		    intent.putExtra("score", score);
//		    intent.putExtra("set", set);
		    startActivity(intent);
//		
//
		}	
	    //switch to the next question

	    
	    
	}
	
//	Options menu
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_test, menu);
//		return true;
//	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
	
//	@Override
//	public void onPause(){
//		countDownTimer1.cancel();
//	}
	
//	@Override
//	public void onResume(){
//		countDownTimer = new CountDownTimer(min*60*1000+sec*1000, 1000) {
//			//long min, sec;	
//			     public void onTick(long millisUntilFinished) {
//			    	 min = millisUntilFinished/1000/60;
//			    	 sec = millisUntilFinished/1000 - min*60;
//			    	 if (sec>9)
//			    		 timer.setText("" + min + ':' + sec );
//			    	 else 
//			    		 timer.setText("" + min + ":0" + sec );
//			     }
//
//			     public void onFinish() {
//			    	// timer.setText("done!");
//			    	 clickFinish(null);
//			     }
//			  }.start();
//	}

}
