package namtran.mygift;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import namtran.mygift.fragment.FireworkFragment;
import namtran.mygift.fragment.SliderImageFragment;
import namtran.mygift.fragment.TextFirstFragment;

import static namtran.mygift.TypePosition.BACKGROUND_FIREWORK;
import static namtran.mygift.TypePosition.BACKGROUND_RAIN;
import static namtran.mygift.TypePosition.FIRST;
import static namtran.mygift.TypePosition.IMAGE_FIREWORK;
import static namtran.mygift.TypePosition.INTRO;
import static namtran.mygift.TypePosition.SHOW_SLIDER;
import static namtran.mygift.TypePosition.SLIDER;
import static namtran.mygift.TypePosition.TEXT_FIREWORK;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mPlayer;
    private boolean isPause;

    public static @TypePosition
    int type = FIRST;

    private Handler handler = new Handler();
    private Handler handlerFirework = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Fragment fragment = getFragmentManager().findFragmentById(R.id.contain_main);
            switch (type) {
                case FIRST:
                    replaceFragment(TextFirstFragment.getInstance(), false);
                    type = INTRO;
                    break;
                case INTRO:
                    if (fragment != null && fragment instanceof TextFirstFragment) {
                        ((TextFirstFragment) fragment).setHandler();
                    }
                    break;
                case SLIDER:
                    replaceFragment(SliderImageFragment.getInstance(), true);
                    type = TypePosition.SHOW_SLIDER;
                    handler.postDelayed(runnable, 2000);
                    break;
                case SHOW_SLIDER:
                    if (fragment != null && fragment instanceof SliderImageFragment) {
                        ((SliderImageFragment) fragment).showSlider();
                    }
                    type = TypePosition.BACKGROUND_RAIN;
                    handler.postDelayed(runnable, 3000);
                    break;
                case BACKGROUND_RAIN:
                    if (fragment != null && fragment instanceof SliderImageFragment) {
                        ((SliderImageFragment) fragment).setBackgroundSnow();
                    }
                    type = TypePosition.BACKGROUND_FIREWORK;
                    handler.postDelayed(runnable, 3000);
                    break;
                case BACKGROUND_FIREWORK:
                    replaceFragment(FireworkFragment.getInstance(), true);
                    type = TypePosition.TEXT_FIREWORK;
                    break;
                case TEXT_FIREWORK:
                    if (fragment != null && fragment instanceof FireworkFragment) {
                        ((FireworkFragment) fragment).setHandler();
                        textFireWork();
                    }
                    break;
                case IMAGE_FIREWORK:
                    if (fragment != null && fragment instanceof FireworkFragment) {
                        ((FireworkFragment) fragment).showImage();
                    }
                    break;
            }
        }
    };

    private Runnable runnableFirework = new Runnable() {
        @Override
        public void run() {
            Fragment fragment = getFragmentManager().findFragmentById(R.id.contain_main);
            if (fragment != null && fragment instanceof FireworkFragment){
                ((FireworkFragment)fragment).showFirework();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.activity_main);

        handler.postDelayed(runnable, 1500);

        final AudioManager mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        assert mAudioManager != null;
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);

        mPlayer = MediaPlayer.create(this, R.raw.love_you_and_love_me);
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    public void textIntro() {
        handler.postDelayed(runnable, 1000);
    }

    public void slideImage() {
        playMedia();
        type = SLIDER;
        handler.postDelayed(runnable, 1500);
    }

    public void textFireWork() {
        handler.postDelayed(runnable, 3000);
        handlerFirework.postDelayed(runnableFirework,100);
    }

    public void showIvFirework() {
        type = IMAGE_FIREWORK;
        handler.postDelayed(runnable,3000);
    }

    public void replaceFragment(Fragment fragment, boolean isAnimation) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (isAnimation)
            transaction.setCustomAnimations(android.R.animator.fade_in,
                    android.R.animator.fade_out);
        transaction.replace(R.id.contain_main, fragment);
        transaction.commitAllowingStateLoss();
    }

    private void playMedia() {
        if (!mPlayer.isPlaying()) {
            mPlayer.start();
        }
    }

    private void pauseMedia() {
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
            isPause = true;
        }
    }

    private void resumeMedia() {
        if (!mPlayer.isPlaying() && isPause) {
            mPlayer.start();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        resumeMedia();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pauseMedia();
    }

    @Override
    public void onBackPressed() {
        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
