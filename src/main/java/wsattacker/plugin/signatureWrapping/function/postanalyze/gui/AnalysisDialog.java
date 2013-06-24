/**
 * WS-Attacker - A Modular Web Services Penetration Testing Framework Copyright
 * (C) 2011 Christian Mainka
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */
package wsattacker.plugin.signatureWrapping.function.postanalyze.gui;

import wsattacker.plugin.signatureWrapping.function.postanalyze.model.AnalysisDataCollector;
import wsattacker.plugin.signatureWrapping.function.postanalyze.model.AnalysisData;
import java.beans.PropertyChangeSupport;
import java.util.logging.*;
import javax.swing.DefaultListModel;
import org.w3c.dom.Document;
import wsattacker.plugin.signatureWrapping.SignatureWrapping;
import wsattacker.library.signatureWrapping.util.dom.DomUtilities;
import wsattacker.library.signatureWrapping.util.exception.InvalidWeaknessException;
import wsattacker.library.signatureWrapping.xpath.weakness.util.WeaknessLog;

public class AnalysisDialog extends javax.swing.JDialog {

	public static final String PROP_SIGNATUREWRAPPINGPLUGIN = "PROP_SIGNATUREWRAPPINGPLUGIN";
	private SignatureWrapping signatureWrappingPlugin;
	private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);

	public AnalysisDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	/**
	 * This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JLabel();
        responseChooser = new javax.swing.JComboBox();
        listScrollPane = new javax.swing.JScrollPane();
        indexList = new javax.swing.JList();
        topDownSplitPane = new javax.swing.JSplitPane();
        leftRightSplitPane = new javax.swing.JSplitPane();
        requestScrollPane = new javax.swing.JScrollPane();
        requestPane = new wsattacker.gui.util.XmlTextPane();
        responseScrollPane = new javax.swing.JScrollPane();
        responsePane = new wsattacker.gui.util.XmlTextPane();
        logScrollPane1 = new javax.swing.JScrollPane();
        logPane = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        title.setText("Signature Wrapping Analysis");

        responseChooser.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        responseChooser.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                responseChooserItemStateChanged(evt);
            }
        });

        indexList.setModel(new DefaultListModel());
        indexList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        indexList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                indexListValueChanged(evt);
            }
        });
        listScrollPane.setViewportView(indexList);

        topDownSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        leftRightSplitPane.setDividerLocation(0.5);

        requestScrollPane.setViewportView(requestPane);

        leftRightSplitPane.setLeftComponent(requestScrollPane);

        responseScrollPane.setViewportView(responsePane);

        leftRightSplitPane.setRightComponent(responseScrollPane);

        topDownSplitPane.setBottomComponent(leftRightSplitPane);

        logPane.setEditable(false);
        logPane.setBackground(getBackground());
        logPane.setFocusable(false);
        logPane.setRequestFocusEnabled(false);
        logScrollPane1.setViewportView(logPane);

        topDownSplitPane.setLeftComponent(logScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
                    .addComponent(responseChooser, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(topDownSplitPane))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(listScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(responseChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(topDownSplitPane)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void responseChooserItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_responseChooserItemStateChanged
		if (signatureWrappingPlugin != null && responseChooser.getSelectedIndex() >= 0) {
			DefaultListModel model = (DefaultListModel) indexList.getModel();
			model.removeAllElements();
			AnalysisDataCollector dataCollector = signatureWrappingPlugin.getAnalysisData();
			String key = (String) responseChooser.getSelectedItem();
			for (AnalysisData data : dataCollector.getDataEntry(key)) {
				model.addElement(data.getIndex()+1);
			}
		}
    }//GEN-LAST:event_responseChooserItemStateChanged

    private void indexListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_indexListValueChanged
		if (signatureWrappingPlugin != null && signatureWrappingPlugin.getWrappingOracle() != null && indexList.getSelectedIndex() >= 0) {
			Integer index = (Integer) indexList.getSelectedValue()-1;
			try {
				logPane.setText(WeaknessLog.representation());
				Document wrappingPossibility = signatureWrappingPlugin.getWrappingOracle().getPossibility(index);
				requestPane.setText(DomUtilities.domToString(wrappingPossibility));
				String key = (String) responseChooser.getSelectedItem();
				String response = signatureWrappingPlugin.getAnalysisData().getDataEntry(key).get(indexList.getSelectedIndex()).getResponse();
				responsePane.setText(response);
			} catch (InvalidWeaknessException ex) {
				Logger.getLogger(AnalysisDialog.class.getName()).log(Level.SEVERE, null, ex);
			} catch (NullPointerException e) {
				Logger.getLogger(AnalysisDialog.class.getName()).log(Level.SEVERE, null, e);
			}
		}
    }//GEN-LAST:event_indexListValueChanged

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/*
		 * Set the Nimbus look and feel
		 */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel.
		 * For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(AnalysisDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(AnalysisDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(AnalysisDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(AnalysisDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/*
		 * Create and display the dialog
		 */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				AnalysisDialog dialog = new AnalysisDialog(new javax.swing.JFrame(), true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList indexList;
    private javax.swing.JSplitPane leftRightSplitPane;
    private javax.swing.JScrollPane listScrollPane;
    private javax.swing.JTextPane logPane;
    private javax.swing.JScrollPane logScrollPane1;
    private wsattacker.gui.util.XmlTextPane requestPane;
    private javax.swing.JScrollPane requestScrollPane;
    private javax.swing.JComboBox responseChooser;
    private wsattacker.gui.util.XmlTextPane responsePane;
    private javax.swing.JScrollPane responseScrollPane;
    private javax.swing.JLabel title;
    private javax.swing.JSplitPane topDownSplitPane;
    // End of variables declaration//GEN-END:variables

	/**
	 * @return the signatureWrappingPlugin
	 */
	public SignatureWrapping getSignatureWrappingPlugin() {
		return signatureWrappingPlugin;
	}

	/**
	 * @param signatureWrappingPlugin the signatureWrappingPlugin to set
	 */
	public void setSignatureWrappingPlugin(SignatureWrapping signatureWrappingPlugin) {
		wsattacker.plugin.signatureWrapping.SignatureWrapping oldSignatureWrappingPlugin = signatureWrappingPlugin;
		this.signatureWrappingPlugin = signatureWrappingPlugin;

		//
		responseChooser.removeAllItems();
		for (String key : signatureWrappingPlugin.getAnalysisData().getData().keySet()) {
			responseChooser.addItem(key);
		}

		if (responseChooser.getItemCount() > 0) {
			responseChooser.setSelectedIndex(0);
		}

		propertyChangeSupport.firePropertyChange(PROP_SIGNATUREWRAPPINGPLUGIN, oldSignatureWrappingPlugin, signatureWrappingPlugin);
	}
}
