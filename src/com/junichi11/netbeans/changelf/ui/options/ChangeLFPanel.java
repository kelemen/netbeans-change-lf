/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 *
 * Contributor(s):
 *
 * Portions Copyrighted 2013 Sun Microsystems, Inc.
 */
package com.junichi11.netbeans.changelf.ui.options;

import com.junichi11.netbeans.changelf.ChangeLF;
import java.util.HashSet;
import java.util.Set;

final class ChangeLFPanel extends javax.swing.JPanel {

    private static final long serialVersionUID = 5718607844987480654L;
    private final ChangeLFOptionsPanelController controller;
    private static final Set<String> LF_KIND = new HashSet<String>();

    static {
        LF_KIND.add(ChangeLF.LF);
        LF_KIND.add(ChangeLF.CR);
        LF_KIND.add(ChangeLF.CRLF);
    }

    ChangeLFPanel(ChangeLFOptionsPanelController controller) {
        this.controller = controller;
        initComponents();
        changeEnable(isEnable());
    }

    public boolean isEnable() {
        return enableCheckBox.isSelected();
    }

    public void setEnable(boolean enable) {
        enableCheckBox.setSelected(enable);
    }

    public boolean useShowDialog() {
        return showDialogCheckBox.isSelected();
    }

    public void setShowDialog(boolean show) {
        showDialogCheckBox.setSelected(show);
    }

    public String getLfKind() {
        return lfKindComboBox.getSelectedItem().toString();
    }

    public void setLfKind(String kind) {
        if (LF_KIND.contains(kind)) {
            lfKindComboBox.setSelectedItem(kind);
        }
    }

    private void changeEnable(boolean enable) {
        lfKindComboBox.setEnabled(enable);
        showDialogCheckBox.setEnabled(enable);
    }

    private ChangeLFOptions getOptions() {
        return ChangeLFOptions.getInstance();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        enableCheckBox = new javax.swing.JCheckBox();
        lfKindComboBox = new javax.swing.JComboBox();
        showDialogCheckBox = new javax.swing.JCheckBox();

        org.openide.awt.Mnemonics.setLocalizedText(enableCheckBox, org.openide.util.NbBundle.getMessage(ChangeLFPanel.class, "ChangeLFPanel.enableCheckBox.text")); // NOI18N
        enableCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enableCheckBoxActionPerformed(evt);
            }
        });

        lfKindComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LF", "CR", "CRLF" }));

        org.openide.awt.Mnemonics.setLocalizedText(showDialogCheckBox, org.openide.util.NbBundle.getMessage(ChangeLFPanel.class, "ChangeLFPanel.showDialogCheckBox.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(enableCheckBox)
                    .addComponent(lfKindComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showDialogCheckBox))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(enableCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lfKindComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showDialogCheckBox)
                .addContainerGap(122, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void enableCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enableCheckBoxActionPerformed
        changeEnable(isEnable());
    }//GEN-LAST:event_enableCheckBoxActionPerformed

    void load() {
        ChangeLFOptions options = getOptions();
        setEnable(options.isEnable());
        setLfKind(options.getLfKind());
        setShowDialog(options.useShowDialog());
        changeEnable(isEnable());
    }

    void store() {
        ChangeLFOptions options = getOptions();
        options.setEnable(isEnable());
        options.setShowDialog(useShowDialog());
        options.setLfKind(getLfKind());
    }

    boolean valid() {
        // TODO check whether form is consistent and complete
        return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox enableCheckBox;
    private javax.swing.JComboBox lfKindComboBox;
    private javax.swing.JCheckBox showDialogCheckBox;
    // End of variables declaration//GEN-END:variables
}