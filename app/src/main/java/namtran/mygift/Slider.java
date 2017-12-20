package namtran.mygift;


import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

import namtran.mygift.htext.evaporate.EvaporateTextView;
import namtran.mygift.htext.fade.FadeTextView;
import namtran.mygift.htext.fall.FallTextView;
import namtran.mygift.htext.rainbow.RainbowTextView;
import namtran.mygift.htext.scale.ScaleTextView;
import namtran.mygift.htext.typer.TyperTextView;
import namtran.mygift.library.SliderTypes.BaseSliderView;

public class Slider extends BaseSliderView {

    public Slider(Context context) {
        super(context);
    }

    @Override
    public View getView() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.slider_item_layout, null);
        ImageView target = v.findViewById(R.id.iv);
        RainbowTextView tv_evaporate = v.findViewById(R.id.tv_evaporate);

        LinearLayout.LayoutParams full = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                getScreenWidth());
        target.setLayoutParams(full);

        tv_evaporate.animateText(getDescription());
        bindEventAndShow(v, target);
        return v;
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
}
