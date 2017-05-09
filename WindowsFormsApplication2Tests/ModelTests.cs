using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Data;						

namespace WindowsFormsApplication2.Tests {

	[TestClass()]
	public class ModelTests
	{

		private Model TestModel;
					 
		[TestInitialize()]
		public void ModelTest() {
			TestModel = new Model("DESKTOP-9K3Q22D", "TestTaskDatabase");
			Assert.IsNotNull(TestModel.ModelDataSet, "DataSet is not working!");
			Assert.IsNotNull(TestModel.ModelBindingSource, "BindingSource is not working!");
			Assert.IsNotNull(TestModel.ModelConnection, "Connection is not working!");
			Assert.IsNotNull(TestModel.ModelDataAdaptor, "DataAdaptor is not working!");
		}

		[TestMethod()]
		public void GetTableTest()
		{		
			Project project = new Project(1, "ProjectTest");   
			DataTable result = TestModel.GetTable("Project");
			Assert.IsTrue(result.Rows[0].ItemArray[0].Equals(project.GetId()) && 
				result.Rows[0].ItemArray[1].Equals(project.Text), 
				"Getting the data from table is not working!");	
		}

		[TestMethod()]
		public void GetChildTableOfIdTest()
		{
			Project project = new Project(1, "ProjectTest");
			Task task = new Task(17, "TaskTest", project.GetId());
			DataTable result = TestModel.GetChildTableOfId(1, "id_project", "Task");
			Assert.IsTrue(result.Rows[0].ItemArray[0].Equals(task.GetId()) &&
			              result.Rows[0].ItemArray[2].Equals(project.GetId()) &&
						  result.Rows[0].ItemArray[1].Equals(task.Text),
						  "Getting data from child table is not working!");
		}

		[TestMethod()]
		public void AddTaskToProject()
		{
			Project project = new Project(1, "ProjectTest");
			Task task = new Task(2, "UnitTestTask", 1);		 
			int idTask = TestModel.AddTaskToProject(project.GetId(), task, "Task");
			DataTable result = TestModel.GetChildTableOfId(1, "id_project", "Task");
			Assert.IsTrue(result.Rows[1].ItemArray[0].Equals(idTask) &&
				result.Rows[1].ItemArray[1].Equals("UnitTestTask"),
				"Adding data in table is not working!");
			TestModel.DeleteTask(idTask, "Task");
		}

		[TestMethod()]
		public void UpdateTaskToProject()
		{
			Project project = new Project(1, "ProjectTest");
			Task task = new Task(2, "UnitTestTask", 1);
			int idTask = TestModel.AddTaskToProject(project.GetId(), task, "Task");
			Task update = new Task(idTask, "EditTask", 1);
			TestModel.UpdateTask(idTask, update, "Task");
			DataTable result = TestModel.GetChildTableOfId(1, "id_project", "Task");
			Assert.IsTrue(result.Rows[1].ItemArray[0].Equals(idTask) && 
							result.Rows[1].ItemArray[1].Equals("EditTask"),
							"Updating data in table is not working!");
			TestModel.DeleteTask(idTask, "Task");

		}

		[TestMethod()]
		public void DeleteTask()
		{
			Project project = new Project(1, "ProjectTest");
			Task task = new Task(2, "UnitTestTask", 1);
			int idTask = TestModel.AddTaskToProject(project.GetId(), task, "Task");	  
			TestModel.DeleteTask(idTask, "Task");									  
			DataTable result = TestModel.GetChildTableOfId(1, "id_project", "Task");
			Assert.IsTrue(result.Rows.Count.Equals(1),
			   "Delete is not working!");
		}

	}

}