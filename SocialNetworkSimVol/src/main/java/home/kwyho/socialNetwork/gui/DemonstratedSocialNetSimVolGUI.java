package home.kwyho.socialNetwork.gui;

import home.kwyho.socialNetwork.DemonstratedSocialNetSimVol;
import home.kwyho.socialNetwork.DirectedLink;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class DemonstratedSocialNetSimVolGUI extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton jButtonQuit;
	private JList jWordList2;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JButton jDisplayNetwork;
	private JLabel jResistanceVal;
	private JLabel jLabel1;
	private JList jWordList1;
	private JButton jCalculateButton;
	private DemonstratedSocialNetSimVol wn1;
	private JFrame wordNetJFrame;
	private boolean graphShowing;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				DemonstratedSocialNetSimVolGUI inst = new DemonstratedSocialNetSimVolGUI();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public DemonstratedSocialNetSimVolGUI() {
		super();
		initGUI();
		prepareWordNetGraph();
	}
	
	private void initGUI() {
		wn1 = new DemonstratedSocialNetSimVol();
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jButtonQuit = new JButton();
				jButtonQuit.setText("Quit");
				jButtonQuit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						System.out.println("jButtonQuit.actionPerformed, event="+evt);
						//TODO add your code for jButtonQuit.actionPerformed
						System.exit(0);
					}
				});
			}
			{
				jCalculateButton = new JButton();
				jCalculateButton.setText("Calculate");
				jCalculateButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						System.out.println("jCalculateButton.actionPerformed, event="+evt);
						//TODO add your code for jCalculateButton.actionPerformed
						String word1 = (String)jWordList1.getSelectedValue();
						String word2 = (String)jWordList2.getSelectedValue();
						if ((word1==null)||(word2==null))
							jResistanceVal.setText("#######.##");
						else {
							double resistance = wn1.getResistance(word1, word2);
							DecimalFormat formatedResistance = new DecimalFormat("#.##");
							jResistanceVal.setText(formatedResistance.format(resistance).toString());
						}
					}
				});
			}
			{
				jLabel1 = new JLabel();
				jLabel1.setText("Resistance / Closeness = ");
			}
			{
				jDisplayNetwork = new JButton();
				jDisplayNetwork.setText("Display SocialNet");
				graphShowing = false;
				jDisplayNetwork.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						System.out.println("jDisplayNetwork.actionPerformed, event="+evt);
						//TODO add your code for jDisplayNetwork.actionPerformed
						if (!graphShowing) {
							displayWordNetGraph();
							graphShowing = true;
							jDisplayNetwork.setText("Hide SocialNet");
						} else {
							hideWordNetGraph();
							graphShowing = false;
							jDisplayNetwork.setText("Display SocialNet");
						}
					}
				});
			}
			{
				jResistanceVal = new JLabel();
				jResistanceVal.setText("#######.##");
			}
			{
				jScrollPane1 = new JScrollPane();
				{
					String[] wordList1 = wn1.getRosterList();
					ListModel jWordList1Model = new DefaultComboBoxModel(wordList1);
					jWordList1 = new JList();
					jScrollPane1.setViewportView(jWordList1);
					jWordList1.setModel(jWordList1Model);
				}
			}
			{
				jScrollPane2 = new JScrollPane();
				{
					String[] wordList2 = wn1.getRosterList();
					ListModel jWordList2Model = new DefaultComboBoxModel(wordList2);
					jWordList2 = new JList();
					jScrollPane2.setViewportView(jWordList2);
					jWordList2.setModel(jWordList2Model);
					jWordList2.setSize(130, 155);
				}
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(26, 26)
				.addGroup(thisLayout.createParallelGroup()
				    .addComponent(jScrollPane2, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jScrollPane1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
				.addGap(17)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(jResistanceVal, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(24)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(jCalculateButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jButtonQuit, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jDisplayNetwork, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(19, 19));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(21, 21)
				.addGroup(thisLayout.createParallelGroup()
				    .addComponent(jLabel1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
				    .addGroup(thisLayout.createSequentialGroup()
				        .addGap(30)
				        .addGroup(thisLayout.createParallelGroup()
				            .addComponent(jScrollPane1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(jDisplayNetwork, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
				                .addGap(12)))))
				.addGap(22)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addGroup(thisLayout.createParallelGroup()
				            .addComponent(jResistanceVal, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addGap(7)
				                .addComponent(jCalculateButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
				                .addGap(18)))
				        .addComponent(jButtonQuit, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 0, Short.MAX_VALUE))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 46, Short.MAX_VALUE)))
				.addContainerGap(25, 25));
			pack();
			setSize(400, 300);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	protected void prepareWordNetGraph() {
		Layout<String, DirectedLink> layout = new SpringLayout<String, DirectedLink>(wn1.getSocialNet());
		layout.setSize(new Dimension(350,350));
		BasicVisualizationServer<String, DirectedLink> vv = new BasicVisualizationServer<String, DirectedLink>(layout);
		vv.setPreferredSize(new Dimension(450, 450));
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
		wordNetJFrame = new JFrame("Innovative WordNet");
		wordNetJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wordNetJFrame.getContentPane().add(vv);
		wordNetJFrame.pack();
	}

	public void displayWordNetGraph() {
		wordNetJFrame.setVisible(true);
	}
	
	public void hideWordNetGraph() {
		wordNetJFrame.setVisible(false);
	}
	
}
