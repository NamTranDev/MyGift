package namtran.mygift.fragment;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

import namtran.mygift.MainActivity;
import namtran.mygift.R;
import namtran.mygift.fireworks.Firework;
import namtran.mygift.fireworks.explosions.CircleExplosion;
import namtran.mygift.fireworks.explosions.Explosion;
import namtran.mygift.fireworks.explosions.RandomExplosion;
import namtran.mygift.fireworks.explosions.SmileyFaceExplosion;
import namtran.mygift.fireworks.explosions.SpiderExplosion;
import namtran.mygift.fireworks.explosions.ThreeCircleExplosion;
import namtran.mygift.fireworks.view.FireworkComponent;
import namtran.mygift.fireworks.view.InvalidateThread;
import namtran.mygift.htext.rainbow.RainbowTextView;

public class FireworkFragment extends Fragment implements Animator.AnimatorListener {

    private RainbowTextView tv_display;
    private FireworkComponent firework;
    private ImageView iv;
    private Random random;
    private ArrayList<Class<? extends Explosion>> explosionTypes;
    private int position = 0;
    String textDisplay = "";
    String[] text = new String[]{
            "CHÚC MỪNG",
            "QUỐC TẾ",
            "PHỤ NỮ - 8/3",
            "^^!",
    };

    public static FireworkFragment getInstance() {
        return new FireworkFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.firework_fragment,container,false);
        tv_display = view.findViewById(R.id.text_display);
        firework = view.findViewById(R.id.firework);
        iv = view.findViewById(R.id.iv);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        random = new Random();
        createExplosionList();
        InvalidateThread invalidate = new InvalidateThread(firework);
        invalidate.start();
        showFirework();
        textDisplay+=text[position];
        tv_display.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_display.animateText(textDisplay);
                if (getActivity() != null && getActivity() instanceof MainActivity){
                    ((MainActivity)getActivity()).textFireWork();
                }
            }
        },1000);
    }

    private void createExplosionList() {
        explosionTypes = new ArrayList<>();
        explosionTypes.add(RandomExplosion.class);
        explosionTypes.add(SpiderExplosion.class);
        explosionTypes.add(SmileyFaceExplosion.class);
        explosionTypes.add(CircleExplosion.class);
        explosionTypes.add(ThreeCircleExplosion.class);
    }

    public void setHandler(){
        if (position < text.length - 1){
            position++;
            setText();
        }else {
            if (getActivity() != null && getActivity() instanceof MainActivity){
                ((MainActivity)getActivity()).showIvFirework();
            }
        }
    }

    public void showFirework() {
        for (int i = 0 ; i < 3;i++){
            drawFirework();
        }
    }

    private void drawFirework(){
        int Low = 1000;
        int High = 1500;
        int y = random.nextInt(High-Low) + Low;
        int x = random.nextInt(getScreenWidth());
        Explosion xplsn = getExplosionType();
        int color = getColor();
        firework.getWorld().addFirework(
                new Firework(x, y, 60, 90, color, 0,
                        xplsn, null));
        Log.d("touch","y : " + y);
        Log.d("touch","x : " + x);
        firework.invalidate();
    }

    private void setText(){
        textDisplay+=("\n");
        textDisplay+=(text[position]);
        tv_display.animateText(textDisplay);
    }

    public void showImage(){
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(iv, "alpha",
                0f);
        fadeOut.setDuration(5000);
        ObjectAnimator mover = ObjectAnimator.ofFloat(iv,
                "translationY", 600f, 0f);
        mover.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                iv.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mover.setDuration(5000);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(iv, "alpha",
                0f, 1f);
        fadeIn.setDuration(5000);
        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.play(mover).with(fadeIn).after(fadeOut);
        animatorSet.addListener(this);
        animatorSet.start();
    }

    private Explosion getExplosionType() {
        try {
            int explsn = new Random().nextInt(explosionTypes.size());
            return explosionTypes.get(explsn).newInstance();
        } catch (java.lang.InstantiationException | IllegalAccessException e) {
            Log.d("Error", e.getMessage());
            return new RandomExplosion();
        }
    }

    private int getColor() {
        return Color.rgb(random.nextInt(), random.nextInt(), random.nextInt());
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        if (getActivity() != null && getActivity() instanceof MainActivity){
            ((MainActivity)getActivity()).aroundWorld();
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
