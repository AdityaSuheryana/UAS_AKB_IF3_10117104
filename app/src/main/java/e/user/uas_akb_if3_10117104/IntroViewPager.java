package e.user.uas_akb_if3_10117104;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import e.user.uas_akb_if3_10117104.database.DatabaseHelper;

import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;

public class IntroViewPager extends AppCompatActivity {
    ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    Button btnNext;
    int position = 0;
    Button btnGetStarted;
    Animation btnAnim;
    DatabaseHelper db = new DatabaseHelper(this);

    /*
        tgl pengerjaan  : 11-Mei-2020
        Nim             : 10117104
        Nama            : Aditya Suheryana
        Kelas           : IF-3 / AKB-3
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setup tab layout
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro_view_pager);

        //BATASSS
        //When This Activity is about to Launch we need to Check if its open before or not
        if (restorePrefData()){
            Intent mainActivity = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(mainActivity);
            finish();
        }
        //BATASSS
        //Hide the Action Bar



        //Ini View
        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_get_started);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);

        //list
        final List<ScreenItem> mList = new ArrayList<>();

        mList.add(new ScreenItem("Wisata Apps", "Aplikasi ini bisa digunakan untuk mencatat tempat wisata yang ada di bandung", R.drawable.logo_fix));
        mList.add(new ScreenItem("Wisata Apps", "Aplikasi ini bisa digunakan untuk mencatat tempat wisata yang ada di bandung", R.drawable.logo_fix));


        //setup
        screenPager = findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);

        //setup tablayout with viewpager
        tabIndicator.setupWithViewPager(screenPager);

        //Next Button Klik Listener
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = screenPager.getCurrentItem();
                if (position < mList.size()){
                    position++;
                    screenPager.setCurrentItem(position);
                }
                //When We Reach The Last Screen
                if (position == mList.size()-1){
                    //TODO : Show the GETSTARTED Button and Hide the Indicator and the Next Button
                    loadLastScreen();
                }
            }
        });
        //Tablayout Add Change Listener
        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size()-1){
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //GETSTARTED Button Click Listener
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Open The Main Activity
                save();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

                //Also we need the save a boolean value to storage so next time when the user run
                //the app we could know that he is already checked the intro screen activity
                //so use the shared preference to that proses
                //BATASSS
                savePrefsData();
                //BATASSS
                finish();
            }
        });
    }
    //BATASS
    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroActivityOpenedBefore = pref.getBoolean("isIntroOpened",false);
        return isIntroActivityOpenedBefore;
    }

    private void savePrefsData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpened",true);
        editor.commit();
    }
    //BATASSS
    //Show the GETSTARTED Button and Hide the Indicator and the Next Button
    private void loadLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.VISIBLE);

        //TODO : ADD An Animnation to the GETSTARTED Button
        //Setup Animation
        btnGetStarted.setAnimation(btnAnim);

    }
    private void save() {
        db.insert("Hutan","20000","-6.873109" ," 107.619465","curug","main kuy");
        Toast.makeText(getApplicationContext(), "Data Berhasil di simpan", Toast.LENGTH_SHORT).show();
    }


}
