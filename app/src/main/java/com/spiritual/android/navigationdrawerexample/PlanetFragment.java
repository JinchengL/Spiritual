package com.spiritual.android.navigationdrawerexample;

import android.app.Dialog;
import android.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Fragment that appears in the "content_frame", shows a planet
 */

public class PlanetFragment extends Fragment {
    //private View web;
    public static final String FRAGMENT_NUMBER = "fragment_number";
    public int time;

    public String prayerTextString;
    public PlanetFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView;

        int i = getArguments().getInt(FRAGMENT_NUMBER);
        WebView myWebview;



        //calling different layout to replace content part

        if(i == 1){
            rootView = inflater.inflate(R.layout.html_view,container,false);
            TextView about = (TextView) rootView.findViewById(R.id.textView10);
            myWebview = (WebView) rootView.findViewById(R.id.webview);
            myWebview.loadUrl("file:///android_asset/about.html");

            WebSettings webSettings = myWebview.getSettings();
            webSettings.setJavaScriptEnabled(true);
            myWebview.setWebViewClient(new WebViewClient());

         }
        else if(i == 2){
            rootView = inflater.inflate(R.layout.html_view,container,false);
            //TextView about = (TextView) rootView.findViewById(R.id.textView10);
            myWebview = (WebView) rootView.findViewById(R.id.webview);
            myWebview.loadUrl("file:///android_asset/prayer.html");

            WebSettings webSettings = myWebview.getSettings();
            webSettings.setJavaScriptEnabled(true);
            myWebview.setWebViewClient(new WebViewClient());

        }
        else if (i==3) { //chaples
            rootView = inflater.inflate(R.layout.chapel,container,false);



        }
        else if(i == 4) {


            rootView = inflater.inflate(R.layout.prayers_resource,container,false);

            //image button 1
            ImageButton button1 = (ImageButton)rootView.findViewById(R.id.imageButton1);
            button1.setOnClickListener(new ClickWebViewListener(getActivity(),"https://www.biblegateway.com/"));

            //image button2
            ImageButton button2 = (ImageButton)rootView.findViewById(R.id.imageButton2);
            button2.setOnClickListener(new ClickWebViewListener(getActivity(),"https://www.biblegateway.com/"));

            //image button3
            ImageButton button3 = (ImageButton)rootView.findViewById(R.id.imageButton3);
            button3.setOnClickListener(new ClickWebViewListener(getActivity(),"http://www.buddhanet.net/e-learning/sutras.htm"));

            //image button4
            ImageButton button4 = (ImageButton)rootView.findViewById(R.id.imageButton4);
            button4.setOnClickListener(new ClickWebViewListener(getActivity(),"http://www.breslov.com/bible/"));
            ImageButton button5 = (ImageButton)rootView.findViewById(R.id.imageButton5);
            button5.setOnClickListener(new ClickWebViewListener(getActivity(),"http://al-quran.info/#home"));
        }
        else if(i ==5){
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
        else if(i == 6){
            rootView = inflater.inflate(R.layout.contact_us,container,false);

        }
        else{

            rootView = inflater.inflate(R.layout.home_page,container,false);

            class UpdateAnimationFrame extends TimerTask{
                public void run(){
                    time++;
                }

            }

            Timer timer = new Timer();
            timer.schedule(new UpdateAnimationFrame(), 1000);


            final Integer[] images = {R.drawable.believe, R.drawable.droplet, R.drawable.hands, R.drawable.kneel_prayer, R.drawable.sunset, R.drawable.spirituality, R.drawable.meditation};
            RadioButton radio1, radio2, radio3, radio4, radio5, radio6, radio7;
            final EditText prayerText = (EditText)rootView.findViewById(R.id.PrayerText);
            Button prayerSubmitButton = (Button)rootView.findViewById(R.id.prayerRequestSubmitButton);
            final ImageSwitcher imageSwitcher;
            final int counter = 0;
            int updater = time % 8;


            radio1 = (RadioButton)rootView.findViewById(R.id.radioButton);
            radio2 = (RadioButton)rootView.findViewById(R.id.radioButton2);
            radio3 = (RadioButton)rootView.findViewById(R.id.radioButton3);
            radio4 = (RadioButton)rootView.findViewById(R.id.radioButton4);
            radio5 = (RadioButton)rootView.findViewById(R.id.radioButton5);
            radio6 = (RadioButton)rootView.findViewById(R.id.radioButton6);
            radio7 = (RadioButton)rootView.findViewById(R.id.radioButton7);



            prayerSubmitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    prayerTextString = prayerText.getText().toString().trim();
                    sendPrayer();
                }
            });

            imageSwitcher = (ImageSwitcher)rootView.findViewById(R.id.imageSwitcher);
            imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                @Override
                public View makeView() {
                    //****************************************************************************************************//
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    return imageView;
                }
            });




            imageSwitcher.setImageResource(images[0]);
            String planet = getResources().getStringArray(R.array.menu_array)[i];
            getActivity().setTitle(planet);

            ImageButton calendarButton = (ImageButton)rootView.findViewById(R.id.calendarButton);
            // calendarButton.setOnClickListener();

            //****************************************************************************************************//
            Animation in = AnimationUtils.loadAnimation(getActivity(), R.anim.in);
            Animation out = AnimationUtils.loadAnimation(getActivity(), R.anim.out);

            imageSwitcher.setInAnimation(in);
            imageSwitcher.setOutAnimation(out);

            //Toast.makeText(getApplicationContext(), "Prayer Request Sent!", Toast.LENGTH_LONG).show();

            RadioGroup radioGroup = (RadioGroup)rootView.findViewById(R.id.radioGroup);


            ImageButton button4 = (ImageButton)rootView.findViewById(R.id.calendarButton);
            button4.setOnClickListener(new ClickWebViewListener(getActivity(),"https://calendar.google.com/calendar/render#main_7"));

            switch (updater){
                case 0:
                    imageSwitcher.setImageResource(images[0]);
                    break;
                case 1:
                    imageSwitcher.setImageResource(images[1]);
                    break;
                case 2:
                    imageSwitcher.setImageResource(images[2]);
                    break;
                case 3:
                    imageSwitcher.setImageResource(images[3]);
                    break;
                case 4:
                    imageSwitcher.setImageResource(images[4]);
                    break;
                case 5:
                    imageSwitcher.setImageResource(images[5]);
                    break;
                case 6:
                    imageSwitcher.setImageResource(images[6]);
                    break;
                default:
                    imageSwitcher.setImageResource(images[0]);
                    break;
            }



            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                    int counter = radioGroup.indexOfChild(rootView.findViewById(checkedID));

                    switch (counter){
                        case 0:
                            imageSwitcher.setImageResource(images[0]);
                            break;
                        case 1:
                            imageSwitcher.setImageResource(images[1]);
                            break;
                        case 2:
                            imageSwitcher.setImageResource(images[2]);
                            break;
                        case 3:
                            imageSwitcher.setImageResource(images[3]);
                            break;
                        case 4:
                            imageSwitcher.setImageResource(images[4]);
                            break;
                        case 5:
                            imageSwitcher.setImageResource(images[5]);
                            break;
                        case 6:
                            imageSwitcher.setImageResource(images[6]);
                            break;
                        default:
                            imageSwitcher.setImageResource(images[0]);
                            break;
                    }

                }
            });

        }
        return rootView;

    }

    private void sendPrayer() {
            String url = "http://35.162.162.165/sendPrayer.php";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    //****************************************************************************************************//
                    Toast.makeText(getActivity(), response,Toast.LENGTH_LONG).show();
                    Log.w("myApp", "got a text link");
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //****************************************************************************************************//
                            Toast.makeText(getActivity(), error.toString(),Toast.LENGTH_LONG).show();
                        }
                    }){
                @Override
                protected Map<String,String> getParams(){
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("prayer",prayerTextString);
                    return params;
                }
            };
        //****************************************************************************************************//
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(stringRequest);
    }


}