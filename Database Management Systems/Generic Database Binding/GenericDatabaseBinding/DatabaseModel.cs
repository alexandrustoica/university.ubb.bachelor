using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Text.RegularExpressions;
using System.Windows.Forms;

namespace WindowsFormsApplication2
{
    public class DatabaseModel: Observable
    {
        public SqlConnection ModelConnection { get; }
        public SqlDataAdapter ModelDataAdaptor { get; }
        public DataSet ModelDataSet { get; }
        public BindingSource ModelBindingSource { get; }
        private static readonly string ServerName =
            ConfigurationManager.ConnectionStrings["serverName"].ConnectionString;
        private static readonly string DatabaseName = 
            ConfigurationManager.ConnectionStrings["databaseName"].ConnectionString;

        public DatabaseModel() : this(ServerName, DatabaseName) { }

        public DatabaseModel(string dataSource, string databaseName)
        {
            ModelConnection = new SqlConnection(
                    "Data Source =" + dataSource + "; " +
                    "Initial Catalog =" + databaseName + "; " +
                    "Integrated Security = True"
            );
            ModelDataAdaptor = new SqlDataAdapter();
            ModelDataSet = new DataSet();
            ModelBindingSource = new BindingSource();
        }

        public DataTable GetTable(DatabaseTable table)
        {   
            ModelDataAdaptor.SelectCommand = new SqlCommand(ConfigurationManager
                .AppSettings["SelectQuery"] + table.Name, ModelConnection);
            ModelDataSet.Clear();
            ModelDataAdaptor.Fill(ModelDataSet);
            ModelBindingSource.DataSource = ModelDataSet.Tables[0];
            return ModelDataSet.Tables[0];
        }

        public DataTable GetTableFromForeignKey(DatabaseTable table, int foreignKey)
        {
            ModelDataAdaptor.SelectCommand = new SqlCommand(ConfigurationManager
                .AppSettings["SelectQuery"] + table.Name + " where "
                + table.ForeignKey + " = " + foreignKey, ModelConnection);
            ModelDataSet.Clear();
            ModelDataAdaptor.Fill(ModelDataSet);
            ModelBindingSource.DataSource = ModelDataSet.Tables[0];
            return ModelDataSet.Tables[0];
        }

        private bool IsNumber(string data)
        {
            return new Regex(@"^\d$").IsMatch(data);
        }

        private string GetValue(string data)
        {
            return IsNumber(data) ? data : "'" + data + "'";
;        }

        public void InsertTable(DatabaseTable table, List<string> values)
        {
            ModelDataAdaptor.InsertCommand = new SqlCommand(ConfigurationManager
                .AppSettings["InsertQuery"], ModelConnection);
            var added = table.Parameters.Aggregate(" (", (accumulator, element) =>
                accumulator + GetValue(values[table.Parameters.IndexOf(element)]) + ",");
            added = added.Remove(added.Length - 1) + ")";
            ModelDataAdaptor.InsertCommand = new SqlCommand(ConfigurationManager
                .AppSettings["InsertQuery"].Replace("@TableName", table.Name) + added, ModelConnection);
            ModelConnection.Open();
            ModelDataAdaptor.InsertCommand.ExecuteNonQuery();
            ModelDataSet.Clear();
            ModelDataAdaptor.Fill(ModelDataSet, table.Name);
            ModelConnection.Close();
            NotifyObservers(NotificationType.NotificationAdd);
        }

        public void UpdateTable(DatabaseTable table, List<string> values, int primaryKey)
        {
            var rest = table.Columns.Aggregate(" ", (accumulator, element) =>
                accumulator + (element + " = " + GetValue(values[table.Columns.IndexOf(element)]) + ","));
            rest = rest.Remove(rest.Length - 1) + " where " + table.PrimaryKey + " = " + primaryKey; 
            ModelDataAdaptor.UpdateCommand =
                new SqlCommand(ConfigurationManager
                .AppSettings["UpdateQuery"].Replace("@TableName", table.Name) + rest, ModelConnection);
            ModelConnection.Open();
            ModelDataAdaptor.UpdateCommand.ExecuteNonQuery();
            ModelDataSet.Clear();
            ModelDataAdaptor.Fill(ModelDataSet, table.Name);
            ModelConnection.Close();
            NotifyObservers(NotificationType.NotificationUpdate);
        }

        public void DeleteTable(DatabaseTable table, int primaryKey)
        {
            ModelDataAdaptor.DeleteCommand = new SqlCommand(ConfigurationManager
                .AppSettings["DeleteQuery"]
                .Replace("@TableName", table.Name)
                .Replace("@PK", table.PrimaryKey)
                .Replace("@Value", primaryKey.ToString()), ModelConnection);
            ModelConnection.Open();
            ModelDataAdaptor.DeleteCommand.ExecuteNonQuery();
            ModelDataSet.Clear();
            ModelDataAdaptor.Fill(ModelDataSet, table.Name);
            ModelConnection.Close();
            NotifyObservers(NotificationType.NotificationDelete);
        }

    }
}