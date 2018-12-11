
namespace WindowsFormsApplication2 {
	public class Task : IDable
	{							
		public string Text { get; set; }
		public int IdProject { get; }

		public Task(string text) : this(0, text, 0) { }

		public Task(int id, string text, int idProject)
		{
			Id = id;
			IdProject = idProject;
			Text = text;
		}
	}
}
