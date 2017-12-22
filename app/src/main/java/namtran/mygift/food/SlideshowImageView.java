package namtran.mygift.food;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import namtran.mygift.BaseSlide;
import namtran.mygift.food.constants.Anim;
import namtran.mygift.food.viewmodel.SlideshowViewModel;

/*
 * Copyright (C) 2016 JuL <jul.funtory@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class SlideshowImageView extends RelativeLayout {

    private SlideshowViewModel slideshowViewModel = new SlideshowViewModel();
    private List<AnimatorSet> animatorSets = new ArrayList<>();

    @SuppressLint("HandlerLeak")
    private Handler loopHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            anim(Math.abs(msg.arg1 - 1));
        }
    };
    private long duration = Anim.DURATION;
    private BaseSlide baseSlide = null;

    private OnSlidesListener onSlidesListener;

    public SlideshowImageView(Context context) {
        super(context);
        init();
    }

    public SlideshowImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlideshowImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        //2개의 이미지뷰로 번갈아 가며..
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        addView(imageView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        addView(imageView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public void setOnSlidesListener(OnSlidesListener onSlidesListener) {
        this.onSlidesListener = onSlidesListener;
    }

    private ImageView getImageView(int index) {
        return (ImageView) getChildAt(index);
    }

    /**
     * Replace with new images.
     *
     * @param resIds
     */
    public void setImages(List<? extends BaseSlide> resIds) {
        for (int i = 0; i < getChildCount(); i++) {
            getImageView(i).setTag(null);
        }

        slideshowViewModel.setImages(resIds);
        tryAnim(resIds);
    }

    /**
     * Add a new image.
     *
     * @param resIds
     */
    public void addImages(List<? extends BaseSlide> resIds) {
        slideshowViewModel.addImages(resIds);
        tryAnim(resIds);
    }

    private void tryAnim(List<? extends BaseSlide> resIds) {
        if (slideshowViewModel.getImageCount() > 1) { //Animation from two or more
            if (!slideshowViewModel.isAnimate()) {     //If you are not animated
                final int currentChildIndex = slideshowViewModel.getCurrentChildIndex();
                if (currentChildIndex > -1) {
                    gone(currentChildIndex);
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            anim(Math.abs(currentChildIndex - 1));
                        }
                    }, Anim.DURATION/4);
                } else {
                    post(new Runnable() {
                        @Override
                        public void run() {
                            anim(0);
                        }
                    });
                }
            } else {
                //If it has already been animated, it will work.
            }
        } else { //If it is 1, just set it
            getImageView(0).setImageResource(resIds.get(0).getImage());
            getImageView(0).setTag(0);

            slideshowViewModel.initAnimConfig();
        }
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    private void gone(int targetChildIndex) {
        ImageView target = getImageView(targetChildIndex);

        ObjectAnimator goneAlpha = ObjectAnimator.ofFloat(target, "alpha", 1.0f, 0.0f);
        goneAlpha.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animation.setTarget(null);
                animation.removeListener(this);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        goneAlpha.setDuration(Anim.DURATION);

        goneAlpha.start();
    }

    private List<Integer> getCurrentIndex() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < getChildCount(); i++) {
            result.add(getChildAt(i).getTag() == null ? -1 : (Integer) getChildAt(i).getTag());
        }

        return result;
    }

    private void anim(final int targetChildIndex) {
        if (animatorSets == null) {
            return;
        }

        slideshowViewModel.updateAnimConfig(this, targetChildIndex);

        final ImageView target = getImageView(targetChildIndex);
        target.setScaleX(Anim.SCALE);
        target.setScaleY(Anim.SCALE);

        int imageIndex = slideshowViewModel.getImageIndex(getCurrentIndex());

        if (imageIndex > -1) {
            target.setTag(imageIndex);
            target.setImageBitmap(null);

            baseSlide = slideshowViewModel.getImage(imageIndex);
            target.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(), baseSlide.getImage()));
        }else{
            baseSlide = null;
            if (onSlidesListener != null){
                onSlidesListener.onSlideCancel();
            }
            return;
        }


        float rangeX = slideshowViewModel.getRangeX();
        ObjectAnimator transX = ObjectAnimator.ofFloat(target, "translationX", rangeX, -rangeX);
//        transX.setRepeatMode(ObjectAnimator.REVERSE);
//        transX.setRepeatCount(ObjectAnimator.INFINITE);
        transX.setDuration(Anim.DURATION);

        float rangeY = slideshowViewModel.getRangeY();
        ObjectAnimator transY = ObjectAnimator.ofFloat(target, "translationY", rangeY, -rangeY);
//        transY.setRepeatMode(ObjectAnimator.REVERSE);
//        transY.setRepeatCount(ObjectAnimator.INFINITE);
        transY.setDuration(Anim.DURATION);

        ObjectAnimator alpha = ObjectAnimator.ofFloat(target, "alpha", 0.0f, 1.0f);
        alpha.setDuration(Anim.DURATION / 2);

        ObjectAnimator alphaAfter = ObjectAnimator.ofFloat(target, "alpha", 1.0f, 0.0f);
        alphaAfter.setDuration(Anim.DURATION / 2);
        alphaAfter.setStartDelay(Anim.DURATION / 2);


        final AnimatorSet animSet = new AnimatorSet();
        animSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animSet.playTogether(transX, transY, alpha/*, alphaAfter*/);
        animSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (onSlidesListener != null)
                    onSlidesListener.onSlidesListener(baseSlide);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (onSlidesListener != null){
                    onSlidesListener.onSlideComplete();
                }
                animSet.setTarget(null);
                animSet.removeListener(this);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        animatorSets.add(animSet);
        animSet.start();

        //Infinite repeat
        Message message = new Message();
        message.what = 1;
        message.arg1 = targetChildIndex;
        loopHandler.sendMessageDelayed(message, duration);
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        loopHandler.removeMessages(1);

        for (AnimatorSet animatorSet : animatorSets) {
            animatorSet.end();
        }

        animatorSets.clear();
        animatorSets = null;
    }

    public interface OnSlidesListener {
        void onSlidesListener(BaseSlide baseSlide);
        void onSlideComplete();
        void onSlideCancel();
    }
}
