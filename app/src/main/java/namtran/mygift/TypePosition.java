package namtran.mygift;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static namtran.mygift.TypePosition.AROUND_WORLD;
import static namtran.mygift.TypePosition.BACKGROUND_FIREWORK;
import static namtran.mygift.TypePosition.BACKGROUND_RAIN;
import static namtran.mygift.TypePosition.FIRST;
import static namtran.mygift.TypePosition.IMAGE_FIREWORK;
import static namtran.mygift.TypePosition.PLANES_AROUND_WORLD;
import static namtran.mygift.TypePosition.SHOW_SLIDER;
import static namtran.mygift.TypePosition.SLIDER;
import static namtran.mygift.TypePosition.INTRO;
import static namtran.mygift.TypePosition.TEXT_AROUND_WORLD;
import static namtran.mygift.TypePosition.TEXT_END;
import static namtran.mygift.TypePosition.TEXT_FIREWORK;

@IntDef({FIRST,INTRO,SLIDER,BACKGROUND_RAIN,SHOW_SLIDER,BACKGROUND_FIREWORK,TEXT_FIREWORK,IMAGE_FIREWORK,AROUND_WORLD,TEXT_AROUND_WORLD,PLANES_AROUND_WORLD,TEXT_END})
@Retention(RetentionPolicy.SOURCE)
public @interface TypePosition {
    int FIRST = 0;
    int INTRO = 1;
    int SLIDER = 2;
    int SHOW_SLIDER = 3;
    int BACKGROUND_RAIN = 4;
    int BACKGROUND_FIREWORK = 5;
    int TEXT_FIREWORK = 6;
    int IMAGE_FIREWORK = 7;
    int AROUND_WORLD = 8;
    int TEXT_AROUND_WORLD = 9;
    int PLANES_AROUND_WORLD = 10;
    int TEXT_END = 11;
}
