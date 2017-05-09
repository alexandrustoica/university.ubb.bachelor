using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Data;						

namespace WindowsFormsApplication2.Tests {

	[TestClass()]
	public class ModelTests
	{

		private Model TestModel;
					 
		[TestInitialize()]
		public void ModelTest() {
			TestModel = new Model("DESKTOP-NENSE3P\\SERVERDATABASE", "TaskProjectTest");
			Assert.IsNotNull(TestModel.ModelDataSet, "DataSet is not working!");
			Assert.IsNotNull(TestModel.ModelBindingSource, "BindingSource is not working!");
			Assert.IsNotNull(TestModel.ModelConnection, "Connection is not working!");
			Assert.IsNotNull(TestModel.ModelDataAdaptor, "DataAdaptor is not working!");
		}

		[TestMethod()]
		public void GetTableTest()
		{		
			var project = new Project(1, "ProjectTest");   
			var result = TestModel.GetTable("Project");
			Assert.IsTrue(result.Rows[0].ItemArray[0].Equals(project.GetId()) && 
				result.Rows[0].ItemArray[1].Equals(project.Text), 
				"Getting the data from table is not working!");	
		}

		[TestMethod()]
		public void GetChildTableOfIdTest()
		{
			var project = new Project(1, "ProjectTest");
			var task = new Task(17, "TaskTest", project.GetId());
			var result = TestModel.GetChildTableOfId(1, "id_project", "Task");
			Assert.IsTrue(result.Rows[0].ItemArray[0].Equals(task.GetId()) &&
			              result.Rows[0].ItemArray[2].Equals(project.GetId()) &&
						  result.Rows[0].ItemArray[1].Equals(task.Text),
						  "Getting data from child table is not working!");
		}

		[TestMethod()]
		public void AddTaskToProject()
		{
			var project = new Project(1, "ProjectTest");
			var task = new Task(2, "UnitTestTask", 1);		 
			var idTask = TestModel.AddTaskToProject(project.GetId(), task, "Task");
			var result = TestModel.GetChildTableOfId(1, "id_project", "Task");
			Assert.IsTrue(result.Rows[1].ItemArray[0].Equals(idTask) &&
				result.Rows[1].ItemArray[1].Equals("UnitTestTask"),
				"Adding data in table is not working!");
			TestModel.DeleteTask(idTask, "Task");
		}

		[TestMethod()]
		public void UpdateTaskToProject()
		{
			var project = new Project(1, "ProjectTest");
			var task = new Task(2, "UnitTestTask", 1);
			var idTask = TestModel.AddTaskToProject(project.GetId(), task, "Task");
			var update = new Task(idTask, "EditTask", 1);
			TestModel.UpdateTask(idTask, update, "Task");
			var result = TestModel.GetChildTableOfId(1, "id_project", "Task");
			Assert.IsTrue(result.Rows[1].ItemArray[0].Equals(idTask) && 
							result.Rows[1].ItemArray[1].Equals("EditTask"),
							"Updating data in table is not working!");
			TestModel.DeleteTask(idTask, "Task");

		}

		[TestMethod()]
		public void DeleteTask()
		{
			var project = new Project(1, "ProjectTest");
			var task = new Task(2, "UnitTestTask", 1);
			var idTask = TestModel.AddTaskToProject(project.GetId(), task, "Task");	  
			TestModel.DeleteTask(idTask, "Task");									  
			var result = TestModel.GetChildTableOfId(1, "id_project", "Task");
			Assert.IsTrue(result.Rows.Count.Equals(1),
			   "Delete is not working!");
		}

	}

}