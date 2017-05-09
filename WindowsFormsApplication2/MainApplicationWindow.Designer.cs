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
			this.TaskTextBox = new System.Windows.Forms.TextBox();
			this.AddTaskButton = new System.Windows.Forms.Button();
			this.UpdateTaskButton = new System.Windows.Forms.Button();
			this.DeleteTaskButton = new System.Windows.Forms.Button();
			this.EventLabel = new System.Windows.Forms.Label();
			((System.ComponentModel.ISupportInitialize)(this.ProjectGridView)).BeginInit();
			((System.ComponentModel.ISupportInitialize)(this.TaskGridView)).BeginInit();
			this.SuspendLayout();
			// 
			// ProjectGridView
			// 
			this.ProjectGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
			this.ProjectGridView.Location = new System.Drawing.Point(12, 99);
			this.ProjectGridView.Name = "ProjectGridView";
			this.ProjectGridView.Size = new System.Drawing.Size(260, 150);
			this.ProjectGridView.TabIndex = 0;
			this.ProjectGridView.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.OnCellClickProjectGridView);
			// 
			// TaskGridView
			// 
			this.TaskGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
			this.TaskGridView.Location = new System.Drawing.Point(12, 255);
			this.TaskGridView.Name = "TaskGridView";
			this.TaskGridView.Size = new System.Drawing.Size(260, 209);
			this.TaskGridView.TabIndex = 1;
			// 
			// TaskTextBox
			// 
			this.TaskTextBox.Location = new System.Drawing.Point(12, 12);
			this.TaskTextBox.Name = "TaskTextBox";
			this.TaskTextBox.Size = new System.Drawing.Size(260, 20);
			this.TaskTextBox.TabIndex = 2;
			this.TaskTextBox.Text = "Task Text ...";
			this.TaskTextBox.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
			// 
			// AddTaskButton
			// 
			this.AddTaskButton.Location = new System.Drawing.Point(13, 38);
			this.AddTaskButton.Name = "AddTaskButton";
			this.AddTaskButton.Size = new System.Drawing.Size(79, 23);
			this.AddTaskButton.TabIndex = 3;
			this.AddTaskButton.Text = "Add Task";
			this.AddTaskButton.UseVisualStyleBackColor = true;
			this.AddTaskButton.Click += new System.EventHandler(this.OnAddTaskClick);
			// 
			// UpdateTaskButton
			// 
			this.UpdateTaskButton.Location = new System.Drawing.Point(98, 38);
			this.UpdateTaskButton.Name = "UpdateTaskButton";
			this.UpdateTaskButton.Size = new System.Drawing.Size(95, 23);
			this.UpdateTaskButton.TabIndex = 4;
			this.UpdateTaskButton.Text = "UpdateTask";
			this.UpdateTaskButton.UseVisualStyleBackColor = true;
			this.UpdateTaskButton.Click += new System.EventHandler(this.OnUpdateTaskClick);
			// 
			// DeleteTaskButton
			// 
			this.DeleteTaskButton.Location = new System.Drawing.Point(198, 38);
			this.DeleteTaskButton.Name = "DeleteTaskButton";
			this.DeleteTaskButton.Size = new System.Drawing.Size(75, 23);
			this.DeleteTaskButton.TabIndex = 5;
			this.DeleteTaskButton.Text = "DeleteTask";
			this.DeleteTaskButton.UseVisualStyleBackColor = true;
			this.DeleteTaskButton.Click += new System.EventHandler(this.OnDeleteTaskClick);
			// 
			// EventLabel
			// 
			this.EventLabel.AutoSize = true;
			this.EventLabel.Location = new System.Drawing.Point(12, 73);
			this.EventLabel.Name = "EventLabel";
			this.EventLabel.Size = new System.Drawing.Size(0, 13);
			this.EventLabel.TabIndex = 6;
			// 
			// MainApplicationWindow
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(284, 476);
			this.Controls.Add(this.EventLabel);
			this.Controls.Add(this.DeleteTaskButton);
			this.Controls.Add(this.UpdateTaskButton);
			this.Controls.Add(this.AddTaskButton);
			this.Controls.Add(this.TaskTextBox);
			this.Controls.Add(this.TaskGridView);
			this.Controls.Add(this.ProjectGridView);
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
		private System.Windows.Forms.TextBox TaskTextBox;
		private System.Windows.Forms.Button AddTaskButton;
		private System.Windows.Forms.Button UpdateTaskButton;
		private System.Windows.Forms.Button DeleteTaskButton;
		private System.Windows.Forms.Label EventLabel;
	}
}

