package com.example.android.navigationdrawerexample;

import android.annotation.TargetApi;
import android.app.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;

import android.renderscript.ScriptGroup;
import android.text.Html;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Fragment that appears in the "content_frame", shows a planet
 */

public class PlanetFragment extends Fragment {
    private View web;
    public static final String FRAGMENT_NUMBER = "fragment_number";
    public int time;



    public PlanetFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView;

        int i = getArguments().getInt(FRAGMENT_NUMBER);



        //calling different layout to replace content part

        if(i == 1){
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
        else if(i == 2){
            rootView = inflater.inflate(R.layout.video,container,false);

        }
        else if(i == 3) {
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
        else if(i ==4){
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
        else if(i == 5){
            rootView = inflater.inflate(R.layout.contact_us,container,false);

        }
        else{
            rootView = inflater.inflate(R.layout.home_page,container,false);}



        class UpdateAnimationFrame extends TimerTask{
            public void run(){
                time++;
            }

        }

        Timer timer = new Timer();
        timer.schedule(new UpdateAnimationFrame(), 1000);


        final Integer[] images = {R.drawable.han, R.drawable.luke, R.drawable.mace, R.drawable.vadar, R.drawable.yoda};
        RadioButton radio1, radio2, radio3, radio4, radio5;
        final ImageSwitcher imageSwitcher;
        final int counter = 0;
        int updater = time % 5;


        radio1 = (RadioButton)rootView.findViewById(R.id.radioButton);
        radio2 = (RadioButton)rootView.findViewById(R.id.radioButton2);
        radio3 = (RadioButton)rootView.findViewById(R.id.radioButton3);
        radio4 = (RadioButton)rootView.findViewById(R.id.radioButton4);
        radio5 = (RadioButton)rootView.findViewById(R.id.radioButton5);


        imageSwitcher = (ImageSwitcher)rootView.findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getContext());
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

        Animation in = AnimationUtils.loadAnimation(getContext(), R.anim.in);
        Animation out = AnimationUtils.loadAnimation(getContext(), R.anim.out);

        imageSwitcher.setInAnimation(in);
        imageSwitcher.setOutAnimation(out);

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
                    default:
                        imageSwitcher.setImageResource(images[0]);
                        break;
                }

            }
        });



        return rootView;

    }



}