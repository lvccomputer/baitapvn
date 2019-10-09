package com.dev.lvc.baitap.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dev.lvc.baitap.R;
import com.dev.lvc.baitap.fragments.BMIFragment;
import com.dev.lvc.baitap.fragments.ConvertFragment;
import com.dev.lvc.baitap.fragments.FoodFragment;
import com.dev.lvc.baitap.fragments.PayFragment;

public class MainActivity extends AppCompatActivity {


    private RelativeLayout layoutConvert, layoutBMI,layoutFood,layoutPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setAction();
    }

    private void init() {
        layoutConvert = findViewById(R.id.layoutConvert);
        layoutBMI = findViewById(R.id.layoutBMI);
        layoutFood = findViewById(R.id.layoutFood);
        layoutPay = findViewById(R.id.layoutPay);
    }

    private void setAction() {
        layoutBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBMIFragment();
            }
        });
        layoutConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConvertFragment();
            }
        });
        layoutFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFoodFragment();
            }
        });
        layoutPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPayFragment();
            }
        });
    }

    private void showConvertFragment() {

        if (getSupportFragmentManager().findFragmentByTag(ConvertFragment.class.getName()) == null) {
            ConvertFragment convertFragment = new ConvertFragment();
            addNewFragments(convertFragment, ConvertFragment.class.getName(), ConvertFragment.class.getName());
        }
    }

    private void showBMIFragment() {
        if (getSupportFragmentManager().findFragmentByTag(BMIFragment.class.getName()) == null) {
            BMIFragment bmi = new BMIFragment();
            addNewFragments(bmi, BMIFragment.class.getName(), BMIFragment.class.getName());
        }
    }
    private void showFoodFragment(){
        if (getSupportFragmentManager().findFragmentByTag(FoodFragment.class.getName()) ==null){
            FoodFragment foodFragment = new FoodFragment();
            addNewFragments(foodFragment, FoodFragment.class.getName(), FoodFragment.class.getName());
        }
    }

    private void showPayFragment(){
        if (getSupportFragmentManager().findFragmentByTag(PayFragment.class.getName()) ==null){
            PayFragment fragment = new PayFragment();
            addNewFragments(fragment,PayFragment.class.getName(),PayFragment.class.getName());
        }
    }
    private void addNewFragments(@NonNull Fragment fragment, @NonNull String fragmentTags, @NonNull String backStack) {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(backStack)
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_en, R.anim.pop_ex)
                .add(R.id.main_activity, fragment, fragmentTags)
                .commitAllowingStateLoss();

    }


    public static void hideKeyBoard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && activity.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void setUserInterface(View view) {

        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideKeyBoard(MainActivity.this);
                    return false;
                }
            });
        }

        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setUserInterface(innerView);
            }
        }
    }

    public boolean isExit = false;

    @Override
    public void onBackPressed() {

        int index = getSupportFragmentManager().getBackStackEntryCount();

        if (index > 0) {
            super.onBackPressed();
        } else {
            if (isExit) {
                finish();
            } else {
                Toast.makeText( MainActivity.this, "Press again to exit!", Toast.LENGTH_SHORT ).show();
                isExit = true;
                new Handler().postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        isExit = false;
                    }
                }, 3000 );
            }
        }
    }
}
