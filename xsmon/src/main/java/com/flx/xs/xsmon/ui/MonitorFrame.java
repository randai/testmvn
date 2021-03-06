/*
 * MonitorFrame.java
 *
 * Created on __DATE__, __TIME__
 */

package com.flx.xs.xsmon.ui;

import org.apache.commons.logging.Log;

import com.flx.common.ui.swing.SwingUILookAndFeel;
import com.flx.xs.common.logger.Logger;

/**
 *
 * @author  __USER__
 */
public class MonitorFrame extends javax.swing.JFrame {
	@Logger private static Log log;
	
	private String title;
	
	private SwingUILookAndFeel lookAndFeel;

	public SwingUILookAndFeel getLookAndFeel() {
		return lookAndFeel;
	}

	public void setLookAndFeel(SwingUILookAndFeel lookAndFeel) {
		this.lookAndFeel = lookAndFeel;
	}

	public void setTitle(String title) {
		this.title = title;
		//log.info("Setting title to "+title);
	}

	/** Creates new form MonitorFrame */
	public MonitorFrame() {
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();
		jPanel2 = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		jPanel3 = new javax.swing.JPanel();
		jLabel3 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jPanel1.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(0, 0, 0)));

		jLabel1.setText("Top Panel");

		jButton1.setText("Exit");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(jLabel1)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												255, Short.MAX_VALUE)
										.addComponent(jButton1)
										.addContainerGap()));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGap(15, 15, 15)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(jButton1)
														.addComponent(jLabel1))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

		jPanel2.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(0, 0, 0)));

		jLabel2.setText("Middle Panel");

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel2Layout.createSequentialGroup().addContainerGap()
						.addComponent(jLabel2)
						.addContainerGap(307, Short.MAX_VALUE)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel2Layout.createSequentialGroup().addGap(36, 36, 36)
						.addComponent(jLabel2)
						.addContainerGap(89, Short.MAX_VALUE)));

		getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

		jPanel3.setBorder(javax.swing.BorderFactory
				.createLineBorder(new java.awt.Color(0, 0, 0)));

		jLabel3.setText("Bottom Panel");

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(
				jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel3Layout.createSequentialGroup().addContainerGap()
						.addComponent(jLabel3)
						.addContainerGap(304, Short.MAX_VALUE)));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel3Layout.createSequentialGroup().addGap(36, 36, 36)
						.addComponent(jLabel3)
						.addContainerGap(48, Short.MAX_VALUE)));

		getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		log.info("Button Clicked");
	}

	/**
	 * Start the swing stuff in the AWT thread
	 */
	public void initialize() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				initComponents();

				jLabel1.setText(title);

				setVisible(true);
			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	// End of variables declaration//GEN-END:variables

}