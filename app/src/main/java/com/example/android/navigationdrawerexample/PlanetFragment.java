package com.example.android.navigationdrawerexample;

import android.app.Fragment;

import android.content.DialogInterface;
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
            rootView = inflater.inflate(R.layout.layout,container,false);

            //image button 1
            ImageButton button1 = (ImageButton)rootView.findViewById(R.id.imageButton1);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    View resource= rootView.findViewById(R.id.activity_resource);
                    resource.setVisibility(View.GONE);
                    web=rootView.findViewById(R.id.activity_open_web_view);
                    web.setVisibility(View.VISIBLE);
                    openBrowser(rootView,"http://www.breslov.com/bible/");

                }
            });

            //image button2
            ImageButton button2 = (ImageButton)rootView.findViewById(R.id.imageButton2);
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    View resource= rootView.findViewById(R.id.activity_resource);
                    resource.setVisibility(View.GONE);
                    web=rootView.findViewById(R.id.activity_open_web_view);
                    web.setVisibility(View.VISIBLE);
                    openBrowser(rootView,"http://www.breslov.com/bible/");

                }
            });

        }
        else if(i == 4){
            rootView = inflater.inflate(R.layout.contact_us,container,false);

        }
        else{
            rootView = inflater.inflate(R.layout.home_page,container,false);}

        String planet = getResources().getStringArray(R.array.menu_array)[i];

        //int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
        //    "drawable", getActivity().getPackageName());
        //((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
        getActivity().setTitle(planet);
        return rootView;

    }

    private void openBrowser(View rootView,String url)
    {
        WebView webView = (WebView) rootView.findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);




    }
}