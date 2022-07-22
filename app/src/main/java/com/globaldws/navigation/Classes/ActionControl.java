package com.globaldws.navigation.Classes;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.globaldws.navigation.Activity.MainActivity;
import com.globaldws.navigation.Helper.Coordinate;
import com.globaldws.navigation.Helper.Global;
import com.google.gson.Gson;
import com.globaldws.navigation.R;
import com.slamtec.slamware.action.MoveDirection;
import com.slamtec.slamware.robot.Location;
import com.slamtec.slamware.robot.MoveOption;
import com.slamtec.slamware.robot.Pose;
import com.slamtec.slamware.robot.Rotation;

import static android.content.ContentValues.TAG;

public class ActionControl extends LinearLayout implements View.OnTouchListener {

    LongClickButton button_forward;
    LongClickButton button_backward;
    LongClickButton button_turn_left;
    LongClickButton button_turn_right;
    Button button_stop, btnsendmsgrobot;

    //Button btnSaveRobotHome,btnSaveStartPoint,btnSaveThirdPoint,btnSaveFourthPoint;
    //Button btnGoToHome,btnGoToStart,btnGoToThird,btnGoToFourth;
    //String[] homepoint = new String[3], startpoint = new String[3], thirdpoint = new String[3], fourthpoint = new String[3];

    public ActionControl(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.action_control, this);

        button_forward = (LongClickButton) findViewById(R.id.button_forward);
        button_backward = (LongClickButton) findViewById(R.id.button_backward);
        button_turn_left = (LongClickButton) findViewById(R.id.button_turn_left);
        button_turn_right = (LongClickButton) findViewById(R.id.button_turn_right);
        button_stop = (Button) findViewById(R.id.button_stop);

        button_forward.setOnTouchListener(this);
        button_backward.setOnTouchListener(this);
        button_turn_left.setOnTouchListener(this);
        button_turn_right.setOnTouchListener(this);

        // go forward
        int delayTime = 300;
        button_forward.setLongClickRepeatListener(new LongClickButton.LongClickRepeatListener() {
            @Override
            public void repeatAction() {
                try {
                    MainActivity.moveAction = MainActivity.robotPlatform.moveBy(MoveDirection.FORWARD);
                    System.out.println("repeatAction===============");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, delayTime);

        // go backward
        button_backward.setLongClickRepeatListener(new LongClickButton.LongClickRepeatListener() {
            @Override
            public void repeatAction() {
                try {
                    MainActivity.moveAction = MainActivity.robotPlatform.moveBy(MoveDirection.BACKWARD);
                    System.out.println("repeatAction===============");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, delayTime);

        button_turn_left.setLongClickRepeatListener(new LongClickButton.LongClickRepeatListener() {
            @Override
            public void repeatAction() {
                try {
                    MainActivity.moveAction = MainActivity.robotPlatform.moveBy(MoveDirection.TURN_LEFT);

                    System.out.println("repeatAction===============");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, delayTime);

        button_turn_right.setLongClickRepeatListener(new LongClickButton.LongClickRepeatListener() {
            @Override
            public void repeatAction() {
                try {
                    MainActivity.moveAction = MainActivity.robotPlatform.moveBy(MoveDirection.TURN_RIGHT);
                    System.out.println("repeatAction===============");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, delayTime);

        button_stop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    if (!MainActivity.moveAction.isEmpty()) {
                        MainActivity.moveAction.cancel();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()) {

            // go forward
            case R.id.button_forward:
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    try {
                        MainActivity.moveAction = MainActivity.robotPlatform.moveBy(MoveDirection.FORWARD);
                        System.out.println("FORWARD===============");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    try {
                        if (!MainActivity.moveAction.isEmpty()) {
                            MainActivity.moveAction.cancel();
                        }
                        System.out.println("cancel===============");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;

            // go backward
            case R.id.button_backward:
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    try {
                        MainActivity.moveAction = MainActivity.robotPlatform.moveBy(MoveDirection.BACKWARD);
                        System.out.println("BACKWARD===============");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    try {
                        if (!MainActivity.moveAction.isEmpty()) {
                            MainActivity.moveAction.cancel();
                        }
                        System.out.println("cancel===============");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;

            // turn left
            case R.id.button_turn_left:
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    try {
                        MainActivity.moveAction = MainActivity.robotPlatform.moveBy(MoveDirection.TURN_LEFT);
                        System.out.println("TURN_LEFT===============");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    try {
                        if (!MainActivity.moveAction.isEmpty()) {
                            MainActivity.moveAction.cancel();
                        }
                        System.out.println("cancel===============");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;

            case R.id.button_turn_right:
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    try {
                        MainActivity.moveAction = MainActivity.robotPlatform.moveBy(MoveDirection.TURN_RIGHT);

                        System.out.println("TURN_RIGHT===============");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    try {
                        if (!MainActivity.moveAction.isEmpty()) {
                            MainActivity.moveAction.cancel();
                        }
                        System.out.println("cancel===============");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
        return false;
    }
}
