package com.app.shresta.shrestaapp.activity.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.app.shresta.shrestaapp.R;

/**
 * Created by navneet on 12/11/16.
 */

public class SplashScreenActivity extends Activity {
    protected boolean _active = true;
    protected int _splashTime = 4000;
    // Font path
    String Ubuntubold = "font/Ubuntubold.ttf";
    String UbuntuR = "font/UbuntuR.ttf";
    String UbuntuC = "font/UbuntuC.ttf";
    String Electrofied = "font/Electrofied.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //code that displays the content in full screen mode
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splashscreenmaker);

       /* TextView CompanyName= (TextView)findViewById(R.id.companyname);
        // Loading Font Face
        Typeface Ubuntubold1 = Typeface.createFromAsset(getAssets(), Ubuntubold);
        Typeface UbuntuC1 = Typeface.createFromAsset(getAssets(), UbuntuC);
        Typeface UbuntuR1 = Typeface.createFromAsset(getAssets(), UbuntuR);
        Typeface Electrofied1 = Typeface.createFromAsset(getAssets(), Electrofied);

        // Applying font
        CompanyName.setTypeface(Electrofied1);*/


        checkInternetConnection();

        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        final Animation animation_1 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);
        final Animation animation_2 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.antirotate);
        final Animation animation_3 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.abc_fade_out);

        imageView.startAnimation(animation_2);
        animation_2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(animation_1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation_1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(animation_3);
                finish();
                Intent i = new Intent(getBaseContext(),TermsAndConditions.class);
                startActivity(i);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            _active = false;
        }
        return true;
    }

    public void checkInternetConnection() {
		/*cd = new ConnectionDetector(getApplicationContext());
		isInternetPresent = cd.isConnectingToInternet();

		// check for Internet status
		if (isInternetPresent) {*/

        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (_active && (waited < _splashTime)) {
                        sleep(100);
                        if (_active) {
                            waited += 100;
                        }
                    }
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    finish();
                    startActivity(new Intent(SplashScreenActivity.this,
                            TermsAndConditions.class));

                }
            }
        };
        splashTread.start();
		/*} else {
			// Internet connection is not present
			// Ask user to connect to Internet
			showAlertDialog(SplashScreen.this, "No Internet Connection",
					"You don't have internet connection.", false);
		}*/
    }

}
