package com.example.garage;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.strivacity.android.sdk.AuthProvider;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private AuthProvider provider;
    private Map<String, Object> claims;

    private String fullName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.provider = Provider.getProvider(getApplicationContext());
        getLastClaims();
        getFullName();
    }

    public void onClick(View view) {
        colorSwitch(view);
        setFullName((TextView) view);
    }

    public void setFullName(TextView view) {
        String placeholder = (String) view.getText();

        if(placeholder.length() == 1) {
            String concatIdName = placeholder + "\n" + fullName;
            view.setText(concatIdName);
        } else {
            String id = placeholder.substring(0,1);
            view.setText(id);
        }
    }

    public void logout(View view) {
        this.provider.logout(
                getApplicationContext(),
                () -> {
                    Intent intent = new Intent(
                            getApplicationContext(),
                            LoginActivity.class
                    );
                    startActivity(intent);
                }
        );
    }

    //todo custom parking spot widget which will hold the colorswitch etc. logic
     private void colorSwitch(View view){
        int colorCode = 0;
        if (view.getBackground() instanceof ColorDrawable) {
            colorCode = ((ColorDrawable) view.getBackground()).getColor();
        }

        int greenColorCode = getColor(R.color.green);

        if (colorCode == greenColorCode) {
            view.setBackgroundColor(getColor(R.color.red));
        }
        else {
            view.setBackgroundColor(getColor(R.color.green));
        }
    }

    private void getLastClaims() {
        this.claims = provider.getLastRetrievedClaims();
    }

    private void getFullName() {
        if (this.claims != null) {
            String given_name = (String) this.claims.get("given_name");
            String family_name = (String) this.claims.get("family_name");

            if(given_name != null) {
                this.fullName += given_name;
            }

            if(family_name != null) {
                this.fullName += " " + family_name;
            }
        }
    }
}