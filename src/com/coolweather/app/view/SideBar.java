package com.coolweather.app.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**ListView右边的字母索引view
 * @author Administrator
 * 显示字母,touch后关联字母
 */
public class SideBar extends View {

	private TextView mTextDialog;
	private int choose = -1;
//	private String[] letterArr = new String[]{"a","b","c","d","e","f","g","h","i",
//			"j","k","l","m","n","o","p","q","r","s","t",
//			"u","v","w","x","y","z",};
	private String[] letterArr = new String[]{"A","B","C","D","E","F","G","H","I",
			"J","K","L","M","N","O","P","Q","R","S","T",
			"U","V","W","X","Y","Z",};
	private Paint paint = new Paint();
	private OnTouchintLetterChangedListener onTouchintLetterChangedListener;
	
	public SideBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SideBar(Context context) {
		super(context);
	}

	public SideBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}
	
	private void init(){
		setBackgroundColor(Color.parseColor("#F0F0F0"));
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int viewHeight = getHeight();
		int viewWidth = getWidth();
		//每一个字母占用的高度
		int singleLetterHeight = viewHeight/letterArr.length;
		
		for(int i = 0;i<letterArr.length;i++){
			String letterOnDrawing = letterArr[i];
			paint.setColor(Color.parseColor("#606060"));
			paint.setTextSize(20);
			paint.setTypeface(Typeface.DEFAULT_BOLD);
			paint.setAntiAlias(true);
			//选中的状态
			if(i == choose){
				paint.setColor(Color.parseColor("#4F41FD"));
				paint.setFakeBoldText(true);
			}
			
			//开始画的X坐标:中间-字符串宽度的一半
			float xPos = viewWidth/2 - paint.measureText(letterOnDrawing)/2;
			float yPos = singleLetterHeight*i + singleLetterHeight/2;
			canvas.drawText(letterOnDrawing, xPos, yPos, paint);
			paint.reset();//重置画笔
		}
	}
	
	/**为sidebar设置:按下字母弹出textViewDialog显示
	 * @param mTextDialog
	 */
	public void setTextView(TextView mTextDialog){
		this.mTextDialog = mTextDialog;
	}
	
	/**为sidebar设置:字母点击监听
	 * @param onTouchintLetterChangedListener
	 */
	public void setOnTouchintLetterChangedListener(OnTouchintLetterChangedListener onTouchintLetterChangedListener){
		this.onTouchintLetterChangedListener = onTouchintLetterChangedListener;
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		OnTouchintLetterChangedListener listener = onTouchintLetterChangedListener;
		int action = event.getAction();
		float yCoordinate = event.getY();//点击位置的y坐标
		int oldChoose = choose;
		//判断点击的是哪个字母:高度比例*letterArr比例
		int count = (int) (yCoordinate/getHeight() * letterArr.length);
		switch (action) {
		case MotionEvent.ACTION_UP://抬起来
			setBackgroundColor(Color.parseColor("#F0F0F0"));
			choose = -1;
			invalidate();
			if(mTextDialog != null){
				mTextDialog.setVisibility(View.GONE);
			}
			break;

		default://.ACTION_CANCEL/.ACTION_SCROLL/.ACTION_DOWN.....总之就是按下去了
			setBackgroundColor(Color.GRAY);
			if(oldChoose != count){
				if(count > 0 && count < letterArr.length){
					if(listener != null){
						listener.onTouchingLetterChanged(letterArr[count]);
					}
					if(mTextDialog != null){
						mTextDialog.setText(letterArr[count]);
						mTextDialog.setVisibility(View.VISIBLE);
					}
					choose = count;
					invalidate();//(重新)绘制该视图
				}
			}
			break;
		}
		return true;
	}
	
}
