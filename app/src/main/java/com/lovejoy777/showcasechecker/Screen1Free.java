package com.lovejoy777.showcasechecker;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.lovejoy777.showcasechecker.adapters.CardViewAdapter;
import com.lovejoy777.showcasechecker.adapters.RecyclerItemClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by lovejoy777 on 24/06/15.
 */
public class Screen1Free extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    ArrayList<Themes> themesList;
    private CardViewAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefresh = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen1);


        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
        setSupportActionBar(toolbar);
        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        themesList = new ArrayList<Themes>();

       doasync();

        mRecyclerView = (RecyclerView)findViewById(R.id.cardList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new CardViewAdapter(themesList, R.layout.adapter_card_layout, this);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(Screen1Free.this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        String free = themesList.get(position).getfree();
                        String title = themesList.get(position).gettitle();
                        String link = themesList.get(position).getlink();
                        String googleplus = themesList.get(position).getgoogleplus();
                        String promo = themesList.get(position).getpromo();
                        String developer = themesList.get(position).getauthor();
                        String screenshot_1 = themesList.get(position).getscreenshot_1();
                        String screenshot_2 = themesList.get(position).getscreenshot_2();
                        String screenshot_3 = themesList.get(position).getscreenshot_3();
                        String description = themesList.get(position).getdescription();

                        Intent Detailsactivity = new Intent(Screen1Free.this, Details.class);

                        Detailsactivity.putExtra("free", free);
                        Detailsactivity.putExtra("keytitle", title);
                        Detailsactivity.putExtra("keylink", link);
                        Detailsactivity.putExtra("keygoogleplus", googleplus);
                        Detailsactivity.putExtra("keypromo", promo);
                        Detailsactivity.putExtra("keyscreenshot_1", screenshot_1);
                        Detailsactivity.putExtra("keyscreenshot_2", screenshot_2);
                        Detailsactivity.putExtra("keyscreenshot_3", screenshot_3);
                        Detailsactivity.putExtra("keydescription", description);
                        Detailsactivity.putExtra("keydeveloper", developer);

                        Bundle bndlanimation =
                                ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.anni1, R.anim.anni2).toBundle();
                        startActivity(Detailsactivity, bndlanimation);
                    }
                })
        );


    }


    public void doasync() {

        try {
            String sampleJson = Environment.getExternalStorageDirectory() + "/sample.json";
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
            String etjson = sp.getString("etjson", sampleJson);

            Toast.makeText(getApplicationContext(), etjson, Toast.LENGTH_LONG).show();


            File testjson = new File(etjson);
            FileInputStream stream = new FileInputStream(testjson);
            String jString = null;
            try {
                FileChannel fc = stream.getChannel();
                MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
                /* Instead of using default, pass in a decoder. */
                jString = Charset.defaultCharset().decode(bb).toString();
            }
            finally {
                stream.close();
            }

            JSONObject object = new JSONObject(jString);

                Themes theme = new Themes();

                theme.settitle(object.getString("title"));
                theme.setdescription(object.getString("description"));
                theme.setauthor(object.getString("author"));
                theme.setlink(object.getString("link"));
                theme.seticon(object.getString("icon"));
                theme.setpromo(object.getString("promo"));
                theme.setscreenshot_1(object.getString("screenshot_1"));
                theme.setscreenshot_2(object.getString("screenshot_2"));
                theme.setscreenshot_3(object.getString("screenshot_3"));
                theme.setgoogleplus(object.getString("googleplus"));
                theme.setversion(object.getString("version"));
                theme.setdonate_link(object.getString("donate_link"));
                theme.setdonate_version(object.getString("donate_version"));
                theme.setbootani(object.getString("bootani"));
                theme.setfont(object.getString("font"));
                theme.setwallpaper(object.getString("wallpaper"));
                theme.setplugin_version(object.getString("plugin_version"));
                theme.setfor_L(object.getString("for_L"));
                theme.setfor_M(object.getString("for_M"));
                theme.setbasic(object.getString("basic"));
                theme.setbasic_m(object.getString("basic_m"));
                theme.settype2(object.getString("type2"));
                theme.settype3(object.getString("type3"));
                theme.settype3_m(object.getString("type3_m"));
                theme.settouchwiz(object.getString("touchwiz"));
                theme.setlg(object.getString("lg"));
                theme.setsense(object.getString("sense"));
                theme.setxperia(object.getString("xperia"));
                theme.sethdpi(object.getString("hdpi"));
                theme.setmdpi(object.getString("mdpi"));
                theme.setxhdpi(object.getString("xhdpi"));
                theme.setxxhdpi(object.getString("xxhdpi"));
                theme.setxxxhdpi(object.getString("xxhdpi"));
                theme.setfree(object.getString("free"));
                theme.setdonate(object.getString("donate"));
                theme.setpaid(object.getString("paid"));

                // if (theme.getfree().contains("true")) {
                themesList.add(theme);
                // }


            //------------------>>

        } catch (ParseException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}