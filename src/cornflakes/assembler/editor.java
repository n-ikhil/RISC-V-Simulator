package assembler;
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class editor extends JFrame
	{

		/**
		 * Launch the application.
		 */
		public static void main(String[] args)
			{
				EventQueue.invokeLater(new Runnable()
					{
						public void run()
							{
								try
									{
										editor frame = new editor();
										frame.setVisible(true);
									} catch (Exception e)
									{
										e.printStackTrace();
									}
							}
					});
			}

		/**
		 * Create the frame.
		 */
		public editor()
			{
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(100, 100, 892, 430);
				setTitle("editor");

				JMenuBar menuBar = new JMenuBar();
				setJMenuBar(menuBar);

				JMenu mnFile = new JMenu("File");
				menuBar.add(mnFile);

				JMenu mnEdit = new JMenu("Edit");
				menuBar.add(mnEdit);

				JMenu mnBuild = new JMenu("Build");
				menuBar.add(mnBuild);

				JPanel panel = new JPanel();
				panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
				getContentPane().add(panel, BorderLayout.CENTER);

				JButton btnNewButton = new JButton("New button");
				btnNewButton.setBounds(new Rectangle(102, 600, 0, 0));
				btnNewButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
							{
							}
					});
				btnNewButton.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
				panel.add(btnNewButton);
				// .ABORT.setResizable("false");

			}

	}
