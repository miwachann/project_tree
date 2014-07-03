package mvc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * モデル。
 */
public class Model extends Object
{
	/**
	 * 依存物（Observerデザインパターンの観測者）：Viewのインスタンスたちを束縛する。
	 * 良好（2010年7月25日）
	 */
	protected ArrayList<View> dependents;

	/**
	 * 内容物として画像を束縛する。
	 * 良好（2010年7月25日）
	 */
	private BufferedImage picture;

	/**
	 * インスタンスを生成して初期化して応答する。
	 * 良好（2010年7月25日）
	 */
	public Model()
	{
		super();
		this.initialize();
	}

	/**
	 * 指定されたビューを依存物に設定する。
	 * 良好（2010年7月25日）
	 */
	public void addDependent(View aView)
	{
		dependents.add(aView);
		return;
	}
	
	/**
	 * モデルの内部状態が変化していたので、自分の依存物へupdateのメッセージを送信する。
	 * 良好（2010年7月25日）
	 */
	public void changed()
	{
		Iterator anIterator = dependents.iterator();
		while (anIterator.hasNext())
		{
			View aView = (View)anIterator.next();
			aView.update();
		}
		return;
	}
	
	/**
	 * 初期化する。
	 * 良好（2010年7月25日）
	 */
	private void initialize()
	{
		dependents = new ArrayList<View>();
		picture = null;
		return;
	}
		
	/**
	 * 何もしない。
	 * 良好（2010年7月25日）
	 */
	public void perform()
	{
		return;
	}
	
	/**
	 * 画像（モデルの内容物）を応答する。
	 * 良好（2010年7月25日）
	 */
	public BufferedImage picture()
	{
		return picture;
	}
		
	/**
	 * 画像（モデルの内容物）を設定する。
	 * 良好（2010年7月25日）
	 */
	public void picture(BufferedImage anImage)
	{
		picture = anImage;
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
		aBuffer.append("[picture=");
		aBuffer.append(picture);
		aBuffer.append("]");
		return aBuffer.toString();
	}
}
