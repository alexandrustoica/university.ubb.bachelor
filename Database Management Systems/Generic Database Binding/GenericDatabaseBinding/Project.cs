namespace WindowsFormsApplication2 {
	public class Project : IDable
	{			
		public string Text { get; }
		public Project(string text) : this(0, text) { }

		public Project(int id, string text)
		{		   
			Id = id;
			Text = text;
		}
	}
}
