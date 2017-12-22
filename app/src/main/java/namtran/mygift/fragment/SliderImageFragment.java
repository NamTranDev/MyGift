package namtran.mygift.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Map;
import java.util.Random;

import namtran.mygift.R;
import namtran.mygift.Slider;
import namtran.mygift.firework.FireworkView;
import namtran.mygift.firework.ParticleLayer;
import namtran.mygift.library.SliderLayout;
import namtran.mygift.library.SliderTypes.BaseSliderView;
import namtran.mygift.library.Tricks.ViewPagerEx;

public class SliderImageFragment extends Fragment implements ViewPagerEx.OnPageChangeListener {

    private SliderLayout slider;
    private FireworkView backgroundSnow;

    public static SliderImageFragment getInstance() {
        return new SliderImageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.slider_image_fragment,container,false);
        slider = view.findViewById(R.id.slider);
        backgroundSnow = view.findViewById(R.id.snow_background);

        slider.setPresetTransformer(SliderLayout.Transformer.Default);
        slider.setDuration(4000);
        slider.addOnPageChangeListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addImage();
    }

    private void startSlider(){
        slider.setVisibility(View.VISIBLE);
        slider.startAutoCycle();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        slider.stopAutoCycle();
    }

    private void addImage(){
        Map<String,Integer> data = new ArrayMap<>();
        data.put("Đang nhát ma đó hả :))))))",R.drawable.a);
        data.put("Thích làm mèo nhỉ @,@",R.drawable.b);
        data.put("Cái mỏ sao chu được hay thế >\"<",R.drawable.c);
        data.put("Cô gái nghiêm túc của năm à =]]]]]z", R.drawable.d);
        data.put("Cô mèo này đáng yêu thế nhể <3", R.drawable.e);
        data.put("Có 360 hok mà sao trắng sáng thế ~.~", R.drawable.l);
        data.put("Lại chu môi ^,^", R.drawable.n);
        data.put("Chân ải chân ai =,=", R.drawable.p);
        data.put("Thời trẻ trâu của ai đó +_=", R.drawable.r);
        data.put("Lúc nào cũng chu mỏ cả 0.0", R.drawable.f);
        data.put("Cũng lại bụm môi nhìn muốn cắn thật *,*", R.drawable.t);
        data.put("Làm ninja à ==", R.drawable.w);
        data.put("Vẫn một kiểu chu môi cũ o.o", R.drawable.q);

        for(String name : data.keySet()){
            Slider textSliderView = new Slider(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(data.get(name))
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            slider.addSlider(textSliderView);
            slider.setVisibility(View.GONE);
        }
    }

    public void showSlider() {
        startSlider();
    }

    public void setBackgroundSnow() {
        backgroundSnow.queueEvent(new Runnable() {
            @Override
            public void run() {
                backgroundSnow.setParticleSystem(new ParticleLayer(getActivity(),R.raw.snow,R.drawable.particle_texture));
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        String animation = SliderLayout.Transformer.values()[new Random().nextInt(13)].toString();
        slider.setPresetTransformer(animation);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
