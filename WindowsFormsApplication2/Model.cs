using System;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Windows.Forms;

namespace WindowsFormsApplication2 {

	
	public class Model : Observable {

		public SqlConnection ModelConnection { get; }
		public SqlDataAdapter ModelDataAdaptor { get; }
		public DataSet ModelDataSet { get; }
	   	public BindingSource ModelBindingSource { get; }

		private readonly string DatabaseDataSource;
		private readonly string DatabaseName;

	    private static readonly string _serverName = ConfigurationManager.ConnectionStrings["serverName"].ConnectionString;
	    private static readonly string _databaseName = ConfigurationManager.ConnectionStrings["databaseName"].ConnectionString;
        
        public Model() : this(_serverName, databaseName: _databaseName) { }

		public Model(string dataSource, string databaseName)
		{
			DatabaseDataSource = dataSource;
			DatabaseName = databaseName;
			try {
				ModelConnection = new SqlConnection(
				   "Data Source =" + DatabaseDataSource + "; " +
				   "Initial Catalog =" + DatabaseName + "; " +
				   "Integrated Security = True"
				);
				ModelDataAdaptor = new SqlDataAdapter();
				ModelDataSet = new DataSet();
				ModelBindingSource = new BindingSource();
			} catch (SqlException error) {
				Console.Write(error.Message);
			}
		}
															
		public DataTable GetTable(string tableName)
		{																		
			ModelDataAdaptor.SelectCommand = new SqlCommand("select * from " + tableName, ModelConnection);
			ModelDataSet.Clear();
			ModelDataAdaptor.Fill(ModelDataSet);
			ModelBindingSource.DataSource = ModelDataSet.Tables[0];
			return ModelDataSet.Tables[0];
		}

	    public DataTable _GetTable(DatabaseTable table)
	    {
            ModelDataAdaptor.SelectCommand = new SqlCommand(ConfigurationManager.AppSettings["SelectQuery"] + table.Name, ModelConnection);
	        ModelDataSet.Clear();
	        ModelDataAdaptor.Fill(ModelDataSet);
	        ModelBindingSource.DataSource = ModelDataSet.Tables[0];
            return ModelDataSet.Tables[0];
	    }

		public DataTable GetChildTableOfId(int idFk, string nameFk, string childTableName)
		{
			ModelDataAdaptor.SelectCommand = new SqlCommand("select * from " +
				childTableName + " where " + nameFk + "=" + idFk, ModelConnection);
			ModelDataSet.Clear();
			ModelDataAdaptor.Fill(ModelDataSet);
			ModelBindingSource.DataSource = ModelDataSet.Tables[0];
			return ModelDataSet.Tables[0];
		}

//	    public DataTable GetChildWithId(DatabaseTable child, int id)
//	    {
//	        ModelDataAdaptor.SelectCommand = new SqlCommand("select * from " +
//	                                                        childTableName + " where " + nameFk + "=" + idFk, ModelConnection);
//
//	        ModelDataAdaptor.SelectCommand = new SqlCommand("", ModelConnection);
//	        ModelDataAdaptor.Selectommand.Parameters.Add("@id", SqlDbType.VarChar).Value = task.Text;
//            ModelDataSet.Clear();
//	        ModelDataAdaptor.Fill(ModelDataSet);
//	        ModelBindingSource.DataSource = ModelDataSet.Tables[0];
//	        return ModelDataSet.Tables[0];
//        }

		public int AddTaskToProject(int idProject, Task task, string tableName)
		{		
			ModelDataAdaptor.InsertCommand = new SqlCommand(" insert into " + tableName + " values (@text, @id_project)", ModelConnection);
			ModelDataAdaptor.InsertCommand.Parameters.Add("@text", SqlDbType.VarChar).Value = task.Text;
			ModelDataAdaptor.InsertCommand.Parameters.Add("@id_project", SqlDbType.Int).Value = idProject;
			var id = 0;
			var selectCommand = new SqlCommand("select @@IDENTITY", ModelConnection);
			ModelConnection.Open();
			ModelDataAdaptor.InsertCommand.ExecuteNonQuery();
			id = Convert.ToInt32(selectCommand.ExecuteScalar());
			ModelDataSet.Clear();
			ModelDataAdaptor.Fill(ModelDataSet, "Task");
			ModelConnection.Close();
			NotifyObservers(NotificationType.NotificationAdd);											 
			return id;   
		}

		public void UpdateTask(int idTask, Task task, string tableName)
		{
			ModelDataAdaptor.UpdateCommand =
				new SqlCommand("update " + tableName + " set text = @text, id_project = @id_project where id = @id_task", ModelConnection);
			ModelDataAdaptor.UpdateCommand.Parameters.Add("@text", SqlDbType.VarChar).Value = task.Text;
			ModelDataAdaptor.UpdateCommand.Parameters.Add("@id_project", SqlDbType.Int).Value = task.IdProject;
			ModelDataAdaptor.UpdateCommand.Parameters.Add("@id_task", SqlDbType.Int).Value = idTask;
			ModelConnection.Open();
			ModelDataAdaptor.UpdateCommand.ExecuteNonQuery();
			ModelDataSet.Clear();
			ModelDataAdaptor.Fill(ModelDataSet, "Task");
			ModelConnection.Close();
			NotifyObservers(NotificationType.NotificationUpdate);
		}

		public void DeleteTask(int idTask, string tableName)
		{	   
			ModelDataAdaptor.DeleteCommand = new SqlCommand("delete from " + tableName + " where id = @idTask", ModelConnection);
			ModelDataAdaptor.DeleteCommand.Parameters.Add("@idTask", SqlDbType.Int).Value = idTask;
			ModelDataAdaptor.DeleteCommand.Parameters.Add("@table", SqlDbType.VarChar).Value = tableName;
			ModelConnection.Open();
			ModelDataAdaptor.DeleteCommand.ExecuteNonQuery();
			ModelDataSet.Clear();
			ModelDataAdaptor.Fill(ModelDataSet, "Task");	   
			ModelConnection.Close();							   
			NotifyObservers(NotificationType.NotificationDelete);
		}

	}
}
