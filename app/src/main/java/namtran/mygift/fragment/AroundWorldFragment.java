package namtran.mygift.fragment;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import namtran.mygift.BaseSlide;
import namtran.mygift.Food;
import namtran.mygift.MainActivity;
import namtran.mygift.R;
import namtran.mygift.food.SlideshowImageView;
import namtran.mygift.htext.base.AnimationListener;
import namtran.mygift.htext.base.HTextView;
import namtran.mygift.htext.evaporate.EvaporateTextView;
import namtran.mygift.htext.fade.FadeTextView;

public class AroundWorldFragment extends Fragment implements Animator.AnimatorListener, SlideshowImageView.OnSlidesListener {

    private ImageView ivPlanes;
    private FadeTextView tv_intro;
    private EvaporateTextView tv_lead;
    private TextView tv_intro_food;

    private List<Food> data = new ArrayList<>();

    private SlideshowImageView slideFood;
    private AnimatorSet set = new AnimatorSet();

    private int position = 0;
    String[] text = new String[]{
            "Chị thích đồ ăn đúng hơm ???",
            "Vậy bây giờ chúng ta sẽ ",
            "Cùng đi vòng quanh thế giới",
            "Để thưởng thức đồ ăn ha",
            "Rồi thắt chặt dây an toàn",
            "Chúng ta bay nào :))))"
    };

