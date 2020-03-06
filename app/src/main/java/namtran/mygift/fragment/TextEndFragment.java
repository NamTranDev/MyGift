package namtran.mygift.fragment;


import android.animation.Animator;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import namtran.mygift.MainActivity;
import namtran.mygift.R;
import namtran.mygift.htext.base.AnimationListener;
import namtran.mygift.htext.base.HTextView;
import namtran.mygift.htext.typer.TyperTextView;

public class TextEndFragment extends Fragment implements Animator.AnimatorListener {

    private TyperTextView tv_end_animation;
    private TextView tv_end;
    private ImageView iv;

    private int position = 0;
    String[] endText = new String[]{
            "Món quà nhỏ gửi đến em",
            "Mong rằng nó sẽ làm cho em vui",
            "Bởi vì em vui thì anh cũng sẽ vui",
            "Ahihi ^^!",
    };

    public static TextEndFragment getInstance(){
        return new TextEndFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.text_end_fragment,container,false);
        tv_end_animation = view.findViewById(R.id.tv_end_animation);
        tv_end = view.findViewById(R.id.tv_end);
        iv = view.findViewById(R.id.iv);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_end_animation.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_end_animation.animateText(endText[position]);
            }
        },1000);
        tv_end_animation.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(HTextView hTextView) {
                if (getActivity() != null && getActivity() instanceof MainActivity){
                    ((MainActivity)getActivity()).textEnnAnimatiob();
                }
            }
        });
    }

    public void setHandler(){
        if (position != endText.length - 1)
            setFirstText(position != 0);
        if (position < endText.length - 1){
            position++;
            animationFirstText();
        }else {
            iv.animate().alpha(1f).setDuration(2000).setListener(this).start();
        }
    }

    private void animationFirstText(){
        tv_end_animation.animateText(endText[position]);
    }

    private void setFirstText(boolean isDownLine){
        if (isDownLine){
            tv_end.append("\n");
        }
        tv_end.setVisibility(View.VISIBLE);
        tv_end.append(endText[position]);
        tv_end_animation.setText("");
    }

    @Override
    public void onAnimationStart(Animator animation) {
        iv.setAlpha(0f);
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
}
