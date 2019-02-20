package com.example.hw2;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * <!-- class MyCanvas -->
 *
 * This class contains the code needed to draw
 * all of the modifiable elements used
 *
 * @author Geryl Vinoya
 * @version Spring 2019
 *
 */
public class MyCanvas extends SurfaceView {

    protected CustomCircle smallCircle;
    protected CustomCircle mediumCircle;
    protected CustomCircle largeCircle;
    protected CustomRect smallRect;
    protected CustomRect mediumRect;
    protected CustomRect largeRect;


    /**constrctors*/
    public MyCanvas(Context context){
        super(context);
        init();
    }

    public MyCanvas(Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }

    public MyCanvas(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * <!-- method init() -->
     *
     * This method initiates all of the elements
     * that are used by creating the elements,
     * setting color and location
     *
     * @author Geryl Vinoya
     * @version Spring 2019
     *
     */

    private void init(){

        this.setZOrderOnTop(true);
        setWillNotDraw(false);

        smallCircle = new CustomCircle("Small Circle",
                0xffff0000, 100, 100, 50);

        mediumCircle = new CustomCircle("Medium Circle",
                0xff00ff00, 300, 150,100);

        largeCircle = new CustomCircle("Large Circle",
                0xff0000ff,650, 250, 200);

        smallRect = new CustomRect("Small Rectangle",
                0xffffff00, 50, 600, 150, 700);

        mediumRect = new CustomRect("Medium Rectangle",
                0xff00ffff, 200, 600, 400, 800);

        largeRect = new CustomRect("Large Rectangle",
                0xffff00ff, 450, 600, 850, 1000);

    }



    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        smallCircle.drawMe(canvas);
        mediumCircle.drawMe(canvas);
        largeCircle.drawMe(canvas);
        smallRect.drawMe(canvas);
        mediumRect.drawMe(canvas);
        largeRect.drawMe(canvas);

    }
}

/**
 * External Resource
 * Date: 20 Feb 2019
 * Problem: View was not showing up
 * Resource:
 *      https://stackoverflow.com/questions/18762368/how-to-put-a-layout-on-top-of-layout-setzorderontop
 * Solution: setZOrderOnTop method showed the SurfaceView drawings
 */
