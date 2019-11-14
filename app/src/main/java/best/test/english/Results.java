package best.test.english;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import best.test.english.R;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Results extends Activity implements Data{
//	ImageView image;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);
		
//		image = (ImageView) findViewById(R.id.image);
		TextView textView1;
		textView1 = (TextView) findViewById(R.id.textView1);
		TextView textView2;
		textView2 = (TextView) findViewById(R.id.textView2);
		TextView textView3;
		textView3 = (TextView) findViewById(R.id.textView3);
		
		Typeface type = Typeface.createFromAsset(getAssets(),"Showcard Gothic.ttf"); 
		
//		ImageView imageView1;
//		imageView1 = (ImageView) findViewById(R.id.imageView1);
//		imageView1.setImageResource(R.drawable.cert);
		//get info about score from Quiz activity
		Bundle extras = getIntent().getExtras();
		int score = extras.getInt("score");
		int set = extras.getInt("set");
		textView1.setText("Your level of English is");
		
		if (score<7){
			
			textView2.setText("Beginner");
			textView2.setTypeface(type);
			textView2.setTextColor(getResources().getColor(R.color.red));			
			textView3.setText("");
			
		}
		else if (score<15) {
			
			textView2.setText("Elementary");
			textView2.setTypeface(type);
			textView2.setTextColor(getResources().getColor(R.color.orange));			
			textView3.setText("");
		}
		else if (score<29) {
			
			textView2.setText("Pre-Intermediate");
			textView2.setTypeface(type);
			textView2.setTextColor(getResources().getColor(R.color.yellow));			
			textView3.setText("");
		}
		else if (score<46) {
			
			textView2.setText("Intermediate");
			textView2.setTypeface(type);
			textView2.setTextColor(getResources().getColor(R.color.green));			
			textView3.setText("");
		}
		else if (score<60) {
			
			textView2.setText("Upper-Intermediate");
			textView2.setTypeface(type);
			textView2.setTextColor(getResources().getColor(R.color.blue));			
			textView3.setText("");
		}
		else  {
			
			textView2.setText("Advanced");
			textView2.setTypeface(type);
			textView2.setTextColor(getResources().getColor(R.color.violet));			
			textView3.setText("");
		}

	}

	public void sendMessage(View view) {
		    Intent intent = new Intent(this, Main.class);
//		    EditText editText = (EditText) findViewById(R.id.edit_message);
//		    String message = editText.getText().toString();
//		    intent.putExtra(EXTRA_MESSAGE, message);
		    startActivity(intent);
	}	    
//		ImageButton button1 = (ImageButton) findViewById(R.id.imageButton1);
//        button1.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//            	setContentView(R.layout.activity_q2);
//            	// Perform action on click
//             }
//        });	
//        
//        ImageButton button2 = (ImageButton) findViewById(R.id.imageButton2);
//        button2.setOnClickListener(new View.OnClickListener() {
//        	@Override
//        	public void onClick(View v) {
//            	//setContentView(R.layout.activity_results);
//            	// Perform action on click
//            	TextView text1 = (TextView) findViewById(R.id.textView2); 
//            	text1.setText("124");
//             }
//        });	
//		

//	Options menu
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_results, menu);
//		return true;
//	}
	
	//Rate button section
	private boolean MyStartActivity(Intent aIntent) {
	    try
	    {
	        startActivity(aIntent);
	        return true;
	    }
	    catch (ActivityNotFoundException e)
	    {
	        return false;
	    }
	}
	 
	//On click event for Rate this app button
	public void rate(View v) {
	    Intent intent = new Intent(Intent.ACTION_VIEW);
	    //Try Google play
	    intent.setData(Uri.parse("market://details?id=best.test.english"));
	    if (MyStartActivity(intent) == false) {
	        //Market (Google play) app seems not installed, let's try to open a webbrowser
	        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=best.test.english"));
	        if (MyStartActivity(intent) == false) {
	            //Well if this also fails, we have run out of options, inform the user.
	            Toast.makeText(this, "Could not open Android market, please install the market app.", Toast.LENGTH_SHORT).show();
	        }
	    }
	}
	
	//On click event for More button
	public void more(View v) {
	    Intent intent = new Intent(Intent.ACTION_VIEW);
	    //Try Google play
	    intent.setData(Uri.parse("market://search?q=pub:Michael Bilonoha"));
	    if (MyStartActivity(intent) == false) {
	        //Market (Google play) app seems not installed, let's try to open a webbrowser
	        intent.setData(Uri.parse("https://play.google.com/store/search?q=pub:Michael Bilonoha"));
	        if (MyStartActivity(intent) == false) {
	            //Well if this also fails, we have run out of options, inform the user.
	            Toast.makeText(this, "Could not open Android market, please install the market app.", Toast.LENGTH_SHORT).show();
	        }
	    }
	}
	
	//Retry Again button
	public void retry(View view) {
	    Intent intent = new Intent(this, Test.class);
		startActivity(intent);
	}
	
	//Exit button
	public void exit(View view) {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}
	
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

}
