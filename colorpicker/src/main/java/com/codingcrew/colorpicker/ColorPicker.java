package com.codingcrew.colorpicker;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputEditText;

public class ColorPicker extends Dialog implements Slider.OnChangeListener {

    private final CustomColor color;
    public OnSubmit onSubmit;
    private View colourDisplayView;
    private Button setColorButton;
    private TextInputEditText hexCodeEditText;
    private TextView hexCodeView;
    private Slider redSlider;
    private Slider greenSlider;
    private Slider blueSlider;


    public ColorPicker(@NonNull Context context, OnSubmit onSubmit) {
        super(context);
        color = new CustomColor(30, 75, 90);
        this.onSubmit = onSubmit;
    }

    public ColorPicker(@NonNull Context context, String hexCode, OnSubmit onSubmit) {
        super(context);
        color = new CustomColor(hexCode);
        this.onSubmit = onSubmit;
    }

    public ColorPicker(@NonNull Context context, int red, int green, int blue, OnSubmit onSubmit) {
        super(context);
        color = new CustomColor(red, green, blue);
        this.onSubmit = onSubmit;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.colour_picker_layout);
        colourDisplayView = findViewById(R.id.color_display_tab);
        setColorButton = findViewById(R.id.submit_button);
        hexCodeEditText = findViewById(R.id.hex_code);
        hexCodeView = findViewById(R.id.hex_code_display);
        redSlider = findViewById(R.id.red_slider);
        greenSlider = findViewById(R.id.green_slider);
        blueSlider = findViewById(R.id.blue_slider);


        colourDisplayView.setBackgroundColor(Color.rgb(color.red, color.green, color.blue));
        colourDisplayView.invalidate();

        redSlider.setValue(color.red);
        greenSlider.setValue(color.green);
        blueSlider.setValue(color.blue);
        hexCodeView.setText(color.getHexCode());

        redSlider.addOnChangeListener(this);
        greenSlider.addOnChangeListener(this);
        blueSlider.addOnChangeListener(this);

        setColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmit.resultAsHexCode(color.getHexCode());
                onSubmit.resultAsRGB(color.red, color.green, color.blue);
                hide();
            }
        });


        hexCodeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence data, int i, int i1, int i2) {
                if (data != null && data.length() > 0) {
                    try {
                        color.convertToRGB(data.toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        return;
                    }

                    redSlider.setValue(color.red);
                    greenSlider.setValue(color.green);
                    blueSlider.setValue(color.blue);
                }
            }

            @Override
            public void afterTextChanged(Editable data) {

            }
        });


    }

    @Override
    public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
        setColor();
    }

    private void setColor() {
        color.red = (int) redSlider.getValue();
        color.green = (int) greenSlider.getValue();
        color.blue = (int) blueSlider.getValue();

        colourDisplayView.setBackgroundColor(Color.rgb(color.red, color.green, color.blue));
        colourDisplayView.invalidate();

        hexCodeView.setText(color.getHexCode());
    }

}
