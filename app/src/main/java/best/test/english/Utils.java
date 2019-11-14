package best.test.english;

import best.test.english.R;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

public class Utils
{
     private static int sTheme;
     public static int kkk;
     public final static int THEME_DARK = 0;
     public final static int THEME_WHITE = 1;
     public final static int THEME_BLUE = 2;

     /**
      * Set the theme of the Activity, and restart it by creating a new Activity of the same type.
      */
     public static void changeToTheme(Activity activity, int theme)
     {
//          sTheme = theme;
//          activity.finish();
          
          activity.setTheme(R.style.DarkTheme);
activity.startActivity(new Intent(activity, activity.getClass()));

     }

     /** Set the theme of the activity, according to the configuration. */
     public static void onActivityCreateSetTheme(Activity activity)
     {
          switch (sTheme)
          {
          default:
          case THEME_DARK:
              activity.setTheme(R.style.DarkTheme);
              break;
          case THEME_WHITE:
              activity.setTheme(R.style.SecondTheme);
              break;
          case THEME_BLUE:
              activity.setTheme(R.style.Thirdheme);
      		Toast.makeText(activity, "Please upgrade to the Full verion in order to open this and more tests", Toast.LENGTH_LONG).show();
              break;
          }
     }
}