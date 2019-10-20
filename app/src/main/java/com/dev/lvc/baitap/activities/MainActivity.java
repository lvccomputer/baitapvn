package com.dev.lvc.baitap.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.dev.lvc.baitap.adapters.MenuAdapter;
import com.dev.lvc.baitap.fragments.BMIFragment;
import com.dev.lvc.baitap.fragments.ConvertFragment;
import com.dev.lvc.baitap.fragments.DetailTravelFragment;
import com.dev.lvc.baitap.fragments.FoodFragment;
import com.dev.lvc.baitap.fragments.PayFragment;
import com.dev.lvc.baitap.fragments.TravelFragment;
import com.dev.lvc.baitap.models.Menu;
import com.dev.lvc.baitap.models.Travel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private RecyclerView rcvMenu;
    private MenuAdapter menuAdapter;
    private ArrayList<Menu> menuArrayList;

    private int[] icon = {
            R.drawable.bmi,
            R.drawable.convert,
            R.drawable.food,
            R.drawable.money,
            R.drawable.travel
    };
    private String[] title = {
            "Tính Chỉ số BMI",
            "Đổi độ C sang độ F",
            "Danh sách thức ăn - Đồ uống",
            "Thanh toán tiền thức ăn - đồ uống",
            "Giới thiệu địa danh du lịch (Kiểm tra giữa kì)"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setAction();
    }

    private void init() {
        menuArrayList = new ArrayList<>();
        rcvMenu = findViewById(R.id.rcvMenu);

        menuArrayList.add(new Menu(icon[0], title[0]));
        menuArrayList.add(new Menu(icon[1], title[1]));
        menuArrayList.add(new Menu(icon[2], title[2]));
        menuArrayList.add(new Menu(icon[3], title[3]));
        menuArrayList.add(new Menu(icon[4], title[4]));

        menuAdapter = new MenuAdapter(menuArrayList, this);
        rcvMenu.setLayoutManager(new LinearLayoutManager(this));
        rcvMenu.setAdapter(menuAdapter);


    }

    private void setAction() {
        menuAdapter.setOnClickItemMenuListener((position, menu) -> {
            if (position == 0) {
                showBMIFragment();
            } else if (position == 1) {
                showConvertFragment();
            } else if (position == 2) {
                showFoodFragment();
            } else if (position == 3) {
                showPayFragment();
            } else if (position == 4) {
                showTravelFragment();
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

    private void showFoodFragment() {
        if (getSupportFragmentManager().findFragmentByTag(FoodFragment.class.getName()) == null) {
            FoodFragment foodFragment = new FoodFragment();
            addNewFragments(foodFragment, FoodFragment.class.getName(), FoodFragment.class.getName());
        }
    }

    private void showPayFragment() {
        if (getSupportFragmentManager().findFragmentByTag(PayFragment.class.getName()) == null) {
            PayFragment fragment = new PayFragment();
            addNewFragments(fragment, PayFragment.class.getName(), PayFragment.class.getName());
        }
    }

    private void showTravelFragment() {
        if (getSupportFragmentManager().findFragmentByTag(TravelFragment.class.getName()) == null) {
            TravelFragment fragment = new TravelFragment();
            addNewFragments(fragment, TravelFragment.class.getName(), TravelFragment.class.getName());
        }
    }
    public void showDetailFragment(Travel travel){
        if (getSupportFragmentManager().findFragmentByTag(DetailTravelFragment.class.getName()) ==null){
            DetailTravelFragment fragment = new DetailTravelFragment();
            fragment.setTravel(travel);
            addNewFragments(fragment,DetailTravelFragment.class.getName(),DetailTravelFragment.class.getName());
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
            view.setOnTouchListener((v, event) -> {
                hideKeyBoard(MainActivity.this);
                return false;
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
                Toast.makeText(MainActivity.this, "Press again to exit!", Toast.LENGTH_SHORT).show();
                isExit = true;
                new Handler().postDelayed(() -> isExit = false, 3000);
            }
        }
    }
}
