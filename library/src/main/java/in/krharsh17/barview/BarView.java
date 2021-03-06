package in.krharsh17.barview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;


import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

public class BarView extends ScrollView implements Constants {
    private LinearLayout containerLayout;
    private Context context;

    private List<BarGroup> barGroups;
    private List<BarModel> data;

    private int BAR_MARGIN = 6, VERTICAL_SPACING = 48, BAR_HEIGHT = 20, LABEL_FONT_SIZE = 18, VALUE_FONT_SIZE = 9;
    private String LABEL_TEXT_COLOR = labelTextColor, VALUE_TEXT_COLOR = valueTextColor, RIPPLE_COLOUR = rippleColor;// has to be >2

    public void setData(List<BarModel> data) {
        this.data = data;
        populateBarView();
    }

    private void populateBarView() {
        for (BarModel b : data) {
            addBar(b);
        }
    }

    private void addBar(BarModel data) {
        BarGroup barGroup = new BarGroup(
                context,
                data.getLabel(),
                data.getColor(),
                data.getValue(),
                data.getFillRatio(),
                BAR_MARGIN,
                VERTICAL_SPACING,
                BAR_HEIGHT,
                LABEL_FONT_SIZE,
                VALUE_FONT_SIZE,
                LABEL_TEXT_COLOR,
                VALUE_TEXT_COLOR,
                RIPPLE_COLOUR
        );
        barGroup.setLayoutParams(new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        barGroups.add(barGroup);

        containerLayout.addView(barGroup);
        invalidate();
        requestLayout();
    }

    public BarView(Context context) {
        super(context);
        this.context = context;
        barGroups = new ArrayList<>();
        containerLayout = new LinearLayout(context);

        containerLayout.setLayoutParams(new ScrollView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        containerLayout.setOrientation(LinearLayout.VERTICAL);

        this.addView(containerLayout);

    }

    public BarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        barGroups = new ArrayList<>();
        containerLayout = new LinearLayout(context);

        containerLayout.setLayoutParams(new ScrollView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        containerLayout.setOrientation(LinearLayout.VERTICAL);

        this.addView(containerLayout);

        if (attrs != null) {

            final TypedArray a = context.obtainStyledAttributes(attrs,
                    R.styleable.BarView, 0, 0);
            VERTICAL_SPACING = a.getInteger(R.styleable.BarView_barGroupSpacing, VERTICAL_SPACING);
            BAR_HEIGHT = a.getInteger(R.styleable.BarView_barHeight, BAR_HEIGHT);
            LABEL_FONT_SIZE = a.getInteger(R.styleable.BarView_labelTextSize, LABEL_FONT_SIZE);
            VALUE_FONT_SIZE = a.getInteger(R.styleable.BarView_valueTextSize, VALUE_FONT_SIZE);
            LABEL_TEXT_COLOR = a.getString(R.styleable.BarView_labelTextColor);
            VALUE_TEXT_COLOR = a.getString(R.styleable.BarView_valueTextColor);
            RIPPLE_COLOUR = a.getString(R.styleable.BarView_rippleColor);
            if (LABEL_TEXT_COLOR == null)
                LABEL_TEXT_COLOR = labelTextColor;
            if (VALUE_TEXT_COLOR == null)
                VALUE_TEXT_COLOR = valueTextColor;
            if (RIPPLE_COLOUR == null)
                RIPPLE_COLOUR = rippleColor;
            a.recycle();
        }
    }

    public static String getRandomColor() {
        char[] letters = charArray.toCharArray();
        StringBuilder color = new StringBuilder("#");
        for (int i = 0; i < 6; i++) {
            color.append(letters[(int) Math.round(Math.floor(Math.random() * 16))]);
        }
        return color.toString();
    }
}
