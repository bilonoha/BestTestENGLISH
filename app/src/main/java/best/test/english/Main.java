package best.test.english;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import best.test.english.R;

import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {
//	Button buttonMain;
	public static Integer score=0;
	int theme;

	
	/** Called when the user clicks the Send button */
	public void sendMessage(View view) {
	    Intent intent = new Intent(this, Test.class);
		startActivity(intent);
//	    EditText editText = (EditText) findViewById(R.id.edit_message);
//	    String message = editText.getText().toString();
//	    intent.putExtra(EXTRA_MESSAGE, message);
//	    
//		String q = null;
//		
//		CSVReader f;
//		try {
//			f = new CSVReader(getFilesDir().toString());
//			ArrayList<String[]> data = f.list;
//			q = data.get(3)[0];
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	    TextView text2 = (TextView) findViewById(R.id.textView3); 
//	    text2.setText("");
//	    for (int i=0; i<q.split(";;").length; i++){
//  			text2.append(q.split(";;")[i]+"\n");
//  			
//  		}

	}
	
	public void exit(View view) {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}
		
	//More button section
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
	
    private void assetsToFiles() throws IOException { //unpack files from assets to getFilesDir()
        File dir = getFilesDir();
        AssetManager assetManager = getAssets();
        String[] files = null;
        files = assetManager.list("");
        byte[] buffer = new byte[1024];
        for(String filename : files) {
            File outfile = new File(dir, filename);
            if(outfile.exists()) continue;
            InputStream in;
            try{
                in = assetManager.open(filename, AssetManager.ACCESS_STREAMING);
            }catch (java.io.FileNotFoundException e) {
                continue;
            }
            OutputStream out = new FileOutputStream(outfile);
            int read;
            while ((read = in.read(buffer)) != -1) {
                  out.write(buffer, 0, read);
            }
            in.close();
            out.close();
        }
    }

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//disable screen rotation
//		TextView textView3;
//		textView3 = (TextView) findViewById(R.id.textView3);
//		textView3.setText("This is a quick, free test to ");
		try {

			Bundle extras = getIntent().getExtras();
			Utils.kkk=0;
			theme=extras.getInt("theme");
			if (theme==0) {
			this.setTheme(R.style.DarkTheme);	
			}
			else this.setTheme(R.style.AppTheme);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		try {
			assetsToFiles();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		

//		buttonMain = (Button) findViewById(R.id.button1);
//		buttonMain.setOnClickListener((android.view.View.OnClickListener) this);
		
		//ImageButton button1 = (ImageButton) findViewById(R.id.imageButton1);
	}	
 //       buttonMain.setOnClickListener(new View.OnClickListener() {

        		
        
//        button1.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//          //  	setContentView(R.layout.activity_q1);
//            	// Perform action on click
//                  	TextView text1 = (TextView) findViewById(R.id.textView2); 
//                   	text1.setText("erc4f564");
//             }
//        });	
//
//	}
	
//	Menu options
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.options_menu, menu);
//		return true;
//	}
//	
////    public boolean onCreateOptionsMenu(Menu menu) {
////        MenuInflater inflater = getMenuInflater();
////        inflater.inflate(R.menu.my_options_menu, menu);
////        return true;
////    }
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//        case R.id.mode:
//
//        	    Intent n = new Intent(this , Main.class);
//        	    if (theme==0){
//        	    	n.putExtra("theme",1);
//        	    }
//        	    else 
//        	    	n.putExtra("theme",0);
//        	    startActivity(n);
//        	    
//   //     	Utils.changeToTheme(this, Utils.THEME_BLUE);
//  //      startActivity(new Intent(this, About.class));
//        return true;
//        case R.id.exit:
// //       startActivity(new Intent(this, Help.class));
//            finish();
//      //      System.exit(0);
//        return true;
//        default:
//        return super.onOptionsItemSelected(item);
//        }
//    }
    
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}
	
	//disable screen rotation
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

}