    public static AroundWorldFragment getInstance() {
        return new AroundWorldFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.around_world_fragment,container,false);
        tv_intro  = view.findViewById(R.id.tv_intro);
        tv_lead  = view.findViewById(R.id.tv_lead);
        tv_intro_food  = view.findViewById(R.id.tv_intro_food);
        ivPlanes  = view.findViewById(R.id.iv_planes);
        slideFood  = view.findViewById(R.id.iv_food);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(getScreenWidth(),getScreenWidth());
        layoutParams.addRule(RelativeLayout.BELOW, R.id.tv_lead);
        slideFood.setLayoutParams(layoutParams);
        slideFood.setOnSlidesListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_intro.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_intro.animateText(text[0]);
            }
        },1000);
        tv_intro.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(HTextView hTextView) {
                if (getActivity() != null && getActivity() instanceof MainActivity){
                    ((MainActivity)getActivity()).textIntroArounWorld();
                }
            }
        });

        List<String> b = new ArrayList<>();
        b.add("Giờ chúng ta đi đên New Orleans");
        b.add("Bánh kẹp cá da trơn chiên giòn, sò, gà hoặc cá sấu tại nhà hàng Po'boys Johnny");
        data.add(new Food(R.drawable.banh_kep,b));

        List<String> d = new ArrayList<>();
        d.add("Đi qua Mỹ thoy");
        d.add("Bánh rán vòng Doughnut - oại bánh này là một sự kết hợp đầy bí ẩn, bao gồm 1 lạng thịt muối, bơ đặt giữa hai nửa chiếc bánh rán vòng" +
                " bọc đường ngọt ngào. Bánh được bán nhiều ở Portland, Oregon");
        data.add(new Food(R.drawable.banh_ran_vong_doughnut,d));

        List<String> e = new ArrayList<>();
        e.add("Qua mexico nhé <3");
        e.add("Bánh cá thịt chiên giòn. Cá nguyên liệu cho món này là cá tilapia, thuộc họ Cichilidae, được ép dẹt và rán, phần trên phủ nước sốt " +
                "mayonnaise và cải bắp đỏ");
        data.add(new Food(R.drawable.banh_thit_ca_chien_gion,e));

        List<String> f = new ArrayList<>();
        f.add("Đến Ấn Độ nào");
        f.add("Thưởng thức bữa sáng với bánh xèo - gọi là Masala Dosa cùng với chai trà ở miền nam Ấn Độ. Nguyên liệu chủ yếu để làm nên dosa chỉ là gạo và " +
                "đậu lăng đen, điều đó cũng có nghĩa, dosa chứa nhiều protein");
        data.add(new Food(R.drawable.banh_xeo_india,f));


        List<String> l = new ArrayList<>();
        l.add("Cùng đến thành phố Vienna nào");
        l.add("Món bánh Sachertorte \n Nó được làm từ ruột bánh mì và bánh sữa sô-cô-la, xen lẫn ở giữa là mứt mơ, bên ngoài được bao phủ một lớp " +
                "chocolate . Người ta còn cho thêm vào bánh vị hạt nhục đậu khấu, việt quất hoặc hạnh nhân");
        data.add(new Food(R.drawable.chocolate,l));

        List<String> m = new ArrayList<>();
        m.add("Qua Singapore nghen ^^!");
        m.add("Các món cơm gà, mỳ laska, thịt xiên nướng satay . Ngoài ra các quàn ăn ở đây còn hàng chục món khác để chị lựa chọn nữa :))");
        data.add(new Food(R.drawable.com_ga,m));

        List<String> n = new ArrayList<>();
        n.add("Giờ qua Cuba ăn cơm trộn thoy >\"<");
        n.add("Đến Havana, chị đừng bỏ qua món cơm trộn với đậu đen, thịt gà và rau");
        data.add(new Food(R.drawable.com_tron,n));

        List<String> o = new ArrayList<>();
        o.add("Giờ qua Tây Ban Nha ăn nhóe ^^!");
        o.add("Món Cơm trộn thập cẩm Paella \n Món ăn được pha trộn từ cơm, hải sản, xúc xích và màu vàng của nghệ");
        data.add(new Food(R.drawable.com_tron_thap_cam,o));

        List<String> q = new ArrayList<>();
        q.add("Giờ mình cùng bay qua Brazil ha >'<");
        q.add("Món feijoada \n Nó là một nồi hầm bao gồm thịt bò, thịt lợn và đậu đen");
        data.add(new Food(R.drawable.feijoada,q));

        List<String> s = new ArrayList<>();
        s.add("Đến Chicago ăn Pizza hoy hí hí");
        data.add(new Food(R.drawable.food_chicago,s));
    }

    public void animationPlanes(){
        ObjectAnimator moveX = ObjectAnimator.ofFloat(ivPlanes,
                "translationX", 0, getScreenWidth() - 50);
        ObjectAnimator moveY = ObjectAnimator.ofFloat(ivPlanes,
                "translationY", 0,-getScreenHeight() - 50);


        set.playTogether(moveY,moveX);
        set.setDuration(5000);
        set.addListener(this);
        if (!set.isRunning())
            set.start();
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    @Override
    public void onAnimationStart(Animator animation) {
        ivPlanes.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        tv_intro.setVisibility(View.GONE);
        ivPlanes.setVisibility(View.GONE);
        slideFood.setImages(data);
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    public void setHander() {
        if (position < text.length - 1){
            position++;
            tv_intro.animateText(text[position]);
        }else {
            if (getActivity() != null && getActivity() instanceof MainActivity){
                ((MainActivity)getActivity()).animationPlanes();
            }
        }
    }

    @Override
    public void onSlidesListener(BaseSlide baseSlide) {
        if (baseSlide != null && baseSlide instanceof Food){
//            Toast.makeText(getActivity(),((Food)baseSlide).getIntro().get(0),Toast.LENGTH_SHORT).show();
            final List<String> data = ((Food)baseSlide).getIntro();
            tv_lead.setVisibility(View.VISIBLE);
            if (data.size() == 1){
                tv_lead.animateText(data.get(0));
            }else {
                tv_lead.animateText(data.get(0));
                tv_lead.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tv_intro_food.setText(data.get(1));
                    }
                },7000);
            }
        }
    }

    @Override
    public void onSlideComplete() {
        tv_lead.setVisibility(View.INVISIBLE);
        tv_intro_food.setText("");
    }

    @Override
    public void onSlideCancel() {
        if (getActivity() != null && getActivity() instanceof MainActivity){
            ((MainActivity)getActivity()).textEnd();
        }
    }
}
