package namtran.mygift.htext.line;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import namtran.mygift.htext.base.AnimationListener;
import namtran.mygift.htext.base.HTextView;


/**
 * line effect view
 */

public class LineTextView extends HTextView {

    private LineText lineText;

    public LineTextView(Context context) {
        this(context, null);
    }

    public LineTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    @Override
    public void setAnimationListener(AnimationListener listener) {
        lineText.setAnimationListener(listener);
    }

    public void setLineColor(int color) {
        lineText.setLineColor(color);
    }

    public float getLineWidth() {
        return lineText.getLineWidth();
    }

    public void setLineWidth(float lineWidth) {
        lineText.setLineWidth(lineWidth);
    }

    public float getAnimationDuration() {
        return lineText.getAnimationDuration();
    }

    public void setAnimationDuration(float animationDuration) {
        lineText.setAnimationDuration(animationDuration);
    }

    @Override
    public void setProgress(float progress) {
        lineText.setProgress(progress);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        lineText = new LineText();
        lineText.init(this, attrs, defStyleAttr);
    }

    @Override
    public void animateText(CharSequence text) {
        lineText.animateText(text);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        lineText.onDraw(canvas);
    }

}
