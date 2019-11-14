package best.test.english;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

import best.test.english.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Choise extends Activity {
	
	Integer set;
	Integer level=0;
	Toast mToast;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choise);
		mToast = Toast.makeText( this  , "" , Toast.LENGTH_LONG );		    
			File f = new File(getFilesDir().toString()+"/l");
			Scanner s;
			try {
				s = new Scanner(f);
				level = s.nextInt();
			} catch (FileNotFoundException e) {
			//	Toast.makeText(this, "Sorry, application file is corrupted. Please reinstall", Toast.LENGTH_LONG).show();
				mToast.setText("Sorry, application file is corrupted. Please reinstall");
				mToast.show();
				e.printStackTrace();
			}

		Button button1,button2,button3,button4,button5,button6,button7,button8,button9;
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
//		button4 = (Button) findViewById(R.id.button4);
//		button5 = (Button) findViewById(R.id.button5);
//		button6 = (Button) findViewById(R.id.button6);
//		button7 = (Button) findViewById(R.id.button7);
//		button8 = (Button) findViewById(R.id.button8);
//		button9 = (Button) findViewById(R.id.button9);
//		button1.setTextColor(getResources().getColor(R.color.green));
//		switch (level){
//			case 1:
//				button1.setTextColor(getResources().getColor(R.color.green));
//				button2.setTextColor(getResources().getColor(R.color.yellow));
//				break;
//			case 2:
//				button1.setTextColor(getResources().getColor(R.color.green));
//				button2.setTextColor(getResources().getColor(R.color.green));
//				button3.setTextColor(getResources().getColor(R.color.yellow));
//				break;
//			case 3:
//				button1.setTextColor(getResources().getColor(R.color.green));
//				button2.setTextColor(getResources().getColor(R.color.green));
//				button3.setTextColor(getResources().getColor(R.color.green));
//				break;
//		}	
		switch (level){
		case 1:
	//		button.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			button1.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_button));
			button2.setBackgroundDrawable(getResources().getDrawable(R.drawable.yellow_button));
			break;
		case 2:
			button1.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_button));
			button2.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_button));
			button3.setBackgroundDrawable(getResources().getDrawable(R.drawable.yellow_button));
			break;
		case 3:
			button1.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_button));
			button2.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_button));
			button3.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_button));
			break;
	}	
	}

//	Menu options	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_choise, menu);
//		return true;
//	}
	
	public void gotoMain(View view) {
	    Intent intent = new Intent(this, Main.class);
//	    EditText editText = (EditText) findViewById(R.id.edit_message);
//	    String message = editText.getText().toString();
//	    intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	}
	public void startTest(View view) {			    
//	    EditText editText = (EditText) findViewById(R.id.edit_message);
//	    String message = editText.getText().toString();
//	    intent.putExtra(EXTRA_MESSAGE, message);
	    Button button;
	    button= (Button) view;
	    set=Integer.parseInt(button.getText().toString())-1;
	    if (level<set) {
//	    	Toast.makeText(this, "You should successfully pass previous test first.", Toast.LENGTH_LONG).show();
	    	mToast.setText("You should successfully pass previous test first.");
			mToast.show();
	    }
	    else {	
		    Intent intent = new Intent(this, Test.class);
		    intent.putExtra("set", set);
		    intent.putExtra("level", level);
		    startActivity(intent);   
	    }
	}	
	public void blockedTest(View view) {
		mToast.setText("Please upgrade to the Full verion in order to open this and more tests.");	
		mToast.show();
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

	public void buy(View view) {
		mToast.setText("Unfortunately, commercial version is not available yet. You'll be notified as soon as it appears on the market.");
		mToast.show();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
	
}
