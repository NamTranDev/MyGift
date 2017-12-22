package namtran.mygift;

import java.util.List;

public class Food extends BaseSlide{

    public List<String> intro;

    public Food(int image, List<String> intro) {
        this.image = image;
        this.intro = intro;
    }

    public List<String> getIntro() {
        return intro;
    }

    public void setIntro(List<String> intro) {
        this.intro = intro;
    }
}
