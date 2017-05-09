using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Windows.Forms;

namespace WindowsFormsApplication2 {

	public partial class MainApplicationWindow : Form, Observer
	{

	    private readonly DatabaseModel _modelChild;
	    private readonly DatabaseModel _modelParent;
        private readonly DatabaseTable _parent;
	    private readonly DatabaseTable _child;
	    private readonly List<TextBox> _textBoxes;

        public MainApplicationWindow() {
			InitializeComponent();
            _textBoxes = new List<TextBox>();
            _modelChild = new DatabaseModel();
            _modelParent = new DatabaseModel();
            _parent = new DatabaseTable
            {
                Name = ConfigurationManager.AppSettings["ParentTable"],
                PrimaryKey = ConfigurationManager.AppSettings["ParentPrimaryKey"],
                ForeignKey = ConfigurationManager.AppSettings["ParentForeignKey"],
                Parent = ConfigurationManager.AppSettings["ParentParent"],
                Columns = ConfigurationManager.AppSettings["ParentColumns"].Split(',').ToList(),
                Parameters = ConfigurationManager.AppSettings["ParentParameters"].Split(',').ToList()
            };

            _child = new DatabaseTable()
            {
                Name = ConfigurationManager.AppSettings["ChildTable"],
                PrimaryKey = ConfigurationManager.AppSettings["ChildPrimaryKey"],
                ForeignKey = ConfigurationManager.AppSettings["ChildForeignKey"],
                Parent = ConfigurationManager.AppSettings["ChildParent"],
                Columns = ConfigurationManager.AppSettings["ChildColumns"].Split(',').ToList(),
                Parameters = ConfigurationManager.AppSettings["ChildParameters"].Split(',').ToList()
            };
		    _modelChild.AddObserver(this);
            ProjectGridView.DataSource = _modelParent.GetTable(_parent);
            LoadTextBoxes();
        }

	    private void LoadTextBoxes()
	    {
            _child.Columns.ForEach(column =>
            {
                var textBox = new TextBox {Text = column};
                _textBoxes.Add(textBox);
                flowPanel.Controls.Add(textBox);
            });
	    }

        private void OnCellClickProjectGridView(object sender, DataGridViewCellEventArgs @event)
		{
			if (@event.ColumnIndex < 0 || @event.RowIndex < 0) return;
		    var id = int.Parse(ProjectGridView.Rows[@event.RowIndex].Cells[0].Value.ToString());
			TaskGridView.DataSource = _modelChild.GetTableFromForeignKey(_child, id);
		}

		private void OnDeleteTaskClick(object sender, System.EventArgs e)
		{
			var primaryKey = int.Parse(TaskGridView.SelectedCells[0].Value.ToString());
		    _modelChild.DeleteTable(_child, primaryKey);
		}								

		private void OnUpdateTaskClick(object sender, System.EventArgs e)
		{
			var idTask = int.Parse(TaskGridView.SelectedCells[0].Value.ToString());
		    var list = new List<string>();
            _textBoxes.ForEach(box => list.Add(box.Text));
		    _modelChild.UpdateTable(_child, list, idTask);	  
		}

		private void OnAddTaskClick(object sender, System.EventArgs e)
    	{
            var list = new List<string>();
	        _textBoxes.ForEach(box => list.Add(box.Text));
            _modelChild.InsertTable(_child, list);
		}

		public void notifyMe(NotificationType type)
		{					  
    		TaskGridView.DataSource = _modelChild.ModelDataSet.Tables[_child.Name];										  
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
