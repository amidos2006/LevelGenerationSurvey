import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout.Alignment;

public class SubmissionFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JLabel question;
	private JRadioButton neither;
	private JRadioButton first;
	private JRadioButton second;
	private JRadioButton equal;
	private JButton submitButton;
	
	public SubmissionFrame(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Poll Question");
		this.setLocation(300, 100);
	}
	
	@Override
	protected void frameInit() {
		super.frameInit();
		
		question = new JLabel("Which level do you prefer?");
		
		neither = new JRadioButton("Neither any of them");
		neither.setSelected(true);
		first = new JRadioButton("First level");
		second = new JRadioButton("Second level");
		equal = new JRadioButton("Both are equal");
		
		ButtonGroup group = new ButtonGroup();
		group.add(neither);
		group.add(first);
		group.add(second);
		group.add(equal);
		
		submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Runner.mouseClick = true;
				replyToGoogleForm();
			}
		});
		
		 JPanel pane = (JPanel) getContentPane();
	     GroupLayout gl = new GroupLayout(pane);
	     pane.setLayout(gl);
	        
	     pane.setToolTipText("Content pane");
	     gl.setAutoCreateContainerGaps(true);
	     gl.setAutoCreateGaps(true);
	        
	     gl.setHorizontalGroup(gl.createParallelGroup(Alignment.CENTER)
	    		 .addComponent(question)
	    		 .addComponent(neither)
	    		 .addComponent(first)
	    		 .addComponent(second)
	    		 .addComponent(equal)
	             .addComponent(submitButton)
	             .addGap(300)
	     );

	     gl.setVerticalGroup(gl.createSequentialGroup()
	    		 .addComponent(question)
	    		 .addComponent(neither)
	    		 .addComponent(first)
	    		 .addComponent(second)
	    		 .addComponent(equal)
	             .addComponent(submitButton)
	     );
	        
	     pack();   
	}
	
	private void replyToGoogleForm(){
		try {
			String response = "entry.1407994238=" + Runner.games.get(Runner.chosenGame) + 
					"&entry.845771917=" + Runner.firstGenerator + 
					"&entry.1240824314=" + Runner.secondGenerator +
					"&entry.720865890=" + getPreference() + 
					"&entry.1888517410=" + InetAddress.getLocalHost().getHostAddress();
			URL url = new URL("https://docs.google.com/forms/d/1Z2fmFAdErv_LH-U3qIBxBsz_ZHPtZef8hPLZXymaD6o/formResponse");
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0");
			DataOutputStream dataStream = new DataOutputStream(connection.getOutputStream());
			dataStream.writeBytes(response);
			dataStream.flush();
			dataStream.close();
			
			
			InputStream dataInput = connection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInput));
			
			System.out.println(bufferedReader.readLine());
			dataInput.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,
				    "Can not connect to the server! Check your internet connection.");
		}
	}
	
	public String getPreference(){
		if(first.isSelected()){
			return "First";
		}
		if(second.isSelected()){
			return "Second";
		}
		if(equal.isSelected()){
			return "Equal";
		}
		
		return "Neither";
	}
	
	public void resetRadio(){
		neither.setSelected(true);
	}
}
