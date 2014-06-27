package mvc;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 * 例題プログラム。
 */
public class Example extends Object
{
	/**
	 * 画面をキャプチャして画像化し、ビューとコントローラの3つのペア（MVC-1, MVC-2, MVC-3のウィンドウたち）から1つのモデルを観測している状態を作り出す。
	 * その後、モデルの内容物を先ほどキャプチャした画像にして、自分が変化したと騒いだ瞬間、MVC-1, MVC-2, MVC-3のすべてのウィンドウが更新される。
	 * そして、モデルの内容物をnull化して、自分が変化したと騒ぎ、すべてのウィンドウが空に更新される。
	 * この過程を何回か繰り返すことで、MVC: Model-View-Controller（Observerデザインパターン）がきちんと動いているかを確かめる例題プログラムである。
	 * バグ（2010年7月25日）
	 * 良好（2010年7月25日）
	 */
	public static void main(String[] arguments)
	{
		Dimension aDimension = Toolkit.getDefaultToolkit().getScreenSize();
		Robot aRobot = null;
		try
		{
			aRobot = new Robot();
		}
		catch (Exception anException)
		{
			System.err.println(anException);
			throw new RuntimeException(anException.toString());
		}
		BufferedImage anImage = aRobot.createScreenCapture(new Rectangle(aDimension));
		
		Model aModel = new Model();
		
		for (int index = 0; index < 3; index++)
		{
			View aView;
			JFrame aWindow;

			aView = new View(aModel);
			aWindow = new JFrame("MVC-" + Integer.toString(index + 1));
			aWindow.getContentPane().add(aView);
			aWindow.setMinimumSize(new Dimension(400, 300));
			aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			aWindow.setSize(800, 600);
			aWindow.setLocation((200 + (index * 80)), (100 + (index * 60)));
			aWindow.setVisible(true);
		}
		
		for (int index = 0; index < 11; index++)
		{
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException anException)
			{
				System.err.println(anException);
				throw new RuntimeException(anException.toString());
			}
			if (index % 2 == 0)
			{
				aModel.picture(anImage);
			}
			else
			{
				aModel.picture(null);
			}
			aModel.changed();
		}
		
		return;
	}
}
