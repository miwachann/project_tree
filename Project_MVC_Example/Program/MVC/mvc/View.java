package mvc;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import java.awt.Color;

/**
 * ビュー。
 */
public class View extends JPanel
{
	/**
	 * 情報を握っているModelのインスタンスを束縛する。
	 * 束縛されるModelのインスタンスはpicture()というメッセージに応答できなければならない。
	 * 良好（2010年7月25日）
	 */
	protected Model model;

	/**
	 * 制御を司るControllerのインスタンスを束縛する。
	 * 良好（2010年7月25日）
	 */
	protected Controller controller;
	
	/**
	 * スクロール量としてPointのインスタンスを束縛する。
	 * 良好（2010年7月25日）
	 */
	private Point offset;

	/**
	 * インスタンスを生成して応答する。
	 * 指定されたモデルの依存物となり、コントローラを作り、モデルとビューを設定し、スクロール量を(0, 0)に設定する。
	 * 良好（2010年7月25日）
	 */
	public View(Model aModel)
	{
		super();
		model = aModel;
		model.addDependent(this);
		controller = new Controller();
		controller.setModel(model);
		controller.setView(this);
		offset = new Point(0, 0);
	}

	/**
	 * インスタンスを生成して応答する。
	 * 指定されたモデルの依存物となり、指定されたコントローラにモデルとビューを設定し、スクロール量を(0, 0)に設定する。
	 * 良好（2010年7月25日）
	 */
	public View(Model aModel, Controller aController)
	{
		super();
		model = aModel;
		model.addDependent(this);
		controller = aController;
		controller.setModel(model);
		controller.setView(this);
		offset = new Point(0, 0);
	}
	
	/**
	 * 指定されたグラフィクスに背景色（灰色）でビュー全体を塗り、その後にモデルの内容物を描画する。
	 * それはスクロール量（offset）を考慮してモデル画像（picture）をペイン（パネル）内に描画することである。
	 * 良好（2010年7月25日）
	 */
	public void paintComponent(Graphics aGraphics)
	{
		int width = this.getWidth();
		int height = this.getHeight();
		aGraphics.setColor(Color.gray);
		aGraphics.fillRect(0, 0, width, height);
		if (model == null) { return; }
		BufferedImage picture = model.picture();
		if (picture == null) { return; }
		aGraphics.drawImage(picture, offset.x, offset.y, null);
		return;
	}

	/**
	 * スクロール量（offsetの逆向きの大きさ）を応答する。
	 * 良好（2010年7月25日）
	 */
	public Point scrollAmount()
	{
		int x = 0 - offset.x;
		int y = 0 - offset.y;
		return (new Point(x, y));
	}
	
	/**
	 * スクロール量を指定された座標分だけ相対スクロールする。
	 * 良好（2010年7月25日）
	 */
	public void scrollBy(Point aPoint)
	{
		int x = offset.x + aPoint.x;
		int y = offset.y + aPoint.y;
		this.scrollTo(new Point(x, y));
		return;
	}

	/**
	 * スクロール量を指定された座標に設定（絶対スクロール）する。
	 * 良好（2010年7月25日）
	 */
	public void scrollTo(Point aPoint)
	{
		offset = aPoint;
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
		aBuffer.append(",offset=");
		aBuffer.append(offset);
		aBuffer.append("]");
		return aBuffer.toString();
	}
	
	/**
	 * ビューの全領域を再描画する。
	 * 良好（2010年7月25日）
	 */
	public void update()
	{
		this.repaint(0, 0, this.getWidth(), this.getHeight());
		return;
	}
}
