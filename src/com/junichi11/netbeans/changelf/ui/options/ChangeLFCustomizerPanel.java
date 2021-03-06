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

import com.junichi11.netbeans.changelf.ChangeLFImpl;
import com.junichi11.netbeans.changelf.api.ChangeLF;
import com.junichi11.netbeans.changelf.preferences.ChangeLFPreferences;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ui.support.ProjectCustomizer;
import org.openide.util.NbBundle;

/**
 *
 * @author junichi11
 */
public class ChangeLFCustomizerPanel extends JPanel {

    private static final long serialVersionUID = 6306423299382770040L;
    private final ProjectCustomizer.Category category;
    private final Project project;
    private boolean useGlobal = true;
    private boolean useProject = false;
    private boolean isEnabled = false;
    private ChangeLF.TYPE lfKindName;
    private boolean showDialog = false;
    private static final Logger LOGGER = Logger.getLogger(ChangeLFCustomizerPanel.class.getName());

    /**
     * Creates new form ChangeLFCustomizerPanel
     */
    @NbBundle.Messages("LBL_CanNotFindProjectMessage=Can't find Project!")
    ChangeLFCustomizerPanel(ProjectCustomizer.Category category, Project project) {
        category.setOkButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        this.category = category;
        this.project = project;

        if (project != null) {
            initComponents();
            initCombo();
            lfKindComboBox.setSelectedItem(new LFItem(ChangeLF.TYPE.LF));

            load();
            if (useGlobalRadioButton.isSelected()) {
                setProjectOptionGroup(false);
            }
        } else {
            LOGGER.log(Level.INFO, Bundle.LBL_CanNotFindProjectMessage());
            this.category.setErrorMessage(Bundle.LBL_CanNotFindProjectMessage());
        }
    }

    private void initCombo() {
        for (ChangeLF.TYPE type : ChangeLF.TYPE.values()) {
            lfKindComboBox.addItem(new LFItem(type));
        }
    }

    private void load() {
        useGlobal = ChangeLFPreferences.useGlobal(project);
        useProject = ChangeLFPreferences.useProject(project);
        isEnabled = ChangeLFPreferences.isEnable(project);
        lfKindName = ChangeLFImpl.toType(ChangeLFPreferences.getLfKind(project));
        showDialog = ChangeLFPreferences.showDialog(project);

        useGlobalRadioButton.setSelected(useGlobal);
        useProjectRadioButton.setSelected(useProject);
        enableCheckBox.setSelected(isEnabled);
        if (lfKindName != null) {
            lfKindComboBox.setSelectedItem(new LFItem(lfKindName));
        }
        showDialogCheckBox.setSelected(showDialog);
    }

    private void save() {
        if (useGlobal() != useGlobal) {
            ChangeLFPreferences.setGlobal(project, useGlobal());
        }
        if (useProject() != useProject) {
            ChangeLFPreferences.setProject(project, useProject());
        }
        if (isEnable() != isEnabled) {
            ChangeLFPreferences.setEnable(project, isEnable());
        }
        if (useShowDialog() != showDialog) {
            ChangeLFPreferences.setShowDialog(project, useShowDialog());
        }

        ChangeLF.TYPE selectedLFKind = getLfKind();
        if (selectedLFKind != lfKindName) {
            ChangeLFPreferences.setLfKind(project, ChangeLFImpl.fromType(selectedLFKind));
        }
    }

    public boolean isEnable() {
        return enableCheckBox.isSelected();
    }

    public ChangeLF.TYPE getLfKind() {
        LFItem selected = (LFItem) lfKindComboBox.getSelectedItem();
        return selected != null ? selected.getLFType() : null;
    }

    public boolean useShowDialog() {
        return showDialogCheckBox.isSelected();
    }

    public boolean useGlobal() {
        return useGlobalRadioButton.isSelected();
    }

    public boolean useProject() {
        return useProjectRadioButton.isSelected();
    }

    private void setProjectOptionGroup(boolean enabled) {
        showDialogCheckBox.setEnabled(enabled);
        enableCheckBox.setEnabled(enabled);
        lfKindComboBox.setEnabled(enabled);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        useGlobalRadioButton = new javax.swing.JRadioButton();
        useProjectRadioButton = new javax.swing.JRadioButton();
        enableCheckBox = new javax.swing.JCheckBox();
        showDialogCheckBox = new javax.swing.JCheckBox();
        lfKindComboBox = new javax.swing.JComboBox();

        buttonGroup1.add(useGlobalRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(useGlobalRadioButton, org.openide.util.NbBundle.getMessage(ChangeLFCustomizerPanel.class, "ChangeLFCustomizerPanel.useGlobalRadioButton.text")); // NOI18N
        useGlobalRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useGlobalRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(useProjectRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(useProjectRadioButton, org.openide.util.NbBundle.getMessage(ChangeLFCustomizerPanel.class, "ChangeLFCustomizerPanel.useProjectRadioButton.text")); // NOI18N
        useProjectRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useProjectRadioButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(enableCheckBox, org.openide.util.NbBundle.getMessage(ChangeLFCustomizerPanel.class, "ChangeLFCustomizerPanel.enableCheckBox.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(showDialogCheckBox, org.openide.util.NbBundle.getMessage(ChangeLFCustomizerPanel.class, "ChangeLFCustomizerPanel.showDialogCheckBox.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(useGlobalRadioButton)
                    .addComponent(useProjectRadioButton)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(enableCheckBox)
                            .addComponent(showDialogCheckBox)
                            .addComponent(lfKindComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(useGlobalRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(useProjectRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enableCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lfKindComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showDialogCheckBox)
                .addContainerGap(140, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void useProjectRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useProjectRadioButtonActionPerformed
        setProjectOptionGroup(true);
    }//GEN-LAST:event_useProjectRadioButtonActionPerformed

    private void useGlobalRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useGlobalRadioButtonActionPerformed
        setProjectOptionGroup(false);
    }//GEN-LAST:event_useGlobalRadioButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox enableCheckBox;
    private javax.swing.JComboBox lfKindComboBox;
    private javax.swing.JCheckBox showDialogCheckBox;
    private javax.swing.JRadioButton useGlobalRadioButton;
    private javax.swing.JRadioButton useProjectRadioButton;
    // End of variables declaration//GEN-END:variables
}
