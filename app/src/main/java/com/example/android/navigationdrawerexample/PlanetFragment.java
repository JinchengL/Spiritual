package com.example.android.navigationdrawerexample;

import android.annotation.TargetApi;
import android.app.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;

import android.renderscript.ScriptGroup;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.net.HttpURLConnection;

import org.json.*;

/**
 * Fragment that appears in the "content_frame", shows a planet
 */

public class PlanetFragment extends Fragment {
    private View web;
    private ArrayList<String> links;
    String myUrl;
    public static final String FRAGMENT_NUMBER = "fragment_number";


    public PlanetFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView;
        WebView myWebView;


        int i = getArguments().getInt(FRAGMENT_NUMBER);
        //ArrayList<String> religionsAvalaible = getReligions();
        //Log.d("religions:%s", religionsAvalaible.toString());

        //calling different layout to replace content part

        if(i == 1){ //about us
            rootView = inflater.inflate(R.layout.about_us,container,false);
            TextView about = (TextView) rootView.findViewById(R.id.textView10);


            /*AssetManager assetman=myContext.getAssets();
            try {
                String[] files = assetman.list("Files");
                for(int k = 0;k < files.length;k++){
                    about.append("\n Files=>"+i+"Name"+files);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            InputStream input;
            try{
                input=assetman.open("abouttext.docx");
                int size = input.available();
                byte[] buffer = new byte[size];
                input.read(buffer);
                input.close();
                String text = new String(buffer);
                about.setText(Html.fromHtml(text));
            }catch (Exception e){
                e.printStackTrace();
            }
*/
         }
        else if(i == 2) { //prayer
            rootView = inflater.inflate(R.layout.activity_open_web_view,container,false);

            myWebView = (WebView) rootView.findViewById(R.id.webview);
            myWebView.loadUrl("file:///android_asset/prayer.html");

            // Enable Javascript
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);

            // Force links and redirects to open in the WebView instead of in a browser
            myWebView.setWebViewClient(new WebViewClient());

        }
        else if(i == 3){ //inspiration
            rootView = inflater.inflate(R.layout.video,container,false);

        }
        else if(i == 4) { //sacred texts
            rootView = inflater.inflate(R.layout.activity_resource,container,false);

            //image button 1
            ImageButton button1 = (ImageButton)rootView.findViewById(R.id.imageButton1);

            button1.setOnClickListener(new ClickWebViewListener(getActivity(),"http://www.buddhanet.net/e-learning/sutras.htm"));

            //image button2
            ImageButton button2 = (ImageButton)rootView.findViewById(R.id.imageButton2);
            button2.setOnClickListener(new ClickWebViewListener(getActivity(),"http://www.breslov.com/bible/"));

            //image button3
            ImageButton button3 = (ImageButton)rootView.findViewById(R.id.imageButton3);
            button3.setOnClickListener(new ClickWebViewListener(getActivity(),"http://al-quran.info/#home"));

            //image button4
            ImageButton button4 = (ImageButton)rootView.findViewById(R.id.imageButton4);
            button4.setOnClickListener(new ClickWebViewListener(getActivity(),"https://www.biblegateway.com/"));

        }
        else if(i ==5){ //interfaith calendar
            rootView = inflater.inflate(R.layout.interfaith_calendar,container,false);
            //we do not need the layout to bw activity_open_web_view in this case;
    //do we want it to show in the right side? It depends on the content of the calendar


            //start a new activity
            Intent intent=new Intent(getActivity(),OpenWebView.class);
            Bundle bundle=new Bundle();
            bundle.putString("url","http://www.interfaithcalendar.org/");
            intent.putExtras(bundle);
            startActivity(intent);


        }
        else if(i == 6){ //contact us
            rootView = inflater.inflate(R.layout.contact_us,container,false);

        }
        else{ //home page
            rootView = inflater.inflate(R.layout.home_page,container,false);}

        String planet = getResources().getStringArray(R.array.menu_array)[i];
        getActivity().setTitle(planet);
        return rootView;



    }



    public ArrayList<String> getReligions() {
        ArrayList<String> religions = new ArrayList<String>();
        HttpURLConnection conn = null;
        Log.d("here%s", "hey");

        try {
            URL getReligionURL = new URL("http://ec2-35-161-157-75.us-west-2.compute.amazonaws.com/GetReligions.php");
            conn = (HttpURLConnection) getReligionURL.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader dataReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder data = new StringBuilder();
            String line = "";
            Log.d("here%s", line);
            while ((line = dataReader.readLine()) != null) {
                Log.d("line:%s", line);
                data.append(line);
            }

            String religionData = data.toString();
            final JSONArray jsonreligions = new JSONArray(religionData);
            for (int i = 0; i < jsonreligions.length(); i++) {
                religions.add(jsonreligions.getString(i));
            }
        } catch (Exception e) {
            Log.d("something", e.toString());
        }
        return religions;
    }

}