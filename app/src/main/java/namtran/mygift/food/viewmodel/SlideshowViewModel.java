package namtran.mygift.food.viewmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import namtran.mygift.BaseSlide;
import namtran.mygift.food.SlideshowImageView;
import namtran.mygift.food.constants.Anim;

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
public class SlideshowViewModel {

    private List<BaseSlide> images = new ArrayList<>();

    private Random random;

    private float[] animRangeX = null;
    private float[] animRangeY = null;

    private boolean isAnimate = false;
    private int currentChildIndex = -1;
    private int position = -1;

    public SlideshowViewModel(){
        random = new Random(System.currentTimeMillis());
    }

    public void setImages(List<? extends BaseSlide> resIds){
        images.clear();
        addImages(resIds);
    }

    public void addImages(List<? extends BaseSlide> resIds) {
        for (int i = 0;i<resIds.size();i++){
            images.add(resIds.get(i));
        }
    }

    public int getImageCount(){
        return images.size();
    }

    /**
     * Get a random value outside the current child index.
     *
     * @return
     */
    public int getImageIndex() {
        position++;
        if (position < images.size())
            return position;
        return  -1;
    }

    public BaseSlide getImage(/*Context context, */int imageIndex){
//        return BitmapFactory.decodeResource(context.getResources(), images.get(imageIndex).getImage());
        return images.get(imageIndex);
    }

    /**
     * Maximum / minimum range setting for each axis.
     */
    private void initRange(int viewW, int viewH) {
        float x = ((viewW * Anim.SCALE) - viewW) / 2.0f;
        float y = ((viewH * Anim.SCALE) - viewH) / 2.0f;
        animRangeX = new float[]{x, -x};
        animRangeY = new float[]{y, -y};
    }

    public void updateAnimConfig(SlideshowImageView src, int targetChildIndex){
        currentChildIndex = targetChildIndex;

        isAnimate = true;
        if (animRangeX == null || animRangeY == null) {
            initRange(src.getMeasuredWidth(), src.getMeasuredHeight());
        }
    }

    public void initAnimConfig(){
        currentChildIndex = 0;

        isAnimate = false;

        animRangeX = null;
        animRangeY = null;
    }

    /**
     * Gets the x-axis movement value randomly.
     *
     * @return
     */
    public float getRangeX() {
        return animRangeX[random.nextInt(animRangeX.length)];
    }

    /**
     * Gets the value to move the y axis randomly.
     *
     * @return
     */
    public float getRangeY() {
        return animRangeY[random.nextInt(animRangeY.length)];
    }

    public boolean isAnimate(){
        return isAnimate;
    }

    public int getCurrentChildIndex(){
        return currentChildIndex;
    }
}
