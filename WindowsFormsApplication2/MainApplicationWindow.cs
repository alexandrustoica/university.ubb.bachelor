using System.Windows.Forms;

namespace WindowsFormsApplication2 {
	public partial class MainApplicationWindow : Form, Observer
	{

		private Model ProjectModel;
		private Model TaskModel;

		public MainApplicationWindow() {
			InitializeComponent();
			ProjectModel = new Model();
			TaskModel = new Model();  
			ProjectModel.addObserver(this);
			TaskModel.addObserver(this);
			ProjectGridView.DataSource = ProjectModel.GetTable("Project");
		}

		private void OnCellClickProjectGridView(object sender, DataGridViewCellEventArgs @event)
		{
			if (@event.ColumnIndex < 0 || @event.RowIndex < 0) return;
			string idString = ProjectGridView.Rows[@event.RowIndex].Cells[0].Value.ToString();
			int idProject = int.Parse(idString);
			TaskGridView.DataSource = TaskModel.GetChildTableOfId(idProject, "id_project", "Task");
		}

		private void OnDeleteTaskClick(object sender, System.EventArgs e)
		{
			string idString = TaskGridView.SelectedCells[0].Value.ToString();
			int idTask = int.Parse(idString);
			TaskModel.DeleteTask(idTask, "Task");
		}								

		private void OnUpdateTaskClick(object sender, System.EventArgs e)
		{
			string idString = TaskGridView.SelectedCells[0].Value.ToString();
			int idTask = int.Parse(idString);
			idString = ProjectGridView.SelectedCells[0].Value.ToString();
			int idProject = int.Parse(idString);
			string text = TaskTextBox.Text;
			Task task = new Task(idTask, text, idProject);
			TaskModel.UpdateTask(idTask, task, "Task");	  
		}

		private void OnAddTaskClick(object sender, System.EventArgs e)
		{
			string text = TaskTextBox.Text;
			string idString = ProjectGridView.SelectedCells[0].Value.ToString();
			int idProject = int.Parse(idString);
			Task task = new Task(text);
			TaskModel.AddTaskToProject(idProject, task, "Task");
		}

		public void notifyMe(NotificationType type)
		{					  
			TaskGridView.DataSource = TaskModel.ModelDataSet.Tables["Task"];										  
			switch (type)
			{
				case NotificationType.NotificationAdd:
					EventLabel.Text = "Item Added!";
					break;
				case NotificationType.NotificationDelete:
					EventLabel.Text = "Item Deleted!";
					break;
				case NotificationType.NotificationUpdate:
					EventLabel.Text = "Item Updated!";
					break;
			}
		}

	}
}
