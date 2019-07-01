package com.example.asus.mvpmodule.common.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.mvpmodule.R;


/**
 * title bar
 */
public class TitleBarView {

    private View titleBarRoot;
    private View barBack;
    private TextView barTitle;
    private View barRight;

    /**
     * 未设置点击事件，需要自己设置点击事件
     *
     * @param window
     */
    public TitleBarView(View window) {
        if(window!=null){
                titleBarRoot = window.findViewById(R.id.title_bar_layout);
            barBack = window.findViewById(R.id.title_bar_back);
            barTitle = window.findViewById(R.id.title_bar_title);
            barRight = window.findViewById(R.id.title_bar_right);
            setBarRightVisible(false);
            //设置默认的显示状态
            setDefVisible();
        }
    }

    /**
     * 设置点击事件
     *
     * @param activity
     */
    public TitleBarView(BaseMVPActivity activity){
        if(activity!=null){
            titleBarRoot = activity.findViewById(R.id.title_bar_layout);
            barBack = activity.findViewById(R.id.title_bar_back);
            barTitle = activity.findViewById(R.id.title_bar_title);
            barRight = activity.findViewById(R.id.title_bar_right);
            //设置监听
            setClickListener(activity);
            //设置默认的显示状态
            setDefVisible();
        }
    }

    //设置点击监听
    public void setClickListener(View.OnClickListener clickListener){
        if(this.barBack !=null){
            this.barBack.setOnClickListener(clickListener);
        }
        if(this.barTitle !=null){
            this.barTitle.setOnClickListener(clickListener);
        }
        if(this.barRight !=null){
            this.barRight.setOnClickListener(clickListener);
        }
    }

    //设置默认的显示状态
    private void setDefVisible(){
        if(this.barRight !=null){
            setBarRightVisible(false);
        }
    }

    //设置titlebar的title
    public void setBarTitle(String title){
        if(this.barTitle !=null){
            if(title!=null){
                barTitle.setText(title);
            }else{
                barTitle.setText("");
            }
        }
    }

    //设置titlebar右边按钮显示的文字
    public void setBarRightText(String rightText){
        if(barRight instanceof TextView){
            if(rightText!=null){
                ((TextView) barRight).setText(rightText);
                setBarRightVisible(true);
            }else{
                ((TextView) barRight).setText("");
                setBarRightVisible(false);
            }
        }
    }

    //设置titlebar右边按钮显示的图片
    public void setBarRightImage(int imgResId){
        if(barRight instanceof ImageView){
            if(imgResId>0){
                ((ImageView) barRight).setImageResource(imgResId);
                setBarRightVisible(true);
            }else{
                setBarRightVisible(false);
            }
        }
    }

    //设置右边按钮是否显示
    public void setBarRightVisible(boolean isVisible){
        if(barRight !=null){
            barRight.setVisibility(isVisible? View.VISIBLE:View.GONE);
        }
    }
    //设置右边按钮是否显示
    public void setBarBackVisible(boolean isVisible){
        if(barBack !=null){
            barBack.setVisibility(isVisible? View.VISIBLE:View.GONE);
        }
    }

    //title是否显示
    public void setBartextVisible(boolean isVisible){
        if(barTitle !=null){
            barTitle.setVisibility(isVisible? View.VISIBLE:View.GONE);
        }
    }

    //设置titleBar的背景颜色
    public void setTitleBarBgColor(int color){
        if(titleBarRoot!=null){
            titleBarRoot.setBackgroundColor(color);
        }
    }

    public void destory(){
        this.titleBarRoot = null;
        this.barBack = null;
        this.barTitle = null;
        this.barRight = null;
    }

    public boolean isClickLeft(View view){
        return (this.barBack !=null&&this.barBack ==view);
    }
    public boolean isClickTitle(View view){
        return (this.barTitle !=null&&this.barTitle ==view);
    }
    public boolean isClickRight(View view){
        return (this.barRight !=null&&this.barRight ==view);
    }
}
