package com.example.android.navigationdrawerexample;

import android.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

/**
 * Fragment that appears in the "content_frame", shows a planet
 */
public class PlanetFragment extends Fragment {
    private View web;
    public static final String FRAGMENT_NUMBER = "fragment_number";


    public PlanetFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(final LayoutInflater inflater,final ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView;
        int i = getArguments().getInt(FRAGMENT_NUMBER);



        //calling different layout to replace content part

        if(i == 1){
            rootView = inflater.inflate(R.layout.about_us,container,false);

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
            rootView = inflater.inflate(R.layout.activity_open_web_view,container,false);
            //we do not need the layout to bw activity_open_web_view in this case;
    //do we want it to show in the right side? It depends on the content of the calendar


            //start a new activity
            Intent intent=new Intent(getActivity(),OpenWebView.class);
            Bundle bundle=new Bundle();
            bundle.putString("url","http://www.stackoverflow.com");
            intent.putExtras(bundle);
            startActivity(intent);


        }
        else if(i == 5){
            rootView = inflater.inflate(R.layout.contact_us,container,false);

        }
        else{
            rootView = inflater.inflate(R.layout.home_page,container,false);}

        String planet = getResources().getStringArray(R.array.menu_array)[i];
        getActivity().setTitle(planet);
        return rootView;

    }



}