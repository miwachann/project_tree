package mvc;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.event.MouseInputAdapter;

/**
 * コントローラ。
 */
public class Controller extends MouseInputAdapter implements MouseWheelListener
{
	/**
	 * 情報を握っているModelのインスタンスを束縛する。
	 * 良好（2010年7月25日）
	 */
	protected Model model;
	
	/**
	 * 表示を司るViewのインスタンスを束縛する。
	 * 良好（2010年7月25日）
	 */
	protected View view;
	
	/**
	 * 以前にマウスのボタンが押下された場所をPointのインスタンスとして束縛する。
	 * 良好（2010年7月25日）
	 */
	private Point previous;
	
	/**
	 * 現在にマウスのボタンが押下された場所をPointのインスタンスとして束縛する。
	 * 良好（2010年7月25日）
	 */
	private Point current;

	/**
	 * インスタンスを生成して応答する。
	 * すべてのインスタンス変数（model, view, previous, current）をnull化する。
	 * 良好（2010年7月25日）
	 */
	public Controller()
	{
		super();
		model = null;
		view = null;
		previous = null;
		current = null;
	}

	/**
	 * 指定されたマウスイベントからマウスカーサの位置を獲得して、モデル座標系でのクリック位置を割り出して標準出力に出力する。
	 * 良好（2010年7月25日）
	 */
	public void mouseClicked(MouseEvent aMouseEvent)
	{
		Point aPoint = aMouseEvent.getPoint();
		aPoint.translate(view.scrollAmount().x, view.scrollAmount().y);
		System.out.println(aPoint);
		return;
	}

	/**
	 * マウスカーサの形状を移動の形に変化させ、指定されたマウスイベントからマウスカーサの位置を獲得して、
	 * インスタンス変数currentに設定すると共に、以前のマウスカーサの位置からの差分を計算する。
	 * そして、その差分だけビューに対してスクロールを依頼し、その後にビューの再描画を依頼する。
	 * 最後にインスタンス変数previousをインスタンス変数currentに更新する。
	 * 良好（2010年7月25日）
	 */
	public void mouseDragged(MouseEvent aMouseEvent)
	{
		Cursor aCursor = Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
		Component aComponent = (Component)aMouseEvent.getSource();
		aComponent.setCursor(aCursor);
		current = aMouseEvent.getPoint();
		int x = current.x - previous.x;
		int y = current.y - previous.y;
		Point point = new Point(x, y);
		view.scrollBy(point);
		view.repaint();
		previous = current;
		return;
	}

	/**
	 * 何もしない。
	 * 良好（2010年7月25日）
	 */
	public void mouseEntered(MouseEvent aMouseEvent)
	{
		return;
	}

	/**
	 * 何もしない。
	 * 良好（2010年7月25日）
	 */
	public void mouseExited(MouseEvent aMouseEvent)
	{
		return;
	}

	/**
	 * 何もしない。
	 * 良好（2010年7月25日）
	 */
	public void mouseMoved(MouseEvent aMouseEvent)
	{
		return;
	}

	/**
	 * マウスカーサの形状を十字に変化させ、指定されたマウスイベントからマウスカーサの位置を獲得して、
	 * インスタンス変数currentに設定する共にインスタンス変数previousをインスタンス変数currentに更新する。
	 * 良好（2010年7月25日）
	 */
	public void mousePressed(MouseEvent aMouseEvent)
	{
		Cursor aCursor = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
		Component aComponent = (Component)aMouseEvent.getSource();
		aComponent.setCursor(aCursor);
		current = aMouseEvent.getPoint();
		previous = current;
		return;
	}

	/**
	 * マウスカーサの形状をデフォルトに戻し、指定されたマウスイベントからマウスカーサの位置を獲得して、
	 * インスタンス変数currentに設定する共にインスタンス変数previousをインスタンス変数currentに更新する。
	 * 良好（2010年7月25日）
	 */
	public void mouseReleased(MouseEvent aMouseEvent)
	{
		Cursor aCursor = Cursor.getDefaultCursor();
		Component aComponent = (Component)aMouseEvent.getSource();
		aComponent.setCursor(aCursor);
		current = aMouseEvent.getPoint();
		previous = current;
		return;
	}

	/**
	 * 何もしない。
	 * 良好（2010年7月25日）
	 */
	public void mouseWheelMoved(MouseWheelEvent aMouseWheelEvent)
	{
		return;
	}

	/**
	 * 指定されたモデルをインスタンス変数modelに設定する。
	 * 良好（2010年7月25日）
	 */
	public void setModel(Model aModel)
	{
		model = aModel;
		return;
	}

	/**
	 * 指定されたビューをインスタンス変数viewに設定し、ビューのマウスのリスナおよびモーションリスナそしてホイールリスナをこのコントローラにする。
	 * 良好（2010年7月25日）
	 */
	public void setView(View aView)
	{
		view = aView;
		view.addMouseListener(this);
		view.addMouseMotionListener(this);
		view.addMouseWheelListener(this);
		return;
	}
	
	/**
	 * このインスタンスを文字列にして応答する。
	 * 良好（2010年7月25日）
	 */
	public String toString()
	{
		StringBuffer aBuffer = new StringBuffer();
		Class aClass = this.getClass();
		aBuffer.append(aClass.getName());
		aBuffer.append("[model=");
		aBuffer.append(model);
		aBuffer.append(",view=");
		aBuffer.append(view);
		aBuffer.append("]");
		return aBuffer.toString();
	}
}
