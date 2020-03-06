package namtran.mygift.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import namtran.mygift.MainActivity;
import namtran.mygift.R;
import namtran.mygift.htext.base.AnimationListener;
import namtran.mygift.htext.base.HTextView;
import namtran.mygift.htext.typer.TyperTextView;

public class TextFirstFragment extends Fragment {

    private TyperTextView tv_first_animation;
    private TextView tv_first;

    private int position = 0;
    String[] firstText = new String[]{
            "Hi em ^^!",
            "Món quà nhỏ tặng em",
            "Chúc em một ngày 8/3",
            "Luôn hạnh phúc và vui vẻ ^^!",
            "Let go !!!"
    };

    public static TextFirstFragment getInstance(){
        return new TextFirstFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.text_first_fragment,container,false);
        tv_first_animation = view.findViewById(R.id.tv_first_animation);
        tv_first = view.findViewById(R.id.tv_first);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_first_animation.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(HTextView hTextView) {
                if (getActivity() != null && getActivity() instanceof MainActivity){
                    ((MainActivity)getActivity()).textIntro();
                }
            }
        });
        tv_first_animation.setTyperSpeed(75);

        animationFirstText();
    }

    public void setHandler(){
        if (position != firstText.length - 1)
            setFirstText(position != 0);
        if (position < firstText.length - 1){
            position++;
            animationFirstText();
        }else {
            if (getActivity() != null && getActivity() instanceof MainActivity){
                ((MainActivity)getActivity()).slideImage();
            }
        }
    }

    private void animationFirstText(){
        tv_first_animation.animateText(firstText[position]);
    }

    private void setFirstText(boolean isDownLine){
        if (isDownLine){
            tv_first.append("\n");
        }
        tv_first.setVisibility(View.VISIBLE);
        tv_first.append(firstText[position]);
        tv_first_animation.setText("");
    }
}
