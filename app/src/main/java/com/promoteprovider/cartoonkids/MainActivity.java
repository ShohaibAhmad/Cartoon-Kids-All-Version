package com.promoteprovider.cartoonkids;


import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;




import android.os.Bundle;

import android.view.MenuItem;
import android.widget.LinearLayout;


import com.facebook.ads.AdSize;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.promoteprovider.cartoonkids.Fragments.AboutFragment;
import com.promoteprovider.cartoonkids.Fragments.HomeFragment;
import com.promoteprovider.cartoonkids.Fragments.PolicyFragment;
import com.promoteprovider.cartoonkids.Fragments.SettingFragment;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    LinearLayout linearLayout;
    //Ads
    private com.facebook.ads.AdView fbBannerAd;
    private AdView mAdView;
    String AdmobAdsShowValue="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ads
        AudienceNetworkAds.initialize(this);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("AdsController");
        dbRef.child("Admob").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    AdmobAdsShowValue = dataSnapshot.child("AdmobAdPermitted").getValue().toString();
                    if (AdmobAdsShowValue.equals("yes")) {
                        LoadAdmobBannerAd();
                    }else{
                        LoadFbBannerAd();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //firebase massaging
        FirebaseMessaging.getInstance().subscribeToTopic("notification");
        //fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.linearLayout,new HomeFragment());
        transaction.commit();

        bottomNavigation = findViewById(R.id.bottomNavigation);
        linearLayout = findViewById(R.id.linearLayout);

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                switch (item.getItemId()){
                    case R.id.home:
                        transaction.replace(R.id.linearLayout,new HomeFragment());
                        break;
                    case R.id.about:
                        transaction.replace(R.id.linearLayout,new AboutFragment());
                        break;
                    case R.id.policy:
                        transaction.replace(R.id.linearLayout,new PolicyFragment());
                        break;
                    case R.id.setting:
                        transaction.replace(R.id.linearLayout,new SettingFragment());
                        break;
                }
                transaction.commit();
                return true;
            }
        });


        //======================================================================>==============================================================
            // no internet alert
//        if( ! CheckNetwork.isInternetAvailable(this)) //returns true if internet available
//        {
//            //if there is no internet do this
//            setContentView(R.layout.activity_main);
//            //Toast.makeText(this,"No Internet Connection, Chris",Toast.LENGTH_LONG).show();
//
//            new AlertDialog.Builder(this) //alert the person knowing they are about to close
//                    .setTitle("No internet connection available")
//                    .setMessage("Please Check you're Mobile data or Wifi network.")
//                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            finish();
//                        }
//                    })
//                    //.setNegativeButton("No", null)
//                    .show();
//
//        }
//        else
//        {
//            Toast.makeText(this, "internet successfully connection", Toast.LENGTH_SHORT).show();
//        }
    }
    private void LoadFbBannerAd(){
        fbBannerAd = new com.facebook.ads.AdView(this, getString(R.string.fb_banner_test), AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        adContainer.addView(fbBannerAd);
        fbBannerAd.loadAd();
    }
    @Override
    protected void onDestroy() {
        if (fbBannerAd != null) {
            fbBannerAd.destroy();
        }
        super.onDestroy();
    }

    public void LoadAdmobBannerAd(){
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd((adRequest));
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdOpened() {
            }
            @Override
            public void onAdClicked() {
            }

        });

    }
}
//class CheckNetwork {
//
//    private static final String TAG = CheckNetwork.class.getSimpleName();
//
//    public static boolean isInternetAvailable(Context context)
//    {
//        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//        if (activeNetwork == null)
//        {
//            Log.d(TAG,"no internet connection");
//            return false;
//        }
//        else
//        {
//            if(activeNetwork.isConnected())
//            {
//                Log.d(TAG," internet connection available...");
//                return true;
//            }
//            else
//            {
//                Log.d(TAG," internet connection");
//                return true;
//            }
//
//        }
//    }
//}
