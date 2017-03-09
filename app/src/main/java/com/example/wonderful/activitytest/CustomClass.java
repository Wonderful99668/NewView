package com.example.wonderful.activitytest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class CustomClass extends View{

    public CustomClass(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomClass(Context context) {
        super(context);
    }

    public CustomClass(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomClass(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //重构ondraw函数
    int textNum=3;  //用来储存多边形的边数
    int select=0;   //用来控制自定义模式
    int color=0;
    FirstActivity activity=new FirstActivity();

    public void SetNum(int textNum){
        this.textNum=textNum;
    }

    public void SetSlect(int select){this.select=select;}

    public void SetColor(int color){this.color=color;}

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);//消除锯齿
        if(select==1){

        }

        if (select==2){
        drawStar(canvas,paint,color,getHeight()/12,textNum,false);
        canvas.translate(getHeight()/6,0);
        drawStar(canvas,paint,color,getHeight()/12,textNum,true);
        canvas.translate(-getHeight()/6,0);
        canvas.translate(0,getHeight()/2);
        }

    }

    //自定义函数用于绘制多边形
    private void drawStar(Canvas canvas,Paint paint,@ColorInt int color,float radius,int count,boolean isStar){
        canvas.translate(radius,radius);
        if((!isStar)&&count<3){
            Toast.makeText(getContext(),"当前输入错误，输入的边数必须大于等于3",Toast.LENGTH_SHORT).show();//通过getContext()来获取当前对象的上下文
            canvas.translate(-radius,-radius);
            return;
    }
        if(isStar&&count<5){
            canvas.translate(-radius,-radius);
            return;
        }
        canvas.rotate(-90);
        if(paint==null){
            paint=new Paint();
            }else{
            paint.reset();
        }
        Path path=new Path();
        float inerRadius=count%2==0?
                (radius*(cos(360/count/2)-sin(360/count/2)*sin(90-360/count)/cos(90-360/count)))
                :(radius*sin(360/count/4)/sin(180-360/count/2-360/count/4));
        for(int i=0;i<count;i++){
            if(i==0){
                path.moveTo(radius*cos(360/count*i),radius*sin(360/count*i));
                }else{
                path.lineTo(radius*cos(360/count*i),radius*sin(360/count*i));
                }
            if(isStar){
            path.lineTo(inerRadius*cos(360/count*i+360/count/2),inerRadius*sin(360/count*i+360/count/2));
            }
        }
        path.close();
        paint.setColor(color);
        canvas.drawPath(path,paint);
        canvas.rotate(90);
        canvas.translate(-radius,-radius);
    }


    //自定义sin和cos函数，在drawstar中调用
    float sin(int num) {
        return (float) Math.sin(num * Math.PI / 180);
    }

    float cos(int num){
        return (float) Math.cos(num*Math.PI/180);
        }

}






