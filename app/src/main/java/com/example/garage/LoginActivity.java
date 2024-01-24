package com.example.garage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.strivacity.android.sdk.AuthFlowException;
import com.strivacity.android.sdk.AuthProvider;
import com.strivacity.android.sdk.FlowResponseCallback;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private TextView errorText;

    private AuthProvider provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.errorText = findViewById(R.id.errorText);

        this.provider = Provider.getProvider(getApplicationContext());

        this.provider.checkAuthenticated(isAuthenticated -> {
            if (isAuthenticated) {
                Intent mainIntent = new Intent(
                        getApplicationContext(),
                        MainActivity.class
                );
                startActivity(mainIntent);
            }
        });
    }

    public void startFlow(View view) {
        this.provider.startFlow(
                getApplicationContext(),
                new FlowResponseCallback() {
                    @Override
                    public void success(
                            @Nullable String accessToken,
                            @Nullable Map<String, Object> claims
                    ) {

                        Intent mainIntent = new Intent(
                                getApplicationContext(),
                                MainActivity.class
                        );
                        startActivity(mainIntent);
                    }

                    @Override
                    public void failure(@NonNull AuthFlowException exception) {
                        errorText.setText(exception.toString());
                    }
                }
        );
    }
}