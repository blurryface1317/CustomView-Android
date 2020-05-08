package com.sew.customviews.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;

import com.sew.customviews.R;

import static com.sew.customviews.R.color.colorAccent;


public class CustomView extends LinearLayout {



    public static final String VALUE = "Value";
    int plusButtonColor;
    int minusButtonColor;
    int numberColor;
    int valueColor;
    int maxValue;
    int minValue;
    int initialValue;

    public int getCurrentValue() {
        return currentValue;
    }


    int currentValue;
    int newValue;
    ImageView minusImageButton;
    TextView tvNumber;
    TextView tvTextTitle;
    ImageView plusImageButton;

    public int getInitialValue() {
        return initialValue;
    }

    public void setPlusButtonColor(int plusButtonColor) {
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.pluscircle);
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, plusButtonColor);
        plusImageButton.setImageDrawable(wrappedDrawable);
    }

    public void setMinusButtonColor(int minusButtonColor) {
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.minuscircle);
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, minusButtonColor);
        minusImageButton.setImageDrawable(wrappedDrawable);
    }

    Context context;

    public CustomView(Context context)
    {
        super(context);
        this.context=context;
    }


    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0);

        try {
            numberColor = typedArray.getColor(R.styleable.CustomView_numberColor, getResources().getColor(R.color.colorPrimary, null));
            valueColor = typedArray.getColor(R.styleable.CustomView_valueColor, getResources().getColor(colorAccent, null));
            plusButtonColor = typedArray.getColor(R.styleable.CustomView_plusButtonColor, getResources().getColor(colorAccent, null));
            minusButtonColor = typedArray.getColor(R.styleable.CustomView_minusButtonColor, getResources().getColor(R.color.colorPrimary, null));
            maxValue = typedArray.getInteger(R.styleable.CustomView_maxValue, 100);
            minValue = typedArray.getInteger(R.styleable.CustomView_minValue, 0);
            initialValue = typedArray.getInteger(R.styleable.CustomView_InitialValue, 10);
        } finally {
            typedArray.recycle();
            initializeLayout();
        }
    }

        public void initializeLayout()

        {
//            setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//            ImageView imageView = new ImageView(context, attrs);
//            imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//            ll1.addView(imageView);
//            LinearLayout ll2 = new LinearLayout(context, attrs);
//            ll2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//            ll2.setOrientation(LinearLayout.VERTICAL);
//            EditText editText = new EditText(context, attrs);
//            TextView textView = new TextView(context, attrs);
//            ll2.addView(editText);
//            R
//            ll2.addView(textView);
//            ll1.addView(ll2);
//            ImageView imageView1 = new ImageView(context, attrs);
//            imageView1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            setLayoutParams(params);
            setOrientation(LinearLayout.HORIZONTAL);
            setWeightSum(3);
            minusImageButton = new ImageView(context);
            params = new LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f);
            minusImageButton.setLayoutParams(params);
            minusImageButton.setImageResource(R.drawable.minuscircle);
            setMinusButtonColor(minusButtonColor);
            minusImageButton.setPadding(5, 5, 5, 5);
            minusImageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
            minusImageButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentValue = Integer.valueOf(tvNumber.getText().toString());
                    newValue = Math.max(currentValue - 1, minValue);
                    if (onValuesClickListener != null) {
                        onValuesClickListener.OnValueChange(currentValue, newValue);
                    }
                    tvNumber.setText(String.valueOf(newValue));
                }
            });
            LinearLayout llValue = new LinearLayout(context);
            llValue.setLayoutParams(new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f));
            llValue.setOrientation(VERTICAL);
            llValue.setWeightSum(5);
            tvNumber = new TextView(context);
            tvNumber.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 0, 3.0f));
            tvNumber.setTextColor(numberColor);
            tvNumber.setTextSize(24f);
            if(minValue>initialValue)
                tvNumber.setText(String.valueOf(minValue));
            else if(maxValue<initialValue){
                tvNumber.setText(String.valueOf(maxValue));
            }
            else
            tvNumber.setText(String.valueOf(initialValue));
            tvNumber.setGravity(Gravity.CENTER);
            tvNumber.setPadding(2, 2, 2, 2);
            tvTextTitle = new TextView(context);
            tvTextTitle.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 0, 2.0f));
            tvTextTitle.setTextColor(valueColor);
            tvTextTitle.setText(VALUE);
            tvTextTitle.setGravity(Gravity.CENTER);
            tvTextTitle.setTextSize(14f);
            tvTextTitle.setPadding(5, 5, 5, 5);
            llValue.addView(tvNumber);
            llValue.addView(tvTextTitle);

            plusImageButton = new ImageView(context);
            params = new LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f);
            plusImageButton.setLayoutParams(params);
            plusImageButton.setImageResource(R.drawable.pluscircle);
            setPlusButtonColor(plusButtonColor);
            plusImageButton.setPadding(5, 5, 5, 5);
            plusImageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
            plusImageButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentValue = Integer.valueOf(tvNumber.getText().toString());
                    newValue = Math.min(currentValue + 1, maxValue);
                    if (onValuesClickListener != null) {
                        onValuesClickListener.OnValueChange(currentValue, newValue);
                    }
                    tvNumber.setText(String.valueOf(newValue));
                }
            });
            setPadding(10, 40, 10, 40);
            addView(minusImageButton);
            addView(llValue);
            addView(plusImageButton);
        }
    public OnValuesClickListener onValuesClickListener;
    public interface OnValuesClickListener {
        void OnValueChange(int oldValue, int newValue);
    }
    public void setOnValueChangeListener(OnValuesClickListener listener) {
        this.onValuesClickListener = listener;
    }


        }



