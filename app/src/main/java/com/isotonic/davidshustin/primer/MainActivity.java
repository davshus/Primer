package com.isotonic.davidshustin.primer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {
    private int points, difficulty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        points = 0;
        difficulty = 1;
        generate();
        NumberPicker diff = (NumberPicker)findViewById(R.id.difficulty);
        diff.setMinValue(1);
        diff.setMaxValue(5);
        diff.setValue(1);
        diff.setOnValueChangedListener(this);
    }
    private void generate() {
        int lim = (int)Math.pow(10, difficulty);
        boolean shouldBePrime = Math.random() < 0.5;
        TextView display = (TextView)findViewById(R.id.display);
        int oldN = Integer.parseInt(display.getText().toString());
        int n;
        do {
             n = (int)((Math.random() * (lim - 2)) + 2);
            if (n == oldN) System.out.println(n);
        } while (isPrime(n) != shouldBePrime || n == oldN);
        display.setText(Integer.toString(n));
    }

    //Precondition: target greater than 1
    private boolean isPrime(int target) {
        for (int i = 2; i <= Math.sqrt(target); i++) {
            if (target % i == 0) {
                return false;
            }
        }
        return true;
    }

    private void addPoints() {
        points++;
        TextView ptv = (TextView)findViewById(R.id.points);
        ptv.setText("Points: " + points);
    }

    protected void select(View view) {
        Button prime = (Button)findViewById(R.id.prime), composite = (Button)findViewById(R.id.composite);
        TextView display = (TextView)findViewById(R.id.display);
        boolean is = isPrime(Integer.parseInt(display.getText().toString()));
        if ((is && view == prime) || (!is && view == composite)) {
            addPoints();
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
        }
        generate();
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        difficulty = newVal;
        generate();
    }
}
