namespace WindowsFormsApplication2 {
	partial class MainApplicationWindow {
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.IContainer components = null;

		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		/// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
		protected override void Dispose(bool disposing) {
			if (disposing && (components != null)) {
				components.Dispose();
			}
			base.Dispose(disposing);
		}

		#region Windows Form Designer generated code

		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent() {
            this.ProjectGridView = new System.Windows.Forms.DataGridView();
            this.TaskGridView = new System.Windows.Forms.DataGridView();
            this.AddTaskButton = new System.Windows.Forms.Button();
            this.UpdateTaskButton = new System.Windows.Forms.Button();
            this.DeleteTaskButton = new System.Windows.Forms.Button();
            this.EventLabel = new System.Windows.Forms.Label();
            this.panelBox = new System.Windows.Forms.Panel();
            this.flowPanel = new System.Windows.Forms.FlowLayoutPanel();
            ((System.ComponentModel.ISupportInitialize)(this.ProjectGridView)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.TaskGridView)).BeginInit();
            this.SuspendLayout();
            // 
            // ProjectGridView
            // 
            this.ProjectGridView.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.ProjectGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.ProjectGridView.Location = new System.Drawing.Point(24, 268);
            this.ProjectGridView.Margin = new System.Windows.Forms.Padding(6, 6, 6, 6);
            this.ProjectGridView.Name = "ProjectGridView";
            this.ProjectGridView.Size = new System.Drawing.Size(520, 210);
            this.ProjectGridView.TabIndex = 0;
            this.ProjectGridView.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.OnCellClickProjectGridView);
            // 
            // TaskGridView
            // 
            this.TaskGridView.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.TaskGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.TaskGridView.Location = new System.Drawing.Point(24, 490);
            this.TaskGridView.Margin = new System.Windows.Forms.Padding(6, 6, 6, 6);
            this.TaskGridView.Name = "TaskGridView";
            this.TaskGridView.Size = new System.Drawing.Size(520, 402);
            this.TaskGridView.TabIndex = 1;
            // 
            // AddTaskButton
            // 
            this.AddTaskButton.Location = new System.Drawing.Point(24, 212);
            this.AddTaskButton.Margin = new System.Windows.Forms.Padding(6, 6, 6, 6);
            this.AddTaskButton.Name = "AddTaskButton";
            this.AddTaskButton.Size = new System.Drawing.Size(158, 44);
            this.AddTaskButton.TabIndex = 3;
            this.AddTaskButton.Text = "Add ";
            this.AddTaskButton.UseVisualStyleBackColor = true;
            this.AddTaskButton.Click += new System.EventHandler(this.OnAddTaskClick);
            // 
            // UpdateTaskButton
            // 
            this.UpdateTaskButton.Location = new System.Drawing.Point(192, 212);
            this.UpdateTaskButton.Margin = new System.Windows.Forms.Padding(6, 6, 6, 6);
            this.UpdateTaskButton.Name = "UpdateTaskButton";
            this.UpdateTaskButton.Size = new System.Drawing.Size(190, 44);
            this.UpdateTaskButton.TabIndex = 4;
            this.UpdateTaskButton.Text = "Update";
            this.UpdateTaskButton.UseVisualStyleBackColor = true;
            this.UpdateTaskButton.Click += new System.EventHandler(this.OnUpdateTaskClick);
            // 
            // DeleteTaskButton
            // 
            this.DeleteTaskButton.Location = new System.Drawing.Point(394, 212);
            this.DeleteTaskButton.Margin = new System.Windows.Forms.Padding(6, 6, 6, 6);
            this.DeleteTaskButton.Name = "DeleteTaskButton";
            this.DeleteTaskButton.Size = new System.Drawing.Size(150, 44);
            this.DeleteTaskButton.TabIndex = 5;
            this.DeleteTaskButton.Text = "Delete";
            this.DeleteTaskButton.UseVisualStyleBackColor = true;
            this.DeleteTaskButton.Click += new System.EventHandler(this.OnDeleteTaskClick);
            // 
            // EventLabel
            // 
            this.EventLabel.AutoSize = true;
            this.EventLabel.Location = new System.Drawing.Point(24, 140);
            this.EventLabel.Margin = new System.Windows.Forms.Padding(6, 0, 6, 0);
            this.EventLabel.Name = "EventLabel";
            this.EventLabel.Size = new System.Drawing.Size(0, 25);
            this.EventLabel.TabIndex = 6;
            // 
            // panelBox
            // 
            this.panelBox.AutoScroll = true;
            this.panelBox.AutoSize = true;
            this.panelBox.AutoSizeMode = System.Windows.Forms.AutoSizeMode.GrowAndShrink;
            this.panelBox.Dock = System.Windows.Forms.DockStyle.Top;
            this.panelBox.ImeMode = System.Windows.Forms.ImeMode.On;
            this.panelBox.Location = new System.Drawing.Point(0, 0);
            this.panelBox.Name = "panelBox";
            this.panelBox.Size = new System.Drawing.Size(568, 0);
            this.panelBox.TabIndex = 7;
            // 
            // flowPanel
            // 
            this.flowPanel.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.flowPanel.Location = new System.Drawing.Point(24, 12);
            this.flowPanel.Name = "flowPanel";
            this.flowPanel.Size = new System.Drawing.Size(520, 191);
            this.flowPanel.TabIndex = 8;
            // 
            // MainApplicationWindow
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 25F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(568, 915);
            this.Controls.Add(this.flowPanel);
            this.Controls.Add(this.panelBox);
            this.Controls.Add(this.EventLabel);
            this.Controls.Add(this.DeleteTaskButton);
            this.Controls.Add(this.UpdateTaskButton);
            this.Controls.Add(this.AddTaskButton);
            this.Controls.Add(this.TaskGridView);
            this.Controls.Add(this.ProjectGridView);
            this.Margin = new System.Windows.Forms.Padding(6, 6, 6, 6);
            this.Name = "MainApplicationWindow";
            this.Text = "MainApplicationWindow";
            ((System.ComponentModel.ISupportInitialize)(this.ProjectGridView)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.TaskGridView)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

		}

		#endregion

		private System.Windows.Forms.DataGridView ProjectGridView;
		private System.Windows.Forms.DataGridView TaskGridView;
		private System.Windows.Forms.Button AddTaskButton;
		private System.Windows.Forms.Button UpdateTaskButton;
		private System.Windows.Forms.Button DeleteTaskButton;
		private System.Windows.Forms.Label EventLabel;
        private System.Windows.Forms.Panel panelBox;
        private System.Windows.Forms.FlowLayoutPanel flowPanel;
    }
}

