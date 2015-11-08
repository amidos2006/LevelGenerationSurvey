
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RunnerFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	public JLabel tutorialLabel;
	public JButton startButton;
	
	public RunnerFrame(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocation(300, 100);
	}
	
	@Override
	protected void frameInit() {
		super.frameInit();

		startButton = new JButton("Start Tutorial");
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Runner.mouseClick = true;
			}
		});
		
		tutorialLabel = new JLabel();
		tutorialLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel pane = (JPanel) getContentPane();
		pane.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		pane.setAlignmentY(JPanel.CENTER_ALIGNMENT);
	    GroupLayout gl = new GroupLayout(pane);
	    pane.setLayout(gl);
	       
	    pane.setToolTipText("Content pane");
	    gl.setAutoCreateContainerGaps(true);
	    gl.setAutoCreateGaps(true);
	        
	    gl.setHorizontalGroup(gl.createParallelGroup(Alignment.CENTER)
	    		.addComponent(tutorialLabel)
	    		.addComponent(startButton)
	            .addGap(300)
	    );

	    gl.setVerticalGroup(gl.createSequentialGroup()
	    		.addComponent(tutorialLabel)
	    		.addGap(20)
	            .addComponent(startButton)
	            .addGap(25)
	    );
	        
	    pack(); 
	}
}
