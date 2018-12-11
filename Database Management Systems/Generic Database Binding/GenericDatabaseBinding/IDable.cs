namespace WindowsFormsApplication2 {
	public class IDable : IDableProtocol
	{

		protected int Id;

		public int GetId() {
			return Id;
		}

		public void SetId(int id)
		{
			Id = id;
		}
	}
}
