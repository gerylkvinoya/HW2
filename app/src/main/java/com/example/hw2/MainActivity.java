package com.example.hw2;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * <!-- class MainActivity -->
 *
 * This is the MainActivity class.
 *
 * @author Geryl Vinoya
 * @version Spring 2019
 *
 */

public class MainActivity extends AppCompatActivity {

    /**instance variables to use throughout the code*/
    private TextView redNumber;
    private TextView greenNumber;
    private TextView blueNumber;
    private TextView shapeName;
    private SeekBar redSeekBar;
    private SeekBar greenSeekBar;
    private SeekBar blueSeekBar;
    private MyCanvas theCanvas;
    private CustomElement mostRecent;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**initialize the numbers and the seekbars*/
        redNumber = findViewById(R.id.redNum);
        greenNumber = findViewById(R.id.greenNum);
        blueNumber = findViewById(R.id.blueNum);
        shapeName = findViewById(R.id.shapeName);

        redSeekBar = initSeekBar(R.id.redSeekBar, redNumber);
        greenSeekBar = initSeekBar(R.id.greenSeekBar, greenNumber);
        blueSeekBar = initSeekBar(R.id.blueSeekBar, blueNumber);

        /**creates an instance of the MyCanvas class
         * to be used in the SurfaceView
         */
        theCanvas = new MyCanvas(this);
        theCanvas.setOnTouchListener(new MyOnTouchListener());
        LinearLayout surface = findViewById(R.id.theView);
        surface.addView(theCanvas);

    }

    /**
     * <!-- method initSeekBar -->
     *
     * This method initiates all the seekbars.
     * The Parameters are the seekbar IDs and the textView associated with it.
     * The seekbar listener connects them together.
     *
     * @author Geryl Vinoya
     * @version Spring 2019
     *
     */

    public SeekBar initSeekBar(int seekBarId, final TextView textView) {

        /**set the seekbar with all the same settings (progress = 0, max = 255)*/
        SeekBar seekBar = findViewById(seekBarId);
        seekBar.setMax(255);
        seekBar.setProgress(0);

        /** create a new OnSeekBarChangeListener object and override the methods*/
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText("" + progress);

                /**checks if a shape has been previously selected*/
                if(mostRecent != null) {

                    /**checks which color the seekbar is associated with
                     * and adjusts the color of the selected shape
                     */
                    if (seekBar == redSeekBar) {
                        mostRecent.setColor(Color.argb(255,
                                progress,
                                Color.green(mostRecent.getColor()),
                                Color.blue(mostRecent.getColor())));
                        theCanvas.invalidate();
                    }

                    else if (seekBar == greenSeekBar) {
                        mostRecent.setColor(Color.argb(255,
                                Color.red(mostRecent.getColor()),
                                progress,
                                Color.blue(mostRecent.getColor())));
                        theCanvas.invalidate();
                    }

                    else if (seekBar == blueSeekBar) {
                        mostRecent.setColor(Color.argb(255,
                                Color.red(mostRecent.getColor()),
                                Color.green(mostRecent.getColor()),
                                progress));
                        theCanvas.invalidate();
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //not used
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //not used
            }

        });



        return seekBar;
    }

    /**
     * <!-- class MyOnTouchListener -->
     *
     * This class overrides the onTouch method
     * in View.OnTouchListener
     *
     * @author Geryl Vinoya
     * @version Spring 2019
     *
     */

    public class MyOnTouchListener implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int xTouch = (int)event.getX();
            int yTouch = (int)event.getY();

            if(theCanvas.smallCircle.containsPoint(xTouch, yTouch)){
                touchElement(theCanvas.smallCircle);
            }
            else if(theCanvas.mediumCircle.containsPoint(xTouch, yTouch)){
                touchElement(theCanvas.mediumCircle);
            }
            else if(theCanvas.largeCircle.containsPoint(xTouch, yTouch)){
                touchElement(theCanvas.largeCircle);
            }
            else if(theCanvas.smallRect.containsPoint(xTouch, yTouch)){
                touchElement(theCanvas.smallRect);
            }
            else if(theCanvas.mediumRect.containsPoint(xTouch, yTouch)){
                touchElement(theCanvas.mediumRect);
            }
            else if(theCanvas.largeRect.containsPoint(xTouch, yTouch)){
                touchElement(theCanvas.largeRect);

            }

            theCanvas.invalidate();
            return true;
        }

        /**
         * <!-- method touchElement -->
         *
         * This method performs whatever needed when an element is touched.
         * Updates as most recently touched, sets the TextView to name,
         * and sets the seekbars accordingly
         *
         * @author Geryl Vinoya
         * @version Spring 2019
         *
         */

        private void touchElement(CustomElement element){
            mostRecent = element;
            shapeName.setText(element.getName());
            redSeekBar.setProgress(Color.red(element.getColor()));
            greenSeekBar.setProgress(Color.green(element.getColor()));
            blueSeekBar.setProgress(Color.blue(element.getColor()));
        }

    }

}

/**
 * External Citation
 * Date: 20 Feb 2019
 * Problem: Did not know how to get SurfaceView to show up
 * Resource:
 *      https://stackoverflow.com/questions/10185624/surfaceview-in-layout
 *  Solution: I used an example in this post as a guideline
 */


